package com.zsh.labouCapital.dao;

import java.util.Map;

import com.zsh.labouCapital.pojo.tree.NodeInfo;

public interface IndexCountMapper extends BaseSqlMapper<NodeInfo> {

	/**
	 * 加油站工资单状态为-1,审核不通过
	 * 
	 * @param companyPath
	 * @return
	 */
	public int findJyzGzdShbtgCount(String companyPath);

	public int queryJyzSalaryNoticeCount(Map<String, Object> param);

	public int queryXgsSalaryNoticeCount(Map<String, Object> param);

	public int queryJyzExamNoticeCount(Map<String, Object> param);

	public int queryXgsExamNoticeCount(Map<String, Object> param);
}
