package com.zsh.labouCapital.util;

public class TipPop {
	private final static String TITLE = "弹窗";

	public static void main(String[] args) {
		InfoUtil test = new InfoUtil();
		test.show(TITLE, "这是一个弹窗测试！AAAAAAAAAAAAAAAAAAAAAAA"
				+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
				+ "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD"
				+ "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
				+ "FF");
	}
}
