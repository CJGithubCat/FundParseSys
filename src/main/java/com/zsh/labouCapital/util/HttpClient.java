package com.zsh.labouCapital.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClient {
	
	protected static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 函数名:post
	 * 函数功能：Post请求http
	 * @param path
	 * @param params
	 * @param ciper 是否加密
	 * @return 请求相应结果
	 * @throws Exception
	 */
	public static String post( String path, Map<String, Object> params,boolean ciper) throws Exception {
		URL url = new URL(path);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		conn.setConnectTimeout(100000);// 连接超时 单位毫秒
		conn.setReadTimeout(100000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		conn.connect();
		
		System.out.println("提交的参数：" + mapper.writeValueAsString(params));
		byte[] bytes = null;
		if(ciper){
			bytes = CipherTool.getCipherString(mapper.writeValueAsString(params)).getBytes("UTF-8");

			/*if(path.indexOf(SystemConst.HTTP_RIS_SET_PASSWORD)!=-1){//修改密码不加密
				bytes = mapper.writeValueAsString(params).getBytes("UTF-8");
			}*/
			
		}
		else{
			bytes = mapper.writeValueAsString(params).getBytes("UTF-8");
		}
		conn.getOutputStream().write(bytes);// 输入参数
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		
		InputStream in = conn.getInputStream();
		String returnValue = "";
		byte[] buffer = new byte[512];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int len = 0; (len = in.read(buffer)) > 0;) {
			baos.write(buffer, 0, len);
		}
		
		if(ciper && path.indexOf("ris")==-1){//获取用户模块数据的时候接口返回不加密
			returnValue =CipherTool.getOriginString(new String(baos.toByteArray(), "UTF-8")) ;
		}
		else
			returnValue = new String(baos.toByteArray(), "UTF-8");
		baos.flush();
		baos.close();
		in.close();
		System.out.println("服务端返回的内容：" + returnValue);
		
		conn.disconnect();
		return returnValue;
	}
	
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 响应结果
	 * @throws Exception 
     */
    public static String sendGet(String path, String params) throws Exception {
    	
		
        String result = "";
        InputStream in = null;
        try {
            String urlNameString =  path + "?" + params;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
           
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            conn.connect();
            
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            
            
            // 读取URL的响应          
            
            in = conn.getInputStream();
    		String returnValue = "";
    		byte[] buffer = new byte[512];
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		for (int len = 0; (len = in.read(buffer)) > 0;) {
    			baos.write(buffer, 0, len);
    		}
    		returnValue = new String(baos.toByteArray(), "UTF-8");
    		baos.flush();
    		baos.close();
    		in.close();
    		System.out.println("服务端返回的内容：" + returnValue);
            
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String post( String path, Object obj,boolean ciper) throws Exception {
		URL url = new URL(path);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		//conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setConnectTimeout(10000);// 连接超时 单位毫秒
		conn.setReadTimeout(10000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		conn.connect();
		
		
		System.out.println("提交的参数：" + mapper.writeValueAsString(obj));
		byte[] bytes = null;
		if(ciper){
			bytes = CipherTool.getCipherString(mapper.writeValueAsString(obj)).getBytes("UTF-8");
		}
		else{
			bytes = mapper.writeValueAsString(obj).getBytes("UTF-8");
		}
		conn.getOutputStream().write(bytes);// 输入参数
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		
		InputStream in = conn.getInputStream();
		String returnValue = "";
		byte[] buffer = new byte[512];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int len = 0; (len = in.read(buffer)) > 0;) {
			baos.write(buffer, 0, len);
		}
		returnValue = new String(baos.toByteArray(), "UTF-8");
		if(ciper && path.indexOf("ris")==-1){
			returnValue = CipherTool.getOriginString(returnValue);
		}
		
		baos.flush();
		baos.close();
		in.close();
		System.out.println("服务端返回的内容：" + returnValue);
		
		conn.disconnect();
		return returnValue;
	}
	
}
