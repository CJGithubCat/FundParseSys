package com.zsh.labouCapital.entity;

import java.io.Serializable;

/**
 * 类功能：角色权限关系类;
 * */
public class RoleModules implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer roleid;//角色id
	private Integer moduleId;

	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	@Override
	public String toString() {
		return "RoleModules [roleid=" + roleid + ", moduleId=" + moduleId + "]";
	}//权限id
}
	
