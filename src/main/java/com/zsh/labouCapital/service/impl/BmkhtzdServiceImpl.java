package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dao.BmkhtzdMapper;
import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.TTradeModel;
import com.zsh.labouCapital.service.IBmkhtzdService;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.PageBean;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

@Service
public class BmkhtzdServiceImpl extends BaseServiceImpl<DepartmentExamNoticeResult> implements IBmkhtzdService {
	@Autowired
	private BmkhtzdMapper bmkhtzdMapper;

	@SuppressWarnings("rawtypes")
	@Override
	public void exportBmkhtzd(Map<String, Object> params, HttpServletResponse response) throws Exception {
		try {
			List list = new ArrayList();
			list = bmkhtzdMapper.exportBmkhtzd(params);
			IExcelConfig excelConfig = ExcelConfigJson.getInstance();
			ExcelConfigVo excelConfigVo = excelConfig.getExcelConfigById("bmkhtzd_MES_info_excel");
			ExcelExport export = new ExcelExport();
			export.exportExcelFromBeanList(response, excelConfigVo.getFile_name(), excelConfigVo.getShows(),
					excelConfigVo.getFields(), excelConfigVo.getCell_type(), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询部门考核通知单
	 */
	@Override
	public HashMap<String, Object> findBmkhtzdPage(String yearMonthDate, String bmmc, String status,
			String salaryStatus, PageBean pageBean, String companyPath) throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ArrayList<DepartmentExamNoticeResult> list = new ArrayList<DepartmentExamNoticeResult>();
		long count;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("yearMonthDate", yearMonthDate);
		params.put("bmmc", bmmc);
		params.put("status", status);
		params.put("salaryStatus", salaryStatus);
		params.put("companyPath", companyPath);
		if (pageBean.getPage() == 1) {
			params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
		} else {
			params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
		}
		params.put("limit", pageBean.getPagesize() * pageBean.getPage());
		try {
			list = bmkhtzdMapper.findBmkhtzdPage(params);
			count = bmkhtzdMapper.findBmkhtzdPageCount(params);
			hm.put("list", list);
			hm.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

	/**
	 * 单条操作考核通知单
	 */
	@Override
	public Map<String, Object> operateBmkhtzd(String id, String changeStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("changeStatus", changeStatus);
		try {
			bmkhtzdMapper.operateBmkhtzd(params);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			return map;
		}
	}

	/**
	 * 批量操作部门考核通知单
	 */
	@Override
	public void batchOperateBmkhtzd(Map<String, Object> params) {
		bmkhtzdMapper.batchOperateBmkhtzd(params);
	}

	@Override
	public Map<String, Object> opBmNoticeSalaryStatus(String id, String changeStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("changeStatus", changeStatus);
		bmkhtzdMapper.opBmNoticeSalaryStatus(params);
		map.put("success", true);
		return map;
	}

	@Override
	public void batchopBmNoticeSalaryStatus(Map<String, Object> paramMap) {
		bmkhtzdMapper.batchopBmNoticeSalaryStatus(paramMap);
	}

}
