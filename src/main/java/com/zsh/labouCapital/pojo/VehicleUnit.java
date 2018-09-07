package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 车辆终端关联信息实体类
 */
public class VehicleUnit implements Serializable {

	
	private static final long serialVersionUID = 3373819347913744547L;

	private Long unitId;
	private Long vehicleId;
	private String plateNo;
	private String callLetter;
	
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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
	
	
}
