package com.tuvistavie.meetup.util;

import com.tuvistavie.meetup.event.model.DateCell;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 9/1/13.
 */
public final class DateTimeUtil {
    public static final int milliSecondsInDay = 1000 * 3600 * 24;

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

    public static Date getPreviousMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        return c.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        return c.getTime();
    }

    public static String getMonthKey(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + "" + c.get(Calendar.MONTH);
    }

    public static Date getFirstDateToShow(Date date, int firstDayOfWeek) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        while (c.get(Calendar.DAY_OF_WEEK) != firstDayOfWeek) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        }
        return c.getTime();
    }

    public static Date getLastDateToShow(Date date, int firstDayOfWeek) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        while (c.get(Calendar.DAY_OF_WEEK) != firstDayOfWeek) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        }
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        return c.getTime();
    }

    public static List<DateCell> generateDateForMonth(Date date, int firstDayOfWeek) {
        long start = DateTimeUtil.getFirstDateToShow(date, firstDayOfWeek).getTime();
        long end = DateTimeUtil.getLastDateToShow(date, firstDayOfWeek).getTime();
        List<DateCell> dates = new ArrayList<DateCell>();
        for(long current = start; current <= end; current += milliSecondsInDay) {
            dates.add(new DateCell(current));
        }
        return dates;
    }

    public static int getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    // FIXME: not very i18n friendly...
    public static String getShortDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "日";
    }

}
