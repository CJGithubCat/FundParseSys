package com.zsh.labouCapital.pojo;

import java.util.Date;

/**
 * @Package:com.gboss.pojo
 * @ClassName:CustVehicle
 * @Description:TODO
 * @author:liujie
 * @date:2016-3-1 上午11:49:42
 */

public class TWgCustVehicle {
	private Long cv_id;// '客户与车辆关系id',
	private Long customer_id;// '客户ID',
	private Long vehicle_id;// '车辆ID',

	public Long getCv_id() {
		return cv_id;
	}

	public void setCv_id(Long cv_id) {
		this.cv_id = cv_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

}
