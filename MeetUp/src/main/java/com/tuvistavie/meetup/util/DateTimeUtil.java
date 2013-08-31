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
}
