package com.zsh.labouCapital.pojo;

public class TWgXCTJ{
	private String travelRecordId;            //
	private String attributesPath;			//机构路径
	private String serialNumber;            //行程序号（0-255）
	private String unitId;            //终端ID
	private String numberplate;			//车牌号码
	private String callLetter;            //设备呼号
	private String startTime;            //行程开始时间
	private String endTime;            //行程结束时间
	private String distance;            //行程里程（单位:米）
	private String totalOil;            //行程总油耗（单位:0.01升）
	private String runningTime;            //行驶时长（单位:秒）
	private String remainingOil;            //剩余油量（单位:毫升）
	private String idleTime;            //怠速时长（单位:秒）
	private String quickSpeedTurnCount;            //急转弯次数
	private String quickSpeedUpCount;            //急加速次数
	private String quickBrakeCount;            //急刹车次数
	private String emergencyBrakeCount;            //紧急刹车次数
	private String emergencySpeedUpCount;            //紧急加速次数
	private String maxSpeed;            //最大速度（单位:0.1千米/小时）
	private String averageSpeed;            //平均速度（单位:0.1千米/小时）
	private String overSpeedTime;            //超速时长（单位:秒）
	private String maxWaterTemperature;            //发动机最高水温（单位:℃）
	private String maxRotationSpeed;            //发动机最高工作转速（单位:转/分）
	private String voltage;            //电压值（单位:0.1V）
	private String averageOil;            //平均油耗（单位:0.01升/百公里）
	private String tiredDrivingTime;            //疲劳驾驶时长（单位:秒）
	private String averageRotationSpeed;            //平均转速（单位:转/分）
	private String maxOil;            //最高瞬时油耗（单位:0.01升/百公里）
	private String startLng;            //行程开始的经度
	private String startLat;            //行程开始的纬度
	private String startPosition;            //行程开始的参考位置
	private String endLng;            //行程结束的经度
	private String endLat;            //行程结束的纬度
	private String endPosition;            //行程结束的参考位置
	private String totalMile;			//总里程
	private String runCount;			//行驶次数
	private String modelName;				//汽车型号
	private String agencyName;			//所属机构
	private String agencyPname;		//所属账号
	private String refer;	//参考位置
	
