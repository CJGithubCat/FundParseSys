package com.zsh.labouCapital.vo;
/**
 * 
 * @author gongyu
 * 区域绑定车辆VO
 */
public class AreaVehicleVo {
    private Long agencyId;
    private String agencyName;
    private String plateNo;
    private String agencyPath;
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		if(agencyName!=null){
		  this.agencyName = agencyName.trim();
		  }
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		if(plateNo!=null){
		this.plateNo = plateNo.trim();
		}
	}
	public String getAgencyPath() {
		return agencyPath;
	}
	public void setAgencyPath(String agencyPath) {
		this.agencyPath = agencyPath;
	}
	
}
