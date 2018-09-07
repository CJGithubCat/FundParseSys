package com.zsh.labouCapital.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWgAreapoint {
	private int areaId;
	private int  cusId;
	private int departId;
	private String areaName;
	private int shapeType;
	private double lon1;
	private double lon2;
	private double lat1;
	private double lat2;
	private String point;
	private String centre;
	private int rad;
	private int editCusId;
	private int zoonState;
	private int validState;
	private Date add_time;
	private Date stamp;
	private String mapZoom;
	
	private List<String> orgs=new ArrayList<String>();
	
	//客户ID
	private int customerId;
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getShapeType() {
		return shapeType;
	}
	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}

	public double getLon2() {
		return lon2;
	}
	public void setLon2(double lon2) {
		this.lon2 = lon2;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public int getEditCusId() {
		return editCusId;
	}
	public void setEditCusId(int editCusId) {
		this.editCusId = editCusId;
	}
	public int getZoonState() {
		return zoonState;
	}
	public void setZoonState(int zoonState) {
		this.zoonState = zoonState;
	}
	public int getValidState() {
		return validState;
	}
	public void setValidState(int validState) {
		this.validState = validState;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public double getLon1() {
		return lon1;
	}
	public void setLon1(double lon1) {
		this.lon1 = lon1;
	}
	public double getLat1() {
		return lat1;
	}
	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}
	public double getLat2() {
		return lat2;
	}
	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public int getRad() {
		return rad;
	}
	public void setRad(int rad) {
		this.rad = rad;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public List<String> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<String> orgs) {
		this.orgs = orgs;
	}
	public String getMapZoom() {
		return mapZoom;
	}
	public void setMapZoom(String mapZoom) {
		this.mapZoom = mapZoom;
	}
	

}
