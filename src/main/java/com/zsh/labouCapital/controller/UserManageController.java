/*
package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.IUserService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.Pager;
import com.zsh.labouCapital.vo.UserManageView;

*//**
 * 
 * @author 用户管理
 *//*
@Controller
@RequestMapping(value = { "/usermanage" })
public class UserManageController {
	private static Logger logger = LoggerFactory.getLogger(UserManageController.class);

	@Autowired
	private IUserService userManageService;
	@Autowired
	private ILoggerService iloggerService;

	*//**
	 * 根据机构，登录名分页查询用户
	 * 
	 * @param userManageView
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = { "/manager/finduser" }, method = { RequestMethod.POST })
	public ReturnValue findUserByPage(HttpServletRequest request, UserManageView userManageView,
			@RequestParam(value = "page", required = false) String pageNumber,
			@RequestParam(value = "pagesize", required = false) String pageSize) {
		ReturnValue rspData = new ReturnValue();
		Pager pager = new Pager(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		HttpSession session = request.getSession(false);
		Integer userId = (Integer) session.getAttribute(SystemConst.ACCOUNT_ID);

		userManageView.setUserId(userId);
		try {
			Map<String, Object> userMap = userManageService.findUserPage(userManageView, pager);
			rspData.setDatas(userMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rspData;
	}

	*//**
	 * 添加用户
	 * 
	 * @param body
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = { "/manager/adduser" }, method = { RequestMethod.POST })
	public ReturnValue addUser(@RequestBody String body, HttpServletRequest request) {
		ReturnValue rsp = new ReturnValue();
		try {
			HttpSession session = request.getSession();
			User user = new Gson().fromJson(body, User.class);
			User existUser = userManageService.getUserByLoginName(user.getLoginName());
			if (null == existUser) {
				Company company = userManageService.getCompanyById(user.getCompanyId());
//				if (company == null) {
//					rsp.setSuccess(false);
//					rsp.setErrorCode(2);
//					rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
//					return rsp;
//				}
				userManageService.add(user);
				rsp.setSuccess(true);
				rsp.setMessage(SystemConst.MESSAGE_OPSUCCESS);
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				iloggerService.writeLog(userid, sdf.format(nowDate), 10202, ip, "添加用户,用户名称:" + user.getUserName());
			} else {
				rsp.setSuccess(false);
				rsp.setErrorCode(1);
				rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rsp.setSuccess(false);
			rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rsp;
	}

	*//**
	 * 删除用户
	 * 
	 * @param loginName
	 * @return
	 *//*
	@RequestMapping(value = { "/manager/deleteuser" }, method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue deleteUser(@RequestBody String body, HttpServletRequest request) {
		User user = new Gson().fromJson(body, User.class);
		ReturnValue rsp = new ReturnValue();
		HttpSession session = request.getSession();
		user.setStatus(SystemConst.ACCOUNT_DELETESTATUSCODE);
		try {
			userManageService.edit(user);
			rsp.setSuccess(true);
			rsp.setMessage(SystemConst.MESSAGE_OPSUCCESS);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10202, ip, "删除用户,用户名称:" + user.getUserName());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rsp.setSuccess(false);
			rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rsp;
	}

	*//**
	 * 更新用户信息
	 * 
	 * @param body
	 * @return
	 *//*
	@RequestMapping(value = { "/manager/updateuser" }, method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue updateUser(@RequestBody String body, HttpServletRequest request) {
		ReturnValue rsp = new ReturnValue();
		HttpSession session = request.getSession();
		try {
			User user = new Gson().fromJson(body, User.class);
			User userByLoginName = userManageService.getUserByLoginName(user.getLoginName());
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if (null != userByLoginName && user.getUserId().intValue() != userByLoginName.getUserId().intValue()) {
				rsp.setSuccess(false);
				rsp.setErrorCode(1);// 修改了登录名，登录名已经存在
				rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
			} else {
				Company company = userManageService.getCompanyById(user.getCompanyId());
//				if (company == null) {
//					rsp.setSuccess(false);
//					rsp.setErrorCode(2);
//					rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
//					return rsp;
//				}
				if(user.getLoginPassword() !=null && user.getLoginPassword() !=""){
					user.setUpdatePasswordTime(sdf.format(nowDate));
				}
				userManageService.edit(user);
				rsp.setSuccess(true);
				rsp.setMessage(SystemConst.MESSAGE_OPSUCCESS);
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				iloggerService.writeLog(userid, sdf.format(nowDate), 10202, ip, "修改用户,用户名称:" + user.getUserName());
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rsp.setSuccess(false);
			rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rsp;
	}

	*//**
	 * 用户修改密码，验证旧密码是否正确
	 * 
	 * @param request
	 * @param oldPssword
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = { "/user/updatepassword/validate" }, method = { RequestMethod.POST })
	public ReturnValue validateOldPassword(HttpServletRequest request,
			@RequestParam(value = "current_password", required = false) String currentPassword) {
		ReturnValue rsp = new ReturnValue();
		try {
			String loginName = request.getSession().getAttribute(SystemConst.ACCOUNT_LOGINNAME).toString();
			User user = userManageService.getUserByLoginName(loginName);
			if (currentPassword != null && currentPassword.trim().equals(user.getLoginPassword())) {
				rsp.setSuccess(true);
				rsp.setMessage(SystemConst.MESSAGE_OPSUCCESS);
			} else {
				rsp.setSuccess(false);
				rsp.setMessage(SystemConst.MESSAGE_OLDPASSWORDWORONG);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rsp.setSuccess(false);
			rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rsp;
	}

	*//**
	 * 修改密码
	 * 
	 * @param request
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = { "/user/updatepassword/update" }, method = { RequestMethod.POST })
	public ReturnValue udpatePassword(HttpServletRequest request,
			@RequestParam(value = "old_password", required = false) String oldPassword,
			@RequestParam(value = "new_password", required = false) String newPassword) {
		ReturnValue rsp = new ReturnValue();
		try {
			HttpSession session = request.getSession();
			String loginName = request.getSession().getAttribute(SystemConst.ACCOUNT_LOGINNAME).toString();
			User user = userManageService.getUserByLoginName(loginName);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if (oldPassword != null && oldPassword.trim().equals(user.getLoginPassword())) {
				user.setUpdatePasswordTime(sdf.format(nowDate));
				user.setLoginPassword(newPassword);
				userManageService.edit(user);
				rsp.setSuccess(true);
				rsp.setMessage(SystemConst.MESSAGE_OPSUCCESS);
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				iloggerService.writeLog(userid, sdf.format(nowDate), 10202, ip, "修改密码,用户名称:" + user.getUserName());
			} else {
				rsp.setSuccess(false);
				rsp.setMessage(SystemConst.MESSAGE_OLDPASSWORDWORONG);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			rsp.setSuccess(false);
			rsp.setMessage(SystemConst.MESSAGE_OPFAILURE);
		}
		return rsp;
	}

	*//**
	 * 用户信息管理报表导出
	 * 
	 * @param request
	 * @param response
	 *//*
	@RequestMapping("/manager/exportUser")
	@ResponseBody
	public void exportUserManage(HttpServletRequest request, HttpServletResponse response) {
		String status = null;
		String loginName = null;
		Map<String, Object> params = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session == null) {
			return;
		}
		try {
			String attributesPath = request.getParameter("attributesPath");
			if (StringUtils.isBlank(attributesPath)) {
				Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
				attributesPath = company.getCompanyPath();
			}
			status = request.getParameter("status");
			loginName = request.getParameter("loginName");
			loginName = java.net.URLDecoder.decode(loginName, "UTF-8");
			params.put("attributesPath", attributesPath);
			params.put("status", status);
			params.put("loginName", loginName);
			userManageService.exportUserManage(params, response);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10202, ip, "用户信息报表导出");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
*/