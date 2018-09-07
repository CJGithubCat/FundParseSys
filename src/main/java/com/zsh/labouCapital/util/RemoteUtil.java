package com.zsh.labouCapital.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteUtil {
	public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    
   /**
    * 
    * @param address
    * @param method
    * @param contentType
    * @param data
    * @return
    */
    public static String request(
         String address, 
         String method, 
         String contentType,
         String data
    ){
        String responseText = "";
        HttpURLConnection conn = null;
        try{
            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10*1000);
            conn.setReadTimeout(30*1000);
            conn.setDoOutput(true);
            if(data != null)
            {
            	 conn.setDoInput(true);
            
            }
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("User-Agent", "");
            if(data != null)
            {
            conn.setRequestProperty("Content-Length", data.getBytes().length + "");
            }
            conn.setUseCaches(false);
            OutputStreamWriter wr = null;
            if(data != null)
            {
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            }
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream (), "utf-8"));
            String currentLine = "";
            StringBuffer sb = new StringBuffer();
            while((currentLine = rd.readLine()) != null)
            {
                sb.append(currentLine);
                sb.append("\n");
            } 
            responseText = sb.toString();
            if(data != null)
            {
            	wr.close();
            }
            rd.close();
            
        } catch (Exception ex){
        	if(conn != null)
        	{
        		conn.disconnect();
        	}
            ex.printStackTrace();
        } 
        return responseText;
    }
    
    /**
     * 发起 post 消息，并解析 JSON
     * @param address 远程地址
     * @param typeOfG 解析结果的类�?
     * @param request 要发送的数据
     * @return
     */
    public static <G> G postJson(String address, Class<G> typeOfG, Object request){
        String postData = JsonUtil.toJson(request);
        String responseText = "";

        responseText = request(address, METHOD_POST, "application/json", postData);
        
        G result = (G) JsonUtil.fromJson(typeOfG, responseText);
        return result;
    }
}
