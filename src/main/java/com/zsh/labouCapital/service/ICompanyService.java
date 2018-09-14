package com.zsh.labouCapital.service;

import java.util.List;

import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface ICompanyService extends BaseService<Company>{

	public ReturnValue queryCompanyPage(Company company, PageBean pageBean) throws Exception;

	public ReturnValue queryMaxLeng(Company company) throws Exception;
	
	public List<Company> queryChildrenAgencyById(Integer parenetAgencyId) throws Exception;
	
	public List<Company> queryAgencyByAgencyName(String agencyName) throws Exception;
	
	public List<String> getAllAgencyName(String agencyId) throws Exception;
	
	
	/**
	 * 获取父节点及下面所有子节点的数据
	 * @param parenetAgencyId
	 * @return
	 * @throws Exception
	 */
	public List<Company> queryAllAgencyById(Integer parenetAgencyId) throws Exception;
	
	public int add(Company company) throws Exception;
	
	public int removeById(String id) throws Exception ;
	
	public ReturnValue edit(Company tWgAgency) throws Exception;
	
	
	public String getCompanyPathById(Integer agencyId);

	
	/********************************************************/
	public int getCountByCompanyName(Integer companyId, String companyName);

	public List<com.zsh.labouCapital.entity.Company> queryChildrenCompanyById(Integer companyId);

	public List<Company> queryCompanyByCompanyName(String companyName);
	
	/**
	 * 函数功能：根据机构id获取attributespath;
	 * */
}
