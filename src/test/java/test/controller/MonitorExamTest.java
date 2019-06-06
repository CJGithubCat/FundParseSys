package test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.comm.FundNetWorthConstant;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.util.HttpClient;
import com.zsh.labouCapital.util.HttpUtil;
import com.zsh.labouCapital.util.HttpclientUtil;

import jxl.biff.StringHelper;
import net.sf.json.JSONObject;

/**
 *<p> Title: MonitorExamTest </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年3月6日
 */
public class MonitorExamTest {

    public static  List<NetWorthHistory> monitExamInfo(String url,String cityId){
        //1.请求数据
        List<NameValuePair> params = new ArrayList<>();
        List<NetWorthHistory> reList = new ArrayList<>();
        try {
            params.add(new BasicNameValuePair("cityId",cityId));
            String reBody = HttpUtil.post(url, params);
            //String reBody = HttpClient.post(url, params, false);
            System.out.println("AAAAA:" + reBody);
            //2.解析js数据
            /*ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            engine.eval(reBody);
            Object array = engine.get(FundNetWorthConstant.DATA_NETWORTH_Trend);
            JSONObject tempObj = JSONObject.fromObject(array);
            int len = tempObj.size();
            if(beforeDay >= len){
                beforeDay = len;
            }*/
            
            System.out.println("AAAAAA:"+reList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reList;
    }
    
    @Test
    public void monitExam(){
        String url = "http://pscsxbm.cltt.org/Web/SignUpOnLine/OnlineSign.aspx/GetTestSite";
        String cityId = "005";
        com.alibaba.fastjson.JSONObject paramJson = new com.alibaba.fastjson.JSONObject();
        paramJson.put("cityId", "005");
        HttpclientUtil.postRequest(url,paramJson.toJSONString());
        
    }
}
