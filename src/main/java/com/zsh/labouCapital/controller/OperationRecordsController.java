package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.OperationLog;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.pojo.TWgAgency;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.util.StringUtils;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

/**
 * 操作日志统计
 */
@RestController
@RequestMapping(value = "/logger")
public class OperationRecordsController extends BaseController {
	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：分页查询操作日志信息；
	 */
	@RequestMapping("/queryOperationRecordsInfoPage")
	@ResponseBody
	public ReturnValue queryOperationRecordsInfoPage(HttpServletRequest request, String attributesPath) {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		Map<String, Object> params = new HashMap<String, Object>();
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String desc = request.getParameter("desc");
		String companyPath = request.getParameter("companyPath");
		if (desc == null) {
			desc = "";
		}
		if (StringUtils.isBlank(companyPath)) {
			Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
			companyPath = company.getCompanyPath();
		}
		try {
			PageBean pageBean = parsePageParm(request);
			params.put("beginDate", beginDate);
			params.put("endDate", endDate);
			params.put("desc", desc);
			params.put("pageBean", pageBean);
			params.put("companyPath", companyPath);
			HashMap<String, Object> hm = iloggerService.queryOperationRecordsInfoPage(params);
			rv.setDatas(hm);
			rv.setSuccess(true);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10602, ip, "查询日志信息");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage("查询异常.原因：" + e.getMessage());
			rv.setDatas(null);
			rv.setErrorCode(-11);
		}
		return rv;
	}

	/**
	 * 函数功能：导出操作日志信息excel；
	 */
	@RequestMapping("/exportOperationRecordsInfo")
	@ResponseBody
	public void exportOperationRecordsInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		HashMap<String, Object> params = new HashMap<String, Object>();
		try {
			String companyPath = request.getParameter("companyPath");
			if (StringUtils.isBlank(companyPath)) {
				Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
				companyPath = company.getCompanyPath();
			}
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			String desc = request.getParameter("desc");
			params.put("beginDate", beginDate);
			params.put("endDate", endDate);
			params.put("description", desc);
			params.put("companyPath", companyPath);
			ArrayList<OperationLog> list = iloggerService.exportOperationRecordsInfo(params);
			// 导出
			IExcelConfig excelConfig = ExcelConfigJson.getInstance();
			ExcelConfigVo excelConfigVo = excelConfig.getExcelConfigById("operation_records_info_excel");
			ExcelExport export = new ExcelExport();
			export.exportExcelFromBeanList(response, excelConfigVo.getFile_name(), excelConfigVo.getShows(),
					excelConfigVo.getFields(), excelConfigVo.getCell_type(), list);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10602, ip, "导出日志信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
