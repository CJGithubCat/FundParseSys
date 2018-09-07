package com.zsh.labouCapital.vo;

/**
 * 用户管理，根据登录名查询view
 * 
 * @author gongyu
 *
 */
public class UserManageView {
	private String loginName;
	private String companyPath;
	private String status;
	private Integer userId;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCompanyPath() {
		return companyPath;
	}
	public void setCompanyPath(String companyPath) {
		this.companyPath = companyPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserManageView [loginName=" + loginName + ", companyPath=" + companyPath + ", status=" + status
				+ ", userId=" + userId + "]";
	}
}
