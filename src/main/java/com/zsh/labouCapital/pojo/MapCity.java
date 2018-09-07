package com.zsh.labouCapital.pojo;
/**
 * 
 * @author gongyu
 * 区域管理，省市区域，市
 */
public class MapCity {
    private Integer cityid;
    private String name;
    private Integer lon;
    private Integer lat;
    private String py;
    private String pyfir;
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLon() {
		return lon;
	}
	public void setLon(Integer lon) {
		this.lon = lon;
	}
	public Integer getLat() {
		return lat;
	}
	public void setLat(Integer lat) {
		this.lat = lat;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getPyfir() {
		return pyfir;
	}
	public void setPyfir(String pyfir) {
		this.pyfir = pyfir;
	}
}
