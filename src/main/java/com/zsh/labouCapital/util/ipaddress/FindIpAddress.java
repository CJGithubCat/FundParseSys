package com.zsh.labouCapital.util.ipaddress;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zsh.labouCapital.pojo.IpMappedAddress;  
/** 
 *  根据IP地址获取详细的地域信息  
 */  
public class FindIpAddress implements IpToAddress{ 
	private static final Logger logger = 
			LoggerFactory.getLogger(FindIpAddress.class);	
	
	@Override
	public IpMappedAddress getAddress(String interfaceUrl, String ip, String encode) throws Exception {
		if(ip==null||ip.trim().equals("")){
			return null;
		}
		String returnStr = this.getResult(interfaceUrl, ip, encode);
		IpMappedAddress address=jsonAddressToBean(returnStr);
		return address;
	}
   /**
    * 包装接口返回的地址信息到IpMappedAddress对象	
    * @param addressJson
    * @return
    * @throws Exception
    */
	private IpMappedAddress jsonAddressToBean(String addressJson)throws Exception{
		JsonParser parser = new JsonParser();
		JsonElement je=parser.parse(addressJson);
		JsonObject jsonObj=je.getAsJsonObject();
		String errorCode=jsonObj.get("code").getAsString();
		if("0".equals(errorCode)){
		   JsonObject dataObj=jsonObj.get("data").getAsJsonObject();
		   IpMappedAddress realAddress=new IpMappedAddress();
		   realAddress.setCountry(decodeUnicode(dataObj.get("country").getAsString()));
		   realAddress.setCountryId(decodeUnicode(dataObj.get("country_id").getAsString()));
		   realAddress.setArea(decodeUnicode(dataObj.get("area").getAsString()));
		   realAddress.setAreaId(decodeUnicode(dataObj.get("area_id").getAsString()));
		   realAddress.setRegion(decodeUnicode(dataObj.get("region").getAsString()));
		   realAddress.setRegionId(decodeUnicode(dataObj.get("region_id").getAsString()));
		   realAddress.setCity(decodeUnicode(dataObj.get("city").getAsString()));
		   realAddress.setCityId(decodeUnicode(dataObj.get("city_id").getAsString()));
		   realAddress.setCounty(decodeUnicode(dataObj.get("county").getAsString()));
		   realAddress.setCountyId(decodeUnicode(dataObj.get("county_id").getAsString()));
		   realAddress.setIsp(decodeUnicode(dataObj.get("isp").getAsString()));
		   realAddress.setIspId(decodeUnicode(dataObj.get("isp_id").getAsString()));
		   realAddress.setIp(decodeUnicode(dataObj.get("ip").getAsString()));
		   return realAddress;
		}else{			
			return null;
		}
	}	
 /**
  * 从第三方接口获取ip对应的实际地址
  * @param interfaceUrl
  * @param ip
  * @param encode
  * @return
  */
 private String getResult(String interfaceUrl, String ip, String encode)throws Exception {  
	   URL url = null;  
	   HttpURLConnection connection = null;   
	   try {  
	      url = new URL(interfaceUrl);  
	      connection = (HttpURLConnection) url.openConnection();// 新建连接实例  
	      connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒  
	      connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒  
	      connection.setDoOutput(true);// 是否打开输出流 true|false  
	      connection.setDoInput(true);// 是否打开输入流true|false  
	      connection.setRequestMethod("POST");// 提交方法POST|GET  
	      connection.setUseCaches(false);// 是否缓存true|false  
	      connection.connect();// 打开连接端口  
	      DataOutputStream out = new DataOutputStream(connection  
	        .getOutputStream());// 打开输出流往对端服务器写数据  
	      out.writeBytes("ip="+ip);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx  
	      out.flush();// 刷新  
	      out.close();// 关闭输出流  
	      BufferedReader reader = new BufferedReader(new InputStreamReader(  
	        connection.getInputStream(), encode));// 往对端写完数据对端服务器返回数据 ,以BufferedReader流来读取  
	      StringBuffer buffer = new StringBuffer();  
	      String line = "";  
	      while ((line = reader.readLine()) != null) {  
	         buffer.append(line);  
	      }  
	      reader.close();  
	      return buffer.toString();  
	  } catch (IOException ex) {  
	      //ex.printStackTrace();  
	      logger.error(ex.getMessage());
	      throw ex;
	  } finally {  
	      if (connection != null) {  
	         connection.disconnect();// 关闭连接  
	      }  
	  }	  
	}
 /**
  * unicode 转换成中文
  * @param theString
  * @return
  */
 public static String decodeUnicode(String theString)throws Exception{          
     char aChar;  	          
     int len = theString.length();  	          
     StringBuffer buffer = new StringBuffer(len);	          
     for (int i = 0; i < len;) { 	              
         aChar = theString.charAt(i++); 	              
         if(aChar == '\\'){ 	                  
             aChar = theString.charAt(i++); 	              
             if(aChar == 'u'){	                      
                 int val = 0;	                      
                 for(int j = 0; j < 4; j++){	                          
                     aChar = theString.charAt(i++);	                          
                     switch (aChar) { 	                          
                     case '0':	                              
                     case '1': 	                              
                     case '2': 	                              
                     case '3':  	                              
                     case '4':  	                          
                     case '5': 	                              
                     case '6':	                          
                     case '7':  	                              
                     case '8':	                              
                     case '9':	                              
                     val = (val << 4) + aChar - '0';                      
                     break;
   
                     case 'a':	                              
                     case 'b': 	                              
                     case 'c': 
                     case 'd': 
                     case 'e':
                     case 'f':
                     val = (val << 4) + 10 + aChar - 'a';  
                     break; 
                     
                     case 'A':  	                              
                     case 'B': 	                              
                     case 'C':  	                              
                     case 'D':  	                              
                     case 'E':  	                              
                     case 'F': 	                            
                     val = (val << 4) + 10 + aChar - 'A';	                                 
                     break;  
                              
                     default:	                          
                     throw new IllegalArgumentException(	                               
                         "Malformed encoding.");  
                     } 	                          
                 } 	                      
                 buffer.append((char) val);	                      
               }else{  
                    if(aChar == 't'){                               
                         aChar = '\t';  
                     } 	                          
                     if(aChar == 'r'){	                              
                         aChar = '\r';  
                     }	                          
                     if(aChar == 'n'){	                              
                         aChar = '\n';  
                     }	                          
                     if(aChar == 'f'){	                              
                         aChar = '\f'; 	                              
                     } 	                          
                     buffer.append(aChar);  
                 } 	                  
             }else{	                      
                 buffer.append(aChar);	                      
             }	                  
     } 	          
     return buffer.toString();  	          
 }
 public static void main(String[] args){
		String interfaceUrl1="http://ip.taobao.com/service/getIpInfo.php";
		String encode1="UTF-8";
		//String ip1 = "125.70.11.136";address
		//String ip1 = "218.18.2.7";
		String ip1="116.25.161.196";
		FindIpAddress find=new FindIpAddress();
		try {
			IpMappedAddress address=find.getAddress(interfaceUrl1, ip1, encode1);
			System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
} 

