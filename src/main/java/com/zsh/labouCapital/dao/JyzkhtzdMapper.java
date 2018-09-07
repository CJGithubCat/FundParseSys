package com.zsh.labouCapital.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zsh.labouCapital.entity.StationExamNoticeResult;

public interface JyzkhtzdMapper extends BaseSqlMapper<StationExamNoticeResult> {

	ArrayList<StationExamNoticeResult> findJyzkhtzdPage(HashMap<String, Object> params);

	long findJyzkhtzdPageCount(HashMap<String, Object> params);

	@SuppressWarnings("rawtypes")
	public ArrayList exportJyzkhtzd(Map<String, ?> params) throws Exception;

	public void operateJyzkhtzd(HashMap<String, Object> params);

	public void batchOperateJyzkhtzd(Map<String, Object> params);

	void opJyzNoticeSalaryStatus(HashMap<String, Object> params);

	void batchOpJyzNoticeSalaryStatus(Map<String, Object> paramMap);

	public Map<String, Object> queryCanAllocation(HashMap<String, Object> params);
	
	public void updateBMExamSummary(Map<String, Object> params);
	
	public void updateDepartmentExamNotice(Map<String, Object> params);

}
