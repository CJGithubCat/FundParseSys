package com.zsh.labouCapital.pojo;

import java.io.Serializable;

/**
 * 类功能：角色权限关系类;
 * */
public class TWgRolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer roleid;//角色id
	private Integer permissionid;//权限id
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}
	@Override
	public String toString() {
		return "TWgRolePermission [roleid=" + roleid + ", permissionid=" + permissionid + "]";
	}
}
