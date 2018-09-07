package com.zsh.labouCapital.pojo;

import java.util.Date;
import java.util.List;

/**
 * 车辆还贷信息实体类
 * @author Yu
 *
 */
public class VehicleLoan {
	//编号
	private Long loanId;
	//车辆id
	private Long vehicleId;
	//还贷状态 0-已还，1-未还，-1-异常
	private String loanState; 
	//入库时间
	private Date addStamp;
	//时间戳
	private Date stamp;
	//备注
	private String remark;
	//车牌号码
	private String plateNo;
	//车载号码
	private String callLetter;
	//所属机构
	private String subName;
	//用户编号
	private Long cusId;
	//操作人员编码
	private Long updateCusId;
	
	
	
	private String msg;
	private List<String> orgs;
	private int customerId;//用户ID
	
		
	public Long getCusId() {
		return cusId;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}
	public Long getUpdateCusId() {
		return updateCusId;
	}
	public void setUpdateCusId(Long updateCusId) {
		this.updateCusId = updateCusId;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	public Date getAddStamp() {
		return addStamp;
	}
	public void setAddStamp(Date addStamp) {
		this.addStamp = addStamp;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<String> orgs) {
		this.orgs = orgs;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
