package com.zsh.labouCapital.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.zsh.labouCapital.entity.OperationLog;

public interface ILoggerService {
	public int writeLog(int userid, String opTime, int moduleId,String remoteip, String description) throws Exception;
	//查询操作日志信息
	public HashMap<String, Object> queryOperationRecordsInfoPage(Map<String, Object> params);
	//导出操作日志信息
	public ArrayList<OperationLog> exportOperationRecordsInfo(HashMap<String, Object> params);
}
