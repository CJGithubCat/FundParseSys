package com.zsh.labouCapital.pojo;
import java.io.Serializable;

/**
 * 
 * @author gongyu
 * 地图划定的区域。区域实体类
 */
public class AreaPoint implements Serializable{     
	
	 private static final long serialVersionUID = 6885366037067118212L;
	
	 private Long areaId;//区域编号  long
     private Long cusId;//用户ID,区域创建人ID long
     private Long departId;//机构ID,创建该区域的用户所处机构 long
     private String areaName;//区域名称
     private short shapeType;//区域形状 1，圆形 2，长方形 3，多边形 short
     private Double lon1;//经度  double
     private Double lat1;//纬度 double
     private Double lon2;//经度 double
     private Double lat2;//纬度 double
     private String point; //区域点集
     private String centre; //圆形时圆心经纬度
     private Integer rad; //圆形时半径 int
     private Long editCusId;//最后一次编辑用户的ID long
     private short zoonState;//可见  0：自己可见，1:机构可见,2:全网可见 short
     private Integer pId;//共享区域省份
     private Integer pcId;//共享区域城市
     private short validState;//区域是否有效 0：有效，1：失效    short
     private Integer mapZoom;//地图级别     
     private short areaType;//区域类型，普通1，高危2
     private Integer pccId;//省市区域点集ID   
     private Long userId;//当前登录角色Id
     private Long gxId;// 下载共享区域 关联关系表id
     private Integer province; //
     private Integer city; //
     private Integer county; //
	public Long getAreaId() {
		return areaId;
	}



	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}



	public Long getCusId() {
		return cusId;
	}



	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}



	public Long getDepartId() {
		return departId;
	}



	public void setDepartId(Long departId) {
		this.departId = departId;
	}



	public String getAreaName() {
		return areaName;
	}



	public void setAreaName(String areaName) {
		if(areaName!=null){
			this.areaName = areaName.trim();
		}
		
	}



	



	public Double getLon1() {
		return lon1;
	}



	public void setLon1(Double lon1) {
		this.lon1 = lon1;
	}



	public Double getLat1() {
		return lat1;
	}



	public void setLat1(Double lat1) {
		this.lat1 = lat1;
	}



	public Double getLon2() {
		return lon2;
	}



	public void setLon2(Double lon2) {
		this.lon2 = lon2;
	}



	public Double getLat2() {
		return lat2;
	}



	public void setLat2(Double lat2) {
		this.lat2 = lat2;
	}



	public String getPoint() {
		return point;
	}



	public void setPoint(String point) {
		this.point = point;
	}



	public String getCentre() {
		return centre;
	}



	public void setCentre(String centre) {
		this.centre = centre;
	}



	public Integer getRad() {
		return rad;
	}



	public void setRad(Integer rad) {
		this.rad = rad;
	}



	public Long getEditCusId() {
		return editCusId;
	}



	public void setEditCusId(Long editCusId) {
		this.editCusId = editCusId;
	}



	



	public short getZoonState() {
		return zoonState;
	}



	public void setZoonState(short zoonState) {
		this.zoonState = zoonState;
	}



	public short getValidState() {
		return validState;
	}



	public void setValidState(short validState) {
		this.validState = validState;
	}



	public Integer getMapZoom() {
		return mapZoom;
	}



	public void setMapZoom(Integer mapZoom) {
		this.mapZoom = mapZoom;
	}



	public short getShapeType() {
		return shapeType;
	}



	public void setShapeType(short shapeType) {
		this.shapeType = shapeType;
	}



	@Override
	public String toString(){
		//unimplements
		return "";
	}



	public short getAreaType() {
		return areaType;
	}



	public void setAreaType(short areaType) {
		this.areaType = areaType;
	}



	public Integer getPccId() {
		return pccId;
	}



	public void setPccId(Integer pccId) {
		this.pccId = pccId;
	}



	public Integer getpId() {
		return pId;
	}



	public void setpId(Integer pId) {
		this.pId = pId;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public Integer getPcId() {
		return pcId;
	}


	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}



	public Long getGxId() {
		return gxId;
	}



	public void setGxId(Long gxId) {
		this.gxId = gxId;
	}



	public Integer getProvince() {
		return province;
	}



	public void setProvince(Integer province) {
		this.province = province;
	}



	public Integer getCity() {
		return city;
	}



	public void setCity(Integer city) {
		this.city = city;
	}



	public Integer getCounty() {
		return county;
	}



	public void setCounty(Integer county) {
		this.county = county;
	}	
     
	
     
}
