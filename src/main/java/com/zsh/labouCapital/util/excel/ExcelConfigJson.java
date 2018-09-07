package com.zsh.labouCapital.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;
/**
 * 从excel 格式配置文件读出excel 数据格式
 * excel 配置文件是 json格式
 * @author gongyu
 *
 */
public class ExcelConfigJson implements IExcelConfig{
	private static ExcelConfigJson excelConfigJson=new ExcelConfigJson() ;//饿汉模式
	private List<ExcelConfigVo> rs; 
	private ExcelConfigJson(){
		Gson gson = new Gson();
	    FileInputStream configIn = null;
	    try {
	    	String rootDir = new File(ExcelConfigJson.class.getClassLoader().getResource("").getPath()).getCanonicalPath() + File.separatorChar;
	        configIn = new FileInputStream(rootDir+"config"+ File.separatorChar+"excel"+ File.separatorChar+"excelexport.config.json");        
	        Type type = new TypeToken<ArrayList<ExcelConfigVo>>() {}.getType();  
	        rs= gson.fromJson(IOUtils.toString(configIn,"UTF-8"), type);
	    } catch (JsonSyntaxException e) {
	       e.printStackTrace();
	    } catch (IOException e) {
	       e.printStackTrace();
	    } finally {
	       IOUtils.closeQuietly(configIn);
	    }
	}
   /**
    * 懒汉模式
    *     懒汉模式，非线程安全
    *     synchronized 同步方法，保证线程安全，但效率太低（大多数情况下不需要同步）。
    * @return
    */
	/*public static ExcelConfigJson getInstance(){
	  //public static synchronized ExcelConfigJson getInstance(){
	    if(null==excelConfigJson){
	    	excelConfigJson=new ExcelConfigJson();
	    }
	    return excelConfigJson;
	}*/
	public static ExcelConfigJson getInstance(){
	    return excelConfigJson;
	}

	@Override
	public ExcelConfigVo getExcelConfigById(String id) {
		if(rs!=null){
			for(ExcelConfigVo configVo:rs){
				if(id.equals(configVo.getId())){
					return configVo;
				}
			}
		}
        return null;
	}

}
