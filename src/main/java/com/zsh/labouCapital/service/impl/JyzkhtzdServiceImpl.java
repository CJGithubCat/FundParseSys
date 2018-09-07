package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.JyzkhtzdMapper;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.service.IJyzkhtzdService;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.PageBean;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

@Service
public class JyzkhtzdServiceImpl extends BaseServiceImpl<StationExamNoticeResult> implements IJyzkhtzdService {
	@Autowired
	private JyzkhtzdMapper jyzkhtzdMapper;

	@SuppressWarnings("rawtypes")
	@Override
	public void exportJyzkhtzd(Map<String, Object> params, HttpServletResponse response) throws Exception {
		try {
			List list = new ArrayList();
			list = jyzkhtzdMapper.exportJyzkhtzd(params);
			IExcelConfig excelConfig = ExcelConfigJson.getInstance();
			ExcelConfigVo excelConfigVo = excelConfig.getExcelConfigById("jyzkhtzd_MES_info_excel");
			ExcelExport export = new ExcelExport();
			export.exportExcelFromBeanList(response, excelConfigVo.getFile_name(), excelConfigVo.getShows(),
					excelConfigVo.getFields(), excelConfigVo.getCell_type(), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询加油站考核通知单
	 */
	@Override
	public HashMap<String, Object> findJyzkhtzdPage(String yearMonthDate, String jyz, String status,
			String salaryStatus, PageBean pageBean, String companyPath) throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ArrayList<StationExamNoticeResult> list = new ArrayList<StationExamNoticeResult>();
		long count;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("yearMonthDate", yearMonthDate);
		params.put("jyz", jyz);
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
			list = jyzkhtzdMapper.findJyzkhtzdPage(params);
			count = jyzkhtzdMapper.findJyzkhtzdPageCount(params);
			hm.put("list", list);
			hm.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

	/**
	 * 单条操作加油站考核通知单
	 */
	@Override
	public Map<String, Object> operateJyzkhtzd(String id, String changeStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("changeStatus", changeStatus);
		try {
			jyzkhtzdMapper.operateJyzkhtzd(params);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			return map;
		}
	}

	/**
	 * 批量操作加油站考核通知单
	 */
	@Override
	public void batchOperateJyzkhtzd(Map<String, Object> params) {
		jyzkhtzdMapper.batchOperateJyzkhtzd(params);
	}

	/**
	 * 
	 * @param tWgAgency
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ReturnValue edit(StationExamNoticeResult jyzInfo) throws Exception {
		ReturnValue rv = new ReturnValue();
		rv = super.edit(jyzInfo);
		return rv;
	}

	@Override
	public Map<String, Object> opJyzNoticeSalaryStatus(String id, String changeStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("changeStatus", changeStatus);
		jyzkhtzdMapper.opJyzNoticeSalaryStatus(params);
		map.put("success", true);
		return map;
	}

	@Override
	public void batchOpJyzNoticeSalaryStatus(Map<String, Object> paramMap) {
		jyzkhtzdMapper.batchOpJyzNoticeSalaryStatus(paramMap);
	}

	/**
	 * 查询可二次分配的数值
	 */
	@Override
	public Map<String, Object> queryCanAllocation(String companyId,String yearMonthDate) throws Exception {
		Map<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		params.put("yearMonthDate", yearMonthDate);
		try {
			hm = jyzkhtzdMapper.queryCanAllocation(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

	/**
	 * 根据修改加油站二次分配后的记录，修改部门对应的原始考核值
	 */
	@Override
	public void updateBMExamSummary(String companyId, String yearMonthDate) {
		// 查询父机构下子机构每个二次分配值的总和
		Map<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		params.put("yearMonthDate", yearMonthDate);
		hm = jyzkhtzdMapper.queryCanAllocation(params);
		hm.put("companyId", companyId);
		hm.put("yearMonthDate", yearMonthDate);
		jyzkhtzdMapper.updateBMExamSummary(hm);
	}
	
	/**
	 * 修改部门通知单专项奖罚和管理扣罚的值
	 */
	@Override
	public void updateDepartmentExamNotice(String companyId, String yearMonthDate) {
		// 查询父机构下子机构每个二次分配值的总和
		Map<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		params.put("yearMonthDate", yearMonthDate);
		hm = jyzkhtzdMapper.queryCanAllocation(params);
		hm.put("companyId", companyId);
		hm.put("yearMonthDate", yearMonthDate);
		jyzkhtzdMapper.updateDepartmentExamNotice(hm);
	}
}
