package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 车型类型警情提醒规则实体类
 */
public class TWgNoticeCartype implements Serializable {

	
	private static final long serialVersionUID = 7531333711074051398L;
	/** 编号*/
	private Long noticeId;
	/** 品牌*/
	private Long brand;
	/** 车系*/
	private Long series;
	/** 车型*/
	private Long model;
	/** 是否提醒：0=提醒;1=不提醒*/
	private Integer noticeState;
	/** 用户编号*/
	private Long cusId;
	/** 是否有效:0=有效;1=无效*/
	private Integer validState;
	/** 时间戳*/
	private String stamp;
	
	public Long getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	public Long getBrand() {
		return brand;
	}
	public void setBrand(Long brand) {
		this.brand = brand;
	}
	public Long getSeries() {
		return series;
	}
	public void setSeries(Long series) {
		this.series = series;
	}
	public Long getModel() {
		return model;
	}
	public void setModel(Long model) {
		this.model = model;
	}
	public Integer getNoticeState() {
		return noticeState;
	}
	public void setNoticeState(Integer noticeState) {
		this.noticeState = noticeState;
	}
	public Long getCusId() {
		return cusId;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}
	public Integer getValidState() {
		return validState;
	}
	public void setValidState(Integer validState) {
		this.validState = validState;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
}
