package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_index_info
 * create time: 2018-09-08 23:05:59
 */ 
public class IndexInfo{

	private long id;
	private String indexName;
	private String infomation;
	private Date dateCreate;
	private Date dateUpdate;
	private String stockCompany;
	private String indexCode;

	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setIndexName(String indexName){
		this.indexName=indexName;
	}
	public String getIndexName(){
		return indexName;
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
	public void setStockCompany(String stockCompany){
		this.stockCompany=stockCompany;
	}
	public String getStockCompany(){
		return stockCompany;
	}
	public void setIndexCode(String indexCode){
		this.indexCode=indexCode;
	}
	public String getIndexCode(){
		return indexCode;
	}
}

