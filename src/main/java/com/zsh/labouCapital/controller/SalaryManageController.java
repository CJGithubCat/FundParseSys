
package com.zsh.labouCapital.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.Salary;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.service.IBmkhtzdService;
import com.zsh.labouCapital.service.ICompanyService;
import com.zsh.labouCapital.service.IJyzkhtzdService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.ISalaryService;
import com.zsh.labouCapital.util.excel.ReadExcelUtil;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

/**
 * 
 * @author 工资管理
 */
@Controller
@RequestMapping(value = { "/salary" })
public class SalaryManageController extends BaseController {

	@Autowired
	private ISalaryService iSalaryService;
	@Autowired
	private ICompanyService iCompanyService;
	@Autowired
	private IBmkhtzdService bmkhtzdService;
	@Autowired
	private IJyzkhtzdService iJyzkhtzdService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 分页查询工资明细
	 * 
	 * @param userManageView
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/findAllSalaryDetailByPage" })
	public ReturnValue findAllSalaryDetailByPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ReturnValue rv = new ReturnValue();
		String companyPath = request.getParameter("companyPath");
		String tzdh = "";
		if (null != request.getParameter("tzdh")) {
			tzdh = request.getParameter("tzdh");
		}
		int status = -1;
		if (null != request.getParameter("status")) {
			status = Integer.parseInt(request.getParameter("status"));
		}
		String dw = null;
		if (null != request.getParameter("dw")) {
			dw = request.getParameter("dw");
		}
		String jyzName = null;
		if (null != request.getParameter("jyzName")) {
			jyzName = request.getParameter("jyzName");
		}
		int noticeId = 0;
		if (null != request.getParameter("noticeId")) {
			noticeId = Integer.parseInt(request.getParameter("noticeId"));
		}

