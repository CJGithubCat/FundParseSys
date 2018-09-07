package com.zsh.labouCapital.pojo;

import java.util.List;

/**
 * 车辆车牌号码-车辆ID-车载呼号
 * @author Lxb
 *
 */
public class TBaVehicleMin  implements java.io.Serializable {
	
	private static final long serialVersionUID = 420425080011217721L;
	
	/** 车辆ID*/
	private Long vehicleId;
	/** 用户编号*/
	private Long unitId;
	/** 车牌号码*/
	private String plateNo;
	
	private String callLetter;

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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


