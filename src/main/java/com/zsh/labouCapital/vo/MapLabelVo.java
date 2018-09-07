package com.zsh.labouCapital.vo;

public class MapLabelVo {
    private Integer userId;
    private String mapLabelName;
    private String mapLabelLat;
    private String mapLabelLng;
	
	public String getMapLabelName() {
		return mapLabelName;
	}
	public void setMapLabelName(String mapLabelName) {
		this.mapLabelName = mapLabelName;
	}
	public String getMapLabelLat() {
		return mapLabelLat;
	}
	public void setMapLabelLat(String mapLabelLat) {
		this.mapLabelLat = mapLabelLat;
	}
	public String getMapLabelLng() {
		return mapLabelLng;
	}
	public void setMapLabelLng(String mapLabelLng) {
		this.mapLabelLng = mapLabelLng;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
