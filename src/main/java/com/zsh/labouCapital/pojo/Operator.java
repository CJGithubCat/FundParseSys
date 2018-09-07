package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 操作员实体类
 */
public class Operator implements Serializable{

	
	private static final long serialVersionUID = 3666387279896968529L;

	/**操作员ID */
	private String opid;
	/**操作员姓名*/
	private String opname;
	/**备注*/
	private String remark;
	/**操作员操作名*/
	private String loginname;
	/**性别 1:男 2：女 */
	private String sex;
	/**邮编 */
	private String post;
	/**手机号码 */
	private String mobile;
	/**传真 */
	private String fax;
	/**电子邮箱 */
	private String mail;
	/**排序 */
	private String order;
	/**密码，默认123456，写死 */
	private String userPassword;
	/**工号 */
	private String jobnumber;
	/**联系电话 */
	private String phone;
	/**归属机构串，”,”号分隔 */
	private String companyname;
	/** 归属机构标识串，”,”号分隔*/
	private String companynos;
	/**操作员类型，写死1*/
	private String status;
	/**操作员归属机构id*/
	private String orgid;
	
	
	public String getOpid() {
		return opid;
	}
	public void setOpid(String opid) {
		this.opid = opid;
	}
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getJobnumber() {
		return jobnumber;
	}
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanynos() {
		return companynos;
	}
	public void setCompanynos(String companynos) {
		this.companynos = companynos;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	

}
