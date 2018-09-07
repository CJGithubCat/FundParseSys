package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.bean.TreeNode;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.IModuleService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.vo.DistPermissionVo;

/**
 * 函数功能：权限管理Controller
 */
@RestController
@RequestMapping(value = "/module")
public class ModuleController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ModuleController.class);

	/*
	 * @Autowired
	 * 
	 * @Qualifier("tWgPermissionServiceImpl") private ITWgPermissionService
	 * moduleService;
	 */

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：根据当前登录用户的userid查出各种对应的permission信息；
	 * */
	@RequestMapping("/queryPermissionsByUid")
	@ResponseBody
	public ReturnValue queryPermissionsByUid(HttpServletRequest request, Integer userid) {
		ReturnValue rv = new ReturnValue();

		try {
			HashMap<String, Object> hm = moduleService.queryModulesByUid(userid);
			rv.setSuccess(true);
			rv.setDatas(hm);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：根绝当前登录用户的userid查出各种对应的permission信息；[不传参]
	 * */
	@RequestMapping("/queryModules")
	@ResponseBody
	public ReturnValue queryPermissionsByUid(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession(false);

		try {
			HashMap<String, Object> hm = moduleService
					.queryModulesByUid(Integer.parseInt(session.getAttribute(SystemConst.ACCOUNT_ID).toString()));
			rv.setSuccess(true);
			rv.setDatas(hm);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：查询可以为用户分配的权限;
	 * */
	@RequestMapping("/queryModulsByRoleid")
	@ResponseBody
	public ReturnValue queryModulssByRoleid(HttpServletRequest request, Integer roleid) {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession(false);

		try {
			Integer nowUserid = (Integer) session.getAttribute(SystemConst.ACCOUNT_ID);
			ArrayList<TreeNode> list = moduleService.queryModulesByRoleid(nowUserid, roleid);
			rv.setDatas(list);
			rv.setSuccess(true);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：为角色分配权限;
	 * */
	@RequestMapping("/distRolePermissions")
	@ResponseBody
	public ReturnValue distRolePermissions(HttpServletRequest request, DistPermissionVo rolePermission) {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			boolean reslut = moduleService.distRoleModules(rolePermission);
			if (reslut) {
				rv.setSuccess(true);
				rv.setMessage("权限分配成功!");
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				iloggerService.writeLog(userid, sdf.format(nowDate), 10203, ip,
						"分配权限,权限ID::" + rolePermission.getRoleid());
			} else {
				rv.setSuccess(false);
				rv.setMessage("权限分配失败!");
			}
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：根据当前登录用户的userid和当前主菜单的类型，查出当前功能的permission信息（目前只有车辆资料管理）；
	 * */
	@RequestMapping("/queryInfoManagePermissionsByUid")
	@ResponseBody
	public ReturnValue queryInfoManagePermissionsByUid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ReturnValue rv = new ReturnValue();
		// 各类管理菜单的按钮控制
		String infoManageType = request.getParameter("infoManageType");
		String permissionsParentID = "";
		switch (infoManageType) {
			// 机构信息管理按钮权限
			case "agencyInfoManage": {
				permissionsParentID = "10201";
			}break;
			// 用户信息管理按钮权限
			case "userInfoManage": {
				permissionsParentID = "10202";
			}break;
			// 角色信息管理按钮权限
			case "roleInfoManage": {
				permissionsParentID = "10203";
			}break;
			// 部门考核通知单管理按钮权限
			case "BMKHTZDManage": {
				permissionsParentID = "10402";
			}break;
			// 加油站考核通知单管理按钮权限
			case "JYZKHTZDManage": {
				permissionsParentID = "10401";
			}break;
			// 工资通知单管理按钮权限
			case "BmGznoticeManage": {
				permissionsParentID = "10502";
			}break;
			// 工资通知单管理按钮权限
			case "JYZGzNoticeManaage": {
				permissionsParentID = "10503";
			}break;
		}
		try {
			HashMap<String, Object> hm = moduleService.queryInfoManageModulesByUid(Integer.parseInt(session.getAttribute(SystemConst.ACCOUNT_ID).toString()), permissionsParentID);
			rv.setSuccess(true);
			rv.setDatas(hm);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}
}
