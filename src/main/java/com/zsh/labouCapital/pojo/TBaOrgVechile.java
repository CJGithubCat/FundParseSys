package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 机构车辆绑定关联实体类
 */
public class TBaOrgVechile implements Serializable {

	
	private static final long serialVersionUID = 487313252385090323L;

	private Long ovId;
	private Long orgId;
	private Long vehicleId;
	
	public Long getOvId() {
		return ovId;
	}
	public void setOvId(Long ovId) {
		this.ovId = ovId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
}
