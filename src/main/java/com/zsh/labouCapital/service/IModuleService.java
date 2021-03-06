package com.zsh.labouCapital.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.zsh.labouCapital.bean.TreeNode;
import com.zsh.labouCapital.vo.DistPermissionVo;

public interface IModuleService{
	//函数功能：根据用户id查询相应的各种权限;
	HashMap<String, Object> queryModulesByUid(Integer userid);
	//函数功能：根绝用户角色查找可以分配的权限;
	ArrayList<TreeNode> queryModulesByRoleid(Integer nowUserid, Integer roleid);
	//函数功能：分配权限;
	boolean distRoleModules(DistPermissionVo rolePermiss);
	/**
	 * 函数功能：根据用户id和当前模块的id，也就是子模块的父id，查询当前模块的按钮权限
	 * @param userid
	 * @param permissionsParentID
	 * @return
	 */
	public HashMap<String, Object> queryInfoManageModulesByUid(Integer userid,String modulesParentID);
}
