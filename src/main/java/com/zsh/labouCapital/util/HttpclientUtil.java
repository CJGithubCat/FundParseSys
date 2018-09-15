package com.zsh.labouCapital.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 类功能：Http请求工具类；
 */
public class HttpclientUtil {
	/**
	 * 函数功能：获取HttpPost对象；
	 */
	public static HttpPost getHttpPost(String serUrl) {
		// 1.设置HttpClient的参数
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setConnectionRequestTimeout(2000)
				.setSocketTimeout(2000).build();
		// 2.创建HttpPost对象
		HttpPost httpPost = new HttpPost(serUrl);
		httpPost.setConfig(requestConfig);
		// 3.设置HttpPost头消息；
		httpPost.setHeader("Content-Type", "application/xml;charset=utf-8");
		return httpPost;
	}

	/**
	 * 函数功能：获取HttpGet对象；
	 */
	public static HttpGet getHttpGet(String serUrl) {
		// 1.设置HttpClient的参数
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000	)
				.setSocketTimeout(20000).build();

		// 2.创建HttpPost对象
		HttpGet httpGet = new HttpGet(serUrl);
		httpGet.setConfig(requestConfig);
		// 3.设置HttpPost头消息；
		httpGet.setHeader("Content-Type", "application/text;charset=utf-8");
		return httpGet;
	}

	/**
	 * 函数功能：关闭响应资源；
	 */
	public static void closeHttpConnet(CloseableHttpClient httpClient, CloseableHttpResponse response) {
		try {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 函数功能：PoolingClientConnectionManager使用请求连接池管理器；适用于多线程.
	 */
	public static HttpClient getHttpClient() {
		PoolingHttpClientConnectionManager poolManger = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolManger).build();
		return httpClient;
	}

	/**
	 * 函数功能：Get方式发送请求;
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@SuppressWarnings("deprecation")
	public static String requestByGet(String serUrl, String lon, String lat) {
		String respStr = null;
//		System.out.println("****请求URL***:" + serUrl + lat + "," + lon);
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();

//		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2965); 
		try {
//			System.out.println("lat:"+lat+";lon:"+lon);
			HttpGet httpGet = getHttpGet(serUrl + lat + "," + lon);
			httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
			response = (CloseableHttpResponse) httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity respEntity = response.getEntity();
				if (respEntity == null) {
					return null;
				}
				respStr = EntityUtils.toString(respEntity, "utf-8");
//				System.out.println("***返回信息***:" + respStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHttpConnet(httpClient, response);
		}
		return respStr;
	}
	
	/**
	 * 函数功能：Get方式发送批量经纬度转换请求;
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	//标准GPS可直接转参考位置 故批量经纬度转换取消使用
	/*
	public static String requestBatchByGet(String serUrl, String lonlatStr) {
		String respStr = null;
//		System.out.println("****请求URL***:" + serUrl + lonlatStr);
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
//		httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
//		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2965); 
		try {
			URL url = new URL(serUrl +lonlatStr);
			URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);  
			System.out.println("uri:   "+uri.toString());
			HttpGet httpGet = getHttpGet(uri.toString());
//			httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
			response = (CloseableHttpResponse) httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity respEntity = response.getEntity();
				if (respEntity == null) {
					return null;
				}
				respStr = EntityUtils.toString(respEntity, "utf-8");
//				System.out.println("***返回信息***:" + respStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHttpConnet(httpClient, response);
		}
		return respStr;
	}*/

	/**
	 * 函数功能：通过post方法进行请求
	 */
	public static JSONObject postRequest(String serUrl, String reqJson) {
		// 1.创建HttpClient对象；
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject respJsonObject = null;
		try {
			// 2.设置HttpClient的参数
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000)
					.setConnectionRequestTimeout(2000).setSocketTimeout(2000).build();

			// 3.创建HttpPost对象
			HttpPost httpPost = new HttpPost(serUrl);

			httpPost.setConfig(requestConfig);
			// 4.设置HttpPost头消息；
			httpPost.setHeader("Content-Type", "application/xml;charset=utf-8");

			// 5.组装json消息实体
			StringEntity strEntity = null;
			CloseableHttpResponse response = null;
			try {
				strEntity = new StringEntity(reqJson, "utf-8");
				strEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/xml;utf-8"));
				// 6.Httppost设置entity
				httpPost.setEntity(strEntity);
				// 7.解析返回的Json数据
				response = httpClient.execute(httpPost);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity respEntity = response.getEntity();
					if (respEntity != null) {
						String respStr = EntityUtils.toString(respEntity, "utf-8");
						System.out.println("respStr:" + respStr);////////////////
						respJsonObject = JSONObject.parseObject(respStr);
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return respJsonObject;
	}
	public static void main(String[] args) {
		// postRequest("http://www.baidu.com","");
		System.out.println();
	}
}
