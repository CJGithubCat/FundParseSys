package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dao.client.OperationRecordsMapper;
import com.zsh.labouCapital.entity.OperationLog;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.util.pagination.PageBean;

@Service
public class OperationRecordsImpl implements ILoggerService {
	@Autowired
	private OperationRecordsMapper operationRecordsMapper;

	@Override
	public int writeLog(int userid, String opTime, int moduleId,String remoteip, String description) throws Exception {
		OperationLog operationRecords = new OperationLog();
		operationRecords.setUserId(userid);
		operationRecords.setOpTime(opTime);
		operationRecords.setModuleId(moduleId);
		operationRecords.setRemoteIp(remoteip);
		operationRecords.setOperateDescription(description);
		return operationRecordsMapper.writeLog(operationRecords);
	}

	@Override
	public HashMap<String, Object> queryOperationRecordsInfoPage(Map<String, Object> params) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			PageBean pageBean = (PageBean) params.get("pageBean");
			if (pageBean.getPage() == 1) {
				params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
			} else {
				params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
			}
			params.put("limit", pageBean.getPagesize() * pageBean.getPage());
			ArrayList<Map<String, Object>> list = operationRecordsMapper.queryOperationRecordsInfoPage(params);
			Long count = operationRecordsMapper.queryOperationRecordsInfoCount(params);
			hm = new HashMap<String, Object>();
			hm.put("list", list);
			hm.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	
	}

	@Override
	public ArrayList<OperationLog> exportOperationRecordsInfo(HashMap<String, Object> params) {
		ArrayList<OperationLog> list = null;
		try {
			list = operationRecordsMapper.queryAllOperationRecordsInfo(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
