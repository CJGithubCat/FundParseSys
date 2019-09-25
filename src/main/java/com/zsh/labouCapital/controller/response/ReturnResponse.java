package com.zsh.labouCapital.controller.response;

/**
 * @ClassName:  ReturnResponse   
 * @Description:统一的返回格式
 * @author: cj
 * @date:   2019年8月17日 下午9:29:09
 */
public class ReturnResponse {
	public String code;
	public int count;
	public Object data;
	public String msg;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
