package com.zsh.labouCapital.pojo;

public class IndexCount {
	private Integer agencyId;//机构id	
	private String attributesPath;//当前用户的attributes;
	private int flag;//是否首次登录，0--首次登陆； 1--不是首次登录
	private int p_online_count;//在线车辆数量
	private int p_offline_count;//离线车辆数量
	private int p_all_count;//总的车辆数目
	private int p_curr_month_inc_count;//当前月新增车辆数目
	private int p_curr_month_inc_online_count;//当月新增上线车辆数目
	private int p_high_risk_count;//高风险车辆数量
	private int p_expired_time_count;//逾期车辆数量
	private int p_no_gps_last_5_day_count;//5天不在线
	private int p_curr_month_settle_count;//当月结清贷款车辆数
	private String treeNodePath;//存储点击时候的右边树的attributesPath;即存储现在选择的机构的attributePath;
	private int p_vehicle_hot_point_count;//热点数量
	private int p_vehicle_usual_runroda_count;//长跑路线数量
	//private int p_supper_speed_count;//疑似
	
	public Integer getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}
	public String getAttributesPath() {
		return attributesPath;
	}
	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getP_online_count() {
		return p_online_count;
	}
	public void setP_online_count(int p_online_count) {
		this.p_online_count = p_online_count;
	}
	public int getP_offline_count() {
		return p_offline_count;
	}
	public void setP_offline_count(int p_offline_count) {
		this.p_offline_count = p_offline_count;
	}
	public int getP_all_count() {
		return p_all_count;
	}
	public void setP_all_count(int p_all_count) {
		this.p_all_count = p_all_count;
	}
	public int getP_curr_month_inc_count() {
		return p_curr_month_inc_count;
	}
	public void setP_curr_month_inc_count(int p_curr_month_inc_count) {
		this.p_curr_month_inc_count = p_curr_month_inc_count;
	}
	public int getP_high_risk_count() {
		return p_high_risk_count;
	}
	public void setP_high_risk_count(int p_high_risk_count) {
		this.p_high_risk_count = p_high_risk_count;
	}
	public int getP_expired_time_count() {
		return p_expired_time_count;
	}
	public void setP_expired_time_count(int p_expired_time_count) {
		this.p_expired_time_count = p_expired_time_count;
	}
	public int getP_no_gps_last_5_day_count() {
		return p_no_gps_last_5_day_count;
	}
	public void setP_no_gps_last_5_day_count(int p_no_gps_last_5_day_count) {
		this.p_no_gps_last_5_day_count = p_no_gps_last_5_day_count;
	}
	public int getP_curr_month_settle_count() {
		return p_curr_month_settle_count;
	}
	public void setP_curr_month_settle_count(int p_curr_month_settle_count) {
		this.p_curr_month_settle_count = p_curr_month_settle_count;
	}
	public String getTreeNodePath() {
		return treeNodePath;
	}
	public void setTreeNodePath(String treeNodePath) {
		this.treeNodePath = treeNodePath;
	}
	public int getP_vehicle_hot_point_count() {
		return p_vehicle_hot_point_count;
	}
	public void setP_vehicle_hot_point_count(int p_vehicle_hot_point_count) {
		this.p_vehicle_hot_point_count = p_vehicle_hot_point_count;
	}
	public int getP_vehicle_usual_runroda_count() {
		return p_vehicle_usual_runroda_count;
	}
	public void setP_vehicle_usual_runroda_count(int p_vehicle_usual_runroda_count) {
		this.p_vehicle_usual_runroda_count = p_vehicle_usual_runroda_count;
	}
	
	public int getP_curr_month_inc_online_count() {
		return p_curr_month_inc_online_count;
	}
	public void setP_curr_month_inc_online_count(int p_curr_month_inc_online_count) {
		this.p_curr_month_inc_online_count = p_curr_month_inc_online_count;
	}
	
	@Override
	public String toString() {
		return "IndexCount [agencyId=" + agencyId + ", attributesPath=" + attributesPath + ", flag=" + flag
				+ ", p_online_count=" + p_online_count + ", p_offline_count=" + p_offline_count + ", p_all_count="
				+ p_all_count + ", p_curr_month_inc_count=" + p_curr_month_inc_count
				+ ", p_curr_month_inc_online_count=" + p_curr_month_inc_online_count + ", p_high_risk_count="
				+ p_high_risk_count + ", p_expired_time_count=" + p_expired_time_count + ", p_no_gps_last_5_day_count="
				+ p_no_gps_last_5_day_count + ", p_curr_month_settle_count=" + p_curr_month_settle_count
				+ ", treeNodePath=" + treeNodePath + ", p_vehicle_hot_point_count=" + p_vehicle_hot_point_count
				+ ", p_vehicle_usual_runroda_count=" + p_vehicle_usual_runroda_count + "]";
	}
	
}
