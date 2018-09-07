/**
 * 
 */
package com.zsh.labouCapital.pojo;
import java.io.Serializable;
/**
 *  @author 
 *  用户信息类
 */
public class User implements Serializable{
	private static final long serialVersionUID = -1881675913369649678L;
    private Integer userId;
    private String userName;
    private String idCard;
    private String loginName;
    private String loginPassword;
    private String mobile;
    private String email;
    private Integer status;
    private String statusStr;
    private Integer companyId;
    private Integer roleid;
    private String roleName;
    
    private String agencyName;
    
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		if(loginName!=null){
		  this.loginName = loginName.trim();
		}
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		if(loginPassword!=null){
		  this.loginPassword = loginPassword.trim();
		}
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", idCard=" + idCard + ", loginName=" + loginName
				+ ", loginPassword=" + loginPassword + ", mobile=" + mobile + ", email=" + email + ", status=" + status
				+ ", statusStr=" + statusStr + ", companyId=" + companyId + ", roleid=" + roleid + ", roleName="
				+ roleName + ", agencyName=" + agencyName + "]";
	}
	
}
