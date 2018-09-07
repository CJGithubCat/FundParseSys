package com.zsh.labouCapital.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsh.labouCapital.entity.Salary;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface ISalaryService extends BaseService<Salary> {
	public HashMap<String, Object> querySalaryDetailByPage(String tzdh, String dw, String jyzName, PageBean pageBean,
			int status, int noticeId, String companyPath,HttpServletRequest request);

	public void addSalaryDetial(List<Salary> datas, String noticeId, int salaryType) throws Exception;

	public void batchEditSalary(ArrayList<Salary> readSarlaryList, int salaryType, int noticeId) throws Exception;

	public void exportSalary(Map<String, Object> params, HttpServletResponse response) throws Exception;

	public Map<String, Object> getNoticeInfoById(Integer noticeId, Integer salaryType);

}
