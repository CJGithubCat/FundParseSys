package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.dao.SalaryMapper;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.Salary;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.service.IBmkhtzdService;
import com.zsh.labouCapital.service.IJyzkhtzdService;
import com.zsh.labouCapital.service.ISalaryService;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.Page;
import com.zsh.labouCapital.util.pagination.PageBean;
import com.zsh.labouCapital.util.pagination.PageUtils;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

@Service
public class SalaryServiceImpl extends BaseServiceImpl<Salary> implements ISalaryService {

	@Autowired
	private SalaryMapper salaryMapper;

	@Autowired
	private IJyzkhtzdService iJyzkhtzdService;

	@Autowired
	private IBmkhtzdService iBmkhtzdService;

	/**
	 * 工资明细分析查询
	 */
	@Override
	public HashMap<String, Object> querySalaryDetailByPage(String tzdh, String dw, String jyzName, PageBean pageBean,
			int status, int noticeId, String companyPath, HttpServletRequest request) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ArrayList<Salary> list = new ArrayList<Salary>();
		long count = 0;
		try {
			Integer companyType = Integer
					.parseInt((String) request.getSession().getAttribute(SystemConst.ACCOUNT_COMPANY_TYPE));
			// 获取分页对象
			Page page = PageUtils.getPage(pageBean);
			if (companyType == 0) {// 市级机构
				if (!StringUtils.isEmpty(dw) && StringUtils.isEmpty(jyzName)) { // 只搜索部门名称
					HashMap<String, Object> tempParam = new HashMap<String, Object>();
					tempParam.put("companyPath", companyPath);
					tempParam.put("dw", dw);
					List<Integer> reComIdList = salaryMapper.queryCompanyInfo(tempParam);

					if (reComIdList != null && reComIdList.size() > 0) {
						for(int i = 0; i < reComIdList.size(); i++){
							Integer companyId = reComIdList.get(i);
							if(companyId != null){
								HashMap<String, Object> params = new HashMap<String, Object>();
								if (pageBean.getPage() == 1) {
									params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
								} else {
									params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
								}
								params.put("limit", pageBean.getPagesize() * pageBean.getPage());
								params.put("tzdh", tzdh);
								params.put("status", status);
								params.put("companyPath", companyPath);
								params.put("dw", dw);
								params.put("jyzName", jyzName);
								params.put("tzdh", tzdh);
								params.put("companyId",companyId);
								ArrayList<Salary> tempList = salaryMapper.queryDepartmentSalaryInfo(params,
										new RowBounds(page.getOffset(), page.getNumPerPage()));
								long tempcount = salaryMapper.queryDepartmentSalaryCount(params);
								
								list.addAll(tempList);
								count += tempcount;
							}
						}
					}

				} else if (StringUtils.isEmpty(dw) && !StringUtils.isEmpty(jyzName)) {// 查询加油站工资信息
					HashMap<String, Object> tempParam = new HashMap<String, Object>();
					tempParam.put("companyPath", companyPath);
					tempParam.put("jyzName", jyzName);

					List<Integer> reComIdList = salaryMapper.queryCompanyInfo(tempParam);
					if (reComIdList != null && reComIdList.size() > 0) {
						for(int i=0; i < reComIdList.size(); i++){
							Integer companyId = reComIdList.get(i);
							if(companyId != null){
								HashMap<String, Object> params = new HashMap<String, Object>();
								if (pageBean.getPage() == 1) {
									params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
								} else {
									params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
								}
								params.put("limit", pageBean.getPagesize() * pageBean.getPage());
								params.put("tzdh", tzdh);
								params.put("status", status);
								params.put("companyPath", companyPath);
								params.put("dw", dw);
								params.put("jyzName", jyzName);
								params.put("tzdh", tzdh);
								params.put("companyId", companyId);

								ArrayList<Salary> tempList = salaryMapper.querySatationSalaryInfo(params,
										new RowBounds(page.getOffset(), page.getNumPerPage()));
								long tempCount = salaryMapper.querySatationSalaryCount(params);
								
								list.addAll(tempList);
								count += tempCount;
							}
						}
					}
				} else if (!StringUtils.isEmpty(dw) && !StringUtils.isEmpty(jyzName)) {// 搜索部门和其子机构的加油站的工资信息
					HashMap<String, Object> tempParam = new HashMap<String, Object>();
					tempParam.put("companyPath", companyPath);
					tempParam.put("dw", dw);
					tempParam.put("jyzName", jyzName);
					Map<String, Object> reMap = salaryMapper.queryFsRelationCompany(tempParam);// 具有父子关系

					if (reMap != null) {
						HashMap<String, Object> params = new HashMap<String, Object>();
						if (pageBean.getPage() == 1) {
							params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
						} else {
							params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
						}
						params.put("limit", pageBean.getPagesize() * pageBean.getPage());
						params.put("tzdh", tzdh);
						params.put("status", status);
						params.put("companyPath", companyPath);
						params.put("dw", dw);
						params.put("jyzName", jyzName);
						params.put("tzdh", tzdh);
						params.put("companyId", reMap.get("companyId"));
						list = salaryMapper.querySatationSalaryInfo(params,
								new RowBounds(page.getOffset(), page.getNumPerPage()));
						count = salaryMapper.querySatationSalaryCount(params);
					}
				} else if (StringUtils.isEmpty(dw) && StringUtils.isEmpty(jyzName)) {// 搜索部门和其子机构的加油站的工资信息
					HashMap<String, Object> params = new HashMap<String, Object>();
					if (pageBean.getPage() == 1) {
						params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
					} else {
						params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
					}
					params.put("limit", pageBean.getPagesize() * pageBean.getPage());
					params.put("tzdh", tzdh);
					params.put("status", status);
					params.put("companyPath", companyPath);
					params.put("dw", dw);
					params.put("jyzName", jyzName);
					params.put("tzdh", tzdh);

					list = salaryMapper.querySalaryPage(params, new RowBounds(page.getOffset(), page.getNumPerPage()));
					count = salaryMapper.querySalaryPageCount(params);
				}
			} else if (companyType == 1 || companyType == 2 || companyType == 3 || companyType == 4) {// 部门
				if (!StringUtils.isEmpty(dw) && StringUtils.isEmpty(jyzName)) { // 只搜索部门名称
					Company tempCompany = (Company) request.getSession().getAttribute(SystemConst.ACCOUNT_AGENCY);
					String dwName = tempCompany.getCompanyName(); 
					if (dw.equals(dwName)) {// 搜索的是自己
						HashMap<String, Object> params = new HashMap<String, Object>();
						if (pageBean.getPage() == 1) {
							params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
						} else {
							params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
						}
						params.put("limit", pageBean.getPagesize() * pageBean.getPage());
						params.put("tzdh", tzdh);
						params.put("status", status);
						params.put("companyPath", companyPath);
						params.put("dw", dw);
						params.put("jyzName", jyzName);
						params.put("tzdh", tzdh);
						params.put("companyId", request.getSession().getAttribute(SystemConst.ACCOUNT_COMPANYID));

						list = salaryMapper.queryDepartmentSalaryInfo(params,
								new RowBounds(page.getOffset(), page.getNumPerPage()));
						count = salaryMapper.queryDepartmentSalaryCount(params);
					}
				} else if (StringUtils.isEmpty(dw) && !StringUtils.isEmpty(jyzName)) { // 只搜加油站名称
					HashMap<String, Object> tempParam = new HashMap<String, Object>();
					tempParam.put("companyPath", companyPath);
					tempParam.put("jyzName", jyzName);

					List<Integer> reComIdList = salaryMapper.queryCompanyInfo(tempParam);

					if (reComIdList != null && reComIdList.size() > 0) {
						for(int i=0; i < reComIdList.size(); i++){
							Integer companyId = reComIdList.get(i);
							if(companyId != null){
								HashMap<String, Object> params = new HashMap<String, Object>();
								if (pageBean.getPage() == 1) {
									params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
								} else {
									params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
								}
								params.put("limit", pageBean.getPagesize() * pageBean.getPage());
								params.put("tzdh", tzdh);
								params.put("status", status);
								params.put("companyPath", companyPath);
								params.put("dw", dw);
								params.put("jyzName", jyzName);
								params.put("tzdh", tzdh);
								params.put("companyId", companyId);

								ArrayList<Salary> tempList = salaryMapper.querySatationSalaryInfo(params,
										new RowBounds(page.getOffset(), page.getNumPerPage()));
								long tempCount = salaryMapper.querySatationSalaryCount(params);
								
								list.addAll(tempList);
								count += tempCount;
							}
						}
					}
				} else if (!StringUtils.isEmpty(dw) && !StringUtils.isEmpty(jyzName)) {// 搜索部门和加油站的工资信息
					HashMap<String, Object> tempParam = new HashMap<String, Object>();
					tempParam.put("companyPath", companyPath);
					tempParam.put("dw", dw);
					tempParam.put("jyzName", jyzName);
					Map<String, Object> reMap = salaryMapper.queryFsRelationCompany(tempParam);// 具有父子关系

					if (reMap != null) {
						HashMap<String, Object> params = new HashMap<String, Object>();
						if (pageBean.getPage() == 1) {
							params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
						} else {
							params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
						}
						params.put("limit", pageBean.getPagesize() * pageBean.getPage());
						params.put("tzdh", tzdh);
						params.put("status", status);
						params.put("companyPath", companyPath);
						params.put("dw", dw);
						params.put("jyzName", jyzName);
						params.put("tzdh", tzdh);
						params.put("companyId", reMap.get("companyId"));
						list = salaryMapper.querySatationSalaryInfo(params,
								new RowBounds(page.getOffset(), page.getNumPerPage()));
						count = salaryMapper.querySatationSalaryCount(params);
					}
				} else if (StringUtils.isEmpty(dw) && StringUtils.isEmpty(jyzName)) {// 查询本机构下所有的工资单信息
					HashMap<String, Object> params = new HashMap<String, Object>();
					if (pageBean.getPage() == 1) {
						params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
					} else {
						params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
					}
					params.put("limit", pageBean.getPagesize() * pageBean.getPage());
					params.put("tzdh", tzdh);
					params.put("status", status);
					params.put("companyPath", companyPath);
					params.put("dw", dw);
					params.put("jyzName", jyzName);
					params.put("tzdh", tzdh);

					list = salaryMapper.querySalaryPage(params, new RowBounds(page.getOffset(), page.getNumPerPage()));
					count = salaryMapper.querySalaryPageCount(params);
				}
			} else {// 加油站
				HashMap<String, Object> params = new HashMap<String, Object>();
				if (pageBean.getPage() == 1) {
					params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
				} else {
					params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
				}
				params.put("limit", pageBean.getPagesize() * pageBean.getPage());
				params.put("tzdh", tzdh);
				params.put("status", status);
				params.put("companyPath", companyPath);
				params.put("dw", dw);
				params.put("jyzName", jyzName);
				params.put("tzdh", tzdh);

				list = salaryMapper.querySalaryPage(params, new RowBounds(page.getOffset(), page.getNumPerPage()));
				count = salaryMapper.querySalaryPageCount(params);
			}
			hm.put("list", list);
			hm.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;

	}

