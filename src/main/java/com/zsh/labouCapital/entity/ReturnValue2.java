package com.zsh.labouCapital.entity;


/**
 * API操作结果
 * 
 * @author Lxb
 * 
 */
public class ReturnValue2 {
	/** 是否成功 */
	private int count;
	/** 业务数据 */
	private Object data;
	/** 错误代码 */
	private String code;
	/** 错误说明 */
	private String msg;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
