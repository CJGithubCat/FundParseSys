package com.zsh.labouCapital.pojo;

import java.io.Serializable;

/**
 * 警情提醒规则交互实体类
 * @author Lxb
 *
 */
public class TWgNoticeAlarmType implements Serializable {

	private static final long serialVersionUID = 8626854138182130538L;

	/** 编号*/
	public Long noticeId;
	/** 警情类型ID*/
	public Long alarmType;
	/**警情名称*/
	public String alarmName;
	/** 提醒状态：0=提醒，1=不提醒*/
	public Integer state;
	
	
	public Long getNoticeId() {
		return noticeId;
	}
	public Long getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Long alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
