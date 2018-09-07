package com.zsh.labouCapital.pojo;
/**
 * 用于导出点熄火数据
 * @author zhangq
 *
 */

public class StatusHistoryData {
	private String  plateNumber;//车牌号码
	private String callLetter;//车载号码
	private String agencyName;//机构名称
	private String agencyId;//机构id
	private String accTime;//点火时间   状态(33)
	private String offTime;//熄火时间   状态(23)
	private String count;//点熄火次数
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getAccTime() {
		return accTime;
	}
	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}
	public String getOffTime() {
		return offTime;
	}
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "StatusHistoryData [plateNumber=" + plateNumber + ", callLetter=" + callLetter + ", agencyName="
				+ agencyName + ", agencyId=" + agencyId + ", accTime=" + accTime + ", offTime=" + offTime + "]";
	}
	
}
