package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 报表
 * @author caoliang
 *
 */
public class TWgLCTJ implements Serializable {
	private static final long serialVersionUID = -4053455797642184050L;
	private String unitid;
	private String number_plate;//车牌号码
	private String callLetter;//车载号码
	private String plateColor;//车辆颜色
	private String agencyName;//机构名称
	private String lc;//里程
	private Integer distance;//行驶距离
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String timeStart;//开始时间
	private String timeEnd;//结束时间
	private String attributesPath;//所属机构路径
	private String replace_date;//电池更换时间
	private String number;//上报条数
	private String leaseStatus;//车辆状态
	private String first_online_time;//第一次上线时间
	private String unitType;//终端类型
	private String productType;//产品类型
	private String imei;//设备号
	
	/**
	 * 2017-12-20 新增字段
	 * @return
	 */
	private String contract_no;//合同号
	private String work_address;//工作地址
	private String home_address;//家庭住址
	private String driver_name;//客户姓名
	private String occupation;//职业

	
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
	public String getFirst_online_time() {
		return first_online_time;
	}
	public void setFirst_online_time(String first_online_time) {
		this.first_online_time = first_online_time;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getReplace_date() {
		return replace_date;
	}
	public void setReplace_date(String replace_date) {
		this.replace_date = replace_date;
	}
	public String getNumber_plate() {
		return number_plate;
	}
	public void setNumber_plate(String number_plate) {
		this.number_plate = number_plate;
	}

	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
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
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
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
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@Override
	public String toString() {
		return "TWgLCTJ [unitid=" + unitid + ", number_plate=" + number_plate + ", callLetter=" + callLetter
				+ ", plateColor=" + plateColor + ", agencyName=" + agencyName + ", lc=" + lc + ", distance=" + distance
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", timeStart=" + timeStart + ", timeEnd="
				+ timeEnd + ", attributesPath=" + attributesPath + ", replace_date=" + replace_date + ", number="
				+ number + ", leaseStatus=" + leaseStatus + ", first_online_time=" + first_online_time + ", unitType="
				+ unitType + ", productType=" + productType + ", imei=" + imei + ", contract_no=" + contract_no
				+ ", work_address=" + work_address + ", home_address=" + home_address + ", driver_name=" + driver_name
				+ ", occupation=" + occupation + "]";
	}
}
