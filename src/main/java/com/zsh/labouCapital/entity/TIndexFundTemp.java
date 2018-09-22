package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_index_fund_temp
 * create time: 2018-09-19 11:35:40
 */ 
public class TIndexFundTemp{

	private String indexCode;
	private String fundCode;
	private Date updateDate;

	public void setIndexCode(String indexCode){
		this.indexCode=indexCode;
	}
	public String getIndexCode(){
		return indexCode;
	}
	public void setFundCode(String fundCode){
		this.fundCode=fundCode;
	}
	public String getFundCode(){
		return fundCode;
	}
	public void setUpdateDate(Date updateDate){
		this.updateDate=updateDate;
	}
	public Date getUpdateDate(){
		return updateDate;
	}
}

