package com.zsh.labouCapital.pojo;
/**
 * 省市区域，地图点
 * @author gongyu
 *
 */
public class MapPolygonePoint {
    private Integer pointid;
    private Integer polyid;
    private Integer holeid;
    private Integer seq; 
    private Integer lat;
    private Integer lon;
	public Integer getPointid() {
		return pointid;
	}
	public void setPointid(Integer pointid) {
		this.pointid = pointid;
	}
	public Integer getPolyid() {
		return polyid;
	}
	public void setPolyid(Integer polyid) {
		this.polyid = polyid;
	}
	public Integer getHoleid() {
		return holeid;
	}
	public void setHoleid(Integer holeid) {
		this.holeid = holeid;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getLat() {
		return lat;
	}
	public void setLat(Integer lat) {
		this.lat = lat;
	}
	public Integer getLon() {
		return lon;
	}
	public void setLon(Integer lon) {
		this.lon = lon;
	} 
}
