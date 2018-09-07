package com.zsh.labouCapital.pojo;
/**
 * 
 * @author gongyu
 * 区域管理，省市区域，省份
 */
public class MapProvince {
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	public String getPrfir() {
		return prfir;
	}
	public void setPrfir(String prfir) {
		this.prfir = prfir;
	}
	private Integer id;
    private String name;
    private String tableName;
    private Integer lon;
    private Integer lat;
    private String py;
    private String prfir;
}
