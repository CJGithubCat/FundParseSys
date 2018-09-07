package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * @author Lxb
 * @Description 子机构实体类
 * @Date 2015-05-17 下午 13:53
 */
public class TBaOrgInfo implements Serializable {

	
	private static final long serialVersionUID = 4338252721740411472L;
	
	/** 机构ID,跟分组相关*/
	private Long orgId;
	/** 机构类型, 0=私家车, 1=集客*/
	private Integer orgType;
	/** LDAP机构(分公司/集客用户)的根节点ID*/
	private Long orgNo;
	/** LDAP机构(分公司/集客用户)子机构代码, 根结构填'0', 每级2位字符*/
	private String orgCode;
	/** 机构名称*/
	private String orgName;
	/** 操作员ID*/
	private String opId;
	/** 时间戳*/
	private String stamp;
	/** 备注*/
	private String remark;
	
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Integer getOrgType() {
		return orgType;
	}
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	public Long getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(Long orgNo) {
		this.orgNo = orgNo;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
