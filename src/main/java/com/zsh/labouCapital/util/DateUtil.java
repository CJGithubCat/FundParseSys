package com.zsh.labouCapital.util;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class DateUtil extends DateUtilBase {
	public static Date parseDate(String s) throws Exception {
		Date date = DateUtilBase.getDate(s, "yyyy-MM-dd");
		return date;
	}

	public static Date parseTime(String s) throws Exception {
		Date date = DateUtilBase.getDate(s, "HH:mm:ss");
		return date;
	}

	public static Date parseDateTime(String s) throws Exception {
		Date date = DateUtilBase.getDate(s, "yyyy-MM-dd HH:mm:ss");
		return date;
	}

	public static Date parseCompactDate(String s) throws Exception {
		Date date = DateUtilBase.getDate(s, "yyyyMMdd");
		return date;
	}

	public static Date parseCompactTime(String s) throws Exception {
		Date date = DateUtilBase.getDate(s, "HHmmss");
		return date;
	}

	public static Date parseCompactDateTime(String s) throws Exception {
		Date date = DateUtilBase.getDate(s, "yyyyMMddHHmmss");
		return date;
	}

	public static String printDate(Date date) throws Exception {
		String dateString = DateUtilBase.getDateString(date, "yyyy-MM-dd");
		return dateString;
	}

	public static String printCompactDate(Date date) throws Exception {
		String dateString = DateUtilBase.getDateString(date, "yyyyMMdd");
		return dateString;
	}

	public static String printDateTime(Date date) throws Exception {
		String dateString = DateUtilBase.getDateString(date, "yyyy-MM-dd HH:mm:ss");
		return dateString;
	}

	public static String printCompactDateTime(Date date) throws Exception {
		String dateString = DateUtilBase.getDateString(date, "yyyyMMddHHmmss");
		return dateString;
	}

	public static String printTime(Date date) throws Exception {
		String dateString = DateUtilBase.getDateString(date, "HH:mm:ss");
		return dateString;
	}

	public static String printCompactTime(Date date) throws Exception {
		String dateString = DateUtilBase.getDateString(date, "HHmmss");
		return dateString;
	}

	public static Date now() {
		Date now = new Date();
		return now;
	}

	public static String printNowDate() throws Exception {
		return printDate(now());
	}

	public static String printNowTime() throws Exception {
		return printTime(now());
	}

	public static String printNowDateTime() throws Exception {
		return printDateTime(now());
	}

	public static String printNowCompactDate() throws Exception {
		return printCompactDate(now());
	}

	public static String printNowCompactTime() throws Exception {
		return printCompactTime(now());
	}

	public static String printNowCompactDateTime() throws Exception {
		return printCompactDateTime(now());
	}

	public static Date tomorrow() {
		DateTime now = DateTime.now();
		now = now.plusDays(1);
		return now.toDate();
	}

	public static Date tomorrow(Date date) {
		long time = date.getTime();
		DateTime dateTime = new DateTime(time);
		dateTime = dateTime.plusDays(1);
		return dateTime.toDate();
	}

	public static Date yesterday() {
		DateTime now = DateTime.now();
		now = now.plusDays(-1);
		return now.toDate();
	}

	public static Date yesterday(Date date) {
		long time = date.getTime();
		DateTime dateTime = new DateTime(time);
		dateTime = dateTime.plusDays(-1);
		return dateTime.toDate();
	}

	public static Date afterDays(Date date, int n) {
		long time = date.getTime();
		DateTime dateTime = new DateTime(time);
		dateTime = dateTime.plusDays(n);
		return dateTime.toDate();
	}

	public static Date afterHours(Date date, int n) {
		long time = date.getTime();
		DateTime dateTime = new DateTime(time);
		dateTime = dateTime.plusHours(n);
		return dateTime.toDate();
	}

	public static Date afterMinutes(Date date, int n) {
		long time = date.getTime();
		DateTime dateTime = new DateTime(time);
		dateTime = dateTime.plusMinutes(n);
		return dateTime.toDate();
	}

	public static Date afterSeconds(Date date, int n) {
		long time = date.getTime();
		DateTime dateTime = new DateTime(time);
		dateTime = dateTime.plusSeconds(n);
		return dateTime.toDate();
	}

	public static Date removeTime(Date date) {
		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.withTimeAtStartOfDay();
		return dateTime.toDate();
	}

	public static int compareDate(Date thisOne, Date anotherOne) {
		DateTime thisDateTime = new DateTime(thisOne);
		DateTime anotherDateTime = new DateTime(anotherOne);
		thisDateTime = thisDateTime.withTimeAtStartOfDay();
		anotherDateTime = anotherDateTime.withTimeAtStartOfDay();
		int result = thisDateTime.compareTo(anotherDateTime);
		return result;
	}

	public static int dayPeriod(Date start, Date end) {
		DateTime startDateTime = new DateTime(start);
		DateTime endDateTime = new DateTime(end);
		startDateTime = startDateTime.withTimeAtStartOfDay();
		endDateTime = endDateTime.withTimeAtStartOfDay();
		Period p = new Period(startDateTime, endDateTime, PeriodType.days());
		int days = p.getDays();
		return days;
	}
}