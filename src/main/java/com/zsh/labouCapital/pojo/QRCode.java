package com.zsh.labouCapital.pojo;

/**
 * 机构二维码实体类
 * @author Administrator
 *
 */
public class QRCode {
	
	private int agencyId;//机构id
	private String agencyQrCode;//机构二维码
	private int isDelete;//记录是否删除：1-是，0-否
	private String createTime;//创建时间
	private String updateTime;//更新时间
	
	public int getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}
	public String getAgencyQrCode() {
		return agencyQrCode;
	}
	public void setAgencyQrCode(String agencyQrCode) {
		this.agencyQrCode = agencyQrCode;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
