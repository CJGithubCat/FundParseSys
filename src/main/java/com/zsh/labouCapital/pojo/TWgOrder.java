package com.zsh.labouCapital.pojo;

public class TWgOrder {
	private String orderId; // 订单id
	private Integer orderSource; // 订单途径:1-PC端,2-移动端       
	private String orderType; //订单类型：1-新增订单，2-维修/拆机订单    
	private Long agencyId; // 机构id
	private String agencyName; // 机构名称
	private String contactsName; // 联系人姓名
	private String contactsPhone; // 联系人电话
	private String customerName; // 客户名称
	private String visitTime; // 上门时间
	private String provinceId; // 省
	private String cityId; // 市
	private String countyId; // 区
	private String detailArea; // 详细地址
	private String vin; // 车架号
	private Long gpsInstallType; // GPS报装类型：1-单有线，2-单无线，3-单有线+单无线，4-单有线+2无线，5-单有线+3无线
	private Long gpsServiceType; // GPS维修类型：1-有线，2-无线，3-有线+无线  
	private String serviceReason; //维修原因 
	private String gpsInstallLocation; //gps安装位置    
	private String vDesc; // 备注栏
	private String vDesc2; // 备用字段 
	private String vType; // 车辆型号
	private String orderStatus; // 订单状态：1-派单中，2-已接单，3-安装，4-完成
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private String callLetterYX; //有线卡号（逗号分隔）
	private String callLetterWX; //无线卡号（逗号分隔）
	private String picture1; // 图片1(路径,存储在装车微信阿里云服务器上)
	private String picture2; // 图片2(路径,存储在装车微信阿里云服务器上)
	private String picture3; // 图片3(路径,存储在装车微信阿里云服务器上)
	private String picture4; // 图片4(路径,存储在装车微信阿里云服务器上)
	private String picture5; // 图片5(路径,存储在装车微信阿里云服务器上)
	private String picture6; // 图片6(路径,存储在装车微信阿里云服务器上)
	private String picture7; // 图片7(路径,存储在装车微信阿里云服务器上)
	private String picture8; // 图片8(路径,存储在装车微信阿里云服务器上)
	private String picture9; // 图片9(路径,存储在装车微信阿里云服务器上)

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}
	public String getContactsName() {
		return contactsName;
	}
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	public String getContactsPhone() {
		return contactsPhone;
	}
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getDetailArea() {
		return detailArea;
	}
	public void setDetailArea(String detailArea) {
		this.detailArea = detailArea;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getvDesc() {
		return vDesc;
	}
	public void setvDesc(String vDesc) {
		this.vDesc = vDesc;
	}
	public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
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
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public Long getGpsInstallType() {
		return gpsInstallType;
	}
	public void setGpsInstallType(Long gpsInstallType) {
		this.gpsInstallType = gpsInstallType;
	}
	public Long getGpsServiceType() {
		return gpsServiceType;
	}
	public void setGpsServiceType(Long gpsServiceType) {
		this.gpsServiceType = gpsServiceType;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getOrderSource() {
		return orderSource;
	}
	public String getServiceReason() {
		return serviceReason;
	}
	public void setServiceReason(String serviceReason) {
		this.serviceReason = serviceReason;
	}
	public String getGpsInstallLocation() {
		return gpsInstallLocation;
	}
	public void setGpsInstallLocation(String gpsInstallLocation) {
		this.gpsInstallLocation = gpsInstallLocation;
	}
	public String getvDesc2() {
		return vDesc2;
	}
	public void setvDesc2(String vDesc2) {
		this.vDesc2 = vDesc2;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getCallLetterYX() {
		return callLetterYX;
	}
	public void setCallLetterYX(String callLetterYX) {
		this.callLetterYX = callLetterYX;
	}
	public String getCallLetterWX() {
		return callLetterWX;
	}
	public void setCallLetterWX(String callLetterWX) {
		this.callLetterWX = callLetterWX;
	}
	@Override
	public String toString() {
		return "TWgOrder [orderId=" + orderId + ", orderSource=" + orderSource + ", orderType=" + orderType + ", agencyId=" + agencyId + ", agencyName="
				+ agencyName + ", contactsName=" + contactsName + ", contactsPhone=" + contactsPhone + ", customerName="
				+ customerName + ", visitTime=" + visitTime + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", countyId=" + countyId + ", detailArea=" + detailArea + ", vin=" + vin + ", gpsInstallType="
				+ gpsInstallType + ", gpsServiceType=" + gpsServiceType + ", serviceReason=" + serviceReason
				+ ", gpsInstallLocation=" + gpsInstallLocation + ", vDesc=" + vDesc + ", vDesc2=" + vDesc2 + ", vType="
				+ vType + ", orderStatus=" + orderStatus + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", callLetterYX=" + callLetterYX + ", callLetterWX=" + callLetterWX + ", picture1=" + picture1
				+ ", picture2=" + picture2 + ", picture3=" + picture3 + ", picture4=" + picture4 + ", picture5="
				+ picture5 + ", picture6=" + picture6 + ", picture7=" + picture7 + ", picture8=" + picture8
				+ ", picture9=" + picture9 + "]";
	}

}
