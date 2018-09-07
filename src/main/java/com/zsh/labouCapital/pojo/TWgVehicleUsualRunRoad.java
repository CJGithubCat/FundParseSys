package com.zsh.labouCapital.pojo;
import java.io.Serializable;

/**
 * 车辆常跑路线实体类
 */
public class TWgVehicleUsualRunRoad implements Serializable{     
	private static final long serialVersionUID = 1L;
	
	private String 	plate_no;
	private String 	plate_color;
	private String 	attributesPath;//所属机构路径
	private int 	usually_run_road_id;
	private int 	vehicle_id;
	private String 	point_set;
	private String 	referenceposition;
	private int 	point_amount;//点的数量
	private String  query_start_time;
	private String  query_end_time;
	/**
	 * 2017-12-20 新增字段
	 * @return
	 */
	private String contract_no;//合同号
	private String work_address;//工作地址
	private String home_address;//家庭住址
	private String driver_name;//客户姓名
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
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public int getUsually_run_road_id() {
		return usually_run_road_id;
	}
	public void setUsually_run_road_id(int usually_run_road_id) {
		this.usually_run_road_id = usually_run_road_id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getPoint_set() {
		return point_set;
	}
	public void setPoint_set(String point_set) {
		this.point_set = point_set;
	}
	public String getReferenceposition() {
		return referenceposition;
	}
	public void setReferenceposition(String referenceposition) {
		this.referenceposition = referenceposition;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	@Override
	public String toString() {
		return "TWgVehicleUsualRunRoad [plate_no=" + plate_no + ", plate_color=" + plate_color + ", attributesPath="
				+ attributesPath + ", usually_run_road_id=" + usually_run_road_id + ", vehicle_id=" + vehicle_id
				+ ", point_set=" + point_set + ", referenceposition=" + referenceposition + ", point_amount="
				+ point_amount + ", query_start_time=" + query_start_time + ", query_end_time=" + query_end_time
				+ ", contract_no=" + contract_no + ", work_address=" + work_address + ", home_address=" + home_address
				+ ", driver_name=" + driver_name + "]";
	}
}