	@Override
	@Transactional
	public void addSalaryDetial(List<Salary> datas, String noticeId, int salaryType) throws Exception {
		// 先删除该notice_id对应的工资单明细
		salaryMapper.removeById(noticeId);

		String noticeNo = "";
		if (salaryType == 1) {// 部门
			// 查询通知单信息
			DepartmentExamNoticeResult tempNotice = iBmkhtzdService.findById(noticeId);
			noticeNo = tempNotice.getNoticeNo();
			for (Salary salary : datas) {
				salary.setNoticeNo(noticeNo);
				salary.setYearMonthDate(tempNotice.getYearMonthDate());
				salaryMapper.addSalaryDetial(salary);
			}

			DepartmentExamNoticeResult departmentExam = new DepartmentExamNoticeResult();
			departmentExam.setId(Integer.parseInt(noticeId));
			departmentExam.setStatus(3);
			departmentExam.setSalaryStatus(1);
			iBmkhtzdService.edit(departmentExam);// 更salary_status状态为未提交
		} else {// 加油站
			StationExamNoticeResult tempNotcie = iJyzkhtzdService.findById(noticeId);
			noticeNo = tempNotcie.getNoticeNo();
			for (Salary salary : datas) {
				salary.setNoticeNo(noticeNo);
				salaryMapper.addSalaryDetial(salary);
			}
			StationExamNoticeResult stationExam = new StationExamNoticeResult();
			stationExam.setId(Integer.parseInt(noticeId));
			stationExam.setStatus(5);
			stationExam.setSalaryStatus(1);
			noticeNo = stationExam.getNoticeNo();
			iJyzkhtzdService.edit(stationExam);
		}
	}

