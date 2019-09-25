package com.zsh.labouCapital.dao.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.zsh.labouCapital.entity.OperationLog;

public interface OperationRecordsMapper  {
	public int writeLog(OperationLog tWgOperationRecords);

	// 查询操作日志信息
	public ArrayList<Map<String, Object>> queryOperationRecordsInfoPage(Map<String, Object> params);

	// 不分页查询操作日志信息,导出用
	public ArrayList<OperationLog> queryAllOperationRecordsInfo(HashMap<String, Object> params);
	
	long queryOperationRecordsInfoCount(Map<String, Object> params);
}
