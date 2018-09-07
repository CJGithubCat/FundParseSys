package com.zsh.labouCapital.pojo;

import java.util.Date;

/**
 * 
 * @author gongyu
 *
 */
public class UserLoginHistory {
    private Long loginHistoryId;
    private Long userId;
    private short loginType;
    private String referenceposition;
    private String ip;
    private Date stamp;//数据库时间戳记录为timestamp，这里只记录到秒，不记录毫秒
    //查询字段
    private String queryStartTime;
    private String queryEndTime;
    private String userName;
    private Long agencyId;
    private String agencyName;
    private String attributesPath;
	//---
    private String loginName;
    private String loginTypeStr;
    
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public Long getLoginHistoryId() {
		return loginHistoryId;
	}
	public String getLoginTypeStr() {
		return loginTypeStr;
	}
	public void setLoginTypeStr(String loginTypeStr) {
		this.loginTypeStr = loginTypeStr;
	}
	public void setLoginHistoryId(Long loginHistoryId) {
		this.loginHistoryId = loginHistoryId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public short getLoginType() {
		return loginType;
	}
	public void setLoginType(short loginType) {
		this.loginType = loginType;
	}
	public String getReferenceposition() {
		return referenceposition;
	}
	public void setReferenceposition(String referenceposition) {
		this.referenceposition = referenceposition;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}	
	
	public String getQueryStartTime() {
		return queryStartTime;
	}
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}
	public String getQueryEndTime() {
		return queryEndTime;
	}
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		if(loginName!=null){
		this.loginName = loginName.trim();
		}
	}  
	
}
