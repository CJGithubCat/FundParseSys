/*package com.zsh.labouCapital.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dao.IndexCountMapper;
import com.zsh.labouCapital.service.IndexCountService;

@Service
public class IndexCountServiceImpl implements IndexCountService {

	@Autowired
	@Qualifier("indexCountMapper")
	private IndexCountMapper indexCountMapper;
	
	@Override
	public int queryJyzSalaryNoticeCount(String companyPath, String status) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyPath", companyPath);
		param.put("salaryStatus", status);
		return indexCountMapper.queryJyzSalaryNoticeCount(param);
	}

	@Override
	public int queryXgsSalaryNoticeCount(String companyPath, String status) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyPath", companyPath);
		param.put("salaryStatus", status);
		return indexCountMapper.queryXgsSalaryNoticeCount(param);
	}

	@Override
	public int queryJyzExamNoticeCount(String companyPath, String status) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyPath", companyPath);
		param.put("status", status);
		return indexCountMapper.queryJyzExamNoticeCount(param);
	}

	@Override
	public int queryXgsExamNoticeCount(String companyPath, String status) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyPath", companyPath);
		param.put("status", status);
		return indexCountMapper.queryXgsExamNoticeCount(param);
	}
}
*/