package com.zsh.labouCapital.pojo;

public class TWgInstallSh {
	private String workId;			//主键安装信息id
	private String agencyId;        //机构id   
	private String azType;			//安装类型 1自行安装 2工单安装
	private String plateNo;			//车牌号
	private String callLetter;		//SIM卡号  
//	private String userName;        //车主姓名                                        
//	private String sqbh;            //申请编号                                        
	private String cjh;             //车架号                                          
//	private String installAdd;      //安装地址                                        
//	private String installTime;     //安装日期                                        
//	private String tradingTime;     //服务期限(单位：月)                              
	private String stamp;           //入库时间                                        
	private String workerId;        //电工id   .
	private String workerName;	    //电工姓名
	private String vehicleId;       //车辆ID                                          
	private String validateState;   //审核状态 0:未审核；1:审核通过;2:审核未通过      
	private String valitime;        //审核时间         
	private String shNoReason;		//审核不通过理由
	private String oldShNoReason;	//审核不通过历史理由
	private String zcType;			//装车状态 1:单机装车 2:双机装机
	private String picture1;        //图片1(路径,存储在装车微信阿里云服务器上)        
	private String picture2;        //图片2(路径,存储在装车微信阿里云服务器上)        
	private String picture3;        //图片3(路径,存储在装车微信阿里云服务器上)        
	private String picture4;        //图片4(路径,存储在装车微信阿里云服务器上)   
	private String picture5;        //图片5(路径,存储在装车微信阿里云服务器上)   
	private String picture6;        //图片6(路径,存储在装车微信阿里云服务器上)   
	private String picture7;        //图片7(路径,存储在装车微信阿里云服务器上)   
	private String picture8;        //图片8(路径,存储在装车微信阿里云服务器上)   
	private String picture9;        //图片9(路径,存储在装车微信阿里云服务器上)   
	private String picture10;       //图片10(路径,存储在装车微信阿里云服务器上)   
	private String pictures; 		//图片拼接成html格式
	
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getAzType() {
		return azType;
	}
	public void setAzType(String azType) {
		this.azType = azType;
	}
	public String getCjh() {
		return cjh;
	}
	public void setCjh(String cjh) {
		this.cjh = cjh;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getValidateState() {
		return validateState;
	}
	public void setValidateState(String validateState) {
		this.validateState = validateState;
	}
	public String getValitime() {
		return valitime;
	}
	public void setValitime(String valitime) {
		this.valitime = valitime;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}
	
	public String getPicture5() {
		return picture5;
	}
	public void setPicture5(String picture5) {
		this.picture5 = picture5;
	}
	public String getPicture6() {
		return picture6;
	}
	public void setPicture6(String picture6) {
		this.picture6 = picture6;
	}
	public String getPicture7() {
		return picture7;
	}
	public void setPicture7(String picture7) {
		this.picture7 = picture7;
	}
	public String getPicture8() {
		return picture8;
	}
	public void setPicture8(String picture8) {
		this.picture8 = picture8;
	}
	public String getPicture9() {
		return picture9;
	}
	public void setPicture9(String picture9) {
		this.picture9 = picture9;
	}
	public String getPicture10() {
		return picture10;
	}
	public void setPicture10(String picture10) {
		this.picture10 = picture10;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public String getShNoReason() {
		return shNoReason;
	}
	public void setShNoReason(String shNoReason) {
		this.shNoReason = shNoReason;
	}
	public String getOldShNoReason() {
		return oldShNoReason;
	}
	public void setOldShNoReason(String oldShNoReason) {
		this.oldShNoReason = oldShNoReason;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getZcType() {
		return zcType;
	}
	public void setZcType(String zcType) {
		this.zcType = zcType;
	}
	@Override
	public String toString() {
		return "TWgInstallSh [workId=" + workId + ", agencyId=" + agencyId + ", azType=" + azType + ", plateNo="
				+ plateNo + ", callLetter=" + callLetter + ", cjh=" + cjh + ", stamp=" + stamp + ", workerId="
				+ workerId + ", workerName=" + workerName + ", vehicleId=" + vehicleId + ", validateState="
				+ validateState + ", valitime=" + valitime + ", shNoReason=" + shNoReason + ", oldShNoReason="
				+ oldShNoReason + ", zcType=" + zcType + ", picture1=" + picture1 + ", picture2=" + picture2
				+ ", picture3=" + picture3 + ", picture4=" + picture4 + ", picture5=" + picture5 + ", picture6="
				+ picture6 + ", picture7=" + picture7 + ", picture8=" + picture8 + ", picture9=" + picture9
				+ ", picture10=" + picture10 + ", pictures=" + pictures + "]";
	}
	
}
