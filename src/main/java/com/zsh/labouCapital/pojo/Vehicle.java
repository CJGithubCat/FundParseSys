package com.zsh.labouCapital.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Lxb
 * @Description 车辆实体类
 * @Date 2015-05-12 下午16:48
 */
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 7955518022923501566L;

    private Long vehicleId;
    private long subcoNo;
    private String plateNo;
    private String defNo;
    private String secondNo;
    private int plateColor;
    private int vehicleType;
    private int vehicleStatus;
    private int flag;
    private Long brand;
    private Long series;
    private Long model;
    private String modelName;
    private String vin;
    private String color;
    private String engineNo;
    private Integer oilType;
    private Integer oilGrade;
    private String factory;
    private Date productionDate;
    private Date buyDate;
    private String chassisNo;
    private String servicePwd;
    private String privatePwd;
    private Float buyMoney;
    private Date registerDate;
    private int vload;
    private String vehicleLicense;
    private Date vlBdate;
    private Date vlEdate;
    private String vocNo;
    private Date vocBdate;
    private Date vocEdate;
    private long opId;
    private Date stamp;
    private Long ssssId;
    private Integer insuranceId;
    private String remark;
    private String remark2;
    //zhouwei
    private int unitId;//车载ID
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public long getSubcoNo() {
		return subcoNo;
	}
	public void setSubcoNo(long subcoNo) {
		this.subcoNo = subcoNo;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getDefNo() {
		return defNo;
	}
	public void setDefNo(String defNo) {
		this.defNo = defNo;
	}
	public String getSecondNo() {
		return secondNo;
	}
	public void setSecondNo(String secondNo) {
		this.secondNo = secondNo;
	}
	public int getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}
	public int getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(int vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Long getBrand() {
		return brand;
	}
	public void setBrand(Long brand) {
		this.brand = brand;
	}
	public Long getSeries() {
		return series;
	}
	public void setSeries(Long series) {
		this.series = series;
	}
	public Long getModel() {
		return model;
	}
	public void setModel(Long model) {
		this.model = model;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public Integer getOilType() {
		return oilType;
	}
	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}
	public Integer getOilGrade() {
		return oilGrade;
	}
	public void setOilGrade(Integer oilGrade) {
		this.oilGrade = oilGrade;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getChassisNo() {
		return chassisNo;
	}
	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}
	public String getServicePwd() {
		return servicePwd;
	}
	public void setServicePwd(String servicePwd) {
		this.servicePwd = servicePwd;
	}
	public String getPrivatePwd() {
		return privatePwd;
	}
	public void setPrivatePwd(String privatePwd) {
		this.privatePwd = privatePwd;
	}
	public Float getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Float buyMoney) {
		this.buyMoney = buyMoney;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public int getVload() {
		return vload;
	}
	public void setVload(int vload) {
		this.vload = vload;
	}
	public String getVehicleLicense() {
		return vehicleLicense;
	}
	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	public Date getVlBdate() {
		return vlBdate;
	}
	public void setVlBdate(Date vlBdate) {
		this.vlBdate = vlBdate;
	}
	public Date getVlEdate() {
		return vlEdate;
	}
	public void setVlEdate(Date vlEdate) {
		this.vlEdate = vlEdate;
	}
	public String getVocNo() {
		return vocNo;
	}
	public void setVocNo(String vocNo) {
		this.vocNo = vocNo;
	}
	public Date getVocBdate() {
		return vocBdate;
	}
	public void setVocBdate(Date vocBdate) {
		this.vocBdate = vocBdate;
	}
	public Date getVocEdate() {
		return vocEdate;
	}
	public void setVocEdate(Date vocEdate) {
		this.vocEdate = vocEdate;
	}
	public long getOpId() {
		return opId;
	}
	public void setOpId(long opId) {
		this.opId = opId;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public Long getSsssId() {
		return ssssId;
	}
	public void setSsssId(Long ssssId) {
		this.ssssId = ssssId;
	}
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	
    
}
