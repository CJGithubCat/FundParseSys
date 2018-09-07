package com.zsh.labouCapital.entity;

/**
 * class desc:考核通知单
 * table name:  t_exam_notice
 * create time: 2018-06-21 00:27:01
 */ 
public class ExamNotice{
	private int noticeId;
	private String noticeNo;
	private int noticeType;
	private double yfze;
	private double zxze;

	public void setNoticeId(int noticeId){
		this.noticeId=noticeId;
	}
	public int getNoticeId(){
		return noticeId;
	}
	public void setNoticeNo(String noticeNo){
		this.noticeNo=noticeNo;
	}
	public String getNoticeNo(){
		return noticeNo;
	}
	public void setNoticeType(int noticeType){
		this.noticeType=noticeType;
	}
	public int getNoticeType(){
		return noticeType;
	}
	public void setYfze(double yfze){
		this.yfze=yfze;
	}
	public double getYfze(){
		return yfze;
	}
	public void setZxze(double zxze){
		this.zxze=zxze;
	}
	public double getZxze(){
		return zxze;
	}
}

