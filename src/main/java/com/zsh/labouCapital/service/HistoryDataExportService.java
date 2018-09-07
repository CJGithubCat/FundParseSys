package com.zsh.labouCapital.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zsh.labouCapital.pojo.ExportHistoryData;
import com.zsh.labouCapital.pojo.LastPos;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.pojo.TBaVehicle;

/**
 * 
 * @author caoliang
 *历史数据导出
 */
public interface HistoryDataExportService {
    /**
     * 历史数据按时间导出
     * @param response
     * @param paramMap
     */
	public void exportHistoryData(HttpServletResponse response,Map<String,Object> paramMap) throws Exception;
	
	 /**
     * 历史数据按时间查询
     * @param response
     * @param paramMap
     */
	public ReturnValue queryHistoryData(HttpServletResponse response,Map<String,Object> paramMap) throws Exception;
	
	/**
     * 历史数据按每日时间间隔导出
     * @param response
     * @param paramMap
     */
	public void exportHistoryDataByTimes(HttpServletResponse response,Map<String,Object> paramMap) throws Exception;
	/**
     * 历史数据按每日时间间隔查询
     * @param response
     * @param paramMap
     */
	public ReturnValue queryHistoryDataByTimes(HttpServletResponse response,Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 最后位置表数据查询
	 */
	public LastPos getLastPosMes(String callLetter) throws Exception;
	
	public TBaVehicle findVehicleByCallLetter(Map<String, Object> map) throws Exception;

	public TBaVehicle findVehicleByNumberPlate(Map<String, Object> params);
	
}
