package com.zsh.labouCapital.vo;

public class DistPermissionVo {
	private String roleid;
	private String permissionids;
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getPermissionids() {
		return permissionids;
	}
	public void setPermissionids(String permissionids) {
		this.permissionids = permissionids;
	}
	@Override
	public String toString() {
		return "DistPermissionVo [roleid=" + roleid + ", permissionids=" + permissionids + "]";
	}
}
