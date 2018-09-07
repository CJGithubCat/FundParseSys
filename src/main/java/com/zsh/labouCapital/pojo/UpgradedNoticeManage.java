package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 实体类
 */
public class UpgradedNoticeManage implements Serializable {

	
	private static final long serialVersionUID = 7531333711074051399L;
	/** 公告信息标题ID */
	private int notice_id;
	/** 公告信息标题*/
	private String notice_title;	
	
	/** 公告信息内容*/
	private String notice_content;
	/** 公告创建时间*/
	private String create_time;
	
    private String beginDate;//开始时间
    private String endDate;//结束时间
    
	
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "UpgradedNoticeManage [notice_id=" + notice_id + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", create_time=" + create_time + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ "]";
	}
	
}
