package com.zsh.labouCapital.util;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class RequestUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static Integer getIntegerValue(String jsonString, String fieldName) throws Exception {
		Integer parameterValue = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonString)) {			
			JsonNode jsonNode = mapper.readTree(jsonString).get(fieldName);
			if (jsonNode != null && jsonNode.getNodeType() != JsonNodeType.NULL && jsonNode.getNodeType() == JsonNodeType.NUMBER) {
				parameterValue = jsonNode.intValue();
			}
		}
		return parameterValue;
	}
	
	public static Long getLongValue(String jsonString, String fieldName) throws Exception {
		Long parameterValue = null;
		if ( org.apache.commons.lang3.StringUtils.isNotBlank(jsonString)) {
			
			JsonNode jsonNode = mapper.readTree(jsonString).get(fieldName);
			if (jsonNode != null && jsonNode.getNodeType() != JsonNodeType.NULL && jsonNode.getNodeType() == JsonNodeType.NUMBER) {
				parameterValue = jsonNode.longValue();
			}
		}
		return parameterValue;
	}
	
	public static String getStringValue(String jsonString, String fieldName) throws Exception {
		String parameterValue = null;
		if (StringUtils.isNotBlank(jsonString)) {
			JsonNode jsonNode = mapper.readTree(jsonString).get(fieldName);
			if (jsonNode != null && jsonNode.getNodeType() != JsonNodeType.NULL && jsonNode.getNodeType() == JsonNodeType.STRING) {
				parameterValue = jsonNode.textValue();
			}
		}
		return parameterValue;
	}
	
	/**
	 * 转换成参数对象
	 * @param <T>
	 * @param encryptStr 原始内容
	 * @param clazz 可被实例化的Class对象
	 * @return
	 */
	public static <T> T getParameters(String jsonString, Class<T> clazz) throws Exception {
		T params = null;
	    if (jsonString != null && !"".equals(jsonString)) {	    	
	    	params = mapper.readValue(jsonString, clazz);
	    }
	    return params;
	}
	
	/**
	 * 转换成泛型对象
	 * @param encryptStr
	 * @param typeRef 泛型
	 * @return
	 * @throws Exception
	 */
	public static <T> T getParameters(String encryptStr, TypeReference<T> typeRef) throws Exception {
		T params = null;
	    if (encryptStr != null && !"".equals(encryptStr)) {	    	
	    	params = mapper.readValue(encryptStr, typeRef);
	    }
	    return params;
	}
}