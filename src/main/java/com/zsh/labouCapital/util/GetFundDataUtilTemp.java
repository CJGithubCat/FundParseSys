package com.zsh.labouCapital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mysql.jdbc.PreparedStatement;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.service.INetWorthHistoryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetFundDataUtilTemp {

	private static String CONNSTR = "jdbc:mysql://127.0.0.1:3306/fund?"
			+ "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://127.0.0.1:3306/fund";
	private static String NAME = "root";
	private static String PASSWORD = "root";

	/***
	 * 获取连接
	 */
	private static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);
			System.out.println("获取连接成功...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 查询
	 */
	private static List<NetWorthHistory> getAll(String sql) {
		Connection conn = getConn();
		PreparedStatement pstmt;
		List<NetWorthHistory> reList = new ArrayList<>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println(col);
			while (rs.next()) {
				NetWorthHistory netWorth = new NetWorthHistory();
				netWorth.setFundCode(rs.getString("fundCode"));
				netWorth.setHistoryUrl(rs.getString("historyUrl"));
				reList.add(netWorth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reList;
	}

	/**
	 * 插入数据
	 * 
	 * @return
	 */
	private static int insert(List<NetWorthHistory> netWorths) {
		Connection conn = getConn();
		String sql = "INSERT INTO t_net_worth_history "
				+ " (fund_code,date_info,net_worth,equity_return,unit_money,week_info,date_create) "
				+ " VALUES(?,?,?,?,?,?,now())";
		PreparedStatement pstmt = null;
		int[] counts = {};
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (int j = 0; j < netWorths.size(); j++) {
				NetWorthHistory netWorthTemp = netWorths.get(j);
				pstmt.setString(1, netWorthTemp.getFundCode());
				pstmt.setString(2, DateTimeUtil.formatDate(netWorthTemp.getDateInfo(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2));
				pstmt.setDouble(3, netWorthTemp.getNetWorth());
				pstmt.setDouble(4, netWorthTemp.getEquityReturn());
				pstmt.setString(5, netWorthTemp.getUnitMoney());
				pstmt.setDouble(6, netWorthTemp.getWeekInfo());
				pstmt.addBatch();
			}
			counts = pstmt.executeBatch();
            conn.commit();
            pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(conn != null && !conn.isClosed()){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return counts.length;
	}
	
	/**
     * 更新累积净值
     * 
     * @return
     */
    private static int updateAddUpWorth(List<NetWorthHistory> netWorths) {
        Connection conn = getConn();
        String sql = "UPDATE t_net_worth_history SET addup_worth = ?  WHERE fund_code = ?  AND date_info = ? ";
        PreparedStatement pstmt = null;
        int counts =0;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (int j = 0; j < netWorths.size(); j++) {
                NetWorthHistory netWorthTemp = netWorths.get(j);
                pstmt.setDouble(1, netWorthTemp.getAddUpWorth());
                pstmt.setString(2, netWorthTemp.getFundCode());
                pstmt.setString(3, DateTimeUtil.formatDate(netWorthTemp.getDateInfo(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2));
                
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return counts;
    }
	
	/**
	 * 解析js中的历史净值数据
	 * "http://fund.eastmoney.com/pingzhongdata/530010.js"
	 */
	public static  List<NetWorthHistory> parseJsHistoryNetWorth(String url,String fundCode){
		List<NameValuePair> params = new ArrayList<>();
		List<NetWorthHistory> reList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			params.add(new BasicNameValuePair("v","20180907161408"));
	        String reBody = HttpclientUtil.get(url, params);
	        ScriptEngineManager manager = new ScriptEngineManager();
	        ScriptEngine engine = manager.getEngineByName("javascript");
	        engine.eval(reBody);
	        Object array = engine.get("Data_netWorthTrend");
	        JSONObject tempObj = JSONObject.fromObject(array);
	        int len = tempObj.size();
	        for (int i = 0; i < len; i++) {
	        	NetWorthHistory temValue = new NetWorthHistory();
	        	JSONObject vaJsonObject = (JSONObject) tempObj.get(""+i);
	        	Long milSecod = vaJsonObject.getLong("x");
	        	Double value = vaJsonObject.getDouble("y");
	        	String equityReturn = vaJsonObject.getString("equityReturn");
	        	String unitMoney = vaJsonObject.getString("unitMoney");
	        	int week = getWeekInfo(milSecod);
	        	
	        	temValue.setFundCode(fundCode);
	        	temValue.setDateInfo(new Date(milSecod));

	        	temValue.setNetWorth(value);
	        	if(!StringUtils.isEmpty(equityReturn)){
	        		temValue.setEquityReturn(Double.parseDouble(equityReturn));
	        	}
	        	temValue.setUnitMoney(unitMoney);
	        	temValue.setWeekInfo(week);
	        	reList.add(temValue);
			}
	        System.out.println("AAAAAA:"+reList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return reList;
	}
	
	/**
	 * 判断是星期几
	 * 入参：毫秒数
	 * 1~7:星期几；1~7代表星期日到星期六
	 */
	public static int getWeekInfo(Long datel){
		int week = Integer.MIN_VALUE;
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			calendar.setTime(new Date(datel));
			int i =calendar.get(Calendar.DAY_OF_WEEK);
			week = i;
			if(i == 1){
				System.out.println("今天是星期日");
			}else{
				System.out.println("今天是星期"+(i-1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return week;
	}
	
	/**
     * 解析js中的历史累计净值数据
     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
     */
    public static  List<NetWorthHistory> parseJsHistoryAddWorth(String url,String fundCode){
        List<NameValuePair> params = new ArrayList<>();
        List<NetWorthHistory> reList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            params.add(new BasicNameValuePair("v","20180921161408"));
            String reBody = HttpclientUtil.get("http://fund.eastmoney.com/pingzhongdata/530010.js", params);
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            engine.eval(reBody);
            Object array = engine.get("Data_ACWorthTrend");
            JSONArray tempObj = JSONArray.fromObject(array);
            int len = tempObj.size();
            for (int i = 0; i < len; i++) {
                NetWorthHistory temValue = new NetWorthHistory();
                JSONArray vaJsonArrary = (JSONArray) tempObj.get(i);
                
                Long dateTime =  vaJsonArrary.getLong(0);
                Double addValue = (double) vaJsonArrary.getDouble(1);
                
                Date date = new Date(dateTime);

                temValue.setFundCode(fundCode);
                temValue.setDateInfo(date);
                temValue.setAddUpWorth(addValue);
                reList.add(temValue);
                System.out.println(temValue);
            }
            System.out.println("AAAAAA:"+reList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reList;
    }
	
	/**
	 * 处理基金的历史数据
	 */
	public static void proceeHistoryData() {
		//String sql = "SELECT t.`fund_code` AS fundCode,t.`history_url` AS historyUrl FROM t_fund_summary t";
		String sql = "SELECT t.fund_code AS fundCode,t.history_url AS historyUrl FROM t_fund_summary t WHERE t.fund_code";
		try {
			List<NetWorthHistory> reList = getAll(sql);
			for (int i=0; i < reList.size();i++) {
			//for (int i=0; i < 1;i++) {
				NetWorthHistory netWorthHistory = reList.get(i);
				List<NetWorthHistory> revelus = parseJsHistoryAddWorth(netWorthHistory.getHistoryUrl(),netWorthHistory.getFundCode());
				//insert(revelus);
				//updateAddUpWorth(revelus);
				//netWorthHistoryService.updateNetWorthHistorys(revelus);
			}
			System.out.println("************处理完成***********");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		proceeHistoryData();
		//System.out.println(getWeekInfo(1536429963000L));
	}
}
