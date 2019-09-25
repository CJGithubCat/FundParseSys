package com.zsh.labouCapital.service;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface IBmkhtzdService{

	public HashMap<String, Object> findBmkhtzdPage(String yearMonthDate, String bmmc, String status,String salaryStatus,PageBean pageBean,
			String companyPath) throws Exception;

	public void exportBmkhtzd(Map<String, Object> params, HttpServletResponse response) throws Exception;

	public Map<String, Object> operateBmkhtzd(String id, String changeStatus);

	public void batchOperateBmkhtzd(Map<String, Object> map);

	public Map<String, Object> opBmNoticeSalaryStatus(String id, String changeStatus);

	public void batchopBmNoticeSalaryStatus(Map<String, Object> paramMap);

}
