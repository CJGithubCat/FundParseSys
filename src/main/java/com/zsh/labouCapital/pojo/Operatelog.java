package com.zsh.labouCapital.pojo;


import java.util.Date;


public class Operatelog {
	
	private static final long serialVersionUID = 1L;
	private Long op_id;//日志id
	private Long user_id;//操作员id
	private Long subco_no;
	private Integer model_id;
	private Integer op_type;//操作类型
	private String remark;//操作说明
	private Date stamp;//操作时间
	
	
	public Long getOp_id() {
		return op_id;
	}
	public void setOp_id(Long op_id) {
		this.op_id = op_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getSubco_no() {
		return subco_no;
	}
	public void setSubco_no(Long subco_no) {
		this.subco_no = subco_no;
	}
	
	public Integer getModel_id() {
		return model_id;
	}
	public void setModel_id(Integer model_id) {
		this.model_id = model_id;
	}
	
	
	public Integer getOp_type() {
		return op_type;
	}
	public void setOp_type(Integer op_type) {
		this.op_type = op_type;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

}
