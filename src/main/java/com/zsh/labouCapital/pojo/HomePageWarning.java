package com.zsh.labouCapital.pojo;

public class HomePageWarning {
	private Integer agencyId;//机构id	
	private String attributesPath;//当前用户的attributes;
	private int p_six_day_not_move_count;//6天停车未移动 
	private int p_supper_speed_count;//疑似风险信息
	private String sixDayNotMovewarnName;//六天未移动警情名称
	private String supperSpeedName;//警情名称
	private String unpackName;//拆机警情名称
	private String pullOutName;//拔除警情名称
	private String powerDumpName;//主电台被切断警情名称
	private int p_unpack_waring_count ;//拆机报警
	private int p_pull_out_waring_count ; //   拔除报警
	private int p_power__dump_count ;   // 车台主电被切断报警
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
	public int getP_six_day_not_move_count() {
		return p_six_day_not_move_count;
	}
	public void setP_six_day_not_move_count(int p_six_day_not_move_count) {
		this.p_six_day_not_move_count = p_six_day_not_move_count;
	}
	public int getP_supper_speed_count() {
		return p_supper_speed_count;
	}
	public void setP_supper_speed_count(int p_supper_speed_count) {
		this.p_supper_speed_count = p_supper_speed_count;
	}
	public String getSixDayNotMovewarnName() {
		return sixDayNotMovewarnName;
	}
	public void setSixDayNotMovewarnName(String sixDayNotMovewarnName) {
		this.sixDayNotMovewarnName = sixDayNotMovewarnName;
	}
	public String getSupperSpeedName() {
		return supperSpeedName;
	}
	public void setSupperSpeedName(String supperSpeedName) {
		this.supperSpeedName = supperSpeedName;
	}
	public String getUnpackName() {
		return unpackName;
	}
	public void setUnpackName(String unpackName) {
		this.unpackName = unpackName;
	}
	public String getPullOutName() {
		return pullOutName;
	}
	public void setPullOutName(String pullOutName) {
		this.pullOutName = pullOutName;
	}
	public String getPowerDumpName() {
		return powerDumpName;
	}
	public void setPowerDumpName(String powerDumpName) {
		this.powerDumpName = powerDumpName;
	}
	public int getP_unpack_waring_count() {
		return p_unpack_waring_count;
	}
	public void setP_unpack_waring_count(int p_unpack_waring_count) {
		this.p_unpack_waring_count = p_unpack_waring_count;
	}
	public int getP_pull_out_waring_count() {
		return p_pull_out_waring_count;
	}
	public void setP_pull_out_waring_count(int p_pull_out_waring_count) {
		this.p_pull_out_waring_count = p_pull_out_waring_count;
	}
	public int getP_power__dump_count() {
		return p_power__dump_count;
	}
	public void setP_power__dump_count(int p_power__dump_count) {
		this.p_power__dump_count = p_power__dump_count;
	}
	@Override
	public String toString() {
		return "HomePageWarning [agencyId=" + agencyId + ", attributesPath=" + attributesPath
				+ ", p_six_day_not_move_count=" + p_six_day_not_move_count + ", p_supper_speed_count="
				+ p_supper_speed_count + ", sixDayNotMovewarnName=" + sixDayNotMovewarnName + ", supperSpeedName="
				+ supperSpeedName + ", unpackName=" + unpackName + ", pullOutName=" + pullOutName + ", powerDumpName="
				+ powerDumpName + ", p_unpack_waring_count=" + p_unpack_waring_count + ", p_pull_out_waring_count="
				+ p_pull_out_waring_count + ", p_power__dump_count=" + p_power__dump_count + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
