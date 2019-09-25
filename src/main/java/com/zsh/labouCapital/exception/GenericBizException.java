package com.zsh.labouCapital.exception;

public class GenericBizException extends Exception{
	private static final String ERROR_MESSAGE_NOT_SET = GenericBizException.class.getName()
			+ "-[ERROR_MESSAGE_NOT_SET]";
	private static final long serialVersionUID = 7069886313039447936L;
	private String errorCode;
	private String errorMessage;
	private Object[] args;
	private Object model;

	public GenericBizException(String errorCode, Throwable cause, Object... args) {
		this.errorMessage = ERROR_MESSAGE_NOT_SET;
		this.args = null;
		this.model = null;
		this.errorCode = errorCode;
		this.args = args;
	}

	public GenericBizException(String errorCode, Object[] args) {
		this(errorCode, (Throwable) null, (Object[]) args);
	}

	public GenericBizException(String errorCode, String errorMsg) {
		this.errorMessage = ERROR_MESSAGE_NOT_SET;
		this.args = null;
		this.model = null;
		this.errorCode = errorCode;
		this.errorMessage = errorMsg;
	}

	public GenericBizException(String errorCode, String errorMsg, Throwable cause) {
		super(cause);
		this.errorMessage = ERROR_MESSAGE_NOT_SET;
		this.args = null;
		this.model = null;
		this.errorCode = errorCode;
		this.errorMessage = errorMsg;
	}

	public GenericBizException(String errorCode) {
		this.errorMessage = ERROR_MESSAGE_NOT_SET;
		this.args = null;
		this.model = null;
		this.errorCode = errorCode;
	}

	@Deprecated
	public GenericBizException() {
		this.errorMessage = ERROR_MESSAGE_NOT_SET;
		this.args = null;
		this.model = null;
		this.errorCode = null;
	}

	public String getMessage() {
		return this.isErrorMessageSet()
				? String.format("ErrorCode:<%s>,ErrorMessage:<%s>", this.errorCode, this.errorMessage)
				: String.format("ErrorCode:<%s>", this.errorCode);
	}

	public boolean isErrorMessageSet() {
		return !ERROR_MESSAGE_NOT_SET.equals(this.getErrorMessage());
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public Object[] getArgs() {
		return this.args;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getModel() {
		return this.model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
}
