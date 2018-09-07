package com.zsh.labouCapital.vo;

public class WtwsjVo {
	private String plateNo;//车牌号码
	private String plateColor;
	private String vehicleType;
	private String callLetter;//车载号码
	private String gpsTime;//最后上报时间
	private String fixTime;//安装时间
	private String agencyName;//所属机构
	private double lon;//经度
	private double lat;//纬度
	private String lon1;//经度
	private String lat1;//纬度
	private Integer electricity;//电量
	private String vehicleRiskState;//风控状态
	private String refer;//参考位置
	private String leaseStatus;//车辆状态
	private String unitType;//终端类型
	/**
	 * 2017-12-20 新增字段
	 * @return
	 */
	
	private String contractNo;//合同号
	private String driverName;//客户姓名
	private String lastAlarmTime;//最后一次警情时间
	private String statusName;//警情名称
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
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
	public String getVehicleRiskState() {
		return vehicleRiskState;
	}
	public void setVehicleRiskState(String vehicleRiskState) {
		this.vehicleRiskState = vehicleRiskState;
	}
	
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getLastAlarmTime() {
		return lastAlarmTime;
	}
	public void setLastAlarmTime(String lastAlarmTime) {
		this.lastAlarmTime = lastAlarmTime;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Override
	public String toString() {
		return "WtwsjVo [plateNo=" + plateNo + ", plateColor=" + plateColor + ", vehicleType=" + vehicleType
				+ ", callLetter=" + callLetter + ", gpsTime=" + gpsTime + ", fixTime=" + fixTime + ", agencyName="
				+ agencyName + ", lon=" + lon + ", lat=" + lat + ", lon1=" + lon1 + ", lat1=" + lat1 + ", electricity="
				+ electricity + ", vehicleRiskState=" + vehicleRiskState + ", refer=" + refer + ", leaseStatus="
				+ leaseStatus + ", unitType=" + unitType + ", contractNo=" + contractNo + ", driverName=" + driverName
				+ ", lastAlarmTime=" + lastAlarmTime + ", statusName=" + statusName + "]";
	}
	
}
