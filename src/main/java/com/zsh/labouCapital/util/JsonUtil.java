package com.zsh.labouCapital.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	private static ObjectMapper mapper = null;
	private static ObjectMapper getMapper()
	{
		if(mapper == null)
		{
			mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(DeserializationConfig.Feature.AUTO_DETECT_FIELDS, true);
			mapper.configure(DeserializationConfig.Feature.AUTO_DETECT_SETTERS, true);
			mapper.configure(DeserializationConfig.Feature.AUTO_DETECT_CREATORS, true);
		}
		return mapper;
	}
	public static String toJson(Object o)
	{
		String result = null;
		
		try {
			result = getMapper().writeValueAsString(o);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.gc();
			e.printStackTrace();
		}
		return result;
	}
	public static Object fromJson(Class<?> cls, String jsonStr)
	{
		Object result = null;
		try {
			if(jsonStr != null && jsonStr.length() > 0)
			{
			result =  getMapper().readValue(jsonStr, cls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map jsonToMap(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;
    }
}
