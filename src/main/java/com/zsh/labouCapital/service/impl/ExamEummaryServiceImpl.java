package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.CompanyMapper;
import com.zsh.labouCapital.dao.ExamSummaryMapper;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.ExamSummary;
import com.zsh.labouCapital.entity.ExamSummaryVo;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.service.IExamEummaryService;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.Page;
import com.zsh.labouCapital.util.pagination.PageBean;
import com.zsh.labouCapital.util.pagination.PageUtils;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

@Service
public class ExamEummaryServiceImpl extends BaseServiceImpl<ExamSummary> implements IExamEummaryService {

	@Autowired
	private ExamSummaryMapper examEummaryMapper;

	@Autowired
	private CompanyMapper companyMapper;

	@Override
	@Transactional
	public void addExamSummary(List<ExamSummary> examSummaryList) throws Exception {
		for (ExamSummary examSummary : examSummaryList) {
			examEummaryMapper.addExamSummary(examSummary);
		}
	}

	@Override
	public int countExamSummaryByDate(String summaryYearMonth) {
		return examEummaryMapper.countExamSummaryByDate(summaryYearMonth);
	}

	@Override
	public List<ExamSummary> queryExamSummarysPage(ExamSummaryVo examSummaryVo, PageBean pageBean) {
		// 获取分页对象
		Page page = PageUtils.getPage(pageBean);
		return examEummaryMapper.queryExamSummarysPage(examSummaryVo,
				new RowBounds(page.getOffset(), page.getNumPerPage()));
	}

	@Override
	public int getExamSummarysCount(ExamSummaryVo examSummaryVo) {
		// 获取分页对象
		return examEummaryMapper.getExamSummarysCount(examSummaryVo);
	}

	@Override
	public Map<String, Object> checkIsCompanysExist(List<ExamSummary> datas) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		ArrayList<String> companyNames = new ArrayList<String>();
		for (ExamSummary examSummary : datas) {
			// 根据fbk(分版块)字段组装机构名称：
			String companyName = null;
			String parentCompanyName = null;
			String fbkName = examSummary.getFbkName();
			int companyType = -1;
			if (fbkName.equals("加油站")) {
				companyName = examSummary.getJyzName();
				parentCompanyName = examSummary.getBnName();
				companyType = 5;// 加油站
			}
			if (fbkName.equals("机关附属")) {
				companyName = examSummary.getBnName() + examSummary.getJyzName();
				parentCompanyName = null;// examSummary.getDwName();
				companyType = 2;
			}
			if (fbkName.equals("机关")) {
				companyName = examSummary.getBnName();
				parentCompanyName = null;// examSummary.getDwName();
				companyType = 1;// 县级公司
			}
			if (fbkName.equals("县公司")) {
				companyName = examSummary.getBnName();
				parentCompanyName = null;// examSummary.getDwName();
				companyType = 3;// 县级公司
			}
			if (fbkName.equals("油库")) {
				companyName = examSummary.getBnName();
				parentCompanyName = null;// examSummary.getDwName();
				companyType = 4;// 县级公司
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ownCompanyName", companyName);
			params.put("parentCompanyName", parentCompanyName);
			params.put("companyType", companyType);

			Company company = companyMapper.getCompanyBySelfAndParentName(params);
			if (company == null) {
				companyNames.add(companyName);
			} else {
				examSummary.setCompanyId(company.getCompanyId());// 设置companyID
			}
		}
		if (companyNames.size() > 0) {
			reMap.put("success", false);
			reMap.put("list", companyNames);
		} else {
			reMap.put("success", true);
			reMap.put("company", null);
		}
		return reMap;
	}

	@Override
	public void genderDepartExamNotice(Map<String, Object> params) {
		// 1.生成相应的通知单实例
		List<ExamSummary> examSummayList = examEummaryMapper.queryAllExamSummaryByDateAndName(params);

		// 2.查询t_company表获取company信息
		DepartmentExamNoticeResult departExamNotice = new DepartmentExamNoticeResult();
		List<Company> companyList = companyMapper.queryCompanyByCompanyName((String) params.get("xbmName"));
		if (companyList != null && companyList.size() > 0) {
			Company company = companyList.get(0);
			if (company != null) {
				departExamNotice.setCompanyId(company.getCompanyId());
			}
		}
		// 计算,然后赋值给通知单
		for (int i = 0; i < examSummayList.size(); i++) {

		}
		departExamNotice.setYearMonthDate((String) params.get("yearMonthDate"));
		departExamNotice.setStatus(1);
		// 3.插入到通知单表
		examEummaryMapper.genderDepartExamNotice(departExamNotice);
	}

	@Override
	public void genderStationExamNotice(Map<String, Object> params) {
		// TODO Auto-generated method stub
		StationExamNoticeResult stationExamNotice = new StationExamNoticeResult();

		examEummaryMapper.genderStationExamNotice(stationExamNotice);
	}

	@Override
	@Transactional
	public void genderDepartNotice(DepartmentExamNoticeResult deNoticeResult) {
		examEummaryMapper.genderDepartExamNotice(deNoticeResult);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", 1);
		param.put("id", deNoticeResult.getExamSummayId());
		// 更新汇总条目的状态为已生成通知单
		examEummaryMapper.updateExamSummayStatus(param);// 0-- 未生成； 1-- 已生成
	}

	@Override
	public void genderStationNotice(StationExamNoticeResult stationExamNotice) {
		examEummaryMapper.genderStationNotice(stationExamNotice);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", 1);
		param.put("id", stationExamNotice.getSummaryId());
		examEummaryMapper.updateExamSummayStatus(param);// 0-- 未生成； 1-- 已生成
	}

	@Override
	public boolean hasExportSalarys(Integer noticeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StationExamNoticeResult checkIsJyzNoticeExist(ExamSummary examSummary) {
		return examEummaryMapper.checkIsJyzNoticeExist(examSummary);
	}

	@Override
	public DepartmentExamNoticeResult checkIsBmNoticeExist(ExamSummary examSummary) {
		// TODO Auto-generated method stub
		return examEummaryMapper.checkIsBmNoticeExist(examSummary);
	}

	@Override
	public void exportExamSummary(Map<String, Object> params, HttpServletResponse response) throws Exception {
		List list = new ArrayList();
		list = examEummaryMapper.exportExamSummary(params);
		System.out.println(list);////////////////////////
		IExcelConfig excelConfig = ExcelConfigJson.getInstance();
		ExcelConfigVo excelConfigVo = excelConfig.getExcelConfigById("exam_summary_info_excel");
		ExcelExport export = new ExcelExport();
		export.exportExcelFromBeanList(response, excelConfigVo.getFile_name(), excelConfigVo.getShows(),
				excelConfigVo.getFields(), excelConfigVo.getCell_type(), list);
	}

}
