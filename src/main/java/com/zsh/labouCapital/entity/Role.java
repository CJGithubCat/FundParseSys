package com.zsh.labouCapital.entity;

import java.io.Serializable;

/**
 * 角色
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer roleId; // 角色id
	private String roleName;// 角色名称
	private Integer isdeleted;// 是否删除(0:否,1:是)
	private String updatestamp;//
	private String remark;// 备注
	private Integer createuserid;// 创建者的userid
	private Integer userId;// 用户id
	private Integer updateuserid;// 维护人id
	private Integer issystem;//

	public Integer getIssystem() {
		return issystem;
	}

	public void setIssystem(Integer issystem) {
		this.issystem = issystem;
	}

	public Integer getUpdateuserid() {
		return updateuserid;
	}

	public void setUpdateuserid(Integer updateuserid) {
		this.updateuserid = updateuserid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getUpdatestamp() {
		return updatestamp;
	}

	public void setUpdatestamp(String updatestamp) {
		this.updatestamp = updatestamp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateuserid() {
		return createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", isdeleted=" + isdeleted + ", updatestamp="
				+ updatestamp + ", remark=" + remark + ", createuserid=" + createuserid + "]";
	}
}
