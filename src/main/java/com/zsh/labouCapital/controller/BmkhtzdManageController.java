
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.IBmkhtzdService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

import net.sf.json.JSONObject;

/**
 * 
 * @author 部门考核通知单管理
 */
@Controller
@RequestMapping(value = { "/bmkhtzdmanage" })
public class BmkhtzdManageController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(BmkhtzdManageController.class);

	@Autowired
	private IBmkhtzdService bmkhtzdService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 分页查询考核通知单
	 * 
	 * @param userManageView
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/queryBmkhtzd" }, method = { RequestMethod.POST })
	public ReturnValue queryBmkhtzd(HttpServletRequest request) {
		ReturnValue rspData = new ReturnValue();
		PageBean pageBean = parsePageParm(request);
		HttpSession session = request.getSession(false);
		String companyPath = request.getParameter("companyPath");
		String yearMonthDate = request.getParameter("yearMonthDate");
		String bmmc = request.getParameter("bmmc");
		String status = request.getParameter("status");
		String salaryStatus = request.getParameter("salaryStatus");
		if (StringUtils.isBlank(companyPath)) {
			Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
			companyPath = company.getCompanyPath();
		}
		try {
			Map<String, Object> bmtzdMap = bmkhtzdService.findBmkhtzdPage(yearMonthDate, bmmc, status,salaryStatus, pageBean,
					companyPath);
			rspData.setDatas(bmtzdMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "分页查询部门考核通知单");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rspData;
	}

	/**
	* @Name: queryBmGzNoticePage  
	* @Description:分页查询部门工资通知单  
	* @param @param request
	* @param @return    参数  
	* @return ReturnValue    返回类型  
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = { "/queryBmGzNoticePage" }, method = { RequestMethod.POST })
	public ReturnValue queryBmGzNoticePage(HttpServletRequest request) {
		ReturnValue rspData = new ReturnValue();
		PageBean pageBean = parsePageParm(request);
		HttpSession session = request.getSession(false);
		String companyPath = request.getParameter("companyPath");
		String yearMonthDate = request.getParameter("yearMonthDate");
		String bmmc = request.getParameter("bmmc");
		String status = request.getParameter("status");
		String salaryStatus = request.getParameter("salaryStatus");
		if (StringUtils.isBlank(companyPath)) {
			Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
			companyPath = company.getCompanyPath();
		}
		try {
			Map<String, Object> bmtzdMap = bmkhtzdService.findBmkhtzdPage(yearMonthDate, bmmc, status,salaryStatus,pageBean,
					companyPath);
			rspData.setDatas(bmtzdMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "分页查询部门考核通知单");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rspData;
	}
	
	/**
	 * 部门考核通知单管理报表导出
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportBmkhtzd")
	@ResponseBody
	public void exportBmkhtzd(HttpServletRequest request, HttpServletResponse response) {
		String status = null;
		String bmmc = null;
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
			bmmc = request.getParameter("bmmc");
			yearMonthDate = request.getParameter("yearMonthDate");
			if(!StringUtils.isEmpty(bmmc)){
				bmmc = java.net.URLDecoder.decode(bmmc, "UTF-8");
			}
			if(!StringUtils.isEmpty(yearMonthDate)){
				yearMonthDate = java.net.URLDecoder.decode(yearMonthDate, "UTF-8");
			}
			params.put("companyPath", companyPath);
			params.put("status", status);
			params.put("bmmc", bmmc);
			params.put("yearMonthDate", yearMonthDate);
			bmkhtzdService.exportBmkhtzd(params, response);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "部门考核通知单报表导出");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* @Name: opBmNoticeSalaryStatus  
	* @Description:操作工资通知单状态  
	* @param @param request
	* @param @return    参数  
	* @return Map<String,Object>    返回类型  
	* @throws
	 */
	@RequestMapping(value = "/opBmNoticeSalaryStatus")
	public @ResponseBody Map<String, Object> opBmNoticeSalaryStatus(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> mps = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String changeStatus = request.getParameter("changeStatus");
		String description="";
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
		mps = bmkhtzdService.opBmNoticeSalaryStatus(id, changeStatus);
		// 写入日志
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		String ip = RequestRealIp.getIpAddress(request);
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, description+"部门考核通知单报表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mps;
	}
	
	/**
	 * 操作考核通知单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/operateBmkhtzd")
	public @ResponseBody Map<String, Object> operateBmkhtzd(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> mps = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String changeStatus = request.getParameter("changeStatus");
		String description="";
		switch (changeStatus) {
		case "2":
			description="下发";
			break;
		case "3":
			description="部门接收";
			break;
		default:
			description="下发";
			break;
		}
		mps = bmkhtzdService.operateBmkhtzd(id, changeStatus);
		// 写入日志
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		String ip = RequestRealIp.getIpAddress(request);
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, description+"部门考核通知单报表");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mps;
	}

	/**
	* @Name: batchOperateBmkhtzd  
	* @Description:批量操作部门工资通知单  
	* @param @param request
	* @param @param vehicleJson
	* @param @return    参数  
	* @return ReturnValue    返回类型  
	* @throws
	 */
	@RequestMapping(value = "/batchopBmNoticeSalaryStatus")
	public @ResponseBody ReturnValue batchopBmNoticeSalaryStatus(HttpServletRequest request, @RequestBody String vehicleJson) {
		ReturnValue rspData = new ReturnValue();
		HttpSession session = request.getSession();
		ArrayList<Integer> list = new ArrayList<Integer>();
		Map<String, Object> paramMap = new HashMap<String,Object>();
		String description="";
		try {
			JSONObject jsonObject = JSONObject.fromObject(vehicleJson);
			String bmkhtzdStrt = jsonObject.getString("BumenkhtzdJson");
			String changeStatus = jsonObject.getString("changeStatus");
			net.sf.json.JSONArray bmkhtzdArray = net.sf.json.JSONArray.fromObject(bmkhtzdStrt);
			for (int i = 0; i < bmkhtzdArray.size(); i++) {
				Object r = bmkhtzdArray.get(i);
				JSONObject bmkhtzdMes = JSONObject.fromObject(r);
				String id = bmkhtzdMes.getString("id");
				list.add(Integer.parseInt(id));
			}
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
			paramMap.put("changeStatus", changeStatus);
			paramMap.put("list", list);
			bmkhtzdService.batchopBmNoticeSalaryStatus(paramMap);
			rspData.setSuccess(true);
			rspData.setMessage("批量"+description+"部门工资通知单成功");
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "批量"+description+"部门考核通知单成功");
		} catch (Exception e) {
			rspData.setSuccess(false);
			rspData.setMessage("批量"+description+"部门考核通知单失败,请联系客服");
			e.printStackTrace();
		}
		return rspData;
	}
	
	
	/**
	 * 批量操作考核通知单 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batchOperateBmkhtzd")
	public @ResponseBody ReturnValue batchOperateBmkhtzd(HttpServletRequest request, @RequestBody String vehicleJson) {
		ReturnValue rspData = new ReturnValue();
		HttpSession session = request.getSession();
		ArrayList<Integer> list = new ArrayList<Integer>();
		Map<String, Object> paramMap = new HashMap<String,Object>();
		String description="";
		try {
			JSONObject jsonObject = JSONObject.fromObject(vehicleJson);
			String bmkhtzdStrt = jsonObject.getString("BumenkhtzdJson");
			String changeStatus = jsonObject.getString("changeStatus");
			net.sf.json.JSONArray bmkhtzdArray = net.sf.json.JSONArray.fromObject(bmkhtzdStrt);
			for (int i = 0; i < bmkhtzdArray.size(); i++) {
				Object r = bmkhtzdArray.get(i);
				JSONObject bmkhtzdMes = JSONObject.fromObject(r);
				String id = bmkhtzdMes.getString("id");
				list.add(Integer.parseInt(id));
			}
			switch (changeStatus) {
			case "2":
				description="下发";
				break;
			case "3":
				description="部门接收";
				break;
			default:
				description="下发";
				break;
			}
			paramMap.put("changeStatus", changeStatus);
			paramMap.put("list", list);
			bmkhtzdService.batchOperateBmkhtzd(paramMap);
			rspData.setSuccess(true);
			rspData.setMessage("批量"+description+"部门考核通知单成功");
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10402, ip, "批量"+description+"部门考核通知单成功");
		} catch (Exception e) {
			rspData.setSuccess(false);
			rspData.setMessage("批量"+description+"部门考核通知单失败,请联系客服");
			e.printStackTrace();
		}
		return rspData;
	}

}
