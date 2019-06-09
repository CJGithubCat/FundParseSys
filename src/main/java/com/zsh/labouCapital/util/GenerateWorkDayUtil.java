package com.zsh.labouCapital.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.comm.DayTypeEnum;

/**
 *<p> Title: GenerateWorkDayUtil </p>
 *<p> Description:生成对应年份的工作日和节假日信息 </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年6月6日
 */
public class GenerateWorkDayUtil {
    private static Logger log = Logger.getLogger(GenerateWorkDayUtil.class);

    public static String checkUrl = "http://api.goseek.cn/Tools/holiday";
    
    
    public static int isWorkDay(String dateStr) throws ParseException, IOException, URISyntaxException{
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("date", dateStr));
        String reJson = HttpclientUtil.get(checkUrl, paramList);
        JSONObject json =  (JSONObject) JSONObject.parse(reJson);
        if(json.containsKey("data")){
            if(DayTypeEnum.DAY_WORK_DAY.getCode().equals(json.get("data"))){
                log.error("结果:工作日");
                return 0;
            }
            if(DayTypeEnum.DAY_FESTIVAL_DAY.getCode().equals(json.get("data"))){
                log.error("结果:法定节假日");
                return 1;
            }
            if(DayTypeEnum.DAY_ADJUST_DAY.getCode().equals(json.get("data"))){
                log.error("结果:节假日调休补班");
                return 2;
            }
            if(DayTypeEnum.DAY_REST_DAY.getCode().equals(json.get("data"))){
                log.error("结果:休息日");
                return 3;
            }
        }
        return -1;
    }
    
    /**
     * @Title: generateCalender   
     * @Description: TODO   
     * @param: @return      
     * @return: List<String>      
     * @throws
     */
    public static List<String> generateCalender(int year,int month){
        if(year <= 1700 || year > 3000 || month < 0 || month > 12){
            log.error("参数错误!");
        }
        int realMonth = month - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, realMonth);        
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        log.error("Month:" + month + " .Max Day:" + maxDay);
        List<String> calenderList = new ArrayList<String>();
        for(int i=1;i <= maxDay; i++){
            calendar.set(Calendar.DAY_OF_MONTH, i);
            String dayStr = DateUtil.format(calendar.getTime(), DateUtil.YMD);
            calenderList.add(dayStr);
        }
        log.error("List:" + JSON.toJSONString(calenderList));
        return calenderList;
    }
    
    
    public static void main(String[] args) {
        try {
//            GenerateWorkDayUtil.isWorkDay(new Date());
           
            List<String> carList = generateCalender(2019,2);
            for (String dateStr : carList) {
                GenerateWorkDayUtil.isWorkDay(dateStr);
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
