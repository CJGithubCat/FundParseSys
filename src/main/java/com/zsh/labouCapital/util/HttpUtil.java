package com.zsh.labouCapital.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.*;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);

	public static String get(String url, List<NameValuePair> params) throws ParseException, IOException,
			URISyntaxException {
		CloseableHttpClient client = HttpClients.createMinimal();
		String body = null;
		HttpGet get = new HttpGet(url);
		// 设置参数
		if(params != null){
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
			get.setURI(new URI(get.getURI().toString() + "?" + str));
		}
		// 发送请求
		HttpResponse httpresponse = client.execute(get);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		body = EntityUtils.toString(entity, Consts.UTF_8);
		get.releaseConnection();
		client.close();
		return body;
	}

	public static String post(String url, List<NameValuePair> params) throws ParseException, IOException {
		CloseableHttpClient client = HttpClients.createMinimal();
		String body = null;
		// Post请求
		HttpPost post = new HttpPost(url);
		String stamp = Long.toString(System.currentTimeMillis());
		String nonce = UUID.randomUUID().toString();
		post.setHeader("nonce", nonce);
		post.setHeader("stamp", stamp);
		post.setHeader("signature", SignUtil.sign(stamp, nonce));

		// 设置参数
		if (params != null)
			post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));

		// 发送请求
		HttpResponse httpresponse = client.execute(post);
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		body = EntityUtils.toString(entity, Consts.UTF_8);
		post.releaseConnection();
		client.close();
		return body;
	}

	/**
	 * 请求中间件接口
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String postAjax(String url, String json) throws ClientProtocolException, IOException {
		log.info("开始调用中间件API：\n地址：" + url + "\n参数：" + json);
		String body = null;
		// Post请求
		HttpPost post = new HttpPost(url);
		String stamp = Long.toString(System.currentTimeMillis());
		post.setHeader("Content-type", "application/json;charset=utf-8");
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
		// 设置参数
		//post.setEntity(new StringEntity(json, Consts.UTF_8));
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(5000).build();
		post.setConfig(requestConfig);

		long t1 = System.currentTimeMillis();
		// 发送请求
		CloseableHttpClient client = HttpClients.createMinimal();
		HttpResponse httpresponse = client.execute(post);
		log.debug("请求返回码：" + httpresponse.getStatusLine().getStatusCode());
		long t2 = System.currentTimeMillis();
		log.info("调用中间件接口使用时间(毫秒数)：" + (t2 - t1));
		// 获取返回数据
		HttpEntity entity = httpresponse.getEntity();
		body = EntityUtils.toString(entity, Consts.UTF_8);
		post.releaseConnection();
		client.close();
		log.info(body);
		return body;
	}
	
	
	public static void jsTest(){
	     ScriptEngineManager manager = new ScriptEngineManager();
	     ScriptEngine engine = manager.getEngineByName("javascript");
	     
	     try {
	         String str="2&1";
	         Double d = (Double) engine.eval(str);
	         Integer i=d.intValue();
	         System.out.println(i);
	     } catch (ScriptException ex) {
	        ex.printStackTrace();
	     } 
	}

	public static void main(String[] args) throws ParseException, IOException, URISyntaxException {
	    try {
	        String url = "http://fund.eastmoney.com/pingzhongdata/530010.js";//v=20180907161408
	        List<NameValuePair> params = new ArrayList<>();
	        params.add(new BasicNameValuePair("v","20180907161408"));
	        String reBody = HttpUtil.get(url, params);
	        //System.out.println(reBody);
	        ScriptEngineManager manager = new ScriptEngineManager();
	        ScriptEngine engine = manager.getEngineByName("javascript");
	        engine.eval(reBody);
	        Object array = engine.get("Data_netWorthTrend");
	        JSONObject tempObj = JSONObject.fromObject(array);
	        System.out.println(tempObj.size());
	        System.out.println(tempObj.get("0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
