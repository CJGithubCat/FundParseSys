package com.zsh.labouCapital.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 类功能：Http请求工具类；
 */
public class HttpclientUtil {
    private static Logger log = Logger.getLogger(HttpclientUtil.class);

    /**
     * 函数功能：获取HttpPost对象；
     */
    public static HttpPost getHttpPost(String serUrl) {
        // 1.设置HttpClient的参数
        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(2000).setConnectionRequestTimeout(2000).setSocketTimeout(2000).build();
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
        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000).setSocketTimeout(20000).build();

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
        }
        catch (Exception e) {
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
    public static String get(String url, List<NameValuePair> params) throws ParseException, IOException, URISyntaxException {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000).setStaleConnectionCheckEnabled(true).build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

        String body = null;
        HttpGet get = new HttpGet(url);

        // 设置参数
        String str = "";
        if (params != null) {
            str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            get.setURI(new URI(get.getURI().toString() + "?" + str));
        }
        else {
            get.setURI(new URI(get.getURI().toString()));
        }

        // 发送请求
        HttpResponse httpresponse = client.execute(get);
        // 获取返回数据
        HttpEntity entity = httpresponse.getEntity();
        body = EntityUtils.toString(entity, Consts.UTF_8);
        log.error("URL:" + get.getURI() + " re:" + body);
        get.releaseConnection();
        client.close();
        return body;
    }
    
    public static String post(String url, List<NameValuePair> params) throws ParseException, IOException {
        CloseableHttpClient client = HttpClients.createMinimal();
        String body = null;
        // Post请求
        HttpPost post = new HttpPost(url);
        // 设置参数
        if (params != null){
            post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
            
        post.setHeader("Content-type", "application/json;charset=utf-8");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        post.setHeader("Content-Security-Policy", "default-src 'self'£»script-src * 'unsafe-inline'");
        
        // 发送请求
        HttpResponse httpresponse = client.execute(post);
        // 获取返回数据
        HttpEntity entity = httpresponse.getEntity();
        body = EntityUtils.toString(entity, Consts.UTF_8);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSON(body));
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
        post.setHeader("Date","Wed, 06 Mar 2019 11:42:44 GMT");
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

    /**
     * 函数功能：通过post方法进行请求
     */
    public static JSONObject postRequest(String serUrl, String reqJson) {
        // 1.创建HttpClient对象；
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject respJsonObject = null;
        try {
            // 2.设置HttpClient的参数
            RequestConfig requestConfig =
                    RequestConfig.custom().setConnectTimeout(2000).setConnectionRequestTimeout(2000).setSocketTimeout(2000).build();

            // 3.创建HttpPost对象
            HttpPost httpPost = new HttpPost(serUrl);

            httpPost.setConfig(requestConfig);
            // 4.设置HttpPost头消息；
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");

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
                System.out.println("response:" + JSONObject.toJSONString(response));////////////////
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity respEntity = response.getEntity();
                    if (respEntity != null) {
                        String respStr = EntityUtils.toString(respEntity, "utf-8");
                        System.out.println("respStr:" + respStr);////////////////
                        respJsonObject = JSONObject.parseObject(respStr);
                    }
                }
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            catch (ClientProtocolException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (response != null) {
                    response.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return respJsonObject;
    }

    /**
     * @Title: main   
     * @Description: 测试方法   
     * @param: @param args      
     * @return: void      
     * @throws
     */
    public static void main(String[] args) {
        String checkUrl = "https://danjuanapp.com/djapi/order/p/list?page=1&size=20&type=all";
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("date", "20190607"));
        try {
            String re = HttpclientUtil.get(checkUrl,paramList);
            log.error("RESULT:" + re);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
