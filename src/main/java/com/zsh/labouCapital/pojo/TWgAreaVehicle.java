package com.zsh.labouCapital.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class TWgAreaVehicle {
	private int id;
	private int areaId;
	private int ruleId;
	private int cusId;
	private String vehicleId;//多个以，号隔开进行批量添加
	private String callLetter;
	private Timestamp stamp;
	
	
	private String ruleName;//规则名称
	private String areaName;//关键点名称
	private String plateNo;//车牌号码
	
	private String isOnline;//是否在线
	private String isInArea;//是否在规定的区域
	//实时车辆点坐标
	private double lon;
	private double lat;
	
	//区域坐标
	private int shapeType;
	private double lon1;
	private double lat1;
	private double lon2;
	private double lat2;
	private String centre;
	private int rad;
	private String point;//多边形顶点
	
	
	//驾驶员信息
	private String driverName;
	private String workNo;
	private int sex;
	private String phone;
	private String idNo;//身份证号
	private String scNo;
	private Date scEdate;
	private String regNo;
	private String regEdate;
	private String drNo;
	private String drClass;
	private Date drEdate;
	private String subcoName;//所属机构
	//最后位置信息
	private double speed;//速度
	private int course;//方向
	private Date gpsTime;
	private double oil;//油量
	private int totalMile;//总里程
	//判断命令字段
	private int preCmd;
	private int unitId; 
	
	
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
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
	public double getLon2() {
		return lon2;
	}
	public void setLon2(double lon2) {
		this.lon2 = lon2;
	}
	public double getLat2() {
		return lat2;
	}
	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}
	public String getIsInArea() {
		return isInArea;
	}
	public void setIsInArea(String isInArea) {
		this.isInArea = isInArea;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getDrNo() {
		return drNo;
	}
	public void setDrNo(String drNo) {
		this.drNo = drNo;
	}
	public String getDrClass() {
		return drClass;
	}
	public void setDrClass(String drClass) {
		this.drClass = drClass;
	}
	public Date getDrEdate() {
		return drEdate;
	}
	public void setDrEdate(Date drEdate) {
		this.drEdate = drEdate;
	}
	public String getSubcoName() {
		return subcoName;
	}
	public void setSubcoName(String subcoName) {
		this.subcoName = subcoName;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public Date getScEdate() {
		return scEdate;
	}
	public void setScEdate(Date scEdate) {
		this.scEdate = scEdate;
	}
	public String getRegEdate() {
		return regEdate;
	}
	public void setRegEdate(String regEdate) {
		this.regEdate = regEdate;
	}
	public Date getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}
	public double getOil() {
		return oil;
	}
	public void setOil(double oil) {
		this.oil = oil;
	}
	public int getTotalMile() {
		return totalMile;
	}
	public void setTotalMile(int totalMile) {
		this.totalMile = totalMile;
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
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public int getShapeType() {
		return shapeType;
	}
	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}
	public int getPreCmd() {
		return preCmd;
	}
	public void setPreCmd(int preCmd) {
		this.preCmd = preCmd;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	
	
	
	
}
