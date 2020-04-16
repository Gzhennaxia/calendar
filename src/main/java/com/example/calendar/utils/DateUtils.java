package com.example.calendar.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author bo li
 * @date 2020-04-16 09:54
 */
public class DateUtils {

    public static Date firstMomentOfMonth() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.MONTH, 0);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date firstMomentOfWeek() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date firstMomentOfYesterday() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(Calendar.DATE, -1);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date firstMomentOfToday() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(Calendar.DATE, 0);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