	@Override
	public void batchEditSalary(ArrayList<Salary> readSarlaryList, int salaryType, int noticeId) throws Exception {
		for (Salary salary : readSarlaryList) {
			salaryMapper.batchEditSalary(salary);
		}
		// 更新通知单中的状态信息
		if (salaryType == 1) {// 部门
			DepartmentExamNoticeResult departmentExam = new DepartmentExamNoticeResult();
			departmentExam.setId(noticeId);
			departmentExam.setStatus(-1);
			departmentExam.setSalaryStatus(6);
			iBmkhtzdService.edit(departmentExam);// 更salary_status状态为未提交
		} else {// 加油站
			StationExamNoticeResult stationExam = new StationExamNoticeResult();
			stationExam.setId(noticeId);
			stationExam.setStatus(-1);
			stationExam.setSalaryStatus(6);
			iJyzkhtzdService.edit(stationExam);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void exportSalary(Map<String, Object> params, HttpServletResponse response) throws Exception {
		try {
			List list = new ArrayList();
			list = salaryMapper.exportSalary(params);
			IExcelConfig excelConfig = ExcelConfigJson.getInstance();
			ExcelConfigVo excelConfigVo = excelConfig.getExcelConfigById("salaryDetail_MES_info_excel");
			ExcelExport export = new ExcelExport();
			export.exportExcelFromBeanList(response, excelConfigVo.getFile_name(), excelConfigVo.getShows(),
					excelConfigVo.getFields(), excelConfigVo.getCell_type(), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getNoticeInfoById(Integer noticeId, Integer salaryType) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		try {
			if (salaryType == 0) {// 加油站
				StationExamNoticeResult stationNoticeRe = iJyzkhtzdService.findById(noticeId + "");
				reMap.put("yearMonthDate", stationNoticeRe.getYearMonthDate());
				reMap.put("dw", stationNoticeRe.getJyz());
			} else {// 部门
				DepartmentExamNoticeResult dNoticeNoticeRe = iBmkhtzdService.findById(noticeId + "");
				reMap.put("yearMonthDate", dNoticeNoticeRe.getYearMonthDate());
				reMap.put("dw", dNoticeNoticeRe.getBmmc());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reMap;
	}
}
