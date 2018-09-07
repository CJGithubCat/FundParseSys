package com.zsh.labouCapital.pojo;

/**
 * 常停点分析实体类
 * @author chenchanglin
 * @creation 2018年5月7日20:47:51
 */
public class TwgCtdfx {

	private String tDate;//统计日期
	private int agencyId;//所属机构ID
	private String agencyName;//所属机构名称
	private String numberPlate;//车牌号码
	private String callLetter;//车载号码
	private String parkingPosition;//停车地址
	private String refer;//参考位置
	private int numBetweenHour_24;//停车2-4小时次数
	private int numBetweenHour_46;//停车4-6小时次数
	private int numAboveHour_6;//停车大于6小时次数
	private String createTime;//创建时间
	private String updateTime;//更新时间
	
	
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	public int getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getParkingPosition() {
		return parkingPosition;
	}
	public void setParkingPosition(String parkingPosition) {
		this.parkingPosition = parkingPosition;
	}
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
	public int getNumBetweenHour_24() {
		return numBetweenHour_24;
	}
	public void setNumBetweenHour_24(int numBetweenHour_24) {
		this.numBetweenHour_24 = numBetweenHour_24;
	}
	public int getNumBetweenHour_46() {
		return numBetweenHour_46;
	}
	public void setNumBetweenHour_46(int numBetweenHour_46) {
		this.numBetweenHour_46 = numBetweenHour_46;
	}
	public int getNumAboveHour_6() {
		return numAboveHour_6;
	}
	public void setNumAboveHour_6(int numAboveHour_6) {
		this.numAboveHour_6 = numAboveHour_6;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
