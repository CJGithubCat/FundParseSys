package com.zsh.labouCapital.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWgAlarm {
	private String statusName;
	private String agencyName;
	private long alarmId;
	private int deelType;
	private String remark;
	private int cusId; 
	private String cusName;
	private int isDeel;
	private int unitId;
	private String unittypeId;
	private String callLetter;
	private String numbrPlate;
	private double lon1;
	private double lon2;
	private double lat1;
	private double lat2;
	private String refer1;
	private String refer2;
	private Date gpstime1;
	private Date gpstime2;
	private Date beginStamp;
	private Date endStamp;
	private int course1;
	private int course2;
	private double speed;
	private Integer speed1;
	private int alarmType;
	private int isDelete;
	private Date addStamp;
	private Date stamp;
	private String  alarmName;
	private String msg;//判断权限查询的标志
    private int customerId;//根用户ID
	private List<String> orgIds=new ArrayList<String>();//子用户机构或子用户的分公司机构ID用户集合
	private List<String> params=new ArrayList<String>();
	
	private String orgName;
	private String plateNo;
	private String attributesPath;
	
	private String beginDate;//开始时间
	private String endDate;//结束时间
	private String areaName;//区域名称
	private String leaseStatus;//车辆状态
	private String area_name;//报警围栏
	/**
	 * 2017-12-20 新增字段
	 * @return
	 */
	private String contract_no;//合同号
	private String work_address;//工作地址
	private String home_address;//家庭住址
	private String driver_name;//客户姓名
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public long getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(long alarmId) {
		this.alarmId = alarmId;
	}
	public int getDeelType() {
		return deelType;
	}
	public void setDeelType(int deelType) {
		this.deelType = deelType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public int getIsDeel() {
		return isDeel;
	}
	public void setIsDeel(int isDeel) {
		this.isDeel = isDeel;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnittypeId() {
		return unittypeId;
	}
	public void setUnittypeId(String unittypeId) {
		this.unittypeId = unittypeId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getNumbrPlate() {
		return numbrPlate;
	}
	public void setNumbrPlate(String numbrPlate) {
		this.numbrPlate = numbrPlate;
	}
	public double getLon1() {
		return lon1;
	}
	public void setLon1(double lon1) {
		this.lon1 = lon1;
	}
	public double getLon2() {
		return lon2;
	}
	public void setLon2(double lon2) {
		this.lon2 = lon2;
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
	public String getRefer1() {
		return refer1;
	}
	public void setRefer1(String refer1) {
		this.refer1 = refer1;
	}
	public String getRefer2() {
		return refer2;
	}
	public void setRefer2(String refer2) {
		this.refer2 = refer2;
	}
	public Date getGpstime1() {
		return gpstime1;
	}
	public void setGpstime1(Date gpstime1) {
		this.gpstime1 = gpstime1;
	}
	public Date getGpstime2() {
		return gpstime2;
	}
	public void setGpstime2(Date gpstime2) {
		this.gpstime2 = gpstime2;
	}
	public Date getBeginStamp() {
		return beginStamp;
	}
	public void setBeginStamp(Date beginStamp) {
		this.beginStamp = beginStamp;
	}
	public Date getEndStamp() {
		return endStamp;
	}
	public void setEndStamp(Date endStamp) {
		this.endStamp = endStamp;
	}
	public int getCourse1() {
		return course1;
	}
	public void setCourse1(int course1) {
		this.course1 = course1;
	}
	public int getCourse2() {
		return course2;
	}
	public void setCourse2(int course2) {
		this.course2 = course2;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Integer getSpeed1() {
		return speed1;
	}
	public void setSpeed1(Integer speed1) {
		this.speed1 = speed1;
	}
	public int getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getAddStamp() {
		return addStamp;
	}
	public void setAddStamp(Date addStamp) {
		this.addStamp = addStamp;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public List<String> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getWork_address() {
		return work_address;
	}
	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	@Override
	public String toString() {
		return "TWgAlarm [statusName=" + statusName + ", agencyName=" + agencyName + ", alarmId=" + alarmId
				+ ", deelType=" + deelType + ", remark=" + remark + ", cusId=" + cusId + ", cusName=" + cusName
				+ ", isDeel=" + isDeel + ", unitId=" + unitId + ", unittypeId=" + unittypeId + ", callLetter="
				+ callLetter + ", numbrPlate=" + numbrPlate + ", lon1=" + lon1 + ", lon2=" + lon2 + ", lat1=" + lat1
				+ ", lat2=" + lat2 + ", refer1=" + refer1 + ", refer2=" + refer2 + ", gpstime1=" + gpstime1
				+ ", gpstime2=" + gpstime2 + ", beginStamp=" + beginStamp + ", endStamp=" + endStamp + ", course1="
				+ course1 + ", course2=" + course2 + ", speed=" + speed + ", speed1=" + speed1 + ", alarmType="
				+ alarmType + ", isDelete=" + isDelete + ", addStamp=" + addStamp + ", stamp=" + stamp + ", alarmName="
				+ alarmName + ", msg=" + msg + ", customerId=" + customerId + ", orgIds=" + orgIds + ", params="
				+ params + ", orgName=" + orgName + ", plateNo=" + plateNo + ", attributesPath=" + attributesPath
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + ", areaName=" + areaName + ", leaseStatus="
				+ leaseStatus + ", area_name=" + area_name + ", contract_no=" + contract_no + ", work_address="
				+ work_address + ", home_address=" + home_address + ", driver_name=" + driver_name + "]";
	}
}
