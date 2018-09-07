package com.zsh.labouCapital.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private  static final  String dateFormat="yyyy-MM-dd hh:mm:ss";
	public static String getDateString(Date d){
		SimpleDateFormat format=new SimpleDateFormat(dateFormat);
		return format.format(d);
	}
}