		try {
			PageBean pageBean = parsePageParm(request);
			if (StringUtils.isBlank(companyPath)) {
				Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
				companyPath = company.getCompanyPath();
			}
			HashMap<String, Object> hmap = iSalaryService.querySalaryDetailByPage(tzdh, dw, jyzName, pageBean, status,
					noticeId, companyPath,request);
			rv.setDatas(hmap);
			rv.setMessage("查询成功!");
			rv.setSuccess(true);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10501, ip, "分页查询工资明细");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setMessage("错误：" + e.getMessage());
			rv.setSuccess(false);
			rv.setDatas(null);
		}
		return rv;
	}

	/**
	 * @Name: uploadSalaryExcel @Description:上传工资表单 @param @param
	 * request @param @param response @param @return 参数 @return ReturnValue
	 * 返回类型 @throws
	 */
	@RequestMapping("/uploadSalaryExcel")
	@ResponseBody
	public ReturnValue uploadSalaryExcel(HttpServletRequest request, HttpServletResponse response) {
		String companyIdStr = request.getParameter("companyId");
		String noticeIdStr = request.getParameter("noticeId");
		String salaryTypeStr = request.getParameter("salaryType");
		Integer companyId = null;
		Integer noticeId = null;
		Integer salaryType = null;

		ReturnValue rv = new ReturnValue();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取前台传值
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String configPath = File.separator + "salary" + File.separator;
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		ctxPath += configPath;
		// 创建文件夹
		try {
			noticeId = Integer.parseInt(noticeIdStr);
			companyId = Integer.parseInt(companyIdStr);
			salaryType = Integer.parseInt(salaryTypeStr);
			File file = new File(ctxPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = null;
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
				fileName = mf.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				String finalFileName = fileName.substring(0, fileName.lastIndexOf("."));
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = finalFileName + df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
						+ fileExt;
				File uploadFile = new File(ctxPath + newFileName);
				String path = uploadFile.getPath();
				try {
					FileUtils.copyInputStreamToFile(mf.getInputStream(), uploadFile);
					// FileCopyUtils.copy(mf.getBytes(), uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
					rv.setSuccess(false);
					rv.setMessage("excel文件上传失败,请联系客服人员解决!");
					return rv;
				}
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
				response.setCharacterEncoding("UTF-8");

				Company nowCompany = (Company) request.getSession().getAttribute(SystemConst.ACCOUNT_AGENCY);

				// 是否是市级部门，是的话，就是市级部门导入具体的工资明细；不是的话，就是本加油站或者部门导入本机构的工资明细
				int nowCompanyType = nowCompany.getCompanyType();
				if (nowCompanyType == 0) {// 市级审核通过，导入工资明细信息
					StationExamNoticeResult stationExamNoticeResult = null;
					DepartmentExamNoticeResult departmentExamNoticeResult = null;
					if (salaryType == 0) {// 加油站通知单
						stationExamNoticeResult = iJyzkhtzdService.findById(noticeId + "");
						if (stationExamNoticeResult == null || stationExamNoticeResult.getSalaryStatus() != 5) {
							rv.setSuccess(false);
							rv.setMessage("异常:工资通知单状态为" + stationExamNoticeResult.getSalaryStatus() + "，不可导入工资明细!");
							return rv;
						}
						/*
						 * // (2)校验是否该通知单已经导入工资明细，但是未走完流程：salary_status不为-1；
						 * Salary salary = new Salary();
						 * salary.setSalaryStatus(5);// 市级公司已经审核通过
						 * salary.setNoticeId(noticeId); List<Salary>
						 * salarieList = iSalaryService.findList(salary); if
						 * (salarieList == null || salarieList.size() == 0) {
						 * rv.setSuccess(false);
						 * rv.setMessage("异常:下级单位未导入工资明细，不可导入工资明细!"); return rv;
						 * }
						 */
					} else {// 部门通知单
						departmentExamNoticeResult = bmkhtzdService.findById(noticeId + "");
						if (departmentExamNoticeResult == null || departmentExamNoticeResult.getSalaryStatus() != 5) {
							rv.setSuccess(false);
							rv.setMessage("异常:工资通知单状态为：" + departmentExamNoticeResult.getSalaryStatus() + "，不可导入工资明细!");
							return rv;
						}
						/*
						 * // (2)校验是否该通知单已经导入工资明细，但是未走完流程：salary_status不为-1；
						 * Salary salary = new Salary();
						 * salary.setSalaryStatus(5);// 市级公司已经审核通过
						 * salary.setNoticeId(noticeId); List<Salary>
						 * salarieList = iSalaryService.findList(salary); if
						 * (salarieList == null || salarieList.size() == 0) {
						 * rv.setSuccess(false);
						 * rv.setMessage("异常:下级单位未导入工资明细，不可导入工资明细!"); return rv;
						 * }
						 */
					}

					// 校验下级单位是否已经导入工资明细，但是未走完流程：
					Salary salary = new Salary();
					salary.setSalaryStatus(5);// 市级公司已经审核通过
					salary.setNoticeId(noticeId);
					List<Salary> salarieList = iSalaryService.findList(salary);
					if (salarieList == null || salarieList.size() == 0) {
						rv.setSuccess(false);
						rv.setMessage("异常:下级单位未导入工资明细，不可导入工资明细!");
						return rv;
					}

					// 读取excel表单中的信息
					Map<String, Object> reMap = ReadExcelUtil.sjbmExportSalaryXls(path, companyId, noticeId, salaryType,
							iCompanyService, iSalaryService);
					if (!(boolean) reMap.get("success")) {
						rv.setSuccess(false);
						rv.setMessage(reMap.get("reason").toString());
						return rv;
					} else {// 读取成功，进行数据校验
						List<Salary> readSarlaryList = (List<Salary>) reMap.get("data");
						if (readSarlaryList == null || readSarlaryList.size() == 0) {
							rv.setSuccess(false);
							rv.setMessage("数据表信息为空,导入失败!");
							return rv;
						}
						// 对比数量
						if (salarieList.size() != readSarlaryList.size()) {
							rv.setSuccess(false);
							rv.setMessage("导入的工资明细数量和本同工资通知单中的人员数量不一致，导入失败!");
							return rv;
						}

						// 数据都一一对应
						ArrayList<Salary> resultList = new ArrayList<Salary>();
						for (int i = 0; i < salarieList.size(); i++) {
							Salary tempSalary = salarieList.get(i);
							for (int j = 0; j < readSarlaryList.size(); j++) {
								Salary readTempSalary = readSarlaryList.get(j);
								if (readTempSalary.getSfzh2().equals(tempSalary.getSfzh())) {// 身份证号一致
									readTempSalary.setId(tempSalary.getId());
									readTempSalary.setYfhj(tempSalary.getYfhj());
									resultList.add(readTempSalary);
								}
							}
						}
						// 校验数据
						String errSfzh2 = "";
						for (int j = 0; j < resultList.size(); j++) {
							Salary readTempSalary = resultList.get(j);
							if (readTempSalary.getId() <= 0) {// id不对
								errSfzh2 += readTempSalary.getSfzh2() + ",";
							}
							// 计算个人所得税
							Map<String, Double> params = new HashMap<String, Double>();
							params.put("yfhj", readTempSalary.getYfhj());
							params.put("gjj", readTempSalary.getGjj());
							params.put("ylj", readTempSalary.getYlj());
							params.put("syj", readTempSalary.getSyj());
							params.put("ybj", readTempSalary.getYbj());
							params.put("nj", readTempSalary.getNj());
							params.put("base", (double) 3500);
							double personTax = calPersonTax(params);
							readTempSalary.setGrsds(personTax);
						}

						if (errSfzh2 != null && !errSfzh2.equals("")) {// 有未对上数据，返回错误
							rv.setSuccess(false);
							rv.setMessage("Excel表格中 身份证号为：" + errSfzh2 + " 的人员与改通知单中已经导入的不匹配，请检查后导入!");
							return rv;
						}
						// 校验都通过之后,就将导入数据更新到salary表中
						iSalaryService.batchEditSalary(resultList, salaryType, noticeId);
					}
				} else {// 部门或者加油站
					// 对比传入的companyId和当前的companyId是否一致
					if (companyId.intValue() != nowCompany.getCompanyId().intValue()) {
						rv.setSuccess(false);
						rv.setMessage("通知单机构和当前机构不一致，导入失败!");
						return rv;
					}

					// 1.查看该通知单是否是可以导入的
					// (1)查看状态是否是3【部门已经确认接收】或者5【加油站已经确认接收】
					StationExamNoticeResult stationExamNoticeResult = null;
					DepartmentExamNoticeResult departmentExamNoticeResult = null;
					if (salaryType == 0) {// 加油站通知单
						stationExamNoticeResult = iJyzkhtzdService.findById(noticeId + "");
						if (stationExamNoticeResult == null || stationExamNoticeResult.getStatus() != 5) {
							rv.setSuccess(false);
							rv.setMessage("异常:通知单状态为" + stationExamNoticeResult.getStatus() + "，不可导入工资明细!");
							return rv;
						}
						// (2)校验是否该通知单已经导入工资明细，但是未走完流程：salary_status不为-1；
						Salary salary = new Salary();
						salary.setNoticeId(noticeId);
						List<Salary> salarieList = iSalaryService.findList(salary);
						if (salarieList != null && salarieList.size() > 0) {
							if (stationExamNoticeResult.getSalaryStatus() != -1) {// 只有为审核不通过时候才可以进行重新插入
								rv.setSuccess(false);
								rv.setMessage("异常:工资通知单已经导入工资明细，并且状态为" + stationExamNoticeResult.getSalaryStatus()
										+ "，不可重复导入工资明细!");
								return rv;
							}
						}
					} else {// 部门通知单
						departmentExamNoticeResult = bmkhtzdService.findById(noticeId + "");
						if (departmentExamNoticeResult == null || departmentExamNoticeResult.getStatus() != 3) {
							rv.setSuccess(false);
							rv.setMessage("异常:通知单状态为：" + departmentExamNoticeResult.getStatus() + "，不可导入工资明细!");
							return rv;
						}
						// (2)校验是否该通知单已经导入工资明细，但是未走完流程：salary_status不为-1；
						Salary salary = new Salary();
						salary.setNoticeId(noticeId);
						List<Salary> salarieList = iSalaryService.findList(salary);
						if (salarieList != null && salarieList.size() > 0) {
							if (departmentExamNoticeResult.getSalaryStatus() != -1) {// 只有为审核不通过时候才可以进行重新插入
								rv.setSuccess(false);
								rv.setMessage("异常:工资通知单已经导入工资明细，并且状态为" + departmentExamNoticeResult.getSalaryStatus()
										+ "，不可重复导入工资明细!");
								return rv;
							}
						}
					}
					// 1.将文件中的数据读入到内存
					Map<String, Object> map = ReadExcelUtil.readCheckSalaryXls(path, companyId, noticeId, salaryType,
							iCompanyService, iSalaryService);
					if (!(boolean) map.get("success")) {
						rv.setSuccess(false);
						rv.setMessage(map.get("reason").toString());
						return rv;
					} else { // 将数据插入到数据库
						// 对比excel中的应发合计与通知单中的合计是否一致
						@SuppressWarnings("unchecked")
						List<Salary> datas = (List<Salary>) map.get("data");
						// double elxTotal = (double) map.get("total");//
						// excle文件中的应发合计
						if (salaryType == 0) {// 加油站
							if (stationExamNoticeResult == null) {
								stationExamNoticeResult = iJyzkhtzdService.findById(noticeIdStr);
							}
							// 校验加油站名称是否和工资单上的一致
							String jyzName = datas.get(0).getDw();
							if (jyzName == null || !jyzName.equals(nowCompany.getCompanyName())) {
								rv.setDatas(null);
								rv.setSuccess(false);
								rv.setMessage("ERR：Excel中的加油站名称和当前机构名称不一致，导入失败！");
								return rv;
							}
							// 校验通知单上的总额和导入的excel中应发的总额一致
							/*
							 * if (stationExamNoticeResult.getTotal() !=
							 * elxTotal) { rv.setDatas(null);
							 * rv.setSuccess(false);
							 * rv.setMessage("ERR：考核通知单中的合计与excel中合计不相等，导入失败！");
							 * return rv; }
							 */
						} else {// 部门
							if (departmentExamNoticeResult == null) {
								departmentExamNoticeResult = bmkhtzdService.findById(noticeIdStr);
							}
							// 校验部门名称是否和工资单上的一致
							String dwName = datas.get(0).getDw();
							if (!dwName.equals(nowCompany.getCompanyName())) {
								rv.setDatas(null);
								rv.setSuccess(false);
								rv.setMessage("ERR：Excel中的机构名称和当前机构名称不一致，导入失败！");
								return rv;
							}
							// 校验通知单上的总额和导入的excel中应发的总额一致
							/*
							 * if (departmentExamNoticeResult.getTotal() !=
							 * elxTotal) { rv.setDatas(null);
							 * rv.setSuccess(false);
							 * rv.setMessage("ERR：考核通知单中的合计与excel中合计不相等，导入失败！");
							 * return rv; }
							 */
						}
						// 校验成功的数据写入到数据库
						iSalaryService.addSalaryDetial(datas, noticeIdStr, salaryType);
					}
				}
			}
			rv.setDatas(null);
			rv.setSuccess(true);
			rv.setMessage("工资明细导入成功！");
		} catch (Exception e) {
			rv.setDatas(null);
			rv.setSuccess(false);
			// log.info("[uploadExcel]:err:{}", e);
			rv.setMessage("ERR:系统异常，请联系管理员!");
			e.printStackTrace();
			return rv;
		}
		return rv;
	}

	/**
	 * @Name: calPersonTax @Description:计算个人所得税
	 * 公式：=ROUND(MAX((Q9-SUM(R9:V9)-3500)*{3,10,20,25,30,35,45}%-{0,105,555,1005,2755,5505,13505},0),2)
	 * 应纳税额=(工资薪金所得-“五险一金”-扣除数)×适用税率-速算扣除数 @param @return 参数 @return double
	 * 返回类型 @throws
	 */
	public double calPersonTax(Map<String, Double> parmas) {
		double personTax = 0;

		try {
			double Q = parmas.get("yfhj");
			// r-v
			double R = parmas.get("gjj");
			double S = parmas.get("ylj");
			double T = parmas.get("syj");
			double U = parmas.get("ybj");
			double V = parmas.get("nj");
			// base
			double base = parmas.get("base");
			// 税率
			double percent = 0;
			double quickDeduct = 0;

			// 计算
			double taxNum = (Q - (R + S + T + U + V) - base);
			if (taxNum <= 0) {
				personTax = 0;
			} else if (taxNum > 0 && taxNum <= 1500) {
				percent = 0.03;
				quickDeduct = 0;
			} else if (taxNum > 1500 && taxNum <= 4500) {
				percent = 0.1;
				quickDeduct = 105;
			} else if (taxNum > 4500 && taxNum <= 9000) {
				percent = 0.2;
				quickDeduct = 555;
			} else if (taxNum > 9000 && taxNum <= 35000) {
				percent = 0.25;
				quickDeduct = 1005;
			} else if (taxNum > 35000 && taxNum <= 55000) {
				percent = 30;
				quickDeduct = 2755;
			} else if (taxNum > 55000 && taxNum <= 80000) {
				percent = 0.35;
				quickDeduct = 5505;
			} else if (taxNum > 80000) {
				percent = 0.45;
				quickDeduct = 13505;
			}

			personTax = taxNum * percent - quickDeduct;
			DecimalFormat df = new DecimalFormat("######0.00");
			return Double.parseDouble(df.format(personTax));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personTax;
	}

	/**
	 * 导出工资明细
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportSalary")
	@ResponseBody
	public void exportSalary(HttpServletRequest request, HttpServletResponse response) {
		String dw = null;
		String tzdh = null;
		Map<String, Object> params = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session == null) {
			return;
		}
		try {
			String companyPath = request.getParameter("companyPath");
			if (StringUtils.isBlank(companyPath)) {
				Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
				companyPath = company.getCompanyPath();
			}
			dw = request.getParameter("dw");
			tzdh = request.getParameter("tzdh");
			params.put("companyPath", companyPath);
			params.put("dw", dw);
			params.put("tzdh", tzdh);
			iSalaryService.exportSalary(params, response);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10501, ip, "工资明细报表导出");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
