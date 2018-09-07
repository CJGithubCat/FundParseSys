package com.zsh.labouCapital.pojo;
/**
 * 车辆警情设置规则
 */
public class VehicleUnitAlarmRule extends VehicleUnit {

	
	private static final long serialVersionUID = -5076258781125441217L;
	
	/** 越界报警状态：0=提醒，1=不提醒*/
	private Integer alarmY;
	/** 断电报警状态：0=提醒，1=不提醒*/
	private Integer alarmD;
	
	public Integer getAlarmY() {
		return alarmY;
	}
	public void setAlarmY(Integer alarmY) {
		this.alarmY = alarmY;
	}
	public Integer getAlarmD() {
		return alarmD;
	}
	public void setAlarmD(Integer alarmD) {
		this.alarmD = alarmD;
	}

	
}
