package com.zsh.labouCapital.vo;

public class VehicleJuJiBaoJing {
	private String plateNo;// 车牌号码
	private int plateNoNum;// 车辆数目
	private double rlon;// 位置中心点经度
	private double rlat;// 位置中心点纬度

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public int getPlateNoNum() {
		return plateNoNum;
	}

	public void setPlateNoNum(int plateNoNum) {
		this.plateNoNum = plateNoNum;
	}

	public double getRlon() {
		return rlon;
	}

	public void setRlon(double rlon) {
		this.rlon = rlon;
	}

	public double getRlat() {
		return rlat;
	}

	public void setRlat(double rlat) {
		this.rlat = rlat;
	}

	@Override
	public String toString() {
		return "VehicleJuJiBaoJing [plateNo=" + plateNo + ", plateNoNum=" + plateNoNum + ", rlon=" + rlon
				+ ", rlat=" + rlat + "]";
	}
}
