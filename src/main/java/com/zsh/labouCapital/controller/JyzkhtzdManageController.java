
package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.service.IJyzkhtzdService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

import net.sf.json.JSONObject;

/**
 * 
 * @author 加油站考核通知单管理
 */
@Controller
@RequestMapping(value = { "/jyzkhtzdmanage" })
public class JyzkhtzdManageController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(JyzkhtzdManageController.class);

	@Autowired
	private IJyzkhtzdService iJyzkhtzdService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 分页查询加油站考核通知单
	 * 
	 * @param userManageView
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/queryJyzkhtzd" }, method = { RequestMethod.POST })
	public ReturnValue queryJyzkhtzd(HttpServletRequest request) {
		ReturnValue rspData = new ReturnValue();
		PageBean pageBean = parsePageParm(request);
		HttpSession session = request.getSession(false);
		String companyPath = request.getParameter("companyPath");
		String yearMonthDate = request.getParameter("yearMonthDate");
		String jyz = request.getParameter("jyz");
		String status = request.getParameter("status");
		String salaryStatus = request.getParameter("salaryStatus");
		if (StringUtils.isBlank(companyPath)) {
			Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
			companyPath = company.getCompanyPath();
		}
		try {
			Map<String, Object> jyztzdMap = iJyzkhtzdService.findJyzkhtzdPage(yearMonthDate, jyz, status, salaryStatus,
					pageBean, companyPath);
			rspData.setDatas(jyztzdMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rspData;
	}

	/**
	 * @Name: queryJyzGzNoticePage @Description: 分页查加油站工资通知单 @param @param
	 * request @param @return 参数 @return ReturnValue 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = { "/queryJyzGzNoticePage" }, method = { RequestMethod.POST })
	public ReturnValue queryJyzGzNoticePage(HttpServletRequest request) {
		ReturnValue rspData = new ReturnValue();
		PageBean pageBean = parsePageParm(request);
		HttpSession session = request.getSession(false);
		String companyPath = request.getParameter("companyPath");
		String yearMonthDate = request.getParameter("yearMonthDate");
		String jyz = request.getParameter("jyz");
		String status = request.getParameter("status");
		String salaryStatus = request.getParameter("salaryStatus");
		if (StringUtils.isBlank(companyPath)) {
			Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
			companyPath = company.getCompanyPath();
		}
		try {
			Map<String, Object> jyztzdMap = iJyzkhtzdService.findJyzkhtzdPage(yearMonthDate, jyz, status, salaryStatus,
					pageBean, companyPath);
			rspData.setDatas(jyztzdMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rspData;
	}

	/**
	 * 加油站考核通知单管理报表导出
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportJyzkhtzdmanage")
	@ResponseBody
	public void exportJyzkhtzdmanage(HttpServletRequest request, HttpServletResponse response) {
		String status = null;
		String jyz = null;
		String yearMonthDate = null;
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
			status = request.getParameter("status");
			jyz = request.getParameter("jyz");
			jyz = java.net.URLDecoder.decode(jyz, "UTF-8");
			yearMonthDate = request.getParameter("yearMonthDate");
			yearMonthDate = java.net.URLDecoder.decode(yearMonthDate, "UTF-8");
			params.put("companyPath", companyPath);
			params.put("status", status);
			params.put("jyz", jyz);
			params.put("yearMonthDate", yearMonthDate);
			iJyzkhtzdService.exportJyzkhtzd(params, response);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10401, ip, "加油站考核通知单报表导出");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 操作加油站考核通知单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/operateJyzkhtzd")
	public @ResponseBody Map<String, Object> operateJyzkhtzd(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> mps = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String changeStatus = request.getParameter("changeStatus");
		String description = "";
		switch (changeStatus) {
		case "2":
			description = "市级下发";
			break;
		case "3":
			description = "县级部门接收";
			break;
		case "4":
			description = "县级下发";
			break;
		case "5":
			description = "加油站接收";
			break;
		default:
			description = "操作";
			break;
		}
		mps = iJyzkhtzdService.operateJyzkhtzd(id, changeStatus);
		// 写入日志
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		String ip = RequestRealIp.getIpAddress(request);
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, description + "加油站考核通知单报表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mps;
	}

	/**
	 * 操作加油站考核通知单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/opJyzNoticeSalaryStatus")
	public @ResponseBody Map<String, Object> opJyzNoticeSalaryStatus(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> mps = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String changeStatus = request.getParameter("changeStatus");
		String description = "";
		switch (changeStatus) {
		case "2":
			description = "加油站提交，县级部门未审核";
			break;
		case "3":
			description = "县级部门审核通过，未提交";
			break;
		case "4":
			description = "县级提交,市级别未审核";
			break;
		case "5":
			description = "市公司已审核";
			break;
		default:
			description = "操作";
			break;
		}
		mps = iJyzkhtzdService.opJyzNoticeSalaryStatus(id, changeStatus);
		// 写入日志
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		String ip = RequestRealIp.getIpAddress(request);
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, description + "加油站考核通知单报表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mps;
	}

	/**
	 * @Name: opJyzNoticeSalaryStatus @Description: @param @param
	 * request @param @return 参数 @return Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping(value = "/batchOpJyzNoticeSalaryStatus")
	public @ResponseBody ReturnValue batchOpJyzNoticeSalaryStatus(HttpServletRequest request,
			@RequestBody String vehicleJson) {
		HttpSession session = request.getSession();
		ReturnValue rspData = new ReturnValue();
		ArrayList<Integer> list = new ArrayList<Integer>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(vehicleJson);
		String jyzkhtzdJson = jsonObject.getString("jyzGzNoticeJson");
		String changeStatus = jsonObject.getString("changeStatus");
		String description = "";
		switch (changeStatus) {
		case "2":
			description = "加油站提交，县级部门未审核";
			break;
		case "3":
			description = "县级部门审核通过，未提交";
			break;
		case "4":
			description = "县级提交,市级别未审核";
			break;
		case "5":
			description = "市公司已审核";
			break;
		default:
			description = "操作";
			break;
		}
		net.sf.json.JSONArray jyzkhtzdArray = net.sf.json.JSONArray.fromObject(jyzkhtzdJson);
		for (int i = 0; i < jyzkhtzdArray.size(); i++) {
			Object r = jyzkhtzdArray.get(i);
			JSONObject jyzkhtzdMes = JSONObject.fromObject(r);
			String id = jyzkhtzdMes.getString("id");
			list.add(Integer.parseInt(id));
		}
		paramMap.put("changeStatus", changeStatus);
		paramMap.put("list", list);
		iJyzkhtzdService.batchOpJyzNoticeSalaryStatus(paramMap);
		rspData.setSuccess(true);
		rspData.setMessage("批量操作加油站考核通知单成功");
		// 写入日志
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		String ip = RequestRealIp.getIpAddress(request);
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, description + "加油站工资通知单报表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rspData;
	}

	/**
	 * 批量操作加油站考核通知单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batchOperateJyzkhtzd")
	public @ResponseBody ReturnValue batchOperateJyzkhtzd(HttpServletRequest request, @RequestBody String vehicleJson) {
		ReturnValue rspData = new ReturnValue();
		HttpSession session = request.getSession();
		ArrayList<Integer> list = new ArrayList<Integer>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			JSONObject jsonObject = JSONObject.fromObject(vehicleJson);
			String jyzkhtzdJson = jsonObject.getString("JyzkhtzdJson");
			String changeStatus = jsonObject.getString("changeStatus");
			String description = "";
			switch (changeStatus) {
			case "2":
				description = "市级下发";
				break;
			case "3":
				description = "县级部门接收";
				break;
			case "4":
				description = "县级下发";
				break;
			case "5":
				description = "加油站接收";
				break;
			default:
				description = "操作";
				break;
			}
			net.sf.json.JSONArray jyzkhtzdArray = net.sf.json.JSONArray.fromObject(jyzkhtzdJson);
			for (int i = 0; i < jyzkhtzdArray.size(); i++) {
				Object r = jyzkhtzdArray.get(i);
				JSONObject jyzkhtzdMes = JSONObject.fromObject(r);
				String id = jyzkhtzdMes.getString("id");
				list.add(Integer.parseInt(id));
			}
			paramMap.put("changeStatus", changeStatus);
			paramMap.put("list", list);
			iJyzkhtzdService.batchOperateJyzkhtzd(paramMap);
			rspData.setSuccess(true);
			rspData.setMessage("批量操作加油站考核通知单成功");
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "批量" + description + "加油站考核通知单");
		} catch (Exception e) {
			rspData.setSuccess(false);
			rspData.setMessage("批量下发加油站考核通知单失败,请联系客服");
			e.printStackTrace();
		}
		return rspData;
	}

	/**
	 * 修改加油站考核通知单
	 * 
	 * @param request
	 * @param jyzInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateJyzkhtzd")
	@ResponseBody
	@Transactional
	public ReturnValue updateJyzkhtzd(@RequestBody String body, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ReturnValue rspData = new ReturnValue();
		StationExamNoticeResult jyzInfo = new Gson().fromJson(body, StationExamNoticeResult.class);
		String companyId=jyzInfo.getCompanyId() + "";
		String yearMonthDate=jyzInfo.getYearMonthDate();
		try {
			// 修改加油站考核通知单
			iJyzkhtzdService.edit(jyzInfo);
			// 对应修改部门考核结果原始数据
			iJyzkhtzdService.updateBMExamSummary(companyId,yearMonthDate);
			// 判断部门考核通知单是否生成，生成则修改管理扣罚和专项奖罚
			iJyzkhtzdService.updateDepartmentExamNotice(companyId,yearMonthDate);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "修改部门考核通知单");
			rspData.setSuccess(true);
			rspData.setMessage("修改加油站考核通知单成功");
			return rspData;
		} catch (Exception e) {
			rspData.setSuccess(false);
			rspData.setMessage("修改加油站考核通知单失败,请联系客服");
			e.printStackTrace();
		}
		return rspData;
	}

	/**
	 * 查询几个可二次分配的参数值分别是多少
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/queryCanAllocation" }, method = { RequestMethod.POST })
	public ReturnValue queryCanAllocation(HttpServletRequest request) {
		ReturnValue rspData = new ReturnValue();
		String companyId = request.getParameter("companyId");
		String yearMonthDate = request.getParameter("yearMonthDate");
		try {
			Map<String, Object> jyztzdMap = iJyzkhtzdService.queryCanAllocation(companyId,yearMonthDate);
			rspData.setDatas(jyztzdMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rspData;
	}
}
