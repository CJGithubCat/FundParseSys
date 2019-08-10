package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_money_pool
 * create time: 2019-06-21 17:24:19
 */ 
public class TMoneyPool{

	private String id;
	private double totalMoney;
	private double occupyMoney;
	private double freeMoney;
	private Date dateUpdate;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setTotalMoney(double totalMoney){
		this.totalMoney=totalMoney;
	}
	public double getTotalMoney(){
		return totalMoney;
	}
	public void setOccupyMoney(double occupyMoney){
		this.occupyMoney=occupyMoney;
	}
	public double getOccupyMoney(){
		return occupyMoney;
	}
	public void setFreeMoney(double freeMoney){
		this.freeMoney=freeMoney;
	}
	public double getFreeMoney(){
		return freeMoney;
	}
	public void setDateUpdate(Date dateUpdate){
		this.dateUpdate=dateUpdate;
	}
	public Date getDateUpdate(){
		return dateUpdate;
	}
}

