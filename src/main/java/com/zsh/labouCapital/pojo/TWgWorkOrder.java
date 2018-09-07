package com.zsh.labouCapital.pojo;

import java.util.Date;

/**
 * 类功能：工单类；
 * */
public class TWgWorkOrder {
	private Integer workOrderId;
	private Integer agencyId;
	private String userName;
	private String sqbh;
	private String cjh;
	private Integer num;
	private String installAdd;
	private String installTime;
	private String user;
	private String phone;
	private String worker;
	private String workerPhone;
	private Integer workOrderState;
	private String workOrderStateStr;
	private String cancelTime;
	private String cancelAdd;
	private String attributesPath;
	private String agencyName;
	private Date stamp;
	private Integer workerid;
	private String daiNum;
	
	public String getWorkOrderStateStr() {
		return workOrderStateStr;
	}
	public void setWorkOrderStateStr(String workOrderStateStr) {
		this.workOrderStateStr = workOrderStateStr;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public Integer getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Integer workOrderId) {
		this.workOrderId = workOrderId;
	}
	public Integer getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSqbh() {
		return sqbh;
	}
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}
	public String getCjh() {
		return cjh;
	}
	public void setCjh(String cjh) {
		this.cjh = cjh;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getInstallAdd() {
		return installAdd;
	}
	public void setInstallAdd(String installAdd) {
		this.installAdd = installAdd;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getWorkerPhone() {
		return workerPhone;
	}
	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}
	public Integer getWorkOrderState() {
		return workOrderState;
	}
	public void setWorkOrderState(Integer workOrderState) {
		this.workOrderState = workOrderState;
	}
	public String getInstallTime() {
		return installTime;
	}
	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getCancelAdd() {
		return cancelAdd;
	}
	public void setCancelAdd(String cancelAdd) {
		this.cancelAdd = cancelAdd;
	}
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public Integer getWorkerid() {
		return workerid;
	}
	public void setWorkerid(Integer workerid) {
		this.workerid = workerid;
	}
	
	public String getDaiNum() {
		return daiNum;
	}
	public void setDaiNum(String daiNum) {
		this.daiNum = daiNum;
	}
	@Override
	public String toString() {
		return "TWgWorkOrder [workOrderId=" + workOrderId + ", agencyId=" + agencyId + ", userName=" + userName
				+ ", sqbh=" + sqbh + ", cjh=" + cjh + ", num=" + num + ", installAdd=" + installAdd + ", installTime="
				+ installTime + ", user=" + user + ", phone=" + phone + ", worker=" + worker + ", workerPhone="
				+ workerPhone + ", workOrderState=" + workOrderState + ", workOrderStateStr=" + workOrderStateStr
				+ ", cancelTime=" + cancelTime + ", cancelAdd=" + cancelAdd + ", attributesPath=" + attributesPath
				+ ", agencyName=" + agencyName + "]";
	}
}
