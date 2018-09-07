package com.zsh.labouCapital.entity;

/**
 *机构类
 */
public class Company {
	private Integer companyId;//机构id
	private String companyName;//机构名称
	private String companyAddress;//机构地址
	private String companyPost;//机构地址
	private String workPhone;
	private String fax;
	private String leader;
	private String leaderPhone;
	private String leaderEmail;	
	private Integer parentCompanyId  = 0 ;//父机构id	
	private Integer companyType = 0 ;
	private Integer isDeleted = 0;//是否删除，0：未删除；1：已经删除
	private String companyPath;//机构树的id路径
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyPost() {
		return companyPost;
	}
	public void setCompanyPost(String companyPost) {
		this.companyPost = companyPost;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getLeaderPhone() {
		return leaderPhone;
	}
	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}
	public String getLeaderEmail() {
		return leaderEmail;
	}
	public void setLeaderEmail(String leaderEmail) {
		this.leaderEmail = leaderEmail;
	}
	public Integer getParentCompanyId() {
		return parentCompanyId;
	}
	public void setParentCompanyId(Integer parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}
	public Integer getCompanyType() {
		return companyType;
	}
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCompanyPath() {
		return companyPath;
	}
	public void setCompanyPath(String companyPath) {
		this.companyPath = companyPath;
	}
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyAddress=" + companyAddress
				+ ", companyPost=" + companyPost + ", workPhone=" + workPhone + ", fax=" + fax + ", leader=" + leader
				+ ", leaderPhone=" + leaderPhone + ", leaderEmail=" + leaderEmail + ", parentCompanyId="
				+ parentCompanyId + ", companyType=" + companyType + ", isDeleted=" + isDeleted + ", companyPath="
				+ companyPath + "]";
	}
}
