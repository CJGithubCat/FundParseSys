package com.zsh.labouCapital.pojo;

import java.util.Date;

public class TWgUpgradeUnits {

	private Long remoteId;//升级编号
	private int unitTypeId;
	private int userid;
	private String ip;
	private String port;
	private String upgradeVersion;
	private String realUpgradeVersion;
	private String beforeUpgradeVersion;
	private String userName;
	private String password;
	private String filePathName;
	private Date stamp;
	private String fileInfo;
	
	
	
	
	
	
	
	
	public String getRealUpgradeVersion() {
		return realUpgradeVersion;
	}
	public void setRealUpgradeVersion(String realUpgradeVersion) {
		this.realUpgradeVersion = realUpgradeVersion;
	}
	public String getBeforeUpgradeVersion() {
		return beforeUpgradeVersion;
	}
	public void setBeforeUpgradeVersion(String beforeUpgradeVersion) {
		this.beforeUpgradeVersion = beforeUpgradeVersion;
	}
	public Long getRemoteId() {
		return remoteId;
	}
	public void setRemoteId(Long remoteId) {
		this.remoteId = remoteId;
	}
	public String getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public String getUpgradeVersion() {
		return upgradeVersion;
	}
	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}
	public int getUnitTypeId() {
		return unitTypeId;
	}
	public void setUnitTypeId(int unitTypeId) {
		this.unitTypeId = unitTypeId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFilePathName() {
		return filePathName;
	}
	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}
	@Override
	public String toString() {
		return "TWgUpgradeUnits [remoteId=" + remoteId + ", unitTypeId=" + unitTypeId + ", userid=" + userid + ", ip="
				+ ip + ", port=" + port + ", upgradeVersion=" + upgradeVersion + ", realUpgradeVersion="
				+ realUpgradeVersion + ", beforeUpgradeVersion=" + beforeUpgradeVersion + ", userName=" + userName
				+ ", password=" + password + ", filePathName=" + filePathName + ", stamp=" + stamp + ", fileInfo="
				+ fileInfo + "]";
	}
	
	
	
}
