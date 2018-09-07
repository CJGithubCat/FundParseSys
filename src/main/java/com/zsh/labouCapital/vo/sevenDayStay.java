package com.zsh.labouCapital.vo;

public class sevenDayStay {
	private String agencyName;//所属机构
	private String callLetter;//车载号码
	private String moveTime;//停留日期
	private String refer;// 车牌号码
	private String plateNo;// 车牌号码
	private String moveLon;// 位置中心点经度
	private String moveLat;// 位置中心点纬度
	
	private String driverName; //客户姓名
	private String homeAddress;//家庭住址
	private String workAddress;//工作地址
	private String contractNo;//合同编号
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

	public String getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(String moveTime) {
		this.moveTime = moveTime;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getMoveLon() {
		return moveLon;
	}

	public void setMoveLon(String moveLon) {
		this.moveLon = moveLon;
	}

	public String getMoveLat() {
		return moveLat;
	}

	public void setMoveLat(String moveLat) {
		this.moveLat = moveLat;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Override
	public String toString() {
		return "sevenDayStay [agencyName=" + agencyName + ", callLetter=" + callLetter + ", moveTime=" + moveTime
				+ ", refer=" + refer + ", plateNo=" + plateNo + ", moveLon=" + moveLon + ", moveLat=" + moveLat
				+ ", driverName=" + driverName + ", homeAddress=" + homeAddress + ", workAddress=" + workAddress
				+ ", contractNo=" + contractNo + "]";
	}

}
