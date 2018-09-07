package com.zsh.labouCapital.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsh.labouCapital.pojo.DataPermission;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.pojo.TBaVehicle;


public class ResponseUtil {
	private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * controller层返回Json数据
	 * @param returnValue
	 * @return
	 */
	public static String json(ReturnValue returnValue) {
		try {
			String valueStr = mapper.writeValueAsString(returnValue);
			return valueStr;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 将用户权限数据信息转成Json格式字符串
	 * @param dp
	 * @return
	 */
	public static String json(DataPermission dp){
		try {
			String valueStr = mapper.writeValueAsString(dp);
			return valueStr;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String json(Map<String, Object> resultMap) {
		try {
			String valueStr = mapper.writeValueAsString(resultMap);
			return valueStr;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String json(List<?> list)  {
		String valueStr;
		try {
			valueStr = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return valueStr;
	}

	public static String json(Object object) {
		String valueStr;
		try {
			valueStr = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return valueStr;
	}
	
}
