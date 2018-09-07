package com.zsh.labouCapital.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.zsh.labouCapital.entity.Company;

public interface CompanyMapper extends BaseSqlMapper<Company> {

	public void refreshCompanyPath();

	public String getCompanyPathById(Integer companyId);

	public Integer getCountByCompanyName(Map<String, Object> map);

	public List<Company> queryCompanyByCompanyName(String companyName);

	public List<Company> queryChildrenCompanyById(Integer companyId);

	public List<String> getAllCompanyNameByAgencyId(String agencyId);

	public int queryCompanyMaxLength(Company company);

	public List<Map<String, Object>> getAllCompanyIdToName();

	public List<Map<String, Object>> queryCompanyPage(Company company, RowBounds rowBounds);

	public int countQueryCompany(Company company);

	public Company getCompanyBySelfAndParentName(Map<String, Object> params);
	
	public void refreshMVAgency()throws Exception;
}
