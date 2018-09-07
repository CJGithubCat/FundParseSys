package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Role;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.IRoleService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

@Controller
@RequestMapping("/role")
public class RolesController extends BaseController {
	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：查询角色角色信息；
	 */
	@RequestMapping("/queryRolesPage")
	@ResponseBody
	public ReturnValue queryRolesPage(HttpServletRequest request, Role role) throws Exception {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession(false);
		try {
			PageBean pageBean = parsePageParm(request);
			Integer uid = (Integer) session.getAttribute(SystemConst.ACCOUNT_ID);
			HashMap<String, Object> hmap = iRoleService.queryRolesPage(role, pageBean, uid);
			rv.setDatas(hmap);
			rv.setMessage("查询成功!");
			rv.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			rv.setMessage("错误：" + e.getMessage());
			rv.setSuccess(false);
			rv.setDatas(null);
		}
		return rv;
	}

	/**
	 * 函数功能：添加角色；
	 */
	@RequestMapping("/addRoles")
	@ResponseBody
	public ReturnValue addRoles(HttpServletRequest request, Role role) throws Exception {
		HttpSession session = request.getSession(false);
		ReturnValue rv = new ReturnValue();
		try {
			if (role.getRoleName() == null || role.getRoleName().length() == 0) {
				rv.setMessage("角色名为空!");
				rv.setSuccess(false);
				return rv;
			}
			if (role.getRoleName().equals("超级管理员") || role.getRoleName().equals("企业管理员")
					|| role.getRoleName().equals("企业操作员")) {
				rv.setMessage("不能添加系统设置角色!");
				rv.setSuccess(false);
				return rv;
			}
			// 1.查看是否已经添加了这个名字的角色;
			long count = iRoleService.queryRolesByName(role.getRoleName());
			if (count == 0) {
				// 当前用户id
				Integer userid = (Integer) session.getAttribute(SystemConst.ACCOUNT_ID);
				role.setUpdateuserid(userid);
				role.setCreateuserid(userid);
				// 入库时间
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				role.setUpdatestamp(sdf.format(nowDate));
				role.setIsdeleted(0);
				iRoleService.add(role);
				rv.setSuccess(true);
				rv.setMessage("角色添加成功!");
				// 写入日志
				int userid1 = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				iloggerService.writeLog(userid1, sdf.format(nowDate), 10203, ip,
						"添加角色信息,角色名称:" + role.getRoleName());
			} else {
				rv.setMessage("角色已经存在!");
				rv.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setMessage("错误：" + e.getMessage());
			rv.setSuccess(false);
		}
		return rv;
	}

	/**
	 * 函数功能：修改角色信息;
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public ReturnValue updateRole(HttpServletRequest request, Role role) throws Exception {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession(false);
		try {
			if (role.getRoleName().equals("超级管理员") || role.getRoleName().equals("企业管理员")
					|| role.getRoleName().equals("企业操作员")) {
				rv.setMessage("不能添加系统设置角色!");
				rv.setSuccess(false);
				return rv;
			}
			// 1.查看是否已经添加了这个名字的角色;
			int count = iRoleService.queryExceptRoles(role);
			if (count == 0) {
				// 当前用户id
				Integer userid = (Integer) session.getAttribute(SystemConst.ACCOUNT_ID);
				role.setUpdateuserid(userid);
				// 入库时间
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				role.setUpdatestamp(sdf.format(nowDate));
				iRoleService.updateRole(role);
				rv.setMessage("更新成功!");
				rv.setSuccess(true);
				// 写入日志
				int userid1 = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				iloggerService.writeLog(userid1, sdf.format(nowDate), 10203, ip,
						"修改角色信息,角色名称:" + role.getRoleName());
			} else {
				rv.setMessage("角色名重复，更新失败!");
				rv.setSuccess(false);
			}

		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：删除角色;
	 */
	@RequestMapping("/deleteRoles")
	@ResponseBody
	public ReturnValue deleteRoles(Role role, HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			// 1.系统设置角色无法删除;
			if (role.getIssystem()== 1) {
				rv.setSuccess(false);
				rv.setMessage("系统设定角色，无法删除!");
				return rv;
			}
			// 2.判断某个角色是否绑定了用户，绑定了则不能删除;
			int count = iRoleService.roleIsBindUser(role.getRoleId());
			if (count == 0) {// 1.用户没有绑定角色，可以删除;
				iRoleService.delteRoles(role);
				rv.setSuccess(true);
				rv.setMessage("删除角色成功!");
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				iloggerService.writeLog(userid, sdf.format(nowDate), 10203, ip,
						"删除角色信息,角色名称:" + role.getRoleName());
			} else {
				rv.setSuccess(false);
				rv.setMessage("有用户指定该角色，无法删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
		}
		return rv;
	}

	/**
	 * 函数功能：判断某个角色是否有指定用户;
	 */
	@RequestMapping("/roleIsBindUser")
	@ResponseBody
	public ReturnValue roleIsBindUser(Role role) throws Exception {
		ReturnValue rv = new ReturnValue();
		try {
			int count = iRoleService.roleIsBindUser(role.getRoleId());
			if (count == 0) {
				rv.setSuccess(true);
				rv.setMessage("角色绑定了用户!");
			} else {
				rv.setSuccess(false);
				rv.setMessage("角色没有绑定用户!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
		}
		return rv;
	}

	/**
	 * 函数功能：根据用户id查询角色;
	 */
	@RequestMapping("/queryRoleByUid")
	@ResponseBody
	public ReturnValue queryRoleByUid(Integer userid) throws Exception {
		ReturnValue rv = new ReturnValue();

		try {
			Role role = iRoleService.queryRoleByUid(userid);
			if (role == null) {
				rv.setSuccess(false);
				rv.setMessage("该用户角色为空!");
			} else {
				rv.setSuccess(true);
				rv.setDatas(role);
				rv.setMessage("查询角色成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
		}
		return rv;
	}

	/**
	 * 函数功能：根据用户id查询可以设置的角色;
	 */
	@RequestMapping("/queryChangeRolesByUid")
	@ResponseBody
	public ReturnValue queryChangeRolesByUid(HttpServletRequest request, Integer userid) throws Exception {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession(false);

		try {
			if (userid == null || userid < 0) {
				userid = (Integer) session.getAttribute(SystemConst.ACCOUNT_ID);
			}
			List<Role> roleList = iRoleService.queryChangeRolesByUid(userid);
			if (roleList == null) {
				rv.setSuccess(false);
				rv.setMessage("该用户角色为空!");
			} else {
				rv.setSuccess(true);
				rv.setDatas(roleList);
				rv.setMessage("查询角色成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
		}
		return rv;
	}
}