	public String getTravelRecordId() {
		return travelRecordId;
	}
	public void setTravelRecordId(String travelRecordId) {
		this.travelRecordId = travelRecordId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
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
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getTotalOil() {
		return totalOil;
	}
	public void setTotalOil(String totalOil) {
		this.totalOil = totalOil;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	public String getRemainingOil() {
		return remainingOil;
	}
	public void setRemainingOil(String remainingOil) {
		this.remainingOil = remainingOil;
	}
	public String getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(String idleTime) {
		this.idleTime = idleTime;
	}
	public String getQuickSpeedTurnCount() {
		return quickSpeedTurnCount;
	}
	public void setQuickSpeedTurnCount(String quickSpeedTurnCount) {
		this.quickSpeedTurnCount = quickSpeedTurnCount;
	}
	public String getQuickSpeedUpCount() {
		return quickSpeedUpCount;
	}
	public void setQuickSpeedUpCount(String quickSpeedUpCount) {
		this.quickSpeedUpCount = quickSpeedUpCount;
	}
	public String getQuickBrakeCount() {
		return quickBrakeCount;
	}
	public void setQuickBrakeCount(String quickBrakeCount) {
		this.quickBrakeCount = quickBrakeCount;
	}
	public String getEmergencyBrakeCount() {
		return emergencyBrakeCount;
	}
	public void setEmergencyBrakeCount(String emergencyBrakeCount) {
		this.emergencyBrakeCount = emergencyBrakeCount;
	}
	public String getEmergencySpeedUpCount() {
		return emergencySpeedUpCount;
	}
	public void setEmergencySpeedUpCount(String emergencySpeedUpCount) {
		this.emergencySpeedUpCount = emergencySpeedUpCount;
	}
	public String getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(String maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(String averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public String getOverSpeedTime() {
		return overSpeedTime;
	}
	public void setOverSpeedTime(String overSpeedTime) {
		this.overSpeedTime = overSpeedTime;
	}
	public String getMaxWaterTemperature() {
		return maxWaterTemperature;
	}
	public void setMaxWaterTemperature(String maxWaterTemperature) {
		this.maxWaterTemperature = maxWaterTemperature;
	}
	public String getMaxRotationSpeed() {
		return maxRotationSpeed;
	}
	public void setMaxRotationSpeed(String maxRotationSpeed) {
		this.maxRotationSpeed = maxRotationSpeed;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getAverageOil() {
		return averageOil;
	}
	public void setAverageOil(String averageOil) {
		this.averageOil = averageOil;
	}
	public String getTiredDrivingTime() {
		return tiredDrivingTime;
	}
	public void setTiredDrivingTime(String tiredDrivingTime) {
		this.tiredDrivingTime = tiredDrivingTime;
	}
	public String getAverageRotationSpeed() {
		return averageRotationSpeed;
	}
	public void setAverageRotationSpeed(String averageRotationSpeed) {
		this.averageRotationSpeed = averageRotationSpeed;
	}
	public String getMaxOil() {
		return maxOil;
	}
	public void setMaxOil(String maxOil) {
		this.maxOil = maxOil;
	}
	public String getStartLng() {
		return startLng;
	}
	public void setStartLng(String startLng) {
		this.startLng = startLng;
	}
	public String getStartLat() {
		return startLat;
	}
	public void setStartLat(String startLat) {
		this.startLat = startLat;
	}
	public String getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}
	public String getEndLng() {
		return endLng;
	}
	public void setEndLng(String endLng) {
		this.endLng = endLng;
	}
	public String getEndLat() {
		return endLat;
	}
	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}
	public String getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(String endPosition) {
		this.endPosition = endPosition;
	}
	public String getTotalMile() {
		return totalMile;
	}
	public void setTotalMile(String totalMile) {
		this.totalMile = totalMile;
	}
	
	public String getRunCount() {
		return runCount;
	}
	public void setRunCount(String runCount) {
		this.runCount = runCount;
	}
	
	public String getNumberplate() {
		return numberplate;
	}
	public void setNumberplate(String numberplate) {
		this.numberplate = numberplate;
	}
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	public String getAgencyPname() {
		return agencyPname;
	}
	public void setAgencyPname(String agencyPname) {
		this.agencyPname = agencyPname;
	}
	
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
	@Override
	public String toString() {
		return "TWgXCTJ [travelRecordId=" + travelRecordId + ", attributesPath=" + attributesPath + ", serialNumber="
				+ serialNumber + ", unitId=" + unitId + ", numberplate=" + numberplate + ", callLetter=" + callLetter
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", distance=" + distance + ", totalOil="
				+ totalOil + ", runningTime=" + runningTime + ", remainingOil=" + remainingOil + ", idleTime="
				+ idleTime + ", quickSpeedTurnCount=" + quickSpeedTurnCount + ", quickSpeedUpCount=" + quickSpeedUpCount
				+ ", quickBrakeCount=" + quickBrakeCount + ", emergencyBrakeCount=" + emergencyBrakeCount
				+ ", emergencySpeedUpCount=" + emergencySpeedUpCount + ", maxSpeed=" + maxSpeed + ", averageSpeed="
				+ averageSpeed + ", overSpeedTime=" + overSpeedTime + ", maxWaterTemperature=" + maxWaterTemperature
				+ ", maxRotationSpeed=" + maxRotationSpeed + ", voltage=" + voltage + ", averageOil=" + averageOil
				+ ", tiredDrivingTime=" + tiredDrivingTime + ", averageRotationSpeed=" + averageRotationSpeed
				+ ", maxOil=" + maxOil + ", startLng=" + startLng + ", startLat=" + startLat + ", startPosition="
				+ startPosition + ", endLng=" + endLng + ", endLat=" + endLat + ", endPosition=" + endPosition
				+ ", totalMile=" + totalMile + ", runCount=" + runCount + ", modelName=" + modelName + ", agencyName="
				+ agencyName + ", agencyPname=" + agencyPname + "]";
	}
	
}
