package com.zsh.labouCapital.pojo;

import java.util.Date;
import java.util.List;

/**
 * @Package:com.chinagps.webgis.pojo TWgVehicle
 * @Description:租赁版集客车入网
 * @author: liujie
 * @date:2016-1-19 下午20:35:09
 */
public class TWgVehicle {

	private Long customer_id;// 客户id
	private Long agency_id;

	/**
	 * 车辆信息
	 */
	private long subcoNo;
	private Long vehicle_id;// 车辆id
	private String number_plate;// 车牌号码
	private Integer plate_color;// 车牌颜色
	private String plateColor;//车牌颜色
	private String engine_no;// 发动机号
	private String color;// 车辆颜色
	private Long vehicle_type;// 车型
	private String vehicleType;//车辆类型
	private String cartype_name;// 车型名称
	private String service_pwd;// 服务密码
	private String remark2;// 备注
	private String vin;// 车辆识别号(车架号)
	private int flag;// '资料状态, 0=快速入网, 1=未审核(补录), 2=已审核',
	private String servicePwd;// 服务密码
	private Date loan_date;// 车辆贷款日期
	private Date square_date;// 车辆贷款还清日期
    private String agencyName;//机构名称
    private int brand_id;//品牌
    private String brand_name;//品牌
    private int series_id;//车系
    private String series_name;//车系
    private int model_id;//车型
    private String model_name;//车型
    private String lease_status;//车辆状态
    private String content;//条形码
    private String third_agency_name;//第三级机构名称
    private String contract_no;//合同编号
    private String work_address;//工作地址
    private String home_address;//家庭住址
    private String regis_address;//户籍地址
    private int oil_tank_capacity;//油箱体积
    private String engine_type;//动力类型
    private Double displacement;//排量
    private String brand;//车辆品牌obd
    private String fdg_status;//反代购状态 0：未分析；1：已分析；
    private String vehicle_risk;//车辆风险状态

	/**
	 * 终端资料
	 */
	private Long unit_id;// 车台id
	private Long unittype_id;
	private String unittype;// 终端类型
	private Integer preCmd;
	private String call_letter;// 车载号码
	private Integer simtype;// SIM卡类型
	private String sim_type;// SIM卡类型
	private Integer telecom;// SIM卡运营商
	private String telecom1;// SIM卡运营商
	private Integer mode;// 通信模式（1短信;2数据流量;3数据流量+短信）
	private String mode1;
	private String area;// 入网地
	private String worker;// 安装电工
	private String install_location;// 安装位置
	private Date fix_time;// 安装时间
	private String sales;// 销售经理姓名
	private Long sales_id;// 销售经理ID
	private long opId;
	private int vehicleStatus;// '车辆状态, 系统值2060, 0=正常, 1=故障, 2=维修, 3=警情',
	private Integer data_node;// '流量通道网关编号, 无填0',
	private Integer pay_model;// '付费方式, 集团客户可能每车不同, 系统值3050, 0=托收, 1=现金, 2=转账,
								// 3=刷卡',
	private Integer reg_status;// '入网状态, 系统值2050, 0=在网, 1=离网, 2=欠费离网, 3=非入网,
								// 4=研发测试, 5=电工测试, 6=重新开通, 7=不开通
	private Integer sms_node;// 短信通道网关编号, 无填0, 多个短信通道时需要, 见t_sys_node, 短信网关节点,
								// 界面上需显示网关别名
	private Date stamp;// 时间戳
	private Date stop_date;// 服务停用时间
	private Integer trade;
	private Integer is_mother_unit;
	private String sqbh;//申请编号
	private String unitVersion;//终端软件版本号
	private String imei;//设备号
	private String imei_phone;//IMEI码
	private Date storage_time;//入库时间
	private String no_1;// 子机1编号
	private String no_2;// 子机2编号
	private String no_3;// 子机3编号
	private String no_4;// 子机4编号
	private String son_call_letter_1;// 子机1车载电话
	private String son_call_letter_2;// 子机2车载电话
	private String son_call_letter_3;// 子机3车载电话
	private String son_call_letter_4;// 子机4车载电话
	private String track_status; //是否追车   
	private Date set_track_stamp; //设置为需要追踪的时间;   
	private String barcode; //终端条形码 
	// 子母机关系表
	private Long mother_unit_id; // 母机终端ID
	private Long son_unit_id;// 子机终端id
	private Long son_unit_id_number;// 子机编号
	private String son_call_letter;// 子机卡号
	private String worker_name;//安装电工名
	/**
	 * 多条数据组成的集合数据
	 */
	private TWgBarcode barcodes;// 母机终端资料条形码
	private TWgFeeInfo serviceInfo;// 服务费计费信息
	private TWgFeeInfo simfeeInfo;// 母机SIM卡计费信息
	private List<TWgDriver> drivers;// 司机
	private List<TWgVehicle> sonsInfo;// 子机基础信息
	private List<TWgBarcode> sonsBarcodes;// 子机终端资料多个条形码
	private List<TWgFeeInfo> sonsSimFee;// 子机SIM卡计费信息

