package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_fund_type
 * create time: 2018-09-08 23:05:59
 */ 
public class FundType{

	private long id;
	private String typeName;
	private String infomation;
	private Date dateCreate;
	private Date dateUpdate;

	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setTypeName(String typeName){
		this.typeName=typeName;
	}
	public String getTypeName(){
		return typeName;
	}
	public void setInfomation(String infomation){
		this.infomation=infomation;
	}
	public String getInfomation(){
		return infomation;
	}
	public void setDateCreate(Date dateCreate){
		this.dateCreate=dateCreate;
	}
	public Date getDateCreate(){
		return dateCreate;
	}
	public void setDateUpdate(Date dateUpdate){
		this.dateUpdate=dateUpdate;
	}
	public Date getDateUpdate(){
		return dateUpdate;
	}
}

