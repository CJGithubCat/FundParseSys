package com.zsh.labouCapital.pojo;

import java.io.Serializable;

public class TWgRoles implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer roleid;  //角色id
	private String description;//角色名称
	private Integer isdeleted;//是否删除(0:否,1:是)
	private Integer updateuserid;//维护人id
	private String remarks;//备注
	private String updatestamp;//最后更新时间
	private Integer createuserid;//创建者的userid
	private Integer issystem;//是否系统设置角色
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Integer getUpdateuserid() {
		return updateuserid;
	}
	public void setUpdateuserid(Integer updateuserid) {
		this.updateuserid = updateuserid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(String updatestamp) {
		this.updatestamp = updatestamp;
	}
	public Integer getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}
	public Integer getIssystem() {
		return issystem;
	}
	public void setIssystem(Integer issystem) {
		this.issystem = issystem;
	}
	@Override
	public String toString() {
		return "TWgRoles [roleid=" + roleid + ", description=" + description + ", isdeleted=" + isdeleted
				+ ", updateuserid=" + updateuserid + ", remarks=" + remarks + ", updatestamp=" + updatestamp
				+ ", createuserid=" + createuserid + ", issystem=" + issystem + "]";
	}
}
