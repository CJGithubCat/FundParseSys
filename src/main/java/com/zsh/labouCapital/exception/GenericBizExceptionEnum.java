package com.zsh.labouCapital.exception;

/**
 * @ClassName:  GenericBizExceptionEnum   
 * @Description:维护统一的系统异常参数
 * @author: cj
 * @date:   2019年8月17日 下午9:46:52
 */
public enum GenericBizExceptionEnum {
	/***************************业务性的返回码***********************************/
	BIZ_RETURN_CODE("0000", "成功"), 
	
	
	/***************************业务性的错误码***********************************/
	ERROR_BIZ_PARAM_NULL("0001", "参数为空"), 
	
	
	
	/**************************************************************/
	EXCEPTION_SYS_ERROR("9999", "系统异常");

	private String bizCode;
	private String bizMsg;

	private GenericBizExceptionEnum(String bizCode, String bizMsg) {
		this.bizCode = bizCode;
		this.bizMsg = bizMsg;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizMsg() {
		return bizMsg;
	}

	public void setBizMsg(String bizMsg) {
		this.bizMsg = bizMsg;
	}
}
