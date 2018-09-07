package com.zsh.labouCapital.pojo;

/**
 * 
 * @author gongyu 区域绑定，车辆，规则 新增 机构绑定区域
 */
public class AreaVehicle {
	private Long id;
	private Long areaId;
	private Long ruleId;
	private Long cusId;
	private Long vehicleId;
	private String callLetter;
	private String plateNo;
	private String agencyName;
	private String attributePath;
	private Long agencyId;
	private Long carStatus;

	public String getAttributePath() {
		return attributePath;
	}

	public void setAttributePath(String attributePath) {
		this.attributePath = attributePath;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
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

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Long carStatus) {
		this.carStatus = carStatus;
	}

	@Override
	public String toString() {
		return "AreaVehicle [id=" + id + ", areaId=" + areaId + ", ruleId=" + ruleId + ", cusId=" + cusId
				+ ", vehicleId=" + vehicleId + ", callLetter=" + callLetter + ", plateNo=" + plateNo + ", agencyName="
				+ agencyName + ", attributePath=" + attributePath + ", agencyId=" + agencyId + ", carStatus="
				+ carStatus + "]";
	}

}
