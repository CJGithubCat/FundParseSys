package com.zsh.labouCapital.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.zsh.labouCapital.entity.Module;

public interface ModuleMapper extends BaseSqlMapper<Module> {
	public ArrayList<Module> queryModulesByUid(HashMap<String, Object> params);

	public ArrayList<Module> distributeModulesByRoleid(Integer roleid);

	public ArrayList<Module> queryModulesByRoleid(Integer roleid);

	public void deleteModulesByRoleid(Integer roleid);

	public void addRoleModules(HashMap<String, Object> params); 
	
	/**
	 * 函数功能：根据用户id和当前模块的id，也就是子模块的父id，查询当前模块的按钮权限
	 * @param params
	 * @return
	 */
	public ArrayList<Module> queryInfoManageModulesByUid(HashMap<String, Object> params);
}
