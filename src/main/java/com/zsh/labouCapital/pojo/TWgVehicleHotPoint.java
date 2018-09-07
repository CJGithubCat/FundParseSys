package com.zsh.labouCapital.pojo;
import java.io.Serializable;

/**
 * 
 * @author gongyu
 * 车辆热点实体类
 */
public class TWgVehicleHotPoint implements Serializable{     
	private static final long serialVersionUID = 1L;
	
	private String referenceposition;//参考位置
	private String 	plate_no;
	private String 	plate_color;
	private String attributesPath;//所属机构路径
	private int hot_point_id;
	private int vehicle_id;
	private double lon;
	private double lat;
	private int radius;
	private int point_amount;//点的数量
	private String  query_start_time;
	private String  query_end_time;
	/**
	 * 2017-12-20 新增字段
	 * @return
	 */
	private Integer financing_count;//融资期限
	private Integer repay_count;//已还期数
	private String repay_status;//还款状态
	private String contract_no;//合同号
	private String work_address;//工作地址
	private String home_address;//家庭住址
	private String driver_name;//客户姓名
	private String distributor;//经销商
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public int getHot_point_id() {
		return hot_point_id;
	}
	public void setHot_point_id(int hot_point_id) {
		this.hot_point_id = hot_point_id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getPoint_amount() {
		return point_amount;
	}
	public void setPoint_amount(int point_amount) {
		this.point_amount = point_amount;
	}
	public String getQuery_start_time() {
		return query_start_time;
	}
	public void setQuery_start_time(String query_start_time) {
		this.query_start_time = query_start_time;
	}
	public String getQuery_end_time() {
		return query_end_time;
	}
	public void setQuery_end_time(String query_end_time) {
		this.query_end_time = query_end_time;
	}
	public String getPlate_no() {
		return plate_no;
	}
	public void setPlate_no(String plate_no) {
		this.plate_no = plate_no;
	}
	public String getPlate_color() {
		return plate_color;
	}
	public void setPlate_color(String plate_color) {
		this.plate_color = plate_color;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getReferenceposition() {
		return referenceposition;
	}
	public void setReferenceposition(String referenceposition) {
		this.referenceposition = referenceposition;
	}
	public Integer getFinancing_count() {
		return financing_count;
	}
	public void setFinancing_count(Integer financing_count) {
		this.financing_count = financing_count;
	}
	public Integer getRepay_count() {
		return repay_count;
	}
	public void setRepay_count(Integer repay_count) {
		this.repay_count = repay_count;
	}
	public String getRepay_status() {
		return repay_status;
	}
	public void setRepay_status(String repay_status) {
		this.repay_status = repay_status;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getWork_address() {
		return work_address;
	}
	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	@Override
	public String toString() {
		return "TWgVehicleHotPoint [referenceposition=" + referenceposition + ", plate_no=" + plate_no
				+ ", plate_color=" + plate_color + ", attributesPath=" + attributesPath + ", hot_point_id="
				+ hot_point_id + ", vehicle_id=" + vehicle_id + ", lon=" + lon + ", lat=" + lat + ", radius=" + radius
				+ ", point_amount=" + point_amount + ", query_start_time=" + query_start_time + ", query_end_time="
				+ query_end_time + ", financing_count=" + financing_count + ", repay_count=" + repay_count
				+ ", repay_status=" + repay_status + ", contract_no=" + contract_no + ", work_address=" + work_address
				+ ", home_address=" + home_address + ", driver_name=" + driver_name + ", distributor=" + distributor
				+ "]";
	}
}
