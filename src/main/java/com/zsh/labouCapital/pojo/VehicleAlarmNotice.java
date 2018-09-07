package com.zsh.labouCapital.pojo;

/**
 * 车辆警情提醒规则
 */
public class VehicleAlarmNotice extends VehicleUnitAlarmRule {

	
	private static final long serialVersionUID = 3561035577836816046L;

	private Long cusId;
	private String opId;
	
	
	public Long getCusId() {
		return cusId;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	
	
}
