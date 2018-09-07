package com.zsh.labouCapital.pojo;

import java.sql.Timestamp;


public class TAlStatus {
	private int statusId;
	private String statusName;
	private int  level;
	private int flag;
	private String beginTime;
	private String endTime;
	private int isDel;
	private Timestamp stamp;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
	@Override
	public String toString() {
		return "TAlStatus [statusId=" + statusId + ", statusName=" + statusName
				+ ", level=" + level + ", flag=" + flag + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", isDel=" + isDel
				+ ", stamp=" + stamp + "]";
	}
	
	

}
