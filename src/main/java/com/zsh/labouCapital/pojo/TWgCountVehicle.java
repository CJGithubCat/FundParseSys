package com.zsh.labouCapital.pojo;

import java.util.Date;

/**
 * @Package:com.chinagps.webgis.pojo TWgCountVehicle
 * 类功能：首页展示数据类;
 */
public class TWgCountVehicle {
	private String posId;//最后位置记录id
	private String plateNo;// 车牌号码
	private String plateColor;// 车牌颜色
	private String agencyName;//机构名称
	private String callLetter;// 车载号码
	private String fixTime;// 安装时间
	private String gspTime;//最后上报时间
	private String newAddtime;//车辆新增时间tbu.stamp
	private String squareDate; //还清贷款时间
	private Date squareDate1;
	private String attributesPath;//机构路径
	private Long vehicleType;// 车型
	private String vehicleType1;
	private Long vehicleId;// 车辆id
	private Long unitId;// 车台id
	private Long unittypeId;
	private Long agencyId;
	private Long customerId;// 客户id
	private String vehicleColor;//车辆颜色
	private String vehicleStatus;//风险状况
	private Date month;//月份  用來查詢某月新增上线的车辆数
	private String lease_status;//车辆状态
	private String unittype;//终端类型
	private String imei;//设备号
	private String productType;//设备号
	private String lon;
	private String lat;
	private String refer;//参考位置
	private String countType;
	
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
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
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getFixTime() {
		return fixTime;
	}
	public void setFixTime(String fixTime) {
		this.fixTime = fixTime;
	}
	public String getGspTime() {
		return gspTime;
	}
	public void setGspTime(String gspTime) {
		this.gspTime = gspTime;
	}
	public String getNewAddtime() {
		return newAddtime;
	}
	public void setNewAddtime(String newAddtime) {
		this.newAddtime = newAddtime;
	}
	public Date getSquareDate1() {
		return squareDate1;
	}
	public void setSquareDate1(Date squareDate1) {
		this.squareDate1 = squareDate1;
	}
	public String getSquareDate() {
		return squareDate;
	}
	public void setSquareDate(String squareDate) {
		this.squareDate = squareDate;
	}

	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public Long getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Long vehicleType) {
		this.vehicleType = vehicleType;
	}
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
	public Long getUnittypeId() {
		return unittypeId;
	}
	public void setUnittypeId(Long unittypeId) {
		this.unittypeId = unittypeId;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getVehicleType1() {
		return vehicleType1;
	}
	public void setVehicleType1(String vehicleType1) {
		this.vehicleType1 = vehicleType1;
	}
	
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
	public String getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	
	public String getLease_status() {
		return lease_status;
	}
	public void setLease_status(String lease_status) {
		this.lease_status = lease_status;
	}
	
	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	
	public String getRefer() {
		return refer;
	}
	
	public void setRefer(String refer) {
		this.refer = refer;
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
	@Override
	public String toString() {
		return "TWgCountVehicle [plateNo=" + plateNo + ", plateColor=" + plateColor + ", agencyName=" + agencyName
				+ ", callLetter=" + callLetter + ", fixTime=" + fixTime + ", gspTime=" + gspTime + ", newAddtime="
				+ newAddtime + ", squareDate=" + squareDate + ", squareDate1=" + squareDate1 + ", attributesPath="
				+ attributesPath + ", vehicleType=" + vehicleType + ", vehicleType1=" + vehicleType1 + ", vehicleId="
				+ vehicleId + ", unitId=" + unitId + ", unittypeId=" + unittypeId + ", agencyId=" + agencyId
				+ ", customerId=" + customerId + ", vehicleColor=" + vehicleColor + ", vehicleStatus=" + vehicleStatus
				+ ", month=" + month + ", lease_status=" + lease_status + ", unittype=" + unittype + ", imei=" + imei
				+ ", productType=" + productType + ", lon=" + lon + ", lat=" + lat + ", refer=" + refer + "]";
	}
	
}
