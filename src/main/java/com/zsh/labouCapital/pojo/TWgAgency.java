package com.zsh.labouCapital.pojo;

import java.util.List;

public class TWgAgency {
	private Integer agencyId;//机构id
	private String agencyCode;//机构编码
	private String agencyName;//机构名称
	private Integer parentAgencyId  = 0 ;//父机构id
	private Integer provinceId = 0 ;//省份id
	private Integer cityId = 0;//城市id
	private String address;//地址
	private String linkMan;//联系人
	private String linkTel;//联系人电话
	private Integer saleManagerId;//销售经理id
	private String saleManagerName ;//销售经理
	private String remark;//备注
	private Integer isDeleted = 0;//是否删除，0：未删除；1：已经删除
	private String attributesPath;//机构树的id路径
	
	private List<TWgAgency> childrenAgency ;
	private TWgAgency parentAgency;
	
	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}


	public Integer getParentAgencyId() {
		return parentAgencyId;
	}

	public void setParentAgencyId(Integer parentAgencyId) {
		this.parentAgencyId = parentAgencyId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public Integer getSaleManagerId() {
		return saleManagerId;
	}

	public void setSaleManagerId(Integer saleManagerId) {
		this.saleManagerId = saleManagerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAttributesPath() {
		return attributesPath;
	}

	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}

	public List<TWgAgency> getChildrenAgency() {
		return childrenAgency;
	}

	public void setChildrenAgency(List<TWgAgency> childrenAgency) {
		this.childrenAgency = childrenAgency;
	}

	public TWgAgency getParentAgency() {
		return parentAgency;
	}

	public void setParentAgency(TWgAgency parentAgency) {
		this.parentAgency = parentAgency;
	}

	public String getSaleManagerName() {
		return saleManagerName;
	}

	public void setSaleManagerName(String saleManagerName) {
		this.saleManagerName = saleManagerName;
	}

	@Override
	public String toString() {
		return "TWgAgency [agencyId=" + agencyId + ", agencyCode=" + agencyCode + ", agencyName=" + agencyName
				+ ", parentAgencyId=" + parentAgencyId + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", address=" + address + ", linkMan=" + linkMan + ", linkTel=" + linkTel + ", saleManagerId="
				+ saleManagerId + ", saleManagerName=" + saleManagerName + ", remark=" + remark + ", isDeleted="
				+ isDeleted + ", attributesPath=" + attributesPath + ", childrenAgency=" + childrenAgency
				+ ", parentAgency=" + parentAgency + "]";
	}

}
