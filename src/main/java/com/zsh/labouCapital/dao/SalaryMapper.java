package com.zsh.labouCapital.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.zsh.labouCapital.entity.Salary;

/**
 * 工资信息操作
 */
public interface SalaryMapper extends BaseSqlMapper<Salary> {
	ArrayList<Salary> querySalaryPage(HashMap<String, Object> params, RowBounds rowBounds);

	long querySalaryPageCount(HashMap<String, Object> params);

	void addSalaryDetial(Salary datas);

	void batchEditSalary(Salary readSarlaryList);

	@SuppressWarnings("rawtypes")
	public ArrayList exportSalary(Map<String, ?> params) throws Exception;

	List<Integer> queryCompanyInfo(HashMap<String, Object> tempParam);

	ArrayList<Salary> queryDepartmentSalaryInfo(HashMap<String, Object> params, RowBounds rowBounds);

	long queryDepartmentSalaryCount(HashMap<String, Object> params);

	ArrayList<Salary> querySatationSalaryInfo(HashMap<String, Object> params, RowBounds rowBounds);

	long querySatationSalaryCount(HashMap<String, Object> params);

	Map<String, Object> queryFsRelationCompany(HashMap<String, Object> tempParam);
}
