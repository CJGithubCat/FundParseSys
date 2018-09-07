package com.zsh.labouCapital.pojo;

import java.util.List;

/**
 * 车辆实体类
 * @author Lxb
 *
 */
public class TBaVehicle  implements java.io.Serializable {
	
	private static final long serialVersionUID = 420425080011217721L;
	
	/** 车辆ID*/
	private Long vehicleId;
	/** LDAP分公司ID*/
	private Long subcoNo;
	/** 用户编号*/
	private Long customerId;
	/** 车牌号码*/
	private String plateNo;
	/** 车牌颜色：车牌颜色, 系统值2110, 1=蓝, 2=黄, 3=黑, 4=白, 9=其他*/
	private Integer plateColor;
	/** 车辆类型, 系统值2030, 1=小型轿车*/
	private Integer vehicleType;
	/** 车辆相关的呼号 callLetters*/
	private String callLetter;
	/** 是否定位, 0=不定位, 1=定位*/
	private int isLoc;
	
	private boolean msg;//标识字段
	
	private List<String> plateNos;//多个车牌查询;
	
	/** 预存指令标记, 0=不预存, 1=预存*/
	private Integer preCmd;
	/**车台编号*/
	private Long unitId;
	private Long orgId;
	private String orgName;
	/** 还贷状态：0=已还;1=未还;2=异常;-1=未知状态*/
	private Integer loanState;
	
	private List<String> orgIds;
	
	private int vehicleRiskState=1;//1：正常；2：高风险；3：逾期
	
	private String agencyAttributesPath;//车辆所属机构的id路径
	
	private String unittype_Typeid;// 终端类型
	private String driverName;// 车主
	private String imei;//设备号
	private String imeiPhone;//IMEI号
	private String vin;
	private String contract_no;
	private int oilTankCapacity ;

	public String getImeiPhone() {
		return imeiPhone;
	}

	public void setImeiPhone(String imeiPhone) {
		this.imeiPhone = imeiPhone;
	}


	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getSubcoNo() {
		return subcoNo;
	}

	public void setSubcoNo(Long subcoNo) {
		this.subcoNo = subcoNo;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Integer getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(Integer plateColor) {
		this.plateColor = plateColor;
	}

	public Integer getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public String getCallLetter() {
		return callLetter;
	}

	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}

	public int getIsLoc() {
		return isLoc;
	}

	public void setIsLoc(int isLoc) {
		this.isLoc = isLoc;
	}

	public boolean isMsg() {
		return msg;
	}

	public void setMsg(boolean msg) {
		this.msg = msg;
	}

	public List<String> getPlateNos() {
		return plateNos;
	}

	public void setPlateNos(List<String> plateNos) {
		this.plateNos = plateNos;
	}

	public Integer getPreCmd() {
		return preCmd;
	}

	public void setPreCmd(Integer preCmd) {
		this.preCmd = preCmd;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public List<String> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getLoanState() {
		return loanState;
	}

	public void setLoanState(Integer loanState) {
		this.loanState = loanState;
	}

	public int getVehicleRiskState() {
		return vehicleRiskState;
	}

	public void setVehicleRiskState(int vehicleRiskState) {
		this.vehicleRiskState = vehicleRiskState;
	}

	public String getAgencyAttributesPath() {
		return agencyAttributesPath;
	}

	public void setAgencyAttributesPath(String agencyAttributesPath) {
		this.agencyAttributesPath = agencyAttributesPath;
	}

	public String getUnittype_Typeid() {
		return unittype_Typeid;
	}

	public void setUnittype_Typeid(String unittype_Typeid) {
		this.unittype_Typeid = unittype_Typeid;
	}

	
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public int getOilTankCapacity() {
		return oilTankCapacity;
	}

	public void setOilTankCapacity(int oilTankCapacity) {
		this.oilTankCapacity = oilTankCapacity;
	}

	@Override
	public String toString() {
		return "TBaVehicle [vehicleId=" + vehicleId + ", subcoNo=" + subcoNo + ", customerId=" + customerId
				+ ", plateNo=" + plateNo + ", plateColor=" + plateColor + ", vehicleType=" + vehicleType
				+ ", callLetter=" + callLetter + ", isLoc=" + isLoc + ", msg=" + msg + ", plateNos=" + plateNos
				+ ", preCmd=" + preCmd + ", unitId=" + unitId + ", orgId=" + orgId + ", orgName=" + orgName
				+ ", loanState=" + loanState + ", orgIds=" + orgIds + ", vehicleRiskState=" + vehicleRiskState
				+ ", agencyAttributesPath=" + agencyAttributesPath + ", unittype_Typeid=" + unittype_Typeid
				+ ", driverName=" + driverName + ", imei=" + imei + ", imeiPhone=" + imeiPhone + ", vin=" + vin
				+ ", contract_no=" + contract_no + ", oilTankCapacity=" + oilTankCapacity + "]";
	}

 }


