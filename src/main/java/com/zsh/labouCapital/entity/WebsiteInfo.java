package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_website_info
 * create time: 2018-09-08 23:06:00
 */ 
public class WebsiteInfo{

	private long id;
	private String itemName;
	private String itemUrl;
	private int type;
	private int level;
	private Date dateCreate;
	private Date dateUpdate;
	private long parentId;

	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setItemName(String itemName){
		this.itemName=itemName;
	}
	public String getItemName(){
		return itemName;
	}
	public void setItemUrl(String itemUrl){
		this.itemUrl=itemUrl;
	}
	public String getItemUrl(){
		return itemUrl;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getType(){
		return type;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getLevel(){
		return level;
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
	public void setParentId(long parentId){
		this.parentId=parentId;
	}
	public long getParentId(){
		return parentId;
	}
}

