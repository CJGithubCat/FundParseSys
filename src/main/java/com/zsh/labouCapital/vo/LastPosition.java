package com.zsh.labouCapital.vo;

public class LastPosition {
	private String plateNo;//车牌号码
	private String plateColor;
	private String vehicleType;
	private String callLetter;//车载号码
	private String gpsTime;//最后上报时间
	private String stamp;//更新时间
	private String fixTime;//安装时间
	private String agencyName;//所属机构
	private double lon;//经度
	private double lat;//纬度
	private String lon1;//经度
	private String lat1;//纬度
	private Integer electricity;//电量
	private double totalMile;//总里程
	private String address;
	private String unittype;//终端类型
	private String firstOnlineTime;//终端首次上线时间
	private String statusIds;//车辆点熄火状态
	
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLon1() {
		return lon1;
	}
	public void setLon1(String lon1) {
		this.lon1 = lon1;
	}
	public String getLat1() {
		return lat1;
	}
	public void setLat1(String lat1) {
		this.lat1 = lat1;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}
	public String getFixTime() {
		return fixTime;
	}
	public void setFixTime(String fixTime) {
		this.fixTime = fixTime;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public Integer getElectricity() {
		return electricity;
	}
	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}
	
	public String getUnittype() {
		return unittype;
	}
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	public String getFirstOnlineTime() {
		return firstOnlineTime;
	}
	public void setFirstOnlineTime(String firstOnlineTime) {
		this.firstOnlineTime = firstOnlineTime;
	}
	public String getStatusIds() {
		return statusIds;
	}
	public void setStatusIds(String statusIds) {
		this.statusIds = statusIds;
	}
	
	public double getTotalMile() {
		return totalMile;
	}
	public void setTotalMile(double totalMile) {
		this.totalMile = totalMile;
	}
	@Override
	public String toString() {
		return "LastPosition [plateNo=" + plateNo + ", plateColor=" + plateColor + ", vehicleType=" + vehicleType
				+ ", callLetter=" + callLetter + ", gpsTime=" + gpsTime + ", stamp=" + stamp + ", fixTime=" + fixTime
				+ ", agencyName=" + agencyName + ", lon=" + lon + ", lat=" + lat + ", lon1=" + lon1 + ", lat1=" + lat1
				+ ", electricity=" + electricity + ", totalMile=" + totalMile + ", address=" + address + ", unittype="
				+ unittype + ", firstOnlineTime=" + firstOnlineTime + ", statusIds=" + statusIds + "]";
	}
	
	
}
