package com.zsh.labouCapital.pojo;

import java.util.Date;

/**
 * @Package:com.gboss.pojo
 * @ClassName:Driver
 * @Description:TODO
 * @author:xiaoke
 * @date:2014-4-18 下午3:56:23
 */
public class TWgDriver {

	private static final long serialVersionUID = 1L;

	private Long driver_id;// '司机ID',
	private Long subco_no;// '分公司, 对应我们的分公司, 内部机构',
	private Long customer_id;// '客户ID',
	private Long vehicle_id;// '车辆ID,有些情况是不直接对应车辆,需根据排班表, 这里只针对司机固定车辆的情况',
	private String driver_name;// '司机姓名',
	private String driver_code;// '司机编号, 通常与终端登录相关',
	private String work_no;// '司机工号, 客户公司定义',
	private Integer sex;// '司机性别, 0=男, 1=女',
	private Integer features;// '政治面貌, 1=群众, 2=共青团员, 3=中共党员, 99=其他',
	private String educational;// '学历',
	private Date birthday;// '出生日期',
	private Date hire_date;// '聘用日期',
	private String phone;// '联系电话',
	private Integer idtype;// '证件类型, 系统参数2010, 1=居民身份证, 2=士官证, 3=学生证, 4=驾驶证,
							// 5=护照, 6=港澳通行证, 99=其它',
	private String id_no;// '身份证',
	private Date id_bdate;// '身份证开始日期',
	private Date id_edate;// '身份证到期日期',
	private String account_loc;// '户口所在地',
	private String adress;// '现在地址',
	private Integer classorder;// '班次, 1=白班/主班, 2=晚班/副班',
	private String sc_no;// '服务资格证',
	private Date sc_bdate;// '资格证开始日期',
	private Date sc_edate;// '资格证到期日期',
	private String reg_no;// '登记证号,
	private Date reg_bdate;// '登记证开始日期',
	private Date reg_edate;// '登记证到期日期',
	private String dr_no;// '驾驶证号',
	private String dr_class;// '驾驶证准驾车型',
	private Date dr_issuedate;// '驾驶证初次领证日期',
	private Date dr_bdate;// '驾驶证有效起始日期',
	private Date dr_edate;// '驾驶证到期日期, 一般是6年, 可自动换算',
	private Long op_id;// '操作员ID',
	private Date stamp;// '操作时间',

	public Long getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Long driver_id) {
		this.driver_id = driver_id;
	}

	public Long getSubco_no() {
		return subco_no;
	}

	public void setSubco_no(Long subco_no) {
		this.subco_no = subco_no;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_code() {
		return driver_code;
	}

	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}

	public String getWork_no() {
		return work_no;
	}

	public void setWork_no(String work_no) {
		this.work_no = work_no;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getFeatures() {
		return features;
	}

	public void setFeatures(Integer features) {
		this.features = features;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIdtype() {
		return idtype;
	}

	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public Date getId_bdate() {
		return id_bdate;
	}

	public void setId_bdate(Date id_bdate) {
		this.id_bdate = id_bdate;
	}

	public Date getId_edate() {
		return id_edate;
	}

	public void setId_edate(Date id_edate) {
		this.id_edate = id_edate;
	}

	public String getAccount_loc() {
		return account_loc;
	}

	public void setAccount_loc(String account_loc) {
		this.account_loc = account_loc;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getClassorder() {
		return classorder;
	}

	public void setClassorder(Integer classorder) {
		this.classorder = classorder;
	}

	public String getSc_no() {
		return sc_no;
	}

	public void setSc_no(String sc_no) {
		this.sc_no = sc_no;
	}

	public Date getSc_bdate() {
		return sc_bdate;
	}

	public void setSc_bdate(Date sc_bdate) {
		this.sc_bdate = sc_bdate;
	}

	public Date getSc_edate() {
		return sc_edate;
	}

	public void setSc_edate(Date sc_edate) {
		this.sc_edate = sc_edate;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public Date getReg_bdate() {
		return reg_bdate;
	}

	public void setReg_bdate(Date reg_bdate) {
		this.reg_bdate = reg_bdate;
	}

	public Date getReg_edate() {
		return reg_edate;
	}

	public void setReg_edate(Date reg_edate) {
		this.reg_edate = reg_edate;
	}

	public String getDr_no() {
		return dr_no;
	}

	public void setDr_no(String dr_no) {
		this.dr_no = dr_no;
	}

	public String getDr_class() {
		return dr_class;
	}

	public void setDr_class(String dr_class) {
		this.dr_class = dr_class;
	}

	public Date getDr_issuedate() {
		return dr_issuedate;
	}

	public void setDr_issuedate(Date dr_issuedate) {
		this.dr_issuedate = dr_issuedate;
	}

	public Date getDr_bdate() {
		return dr_bdate;
	}

	public void setDr_bdate(Date dr_bdate) {
		this.dr_bdate = dr_bdate;
	}

	public Date getDr_edate() {
		return dr_edate;
	}

	public void setDr_edate(Date dr_edate) {
		this.dr_edate = dr_edate;
	}

	public Long getOp_id() {
		return op_id;
	}

	public void setOp_id(Long op_id) {
		this.op_id = op_id;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	
	@Override
	public String toString() {
		return "TWgDriver [driver_id=" + driver_id + ", subco_no=" + subco_no + ", customer_id=" + customer_id
				+ ", vehicle_id=" + vehicle_id + ", driver_name=" + driver_name + ", driver_code=" + driver_code
				+ ", work_no=" + work_no + ", sex=" + sex + ", features=" + features + ", educational=" + educational
				+ ", birthday=" + birthday + ", hire_date=" + hire_date + ", phone=" + phone + ", idtype=" + idtype
				+ ", id_no=" + id_no + ", id_bdate=" + id_bdate + ", id_edate=" + id_edate + ", account_loc="
				+ account_loc + ", adress=" + adress + ", classorder=" + classorder + ", sc_no=" + sc_no + ", sc_bdate="
				+ sc_bdate + ", sc_edate=" + sc_edate + ", reg_no=" + reg_no + ", reg_bdate=" + reg_bdate
				+ ", reg_edate=" + reg_edate + ", dr_no=" + dr_no + ", dr_class=" + dr_class + ", dr_issuedate="
				+ dr_issuedate + ", dr_bdate=" + dr_bdate + ", dr_edate=" + dr_edate + ", op_id=" + op_id 
				+ ", stamp=" + stamp + "]";
	}

}
