package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * @author Lxb
 * @Description 用户实体对象
 * @Date 2015-05-27 下午3:01
 */
public class TBaCustomer implements Serializable {

	
	private static final long serialVersionUID = -6473269557439096780L;
	
	private Long customerId;
	private Long subcoNo  = 0L;
	private Long custcoNo =0L;
	private String custcoCode;
	private String customerName;
	private Integer custType = 0;
	
	//租赁需要
	private Integer idtype = 0;
	private Integer trade = 0;
	private Integer vip = 0;
	private Integer flag = 0;
	private Integer payModel = 0 ;
	private Integer opId = 0;
	private Integer agencyId ;

	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getSubcoNo() {
		return subcoNo;
	}
	public void setSubcoNo(Long subcoNo) {
		this.subcoNo = subcoNo;
	}
	public Long getCustcoNo() {
		return custcoNo;
	}
	public void setCustcoNo(Long custcoNo) {
		this.custcoNo = custcoNo;
	}
	public String getCustcoCode() {
		return custcoCode;
	}
	public void setCustcoCode(String custcoCode) {
		this.custcoCode = custcoCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCustType() {
		return custType;
	}
	public void setCustType(Integer custType) {
		this.custType = custType;
	}
	
	
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public Integer getTrade() {
		return trade;
	}
	public void setTrade(Integer trade) {
		this.trade = trade;
	}
	public Integer getVip() {
		return vip;
	}
	public void setVip(Integer vip) {
		this.vip = vip;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getPayModel() {
		return payModel;
	}
	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}
	public Integer getOpId() {
		return opId;
	}
	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	public Integer getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}
	
}
