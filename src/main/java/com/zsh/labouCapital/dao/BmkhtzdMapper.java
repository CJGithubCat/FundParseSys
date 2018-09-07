package com.zsh.labouCapital.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;

public interface BmkhtzdMapper extends BaseSqlMapper<DepartmentExamNoticeResult> {

	ArrayList<DepartmentExamNoticeResult> findBmkhtzdPage(HashMap<String, Object> params);

	long findBmkhtzdPageCount(HashMap<String, Object> params);

	@SuppressWarnings("rawtypes")
	public ArrayList exportBmkhtzd(Map<String, ?> params) throws Exception;

	public void operateBmkhtzd(HashMap<String, Object> params);

	public void batchOperateBmkhtzd(Map<String, Object> params);

	void opBmNoticeSalaryStatus(HashMap<String, Object> params);

	void batchopBmNoticeSalaryStatus(Map<String, Object> paramMap);


}
