package com.zsh.labouCapital.service.handler;

import java.util.Date;

import com.zsh.labouCapital.util.DateTimeUtil;

public abstract class AbstractHandler {

	public String generateTimeStamp(Date dateTime) {
        return DateTimeUtil.formatDate(dateTime, DateTimeUtil.DEFAULT_DATE_TIME_PATTERN6);
    }
}
