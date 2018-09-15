package com.zsh.labouCapital.util.fund;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.entity.FundRealTimeNetWorthDTO;
import com.zsh.labouCapital.entity.FundSummary;

/**
 *<p> Title: FundRealTimeUtil </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2018 </p>
 *
 * @author HP
 * @version
 * @since 2018年9月14日
 */
public class FundRealTimeUtil {
    
    /**
     * @Title: getFundRealTimeNetWorth   
     * @Description:    
     * @param:       
     * @return: void      
     * @throws
     */
    public static FundRealTimeNetWorthDTO getFundRealTimeNetWorth(FundSummary fundSummary){
        FundRealTimeNetWorthDTO fundRealWorth = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            //1.请求页面信息；
            if(StringUtils.isEmpty(fundSummary.getDetailUrl())){
                System.out.println("url为空，获取最新数据失败！");
                return fundRealWorth;
            }
            //2.jsoup解析页面数据;
            Document doc = Jsoup.connect(fundSummary.getDetailUrl()).get();
            //3.获取当前时间
            Element gzGztimeEle = doc.getElementById("gz_gztime");
            String gzGztimeStr = "20" +gzGztimeEle.text().replace("(", "").replace(")", "") + ":00";
            
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            
            Date eleDate = sdf.parse(gzGztimeStr);
            long eleSecond = (long) (eleDate.getTime() / 1000);
            long checkSecond = (long)(calendar.getTime().getTime()/1000);
            //4.获取当前的净值
            Element gzGszEle = doc.getElementById("gz_gsz");
            String gzNetWorth = gzGszEle.text();
            double netWorth = Double.parseDouble(gzNetWorth);
            
            fundRealWorth = new FundRealTimeNetWorthDTO();
            fundRealWorth.setFundCode(fundSummary.getFundCode());
            fundRealWorth.setGzGztime(eleSecond);
            fundRealWorth.setCheckTie(checkSecond);
            fundRealWorth.setNowNetWorth(netWorth);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return fundRealWorth;
    }
    
    public static void main(String[] args) {
        FundSummary fundSummary = new FundSummary();
        fundSummary.setDetailUrl("http://fund.eastmoney.com/001630.html");
        System.out.println(FundRealTimeUtil.getFundRealTimeNetWorth(fundSummary));
    }
}
