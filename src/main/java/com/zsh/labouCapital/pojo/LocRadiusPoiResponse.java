package com.zsh.labouCapital.pojo;

public class LocRadiusPoiResponse {
	int Status = 0;
	String Cause = "";
	String Map = "";
	Double Lat = 0.0;
	Double Lon = 0.0;
	String Address = "";
	int radius;
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getCause() {
		return Cause;
	}
	public void setCause(String cause) {
		Cause = cause;
	}
	public String getMap() {
		return Map;
	}
	public void setMap(String map) {
		Map = map;
	}
	public Double getLat() {
		return Lat;
	}
	public void setLat(Double lat) {
		Lat = lat;
	}
	public Double getLon() {
		return Lon;
	}
	public void setLon(Double lon) {
		Lon = lon;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
}
