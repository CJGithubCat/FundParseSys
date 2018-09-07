package com.zsh.labouCapital.vo;

public class MyException extends Exception {

	private static final long serialVersionUID = 1L;
	String message = "";

	public MyException(String s) {
		super(s);
	}
	
}
