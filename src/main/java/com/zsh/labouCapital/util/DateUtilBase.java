package com.zsh.labouCapital.util;
import com.google.common.collect.Lists;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

public class DateUtilBase {
    public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String COMPACT_DATE_PATTERN = "yyyyMMdd";
    public static final String COMPACT_DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    public static final String COMPACT_TIME_PATTERN = "HHmmss";
    private static final ConcurrentHashMap<String, DateTimeFormatter> formatterMap = new ConcurrentHashMap();

    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        DateTimeFormatter formatter = (DateTimeFormatter) formatterMap.get(pattern);
        if (formatter == null) {
            formatterMap.putIfAbsent(pattern, DateTimeFormat.forPattern(pattern));
            formatter = (DateTimeFormatter) formatterMap.get(pattern);
        }

        return formatter;
    }

    public static Date getDate(String s, String pattern) throws Exception {
        if (!StringUtils.hasText(s)) {
            return null;
        } else {
            String targetPattern = pattern;
            if (pattern == null) {
                targetPattern = DEFAULT_DATE_PATTERN;
            }

            String temp = s;
            Date tempVal = null;

            try {
                DateTimeFormatter formatter = getDateTimeFormatter(targetPattern);
                if (!targetPattern.contains("HH")) {
                    tempVal = LocalDateTime.parse(temp, formatter).toDate();
                } else {
                    tempVal = DateTime.parse(temp, formatter).toDate();
                }

                return tempVal;
            } catch (Exception var7) {
                String msg = String.format("date format error ,pattern=[%s], cause: %s", pattern, var7.getMessage());
                throw new Exception(msg, var7);
            }
        }
    }

    public static String getDateString(Date date, String pattern) throws Exception {
        if (date == null) {
            return null;
        } else {
            String targetPattern = pattern;
            if (pattern == null) {
                targetPattern = DEFAULT_DATE_PATTERN;
            }

            String tempVal = null;

            try {
                DateTime temp = new DateTime(date);
                DateTimeFormatter formatter = getDateTimeFormatter(targetPattern);
                tempVal = formatter.print(temp);
                return tempVal;
            } catch (Exception var6) {
                String msg = String.format("date format error ,pattern=[%s], cause: %s", pattern, var6.getMessage());
                throw new Exception(msg, var6);
            }
        }
    }

    public static void setDefaultDatePattern(String pattern) {
        if (pattern != null && !pattern.trim().isEmpty()) {
            DEFAULT_DATE_PATTERN = pattern;
        }
    }

    static {
        List<String> patterns = Lists.newArrayList(new String[]{DEFAULT_DATE_PATTERN, "yyyy-MM-dd",
                "yyyy-MM-dd HH:mm:ss", "HH:mm:ss", "yyyyMMdd", "yyyyMMddHHmmss", "HHmmss"});
        Iterator var1 = patterns.iterator();

        while (var1.hasNext()) {
            String e = (String) var1.next();
            formatterMap.putIfAbsent(e, DateTimeFormat.forPattern(e));
        }
    }
}