	private int son_count;// 子机终端数量
	private int driver_count;// 司机数量

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public long getSubcoNo() {
		return subcoNo;
	}

	public void setSubcoNo(long subcoNo) {
		this.subcoNo = subcoNo;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getNumber_plate() {
		return number_plate;
	}

	public void setNumber_plate(String number_plate) {
		this.number_plate = number_plate;
	}

	public Integer getPlate_color() {
		return plate_color;
	}

	public void setPlate_color(Integer plate_color) {
		this.plate_color = plate_color;
	}

	public String getEngine_no() {
		return engine_no;
	}

	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(Long vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public String getCartype_name() {
		return cartype_name;
	}

	public void setCartype_name(String cartype_name) {
		this.cartype_name = cartype_name;
	}

	public String getService_pwd() {
		return service_pwd;
	}

	public void setService_pwd(String service_pwd) {
		this.service_pwd = service_pwd;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getServicePwd() {
		return servicePwd;
	}

	public void setServicePwd(String servicePwd) {
		this.servicePwd = servicePwd;
	}

	public Long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}

	public Long getUnittype_id() {
		return unittype_id;
	}

	public void setUnittype_id(Long unittype_id) {
		this.unittype_id = unittype_id;
	}

	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	
	public Integer getPreCmd() {
		return preCmd;
	}

	public void setPreCmd(Integer preCmd) {
		this.preCmd = preCmd;
	}

	public String getCall_letter() {
		return call_letter;
	}

	public void setCall_letter(String call_letter) {
		this.call_letter = call_letter;
	}

	public Integer getSimtype() {
		return simtype;
	}

	public void setSimtype(Integer simtype) {
		this.simtype = simtype;
	}

	public Integer getTelecom() {
		return telecom;
	}

	public void setTelecom(Integer telecom) {
		this.telecom = telecom;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public String getInstall_location() {
		return install_location;
	}

	public void setInstall_location(String install_location) {
		this.install_location = install_location;
	}

	public Date getFix_time() {
		return fix_time;
	}

	public void setFix_time(Date fix_time) {
		this.fix_time = fix_time;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public Long getSales_id() {
		return sales_id;
	}

	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}

	public long getOpId() {
		return opId;
	}

	public void setOpId(long opId) {
		this.opId = opId;
	}

	public int getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(int vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Integer getData_node() {
		return data_node;
	}

	public void setData_node(Integer data_node) {
		this.data_node = data_node;
	}

	public Integer getPay_model() {
		return pay_model;
	}

	public void setPay_model(Integer pay_model) {
		this.pay_model = pay_model;
	}

	public Integer getReg_status() {
		return reg_status;
	}

	public void setReg_status(Integer reg_status) {
		this.reg_status = reg_status;
	}

	public TWgFeeInfo getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(TWgFeeInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public TWgFeeInfo getSimfeeInfo() {
		return simfeeInfo;
	}

	public void setSimfeeInfo(TWgFeeInfo simfeeInfo) {
		this.simfeeInfo = simfeeInfo;
	}

	public List<TWgDriver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<TWgDriver> drivers) {
		this.drivers = drivers;
	}

	public List<TWgVehicle> getSonsInfo() {
		return sonsInfo;
	}

	public void setSonsInfo(List<TWgVehicle> sonsInfo) {
		this.sonsInfo = sonsInfo;
	}

	public List<TWgFeeInfo> getSonsSimFee() {
		return sonsSimFee;
	}

	public void setSonsSimFee(List<TWgFeeInfo> sonsSimFee) {
		this.sonsSimFee = sonsSimFee;
	}

	public Long getSon_unit_id() {
		return son_unit_id;
	}

	public void setSon_unit_id(Long son_unit_id) {
		this.son_unit_id = son_unit_id;
	}

	public Long getSon_unit_id_number() {
		return son_unit_id_number;
	}

	public void setSon_unit_id_number(Long son_unit_id_number) {
		this.son_unit_id_number = son_unit_id_number;
	}

	public Long getMother_unit_id() {
		return mother_unit_id;
	}

	public void setMother_unit_id(Long mother_unit_id) {
		this.mother_unit_id = mother_unit_id;
	}

	public Integer getSms_node() {
		return sms_node;
	}

	public void setSms_node(Integer sms_node) {
		this.sms_node = sms_node;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public Integer getTrade() {
		return trade;
	}

	public void setTrade(Integer trade) {
		this.trade = trade;
	}

	public Integer getIs_mother_unit() {
		return is_mother_unit;
	}

	public void setIs_mother_unit(Integer is_mother_unit) {
		this.is_mother_unit = is_mother_unit;
	}

	public Date getLoan_date() {
		return loan_date;
	}

	public void setLoan_date(Date loan_date) {
		this.loan_date = loan_date;
	}

	public Date getSquare_date() {
		return square_date;
	}

	public void setSquare_date(Date square_date) {
		this.square_date = square_date;
	}

	public TWgBarcode getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(TWgBarcode barcodes) {
		this.barcodes = barcodes;
	}

	public List<TWgBarcode> getSonsBarcodes() {
		return sonsBarcodes;
	}

	public void setSonsBarcodes(List<TWgBarcode> sonsBarcodes) {
		this.sonsBarcodes = sonsBarcodes;
	}

	public int getSon_count() {
		return son_count;
	}

	public void setSon_count(int son_count) {
		this.son_count = son_count;
	}

	public int getDriver_count() {
		return driver_count;
	}

	public void setDriver_count(int driver_count) {
		this.driver_count = driver_count;
	}

	public Long getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(Long agency_id) {
		this.agency_id = agency_id;
	}

	public String getSon_call_letter() {
		return son_call_letter;
	}

	public void setSon_call_letter(String son_call_letter) {
		this.son_call_letter = son_call_letter;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getSim_type() {
		return sim_type;
	}

	public void setSim_type(String sim_type) {
		this.sim_type = sim_type;
	}

	public String getTelecom1() {
		return telecom1;
	}

	public void setTelecom1(String telecom1) {
		this.telecom1 = telecom1;
	}

	public String getMode1() {
		return mode1;
	}

	public void setMode1(String mode1) {
		this.mode1 = mode1;
	}

	public String getSqbh() {
		return sqbh;
	}

	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}

	public String getUnitVersion() {
		return unitVersion;
	}

	public void setUnitVersion(String unitVersion) {
		this.unitVersion = unitVersion;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImei_phone() {
		return imei_phone;
	}

	public void setImei_phone(String imei_phone) {
		this.imei_phone = imei_phone;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Date getStop_date() {
		return stop_date;
	}

	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public int getSeries_id() {
		return series_id;
	}

	public void setSeries_id(int series_id) {
		this.series_id = series_id;
	}

	public int getModel_id() {
		return model_id;
	}

	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getSeries_name() {
		return series_name;
	}

	public void setSeries_name(String series_name) {
		this.series_name = series_name;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public Date getStorage_time() {
		return storage_time;
	}

	public void setStorage_time(Date date) {
		this.storage_time = date;
	}

	public String getSon_call_letter_1() {
		return son_call_letter_1;
	}

	public void setSon_call_letter_1(String son_call_letter_1) {
		this.son_call_letter_1 = son_call_letter_1;
	}

	public String getSon_call_letter_2() {
		return son_call_letter_2;
	}

	public void setSon_call_letter_2(String son_call_letter_2) {
		this.son_call_letter_2 = son_call_letter_2;
	}

	public String getSon_call_letter_3() {
		return son_call_letter_3;
	}

	public void setSon_call_letter_3(String son_call_letter_3) {
		this.son_call_letter_3 = son_call_letter_3;
	}

	public String getSon_call_letter_4() {
		return son_call_letter_4;
	}

	public void setSon_call_letter_4(String son_call_letter_4) {
		this.son_call_letter_4 = son_call_letter_4;
	}

	public String getNo_1() {
		return no_1;
	}

	public void setNo_1(String no_1) {
		this.no_1 = no_1;
	}

	public String getNo_2() {
		return no_2;
	}

	public void setNo_2(String no_2) {
		this.no_2 = no_2;
	}

	public String getNo_3() {
		return no_3;
	}

	public void setNo_3(String no_3) {
		this.no_3 = no_3;
	}

	public String getNo_4() {
		return no_4;
	}

	public void setNo_4(String no_4) {
		this.no_4 = no_4;
	}

	public String getTrack_status() {
		return track_status;
	}

	public void setTrack_status(String track_status) {
		this.track_status = track_status;
	}

	public Date getSet_track_stamp() {
		return set_track_stamp;
	}

	public void setSet_track_stamp(Date set_track_stamp) {
		this.set_track_stamp = set_track_stamp;
	}

	public String getLease_status() {
		return lease_status;
	}

	public void setLease_status(String lease_status) {
		this.lease_status = lease_status;
	}


	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThird_agency_name() {
		return third_agency_name;
	}
	public String getContract_no() {
		return contract_no;
	}

	public void setThird_agency_name(String third_agency_name) {
		this.third_agency_name = third_agency_name;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getWork_address() {
		return work_address;
	}

	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}

	public String getHome_address() {
		return home_address;
	}

	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}

	public int getOil_tank_capacity() {
		return oil_tank_capacity;
	}

	public void setOil_tank_capacity(int oil_tank_capacity) {
		this.oil_tank_capacity = oil_tank_capacity;
	}

	public String getEngine_type() {
		return engine_type;
	}

	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}

	public Double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getWorker_name() {
		return worker_name;
	}

	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}

	public String getRegis_address() {
		return regis_address;
	}

	public void setRegis_address(String regis_address) {
		this.regis_address = regis_address;
	}

	public String getFdg_status() {
		return fdg_status;
	}

	public void setFdg_status(String fdg_status) {
		this.fdg_status = fdg_status;
	}

	public String getVehicle_risk() {
		return vehicle_risk;
	}

	public void setVehicle_risk(String vehicle_risk) {
		this.vehicle_risk = vehicle_risk;
	}

	@Override
	public String toString() {
		return "TWgVehicle [customer_id=" + customer_id + ", agency_id=" + agency_id + ", subcoNo=" + subcoNo
				+ ", vehicle_id=" + vehicle_id + ", number_plate=" + number_plate + ", plate_color=" + plate_color
				+ ", plateColor=" + plateColor + ", engine_no=" + engine_no + ", color=" + color + ", vehicle_type="
				+ vehicle_type + ", vehicleType=" + vehicleType + ", cartype_name=" + cartype_name + ", service_pwd="
				+ service_pwd + ", remark2=" + remark2 + ", vin=" + vin + ", flag=" + flag + ", servicePwd="
				+ servicePwd + ", loan_date=" + loan_date + ", square_date=" + square_date + ", agencyName="
				+ agencyName + ", brand_id=" + brand_id + ", brand_name=" + brand_name + ", series_id=" + series_id
				+ ", series_name=" + series_name + ", model_id=" + model_id + ", model_name=" + model_name
				+ ", lease_status=" + lease_status + ", content=" + content + ", third_agency_name=" + third_agency_name
				+ ", contract_no=" + contract_no + ", work_address=" + work_address + ", home_address=" + home_address
				+ ", regis_address=" + regis_address + ", oil_tank_capacity=" + oil_tank_capacity + ", engine_type="
				+ engine_type + ", displacement=" + displacement + ", brand=" + brand + ", fdg_status=" + fdg_status
				+ ", vehicle_risk=" + vehicle_risk + ", unit_id=" + unit_id + ", unittype_id=" + unittype_id
				+ ", unittype=" + unittype + ", preCmd=" + preCmd + ", call_letter=" + call_letter + ", simtype="
				+ simtype + ", sim_type=" + sim_type + ", telecom=" + telecom + ", telecom1=" + telecom1 + ", mode="
				+ mode + ", mode1=" + mode1 + ", area=" + area + ", worker=" + worker + ", install_location="
				+ install_location + ", fix_time=" + fix_time + ", sales=" + sales + ", sales_id=" + sales_id
				+ ", opId=" + opId + ", vehicleStatus=" + vehicleStatus + ", data_node=" + data_node + ", pay_model="
				+ pay_model + ", reg_status=" + reg_status + ", sms_node=" + sms_node + ", stamp=" + stamp
				+ ", stop_date=" + stop_date + ", trade=" + trade + ", is_mother_unit=" + is_mother_unit + ", sqbh="
				+ sqbh + ", unitVersion=" + unitVersion + ", imei=" + imei + ", imei_phone=" + imei_phone
				+ ", storage_time=" + storage_time + ", no_1=" + no_1 + ", no_2=" + no_2 + ", no_3=" + no_3 + ", no_4="
				+ no_4 + ", son_call_letter_1=" + son_call_letter_1 + ", son_call_letter_2=" + son_call_letter_2
				+ ", son_call_letter_3=" + son_call_letter_3 + ", son_call_letter_4=" + son_call_letter_4
				+ ", track_status=" + track_status + ", set_track_stamp=" + set_track_stamp + ", barcode=" + barcode
				+ ", mother_unit_id=" + mother_unit_id + ", son_unit_id=" + son_unit_id + ", son_unit_id_number="
				+ son_unit_id_number + ", son_call_letter=" + son_call_letter + ", worker_name=" + worker_name
				+ ", barcodes=" + barcodes + ", serviceInfo=" + serviceInfo + ", simfeeInfo=" + simfeeInfo
				+ ", drivers=" + drivers + ", sonsInfo=" + sonsInfo + ", sonsBarcodes=" + sonsBarcodes + ", sonsSimFee="
				+ sonsSimFee + ", son_count=" + son_count + ", driver_count=" + driver_count + "]";
	}

}
