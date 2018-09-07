package com.zsh.labouCapital.entity;


/**
 * API操作结果
 * 
 * @author Lxb
 * 
 */
public class ReturnValue {
	/** 是否成功 */
	private boolean success;
	/** 业务数据 */
	private Object datas;
	/** 错误代码 */
	private Integer errorCode;
	/** 错误说明 */
	private String message;

	public void systemError() {
		this.setErrorCode(500);
		this.setMessage("系统内部错误!");
	}
	
	public void saveErrror() {
		this.setErrorCode(501);
		this.setMessage("系统错误,保存失败!");
	}
	
	public void editError() {
		this.setErrorCode(502);
		this.setMessage("系统错误,编辑失败!");
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.success = true;
		this.datas = datas;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	
}
