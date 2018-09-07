package com.zsh.labouCapital.pojo;

import java.util.Date;

/**
 * 操作日志实体类
 * 
 * @author liujie
 *
 */
public class TWgOperationRecords implements java.io.Serializable {

	private static final long serialVersionUID = 420425080011217721L;
	private int userid;
	private String loginName;
	private String userName;
	private int permissionsid;
	private String description;
	private String permissionsdesc;
	private Date stamp;
	private String unitids;
	private String numberplates;
	private String remoteip;
	private long responsetime;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPermissionsid() {
		return permissionsid;
	}

	public void setPermissionsid(int permissionsid) {
		this.permissionsid = permissionsid;
	}

	public String getPermissionsdesc() {
		return permissionsdesc;
	}

	public void setPermissionsdesc(String permissionsdesc) {
		this.permissionsdesc = permissionsdesc;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public String getUnitids() {
		return unitids;
	}

	public void setUnitids(String unitids) {
		this.unitids = unitids;
	}

	public String getNumberplates() {
		return numberplates;
	}

	public void setNumberplates(String numberplates) {
		this.numberplates = numberplates;
	}

	public String getRemoteip() {
		return remoteip;
	}

	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}

	public long getResponsetime() {
		return responsetime;
	}

	public void setResponsetime(long responsetime) {
		this.responsetime = responsetime;
	}

	private String agencyAttributesPath;// 车辆所属机构的id路径

	public String getAgencyAttributesPath() {
		return agencyAttributesPath;
	}

	public void setAgencyAttributesPath(String agencyAttributesPath) {
		this.agencyAttributesPath = agencyAttributesPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
