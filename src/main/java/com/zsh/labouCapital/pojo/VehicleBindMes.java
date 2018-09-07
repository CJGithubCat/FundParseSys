package com.zsh.labouCapital.pojo;

import java.io.Serializable;

/*
 * 车辆绑定信息
 * @author caoliang
 */
public class VehicleBindMes implements Serializable {
	/*机构ID*/
	private Long agencyId;
	/*车辆ID*/
	private Integer vehicleId;
	/*规则ID*/
	private Integer ruleId;
	/*区域ID*/
	private Integer areaId;
	/*ID*/
	private Integer id;
	/*机构路径*/
	private String agencyPath;
	/*车牌号码*/
	private String plateNo;
	/* 车牌颜色：车牌颜色, 系统值2110, 1=蓝, 2=黄, 3=黑, 4=白, 9=其他*/
	private Integer plateColor;
	/*区域名称*/
    private String areaName;
    /*企业名称*/
    private String agencyName;
    /*规则名称*/
    private String ruleName;
    /*号码*/
    private String callLetter;
    /*围栏类型*/
    private String shapeType;
    /*车辆状态-是否所有车辆*/
    private String carStatus;
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public Integer getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(Integer plateColor) {
		this.plateColor = plateColor;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public String getAgencyPath() {
		return agencyPath;
	}
	public void setAgencyPath(String agencyPath) {
		this.agencyPath = agencyPath;
	}
	public String getShapeType() {
		return shapeType;
	}
	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}
	
	public String getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}
	
	@Override
	public String toString() {
		return "VehicleBindMes [agencyId=" + agencyId + ", vehicleId=" + vehicleId + ", ruleId=" + ruleId + ", areaId="
				+ areaId + ", id=" + id + ", agencyPath=" + agencyPath + ", plateNo=" + plateNo + ", plateColor="
				+ plateColor + ", areaName=" + areaName + ", agencyName=" + agencyName + ", ruleName=" + ruleName
				+ ", callLetter=" + callLetter + ", shapeType=" + shapeType + ", carStatus=" + carStatus + "]";
	}
	
}
