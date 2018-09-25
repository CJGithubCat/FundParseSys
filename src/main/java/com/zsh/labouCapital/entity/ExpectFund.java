package com.zsh.labouCapital.entity;

/**
 * table name:  t_expect_fund
 * create time: 2018-09-25 16:46:22
 */ 
public class ExpectFund{

	private long id;
	private String fundCode;
	private String fundName;

	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setFundCode(String fundCode){
		this.fundCode=fundCode;
	}
	public String getFundCode(){
		return fundCode;
	}
	public void setFundName(String fundName){
		this.fundName=fundName;
	}
	public String getFundName(){
		return fundName;
	}
}

