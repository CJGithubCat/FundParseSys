package com.zsh.labouCapital.pojo;

/**
 * 用于导出历史数据
 * 
 * @author caoliang
 *
 */
public class ExportHistoryData {
	String plateNumber;// 车牌号码
	String callLetter;// 车载号码
	String speed;// 速度
	String lon;//经度
	String lat;//纬度
	String direction;// 方向
	String gpsMethed;// 定位方式
	String stauts;// 状态
	String gpstime;// 定位时间
	String address;// 位置
	String distance;// 总里程(m)
	String remainOil;//剩余油量(L)
	String electricity;//电量(%)

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

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getGpsMethed() {
		return gpsMethed;
	}

	public void setGpsMethed(String gpsMethed) {
		this.gpsMethed = gpsMethed;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRemainOil() {
		return remainOil;
	}

	public void setRemainOil(String remainOil) {
		this.remainOil = remainOil;
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
    
	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	@Override
	public String toString() {
		return "ExportHistoryData [plateNumber=" + plateNumber + ", callLetter=" + callLetter + ", speed=" + speed
				+ ", lon=" + lon + ", lat=" + lat + ", direction=" + direction + ", gpsMethed=" + gpsMethed
				+ ", stauts=" + stauts + ", gpstime=" + gpstime + ", address=" + address + ", distance=" + distance
				+ ", remainOil=" + remainOil + ", electricity=" + electricity + "]";
	}

}
