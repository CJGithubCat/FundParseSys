package com.zsh.labouCapital.pojo;

import java.io.Serializable;

public class TWgWorker implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer workerId;  //安装人员id
	private String workerName;//安装人姓名
	private String loginName;//微信端登录名
	private String password;//微信端登录密码
	private String idCard;//身份证号
	private String mobilePhone;//手机号码
	private String area;//安装区域
	private String email;//电子邮箱
	private String status;//状态,0:正常;1:暂停;2:删除
	private String openId;//微信端关联id
	private int numbers;//安装次数
	private Integer grade;//安装人员综合评分,0-100
	private String remark;//备注
	private int isChanged;//是否改变身份证号码
	
	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(int isChanged) {
		this.isChanged = isChanged;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "TWgWorker [workerId=" + workerId + ", workerName=" + workerName + ", loginName=" + loginName
				+ ", password=" + password + ", idCard=" + idCard + ", mobilePhone=" + mobilePhone
				+ ", status=" + status + ", openId=" + openId + ", grade=" + grade
				+ ", area=" + area + ", email=" + email + ", remark=" + remark + "]";
	}
}
