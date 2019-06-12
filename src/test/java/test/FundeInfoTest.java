package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.util.DateUtil;
import com.zsh.labouCapital.util.HttpclientUtil;
import com.zsh.labouCapital.util.JDBCUtil;

/**
 *<p> Title: FundeInfoTest </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年6月8日
 */
public class FundeInfoTest {
    private static Logger logger = Logger.getLogger(FundeInfoTest.class);
    
    /**
     * @Title: countAvgFundInfo   
     * @Description: 计算均线   
     * @param:       
     * @return: void      
     * @throws
     */
    @Test
    public void countAvgFundInfo(){
        //1.查询基金净值信息；
        String netWorthSql = "SELECT * FROM t_net_worth_history t WHERE t.fund_code = '161725' ORDER BY t.date_info ASC";
        try {
            PreparedStatement pstm = JDBCUtil.getPrepareStatement(netWorthSql);
            ResultSet rs = pstm.executeQuery();
            List<NetWorthHistory> netWorthList = new ArrayList<NetWorthHistory>();
            while(rs.next()){
                NetWorthHistory netWorthHistory = new NetWorthHistory();
                long id = rs.getInt("id");
                String fundCode = rs.getString("fund_code");
                double netWorth = rs.getDouble("net_worth");
                java.sql.Date dateInfo = rs.getDate("date_info");
                netWorthHistory.setId(id+"");
                netWorthHistory.setNetWorth(netWorth);
                try {
                    netWorthHistory.setDateInfoStr(DateUtil.getDateString(dateInfo, DateUtil.DATE_TIME_PATTERN));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                netWorthHistory.setDateInfo(new Date(dateInfo.getTime()));
                netWorthHistory.setFundCode(fundCode);
                netWorthList.add(netWorthHistory);
            }
            logger.error("$$$:" + JSONObject.toJSONString(netWorthList));
            try {
                int dayNum = 240;
                List<NetWorthHistory> netWorthTempList = countAvgLine(netWorthList,dayNum);
                updateAvgLineData(netWorthTempList,dayNum);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @throws SQLException 
     * @Title: countAvgLine   
     * @Description: TODO   
     * @param:       
     * @return: void      
     * @throws
     */
    public List<NetWorthHistory> countAvgLine(List<NetWorthHistory> netWorthList,int day) throws SQLException{
      //2.计算均线；
        String avg30Sql = "SELECT AVG(tt.net_worth) as  avgLine from (SELECT t.net_worth FROM t_net_worth_history t WHERE t.fund_code = '161725' AND t.date_info < ? ORDER BY t.date_info DESC LIMIT 0,?)tt";
        PreparedStatement pstm30 = JDBCUtil.getPrepareStatement(avg30Sql);
        List<NetWorthHistory> netWorthListTemp = new ArrayList<NetWorthHistory>();
        for (NetWorthHistory netWorthHistory : netWorthList) {
            pstm30.setString(1, netWorthHistory.getDateInfoStr());
            pstm30.setInt(2, day);
            ResultSet rs1 = pstm30.executeQuery();
            while(rs1.next()){
                logger.error("Date:" + netWorthHistory.getDateInfoStr()+"   " + day+"日均线：" + rs1.getDouble("avgLine"));
                if(rs1.getDouble("avgLine") > 0){
                    if(day == 30){
                        netWorthHistory.setAvgLine30(rs1.getDouble("avgLine"));
                    }else if (day == 60) {
                        netWorthHistory.setAvgLine60(rs1.getDouble("avgLine"));
                    }else if (day == 90) {
                        netWorthHistory.setAvgLine90(rs1.getDouble("avgLine"));
                    }else if(day == 120){
                        netWorthHistory.setAvgLine120(rs1.getDouble("avgLine"));
                    }else if (day == 240) {
                        netWorthHistory.setAvgLine240(rs1.getDouble("avgLine"));
                    }
                    netWorthListTemp.add(netWorthHistory);
                }
            }
        }
        return netWorthListTemp;
    }
    
    public void updateAvgLineData(List<NetWorthHistory> netWorthListTemp,int day) throws Exception{
      //3.更新均线字段;
        logger.error(JSONObject.toJSON(netWorthListTemp));
        String avg30SqlUpdate = "UPDATE t_net_worth_history SET avg_line"+day+" = ? WHERE id = ?";
        PreparedStatement pstm30Update = JDBCUtil.getPrepareStatement(avg30SqlUpdate);
        for (NetWorthHistory netWorthHistory : netWorthListTemp) {
            if(day == 30){
                pstm30Update.setDouble(1, netWorthHistory.getAvgLine30());
            }else if (day == 60) {
                pstm30Update.setDouble(1, netWorthHistory.getAvgLine60());
            }else if (day == 90) {
                pstm30Update.setDouble(1, netWorthHistory.getAvgLine90());
            }else if(day == 120){
                pstm30Update.setDouble(1, netWorthHistory.getAvgLine120());
            }else if (day == 240) {
                pstm30Update.setDouble(1, netWorthHistory.getAvgLine240());
            }
            pstm30Update.setString(2, netWorthHistory.getId());
            pstm30Update.addBatch();
        }
        int reAr[] = pstm30Update.executeBatch();
        logger.error(JSONObject.toJSON(reAr));
    }
}
