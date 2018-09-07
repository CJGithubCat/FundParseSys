package com.zsh.labouCapital.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 机构车辆列表
 */
public class OrgVehicle implements Serializable {

	
	private static final long serialVersionUID = -1123171686697471225L;
	
	/** 机构编号*/
	private  String  id;
	/** 机构名称*/
	private  String  text;
	/** 机构车辆警情设置信息列表*/
	private List<VehicleUnitAlarmRule> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<VehicleUnitAlarmRule> getData() {
		return data;
	}

	public void setData(List<VehicleUnitAlarmRule> data) {
		this.data = data;
	}
	

}
