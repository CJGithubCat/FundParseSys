package com.zsh.labouCapital.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * 签名类，用于创建字符串签名（与app、微信服务器交互需要签名字符串）
 * 
 * @author zhangming
 *
 */
public final class SignUtil {

	private static Logger logger = Logger.getLogger(SignUtil.class);

	/**
	 * 签名token
	 */
	private static String token = Config.get("config.ware.token");

	/**
	 * 生成签名文件
	 * 
	 * @return
	 */
	public static String sign(String stamp, String nonce) {
		final String[] arr = new String[] { token, stamp, nonce };
		final StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		logger.debug("排序字符串" + content);
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("MD5");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			final byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于app
		logger.debug("服务器加密串：" + tmpStr);
		return tmpStr;
	}

	private final static char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(final byte[] byteArray) {
		final char[] c = new char[byteArray.length * 2];
		for (int i = 0; i < byteArray.length; i++) {
			c[i * 2] = Digit[byteArray[i] >>> 4 & 0xf];
			c[i * 2 + 1] = Digit[byteArray[i] & 0xf];
		}
		return new String(c);
	}

}
