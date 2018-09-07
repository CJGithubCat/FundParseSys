package com.zsh.labouCapital.pojo;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class LastPos {
	private String  plateNo;
	private String  callLetter;
	private String   speed;
	private String  course;
	private String  status;//车辆状态 熄火 点火
	private String[] statusArray;  
	private Date  gpsTime;
	private String  position;//参考位置
	private String  lon;//
	private String  lat;//
	private BigDecimal  lastLon;
	private BigDecimal  lastLat;
	private int  accTimeLen;//ACC时长
	private int totalMile;//
	private int electricity;
	private String isLoc;
	private int unittypeId;//终端类型
	
	
	
	public int getUnittypeId() {
		return unittypeId;
	}
	public void setUnittypeId(int unittypeId) {
		this.unittypeId = unittypeId;
	}
	public String getIsLoc() {
		return isLoc;
	}
	public void setIsLoc(String isLoc) {
		this.isLoc = isLoc;
	}
	public BigDecimal getLastLon() {
		return lastLon;
	}
	public void setLastLon(BigDecimal lastLon) {
		this.lastLon = lastLon;
	}
	public BigDecimal getLastLat() {
		return lastLat;
	}
	public void setLastLat(BigDecimal lastLat) {
		this.lastLat = lastLat;
	}
	public String[] getStatusArray() {
		return statusArray;
	}
	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}
	public int getElectricity() {
		return electricity;
	}
	public void setElectricity(int electricity) {
		this.electricity = electricity;
	}
	
	public int getTotalMile() {
		return totalMile;
	}
	public void setTotalMile(int totalMile) {
		this.totalMile = totalMile;
	}
	public int getAccTimeLen() {
		return accTimeLen;
	}
	public void setAccTimeLen(int accTimeLen) {
		this.accTimeLen = accTimeLen;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
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
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "LastPos [plateNo=" + plateNo + ", callLetter=" + callLetter + ", speed=" + speed + ", course=" + course
				+ ", status=" + status + ", statusArray=" + Arrays.toString(statusArray) + ", gpsTime=" + gpsTime
				+ ", position=" + position + ", lon=" + lon + ", lat=" + lat + ", lastLon=" + lastLon + ", lastLat="
				+ lastLat + ", accTimeLen=" + accTimeLen + ", totalMile=" + totalMile + ", electricity=" + electricity
				+ ", isLoc=" + isLoc + ", unittypeId=" + unittypeId + "]";
	}
	
}
