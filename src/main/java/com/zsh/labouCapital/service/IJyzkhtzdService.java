package com.zsh.labouCapital.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface IJyzkhtzdService extends BaseService<StationExamNoticeResult> {

	public HashMap<String, Object> findJyzkhtzdPage(String yearMonthDate, String jyz, String status,String salaryStatus, PageBean pageBean,
			String companyPath) throws Exception;

	public void exportJyzkhtzd(Map<String, Object> params, HttpServletResponse response) throws Exception;

	public Map<String, Object> operateJyzkhtzd(String id, String changeStatus);

	public void batchOperateJyzkhtzd(Map<String, Object> map);
	
	public ReturnValue edit(StationExamNoticeResult jyzInfo) throws Exception;

	public Map<String, Object> opJyzNoticeSalaryStatus(String id, String changeStatus);

	public void batchOpJyzNoticeSalaryStatus(Map<String, Object> paramMap);
	
	public Map<String, Object> queryCanAllocation(String companyId,String yearMonthDate) throws Exception;
	
	public void updateBMExamSummary(String companyId,String yearMonthDate);
	
	public void updateDepartmentExamNotice(String companyId,String yearMonthDate);
}
