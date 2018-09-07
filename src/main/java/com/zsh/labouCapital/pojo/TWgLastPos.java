package com.zsh.labouCapital.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lxb
 * @Decription 最后位置信息实体类
 */
public class TWgLastPos implements Serializable {

	
	private static final long serialVersionUID = -4053455797642184050L;

	/** ID*/
	private Long posId;
	/** 终端ID*/
	private Long unitId;
	/** 车载号码*/
	private String callLetter;
	/** Gps上报时间*/
	private String gpsTime;
	/** 经度*/
	private Double lon;
	/** 纬度*/
	private Double lat;
	/** 速度*/
	private Float speed;
	/** 方向*/
	private int course;
	/** 是否定位： 0=不定位, 1=定位）*/
	private int isLoc;
	/** 状态码*/
	private String statsIds;
	/** 总里程*/
	private int totalMile;
	/** 剩余油量百分比*/
	private int oilPer;
	/** 剩余油量*/
	private int oil;
	/** 是否在线：0=不在线；1=在线*/
	private int isOnline;
	/** 时间戳*/
	private String stamp;
	
	private String unittypeId;//车台类型ID 
	
	private String unitType;//车台类型 
	
	private String agencyAttributesPath;//车辆所属机构的id路径
	
 /**
	*@author zhouwei
	*/
	//车牌号
	private String plateNo;
	//机构名称
	private String subName;
	//统计起始时间
	private Date startTime;
	private Date endTime;
	private String msg;//判断权限查询的标志
	private int customerId;//根用户ID
	private List<String> orgIds=new ArrayList<String>();//子用户机构或子用户的分公司机构ID用户集合
	
	public Long getPosId() {
		return posId;
	}
	public void setPosId(Long posId) {
		this.posId = posId;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public int getIsLoc() {
		return isLoc;
	}
	public void setIsLoc(int isLoc) {
		this.isLoc = isLoc;
	}
	public String getStatsIds() {
		return statsIds;
	}
	public void setStatsIds(String statsIds) {
		this.statsIds = statsIds;
	}
	public int getTotalMile() {
		return totalMile;
	}
	public void setTotalMile(int totalMile) {
		this.totalMile = totalMile;
	}
	public int getOilPer() {
		return oilPer;
	}
	public void setOilPer(int oilPer) {
		this.oilPer = oilPer;
	}
	public int getOil() {
		return oil;
	}
	public void setOil(int oil) {
		this.oil = oil;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}
	public String getUnittypeId() {
		return unittypeId;
	}
	public void setUnittypeId(String unittypeId) {
		this.unittypeId = unittypeId;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getAgencyAttributesPath() {
		return agencyAttributesPath;
	}
	public void setAgencyAttributesPath(String agencyAttributesPath) {
		this.agencyAttributesPath = agencyAttributesPath;
	}
	
}
