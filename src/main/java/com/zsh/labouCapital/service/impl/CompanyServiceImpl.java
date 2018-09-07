package com.zsh.labouCapital.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zsh.labouCapital.dao.CompanyMapper;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.pojo.TWgSales;
import com.zsh.labouCapital.service.ICompanyService;
import com.zsh.labouCapital.util.pagination.Page;
import com.zsh.labouCapital.util.pagination.PageBean;
import com.zsh.labouCapital.util.pagination.PageUtils;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements ICompanyService{
	
	@Autowired
	private CompanyMapper companyMapper;

	
	@SuppressWarnings("unused")
	@Transactional
	public int add(Company company) throws Exception{
		/* 新增机构数据到机构表中 */
		int res = super.add(company);
		//刷新表 t_company_path
		companyMapper.refreshCompanyPath();
		return company.getCompanyId();
	}
	
	@Override
	public ReturnValue queryCompanyPage(Company company, PageBean pageBean) throws Exception {
		ReturnValue rv = new ReturnValue();
		List<Map<String, Object>> list = null;// 
		int count = 0;

		// 获取分页对象
		Page page = PageUtils.getPage(pageBean);
		//Map agencyId-agencyName键值对。
		Map<String, Object> IdToNameMap = new HashMap<String, Object>();
		List<Map<String, Object>> idToNameList= companyMapper.getAllCompanyIdToName();
		for (Map<String, Object> map : idToNameList) {
			IdToNameMap.put(map.get("companyId").toString(), map.get("companyName"));
		}
		
		list = companyMapper.queryCompanyPage(company,new RowBounds(page.getOffset(), page.getNumPerPage()));
	
		// 查询总量
		count = companyMapper.countQueryCompany(company);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", list);
		rv.setDatas(map);
		rv.setSuccess(true);
		return rv;
	}

	@Override
	public ReturnValue queryMaxLeng(Company company) throws Exception {
		ReturnValue rv = new ReturnValue();
		int MaxLength = 0;
		MaxLength = companyMapper.queryCompanyMaxLength(company);
		rv.setDatas(MaxLength);
		rv.setSuccess(true);
		return rv;
	}

	@Override
	public List<Company> queryChildrenAgencyById(Integer parenetAgencyId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> queryAgencyByAgencyName(String agencyName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllAgencyName(String agencyId) throws Exception {
		List<String> list = companyMapper.getAllCompanyNameByAgencyId(agencyId);
		return list;
	}

	@Override
	public List<Company> queryAllAgencyById(Integer parenetAgencyId) throws Exception {
		return null;
	}

	@Override
	public List<TWgSales> queryTWgSalesList(TWgSales tWgSales) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCompanyPathById(Integer companyId) {
		if(companyId == null){
			return null;
		}
		String attributesPath = "";
		try {
			attributesPath = companyMapper.getCompanyPathById(companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attributesPath;
	}

	@Override
	public int getCountByCompanyName(Integer companyId, String companyName) {
		Integer count =-1;
		try {
			  Map<String , Object> map = new HashMap<String, Object>();
			  map.put("companyId", companyId);
			  map.put("companyName", companyName);
			 count = companyMapper.getCountByCompanyName(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Company> queryChildrenCompanyById(Integer companyId) {
		List<Company> list = null;
		list =  companyMapper.queryChildrenCompanyById(companyId);
		return list;
	}

	@Override
	public List<Company> queryCompanyByCompanyName(String companyName) {
		List<Company> list = null;
		list =  companyMapper.queryCompanyByCompanyName(companyName);
		return list;
	}
	
	@Transactional
	public ReturnValue edit(Company company) throws Exception{
		ReturnValue rv = new ReturnValue();
		rv = super.edit(company);
		//刷新表 t_company_path
		companyMapper.refreshCompanyPath();
		return rv;
	}
}
