package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 警情类型警情提醒规则实体类
 */
public class TWgNoticeRule implements Serializable {

	
	private static final long serialVersionUID = 7531333711074051398L;
	/** 编号*/
	private Long noticeId;
	/** 警情类型*/
	private Long alarmTypeId;	
	/** 是否提醒：0=提醒;1=不提醒*/
	private Integer noticeState;
	/** 根用户编号*/
	private Long cusId;
	/** 操作员编号*/
	private String opId;
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
	public Long getAlarmTypeId() {
		return alarmTypeId;
	}
	public void setAlarmTypeId(Long alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}
	public Integer getNoticeState() {
		return noticeState;
	}
	public void setNoticeState(Integer noticeState) {
		this.noticeState = noticeState;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
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
