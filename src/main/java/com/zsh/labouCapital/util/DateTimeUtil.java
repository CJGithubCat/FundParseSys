package com.zsh.labouCapital.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
    public static final String DEFAULT_DATE_PATTERN2 = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss SSSS";
    public static final String DEFAULT_DATE_TIME_PATTERN2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_PATTERN3 = "yyyy-MM-dd HH:00:00";
    public static final String DEFAULT_DATE_TIME_PATTERN_S = "yyyy-MM-dd 00:00:00";
    public static final String DEFAULT_DATE_TIME_PATTERN4 = "yyyyMMddHHmmssSSSS";
    public static final String DEFAULT_DATE_TIME_PATTERN5 = "yyyy-MM-dd HH:mm";

    public static String formatDate(Date date, String pattern) {
        return (new DateTime(date)).toString(pattern);
    }

    public static String formatNow() {
        return formatDate(DateTime.now().toDate(), "yyyy-MM-dd HH:mm:ss SSSS");
    }

    public static String formateByPattern5(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm");
    }

    public static String formateByPattern2(Date date) {
        return date == null ? "" : formatDate(date, "yyyy-MM-dd");
    }

    public static String formateByTimePattern2(Date date) {
        return date == null ? "" : formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatCurrentByPattern4() {
        return formatDate(DateTime.now().toDate(), "yyyyMMddHHmmssSSSS");
    }

    public static Date parseDefaultTime(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss SSSS");
    }

    public static Date parseDefaultDate(String dateStr) {
        return parseDate(dateStr, "yyyyMMdd");
    }

    public static Date parsePattern2(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parsePattern4(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    public static Date now() {
        Calendar cal = Calendar.getInstance();
        synchronized (cal) {
            cal.setTimeInMillis(System.currentTimeMillis());
            return cal.getTime();
        }
    }

    public static Date parseDate(String dateStr, String pattern) {
        if (null != dateStr && !"".equals(dateStr)) {
            DateTimeZone dateTimeZone = DateTimeZone.forID("Etc/GMT-8");
            DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withZone(dateTimeZone);
            return DateTime.parse(dateStr, formatter).toDate();
        } else {
            return null;
        }
    }

    public static String formatDate(Date inputDate) {
        String result = "";

        try {
            result = DateUtil.printDateTime(inputDate);
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
        }

        return result;
    }

    public static String formatDate2(Date inputDate) {
        String result = "";

        try {
            result = DateUtil.printDate(inputDate);
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
        }

        return result;
    }

    public static Date getIntHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int hour = ca.get(11);
        int minute = ca.get(12);
        if (minute <= 30) {
            ca.set(12, 0);
            ca.set(13, 0);
        } else {
            ca.set(11, hour + 1);
            ca.set(12, 0);
            ca.set(13, 0);
        }

        return ca.getTime();
    }

    public static Date getIntAfterHalfHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int hour = ca.get(11);
        int minute = ca.get(12);
        if (minute <= 30) {
            ca.set(12, 30);
            ca.set(13, 0);
        } else {
            ca.set(11, hour + 1);
            ca.set(12, 0);
            ca.set(13, 0);
        }

        return ca.getTime();
    }

    public static Date getIntOrHalfHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int minute = ca.get(12);
        if (minute <= 30) {
            ca.set(12, 0);
            ca.set(13, 0);
        } else {
            ca.set(12, 30);
            ca.set(13, 0);
        }

        return ca.getTime();
    }

    public static Date getDownDayTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(11, 0);
            ca.set(12, 0);
            ca.set(13, 0);
            ca.set(14, 0);
            return ca.getTime();
        }
    }

    public static Date getDownDayTime(String str) {
        Date date = parsePattern2(str);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(11, 0);
        ca.set(12, 0);
        ca.set(13, 0);
        ca.set(14, 0);
        return ca.getTime();
    }

    public static Date getDownHalfOrIntDayTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int min = ca.get(12);
        int second = ca.get(13);
        if (min < 30 && min >= 0 && second > 0 || min < 30 && min > 0 && second >= 0) {
            ca.set(12, 30);
        } else if (min > 30 && second >= 0 || min >= 30 && second > 0) {
            int hour = ca.get(11);
            ca.set(11, hour + 1);
            ca.set(12, 0);
        } else if ((min == 0 || min == 30) && second == 0) {
            ca.set(12, min);
        }

        ca.set(13, 0);
        ca.set(14, 0);
        return ca.getTime();
    }

    public static Date getHalfHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int hour = ca.get(11);
        int min = ca.get(12);
        if (min == 0) {
            ca.set(12, 30);
        } else if (min == 30) {
            ca.set(11, hour + 1);
            ca.set(12, 0);
        }

        return ca.getTime();
    }

    public static void main(String[] args) throws Exception {
    }

    public static Date getUpHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(12, 0);
        ca.set(13, 0);
        return ca.getTime();
    }

    public static Date getUpDateTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(12, 0);
        ca.set(13, 0);
        Date truncZD = ca.getTime();
        long numZD = truncZD.getTime();
        long numDate = (new Date()).getTime() - numZD;
        long halfHour = numDate / 1000L / 60L;
        if (halfHour >= 30L) {
            numZD += 1800000L;
            return new Date(numZD);
        } else {
            return truncZD;
        }
    }

    public static Date getAddHourDateTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(12, 0);
        ca.set(13, 0);
        Date truncZD = ca.getTime();
        long numZD = truncZD.getTime();
        long numDate = (new Date()).getTime() - numZD;
        long halfHour = numDate / 1000L / 60L;
        if (halfHour >= 30L) {
            numZD += 5400000L;
            return new Date(numZD);
        } else {
            numZD += 3600000L;
            return new Date(numZD);
        }
    }

    public static Date getDownHourTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            int hour = ca.get(11);
            ca.set(11, hour + 1);
            ca.set(12, 0);
            ca.set(13, 0);
            ca.set(14, 0);
            return ca.getTime();
        }
    }

    public static Date get1HourLater(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            int hour = ca.get(11);
            ca.set(10, hour + 1);
            return ca.getTime();
        }
    }

    public static int getYear(Date date) {
        if (null == date) {
            return 0;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            return ca.get(1);
        }
    }

    public static int getMonth(Date date) {
        if (null == date) {
            return 0;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            return ca.get(2) + 1;
        }
    }

    public static int getDay(Date date) {
        if (null == date) {
            return 0;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            return ca.get(5);
        }
    }

    public static int getHour(Date date) {
        if (null == date) {
            return 0;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            return ca.get(11);
        }
    }

    public static int getMinute(Date date) {
        if (null == date) {
            return 0;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            return ca.get(12);
        }
    }

    public static Date get150DaysLater(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            int day = ca.get(6);
            ca.set(6, day + 150);
            return ca.getTime();
        }
    }

    public static List<Date> getDayTimeList(Date date, int dayNum, int timeNum) {
        if (null == date) {
            return null;
        } else {
            List<Date> dateList = new ArrayList();
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(11, 0);
            ca.set(12, 0);
            ca.set(13, 0);
            ca.set(14, 0);

            int nowDay;
            int maxDayOfThisYear;
            for (nowDay = 0; nowDay < 24; ++nowDay) {
                ca.set(11, nowDay);

                for (maxDayOfThisYear = 0; maxDayOfThisYear < timeNum / 24; ++maxDayOfThisYear) {
                    ca.set(12, maxDayOfThisYear * 60 / (timeNum / 24));
                    dateList.add(ca.getTime());
                }
            }

            nowDay = ca.get(6);
            maxDayOfThisYear = ca.getActualMaximum(6);
            int nextYearDays;
            int dayCount;
            int hourCount;
            int timeCount;
            if (maxDayOfThisYear - nowDay < dayNum - 1) {
                for (nextYearDays = nowDay + 1; nextYearDays <= maxDayOfThisYear; ++nextYearDays) {
                    ca.set(6, nextYearDays);

                    for (dayCount = 0; dayCount < 24; ++dayCount) {
                        ca.set(11, dayCount);

                        for (hourCount = 0; hourCount < timeNum / 24; ++hourCount) {
                            ca.set(12, hourCount * 60 / (timeNum / 24));
                            dateList.add(ca.getTime());
                        }
                    }
                }

                nextYearDays = dayNum - 1 - (maxDayOfThisYear - nowDay);
                if (nextYearDays > 0) {
                    ca.roll(1, 1);

                    for (dayCount = 1; dayCount <= nextYearDays; ++dayCount) {
                        ca.set(6, dayCount);

                        for (hourCount = 0; hourCount < 24; ++hourCount) {
                            ca.set(11, hourCount);

                            for (timeCount = 0; timeCount < timeNum / 24; ++timeCount) {
                                ca.set(12, timeCount * 60 / (timeNum / 24));
                                dateList.add(ca.getTime());
                            }
                        }
                    }
                }

                return dateList;
            } else {
                nextYearDays = nowDay + dayNum - 1;

                for (dayCount = nowDay + 1; dayCount <= nextYearDays; ++dayCount) {
                    ca.set(6, dayCount);

                    for (hourCount = 0; hourCount < 24; ++hourCount) {
                        ca.set(11, hourCount);

                        for (timeCount = 0; timeCount < timeNum / 24; ++timeCount) {
                            ca.set(12, timeCount * 60 / (timeNum / 24));
                            dateList.add(ca.getTime());
                        }
                    }
                }

                return dateList;
            }
        }
    }

    public static Date getDayStartDateTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(11, 0);
            ca.set(12, 0);
            ca.set(13, 0);
            ca.set(14, 0);
            return ca.getTime();
        }
    }

    public static Date before(Date date, int n) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(5, -n);
            return ca.getTime();
        }
    }

    public static Date afterHour(Date date, int n) {
        if (null == date) {
            return null;
        } else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(10, n);
            return ca.getTime();
        }
    }

    public static BigDecimal diffHourBetweenDate(Date dateStart, Date dateEnd) {
        long diff = dateEnd.getTime() - dateStart.getTime();
        BigDecimal bd = (new BigDecimal(diff)).divide(new BigDecimal(86400000), 2, 4);
        return bd;
    }

    public static Date afterDay(Date date, int days) {
        if (null == date) {
            return null;
        } else {
            Calendar calo = Calendar.getInstance();
            calo.setTime(date);
            calo.add(6, days);
            return calo.getTime();
        }
    }

    public static int daysBetween(Date start, Date end) {
        start = parsePattern4(formatDate2(start));
        end = parsePattern4(formatDate2(end));
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static boolean compare(Date a, String opStr, Date b) {
        if (">".equals(opStr)) {
            return a.compareTo(b) > 0;
        } else if (">=".equals(opStr)) {
            return a.compareTo(b) >= 0;
        } else if ("=".equals(opStr)) {
            return a.compareTo(b) == 0;
        } else if ("<=".equals(opStr)) {
            return a.compareTo(b) <= 0;
        } else if ("<".equals(opStr)) {
            return a.compareTo(b) < 0;
        } else {
            return false;
        }
    }
}