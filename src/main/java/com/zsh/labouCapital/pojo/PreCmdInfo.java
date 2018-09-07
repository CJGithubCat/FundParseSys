package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 预存指令实体类
 */
public class PreCmdInfo implements Serializable{

	private static final long serialVersionUID = -2787179356857847165L;
	
	private Long unit_id;
	private String call_letter;
	private String op_name;
	private Integer op_src;
	private Integer cmd_id;
	private String send_params;
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	public String getCall_letter() {
		return call_letter;
	}
	public void setCall_letter(String call_letter) {
		this.call_letter = call_letter;
	}
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	public Integer getOp_src() {
		return op_src;
	}
	public void setOp_src(Integer op_src) {
		this.op_src = op_src;
	}
	public Integer getCmd_id() {
		return cmd_id;
	}
	public void setCmd_id(Integer cmd_id) {
		this.cmd_id = cmd_id;
	}
	public String getSend_params() {
		return send_params;
	}
	public void setSend_params(String send_params) {
		this.send_params = send_params;
	}
	
	
}
