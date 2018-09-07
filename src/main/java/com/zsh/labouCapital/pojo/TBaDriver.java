package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 司机实体类
 */
public class TBaDriver implements Serializable {
	
	private static final long serialVersionUID = 1335025003026295170L;
	
	/** 名字*/
    private String name;
    /** 电话号码*/
    private String tel;
    /** 地址*/
    private String address;
    /** 入网机构*/
    private String subname;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}   
 	
}
