//package com.zsh.labouCapital.spider;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import com.zsh.labouCapital.comm.FundNetWorthConstant;
//import com.zsh.labouCapital.entity.FundSummary;
//import com.zsh.labouCapital.entity.NetWorthHistory;
//import com.zsh.labouCapital.service.IFundSummaryService;
//import com.zsh.labouCapital.service.INetWorthHistoryService;
//import com.zsh.labouCapital.util.DateUtil;
//import com.zsh.labouCapital.util.HttpclientUtil;
//
//import net.sf.json.JSONObject;
//
//@Component
//public class FundNetWorthSpider {
//	@Autowired
//	private IFundSummaryService fundSummaryService;
//	
//	@Autowired
//	private INetWorthHistoryService netWorthHistoryService;
//	
//	/**
//	 * @Title: parseJsNetWorth   
//	 * @Description: 解析js中的净值数据转为实体
//	 * @param: @param url
//	 * @param: @param fundCode
//	 * @param: @param beforeDay
//	 * @param: @return      
//	 * @return: List<NetWorthHistory>      
//	 * @throws
//	 */
//	public static  List<NetWorthHistory> parseJsNetWorth(String url,String fundCode,int beforeDay){
//		//1.请求数据
//		List<NameValuePair> params = new ArrayList<>();
//		List<NetWorthHistory> reList = new ArrayList<>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			String timStamp = sdf.format(new Date());
//			params.add(new BasicNameValuePair("v",timStamp));
//	        String reBody = HttpclientUtil.get(url, params);
//	        //2.解析js数据
//	        ScriptEngineManager manager = new ScriptEngineManager();
//	        ScriptEngine engine = manager.getEngineByName("javascript");
//	        engine.eval(reBody);
//	        Object array = engine.get(FundNetWorthConstant.DATA_NETWORTH_Trend);
//	        JSONObject tempObj = JSONObject.fromObject(array);
//	        int len = tempObj.size();
//	        if(beforeDay >= len){
//	        	beforeDay = len;
//	        }
//	        
//	        for (int i = len - beforeDay; i < len; i++) {
//	        	NetWorthHistory temValue = new NetWorthHistory();
//	        	JSONObject vaJsonObject = (JSONObject) tempObj.get(""+i);
//	        	Long milSecod = vaJsonObject.getLong("x");
//	        	Double value = vaJsonObject.getDouble("y");
//	        	String equityReturn = vaJsonObject.getString("equityReturn");
//	        	String unitMoney = vaJsonObject.getString("unitMoney");
//	        	int week = getWeekInfo(milSecod);
//	        	
//	        	String dateInfo = sdf1.format(new Date(milSecod));
//	        	temValue.setFundCode(fundCode);
//	        	temValue.setDateInfo(DateUtil.parse(dateInfo, DateUtil.YMD));
//	        	temValue.setNetWorth(value);
//	        	if(!StringUtils.isEmpty(equityReturn)){
//	        		temValue.setEquityReturn(Double.parseDouble(equityReturn));
//	        	}
//	        	temValue.setUnitMoney(unitMoney);
//	        	temValue.setWeekInfo(week);
//	        	reList.add(temValue);
//			}
//	        System.out.println("AAAAAA:"+reList.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        return reList;
//	}
//	
//	/**
//	 * 判断是星期几
//	 * 入参：毫秒数
//	 * 1~7:星期几；1~7代表星期日到星期六
//	 */
//	public static int getWeekInfo(Long datel){
//		int week = Integer.MIN_VALUE;
//		try {
//			Calendar calendar = Calendar.getInstance();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//			calendar.setTime(new Date(datel));
//			int i =calendar.get(Calendar.DAY_OF_WEEK);
//			
//			if(i == 1){
//				week = 7;
//				System.out.println("今天是星期日");
//			}else{
//				week = i -1;
//				System.out.println("今天是星期"+(i-1));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return week;
//	}
//	
//	/**
//	 * @Title: updateHistoryNetWorth   
//	 * @Description: 爬取并更新前n天的净值数据，并更新到数据库
//	 * @param: @param beforeDay      
//	 * @return: void      
//	 * @throws
//	 */
//	public void updateHistoryNetWorth(String fundCode,int beforeDay){
//		try {
//			if(StringUtils.isEmpty(fundCode)){//更新全部的数据
//				List<FundSummary> fundList = fundSummaryService.queryAllFundSummary();
//				for (int i=0; i < fundList.size();i++) {
//					FundSummary fundSummary = fundList.get(i);
//					List<NetWorthHistory> revelus = parseJsNetWorth(fundSummary.getHistoryUrl(),fundSummary.getFundCode(),beforeDay);
//					//将历时数据插入或者更新到数据库
//					netWorthHistoryService.updateOrinsertNetWorth(revelus);
//				}
//			}else{
//				FundSummary tempFundInfo = fundSummaryService.getFundSummaryByCode(fundCode);
//				if(null != tempFundInfo){
//					List<NetWorthHistory> revelus = parseJsNetWorth(tempFundInfo.getHistoryUrl(),fundCode,beforeDay);
//					//将历时数据插入或者更新到数据库
//					netWorthHistoryService.updateOrinsertNetWorth(revelus);
//					System.out.println("更新所有数据完成***********");
//				}else{
//					System.out.println("FundCode数据为空，更新失败...");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		List<NetWorthHistory> reList = FundNetWorthSpider.parseJsNetWorth("http://fund.eastmoney.com/pingzhongdata/530010.js", "530010",1);
//		System.out.println(com.alibaba.fastjson.JSONObject.toJSON(reList));
//	}
//}
