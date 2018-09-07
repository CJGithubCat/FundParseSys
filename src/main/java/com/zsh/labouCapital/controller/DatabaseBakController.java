package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.IDatabaseBakService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

@Controller
@RequestMapping(value = "/databaseBak")
public class DatabaseBakController extends BaseController {
	@Autowired
	private IDatabaseBakService iDatabaseBakService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：分页查询数据库备份记录；
	 */
	@RequestMapping("/querydatabaseBak")
	@ResponseBody
	public ReturnValue querydatabaseBak(HttpServletRequest request, String attributesPath) {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		Map<String, Object> params = new HashMap<String, Object>();
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		try {
			PageBean pageBean = parsePageParm(request);
			params.put("beginDate", beginDate);
			params.put("endDate", endDate);
			params.put("pageBean", pageBean);
			HashMap<String, Object> hm = iDatabaseBakService.querydatabaseBak(params);
			rv.setDatas(hm);
			rv.setSuccess(true);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10603, ip, "查询数据库备份记录");
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
	 * 备份数据库
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/bakDatabase" }, method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue bakDatabase(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		try {
			iDatabaseBakService.bakDatabase(userid);
			rv.setSuccess(true);
			rv.setMessage(SystemConst.MESSAGE_OPSUCCESS);
			// 写入日志
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10603, ip, "备份数据库:");
		} catch (Exception ex) {
			ex.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage("异常原因：" + ex.getMessage());
			rv.setDatas(null);
			rv.setErrorCode(-11);
		}
		return rv;
	}
}
