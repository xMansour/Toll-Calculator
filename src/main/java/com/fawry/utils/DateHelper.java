package com.fawry.utils;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class DateHelper {

    public static Set<String> getTollFreeDays(String tollFreeDaysString) {
        Set<String> tollFreeDays = new HashSet<>();
        if (tollFreeDaysString == null || tollFreeDaysString.isBlank() || tollFreeDaysString.equals("None"))
            return tollFreeDays;
        String[] tollFreeDaysSplitted = tollFreeDaysString.split(",");
        for (String tollFreeDay : tollFreeDaysSplitted) {
            tollFreeDays.add(tollFreeDay.trim());
        }
        return tollFreeDays;
    }

    public static Set<Month> getTollFreeMonths(String tollFreeMonthsString) {
        Set<Month> tollFreeMonths = new HashSet<>();
        if (tollFreeMonthsString == null || tollFreeMonthsString.isBlank() || tollFreeMonthsString.equals("None"))
            return tollFreeMonths;
        String[] tollFreeMonthsStringSplitted = tollFreeMonthsString.split(",");
        for (String tollFreeMonth : tollFreeMonthsStringSplitted) {
            tollFreeMonths.add(Month.valueOf(tollFreeMonth.trim()));
        }
        return tollFreeMonths;

    }

    public static Set<DayOfWeek> getWeekends(String weekendsString) {
        Set<DayOfWeek> weekends = new HashSet<>();
        if (weekendsString == null || weekendsString.isBlank() || weekendsString.equals("None"))
            return weekends;
        String[] weekendsStringSplitted = weekendsString.split(",");
        for (String weekend : weekendsStringSplitted) {
            weekends.add(DayOfWeek.valueOf(weekend.trim()));
        }
        return weekends;
    }

}
