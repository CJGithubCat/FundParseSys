/**
 * 
 */
package com.zsh.labouCapital.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = -1881675913369649678L;
	private Integer userId;
	private String userName;
	private String loginName;
	private String loginPassword;
	private Integer status;
	private String statusStr;
	private Integer companyId;
	private Integer roleId;
	private String roleName;
	private String stamp;
	private String companyName;
	private String companyType;
	private String updatePasswordTime;
	private String passwordOutDay;

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		if (loginName != null) {
			this.loginName = loginName.trim();
		}
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		if (loginPassword != null) {
			this.loginPassword = loginPassword.trim();
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getUpdatePasswordTime() {
		return updatePasswordTime;
	}

	public void setUpdatePasswordTime(String updatePasswordTime) {
		this.updatePasswordTime = updatePasswordTime;
	}

	public String getPasswordOutDay() {
		return passwordOutDay;
	}

	public void setPasswordOutDay(String passwordOutDay) {
		this.passwordOutDay = passwordOutDay;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", loginName=" + loginName + ", loginPassword="
				+ loginPassword + ", status=" + status + ", statusStr=" + statusStr + ", companyId=" + companyId
				+ ", roleId=" + roleId + ", roleName=" + roleName + ", stamp=" + stamp + ", companyName=" + companyName
				+ ", companyType=" + companyType + ", updatePasswordTime=" + updatePasswordTime + "]";
	}
}