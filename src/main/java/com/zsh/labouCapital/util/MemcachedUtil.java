package com.zsh.labouCapital.util;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsh.labouCapital.comm.SystemConst;


public class MemcachedUtil {
	
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(MemcachedUtil.class);
	
	private static final long DEFAULT_TIMEOUT = 5;//5秒
	private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;
	private static MemcachedClient mc = null;
	
	
	public static MemcachedClient getClient(boolean useBinary) {
		try {
			if (mc == null) {
				String servers = PropertyUtil.getPropertyValue("memcached.properties", "memcached.servers");
				if (useBinary) {
					mc = new MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses(servers));
				} else {
					mc = new MemcachedClient(AddrUtil.getAddresses(servers));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(),e);
		}
		return mc;
	}
	
	/**
	 * 函数名称：set
	 * 函数功能：添加或者覆盖一组KV对
	 * @author Lxb
	 * @Date 2015-06-01 下午3:47
	 * @param key 关键字
	 * @param value 值
	 * @param expire 有效时间（1）以秒为单位的有效时间，最大是30天，如果超过了30天，会被认为是（2）
	 *                     （2）距离1970/01/01的以秒为单位的时间
	 * @return 布尔值
	 */
	 public static boolean set(String key, Object value, int expire) {  
	        Future<Boolean> f = getClient(false).set(key, expire, value);  
	        return getBooleanValue(f);  
	 }
	
	 
	/**
	 * 函数名称：asyncGet
	 * 函数功能：异步获取值，如果5秒还没有返回则取消操作
	 * @author Lxb
	 * @Date 2015-06-01 下午3:37
	 * @param key
	 * @return
	 */
	public static Object asyncGet(String key) {  
        Object obj = null;  
        Future<Object> f = getClient(false).asyncGet(key);  
        try {  
            obj = f.get(DEFAULT_TIMEOUT,DEFAULT_TIMEUNIT);  
        } catch (Exception e) {  
            f.cancel(false);  
        }  
        return obj;  
    } 
	
	/**
	 * 函数名称：gets
	 * 函数功能：CAS(Compare and Swap)原子操作，处理同一item被多个线程更改过程的并发问题;如果有原子操作问题，使用gets以及cas方法
	 * @author Lxb
	 * @Date 2015-06-01 下午4:31
	 * @param key
	 * @return
	 */
	public static CASValue<Object> gets(String key){
		return getClient(false).gets(key);
		
	}
	
	/**
	 * 函数名称：asyncCAS
	 * 函数功能：原子replace操作
	 * @author Lxb
	 * @Date 2015-06-01 下午5:07
	 * @param key
	 * @param exp
	 * @param value
	 * @return int :0=成功;1=关键字不存在;2=值已修改;-1=异常
	 */
	public static int asyncCAS(String key, int exp, Object value) {

		if(exp==0)
			exp = SystemConst.ACCOUNT_WG_MEMCACHED_DATA_TIME;
		
		Future<CASResponse> f = getClient(false).asyncCAS(key, gets(key)==null?Math.round(10000):gets(key).getCas(), value);  
        return getCasValue(f); 
	}
	
	/**
	 * 函数名称：disConnect
	 * 函数功能：关闭连接
	 * @author Lxb
	 * @Date 2015-06-01 下午3:37
	 */
	public static void disConnect() {  
        if (getClient(false) == null) {  
            return;  
        }  
        getClient(false).shutdown();  
    }  
	
	/**
	 * 函数名称：getBooleanValue
	 * 函数功能：获取结果值的结果,异步读取
	 * @author Lxb
	 * @Date 2015-06-01 下午3:39
	 */
	 private static boolean getBooleanValue(Future<Boolean> f) {  
	        try {  
	            Boolean bool = f.get(DEFAULT_TIMEOUT, DEFAULT_TIMEUNIT);  
	            return bool.booleanValue();  
	        } catch (Exception e) {  
	            f.cancel(false);  
	            return false;  
	        }  
	    }  
	 
	 /**
	  * 函数名称：getCasValue
	  * 函数功能：获取cas操作的返回结果
	  * @author Lxb
	  * @Date 2015-06-01 下午3:39
	  * @param f
	  * @return int :0=成功;1=关键字不存在;2=值已修改;-1=异常
	  */
	 private static int getCasValue(Future<CASResponse> f) {  
		        try {  
		        	CASResponse response = f.get(DEFAULT_TIMEOUT, DEFAULT_TIMEUNIT); 
		        	return response.ordinal();
		        } catch (Exception e) {  
		            f.cancel(false);  
		            return -1;  
		        }  
		    }
	 
	public static void main(String []args){
		CASResponse cr = CASResponse.EXISTS;
		System.out.println(CASResponse.EXISTS.ordinal()+"~~"+CASResponse.NOT_FOUND.ordinal()+"~~"+CASResponse.OK.ordinal());
		
		//读取memcached-gps信息
		String gps_key = SystemConst.MEMCACHED_GPS;
		Object o = MemcachedUtil.getClient(false).get(gps_key);
		
		if(o!=null){
			System.out.println(o);
		}
	}
}
