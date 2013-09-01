package com.tuvistavie.meetup.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by daniel on 9/1/13.
 */
public final class DateTimeUtil {
    public static Date getDate(int year, int month, int day, int hour, int time) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, hour, time);
        return c.getTime();
    }

    public static String formatDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return year + "/" + month + "/" + day + " " + hour + ":" + minute;
    }
}
