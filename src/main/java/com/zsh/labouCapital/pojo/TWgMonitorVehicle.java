package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 监控习惯实体类
 */
public class TWgMonitorVehicle implements Serializable {

	
	private static final long serialVersionUID = 996461030356272023L;
	/** 编号*/
	private Long monitorId;
	/** 车辆ID*/
	private Long vehicleId;
	/** 用户ID,网查根用户*/
	private Long cusId;
	/** 车载号码*/
	private String callLetter;
	/** 是否删除:0=未删除;1=删除*/
	private Integer isDelete;
	/** 时间戳*/
	private String stamp;
	/** 操作员ID*/
	private String opId;
	/**车牌号码*/
	private String plateNo;
	/**车台编号*/
	private Long unitId;
	/** 预存指令标记, 0=不预存, 1=预存*/
	private Integer preCmd;
	
	public Long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Long getCusId() {
		return cusId;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public Integer getPreCmd() {
		return preCmd;
	}
	public void setPreCmd(Integer preCmd) {
		this.preCmd = preCmd;
	}
	
}
