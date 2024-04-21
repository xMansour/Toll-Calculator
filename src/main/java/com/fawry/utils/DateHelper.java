package com.fawry.utils;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateHelper {
    private static final Logger LOGGER = Logger.getLogger(DateHelper.class.getName());

    public static Set<String> getTollFreeDays(String tollFreeDaysString) {
        Set<String> tollFreeDays = new HashSet<>();
        if (tollFreeDaysString == null || tollFreeDaysString.isBlank() || tollFreeDaysString.equals("None")) {
            LOGGER.log(Level.INFO, "Empty or 'None' tollFreeDaysString provided, returning empty set");
            return tollFreeDays;
        }
        LOGGER.log(Level.INFO, "Parsing tollFreeDaysString: " + tollFreeDaysString);
        String[] tollFreeDaysSplitted = tollFreeDaysString.split(",");
        for (String tollFreeDay : tollFreeDaysSplitted) {
            tollFreeDays.add(tollFreeDay.trim());
        }
        return tollFreeDays;
    }

    public static Set<Month> getTollFreeMonths(String tollFreeMonthsString) {
        Set<Month> tollFreeMonths = new HashSet<>();
        if (tollFreeMonthsString == null || tollFreeMonthsString.isBlank() || tollFreeMonthsString.equals("None")) {
            LOGGER.log(Level.INFO, "Empty or 'None' tollFreeMonthsString provided, returning empty set");
            return tollFreeMonths;
        }
        LOGGER.log(Level.INFO, "Parsing tollFreeMonthsString: " + tollFreeMonthsString);
        String[] tollFreeMonthsStringSplitted = tollFreeMonthsString.split(",");
        for (String tollFreeMonth : tollFreeMonthsStringSplitted) {
            try {
                tollFreeMonths.add(Month.valueOf(tollFreeMonth.trim()));
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, "Invalid month value in tollFreeMonthsString: " + tollFreeMonth, e);
            }
        }
        return tollFreeMonths;

    }

    public static Set<DayOfWeek> getWeekends(String weekendsString) {
        Set<DayOfWeek> weekends = new HashSet<>();
        if (weekendsString == null || weekendsString.isBlank() || weekendsString.equals("None")) {
            LOGGER.log(Level.INFO, "Empty or 'None' weekendsString provided, returning empty set");
            return weekends;
        }

        LOGGER.log(Level.INFO, "Parsing weekendsString: " + weekendsString);
        String[] weekendsStringSplitted = weekendsString.split(",");
        for (String weekend : weekendsStringSplitted) {
            try {
                weekends.add(DayOfWeek.valueOf(weekend.trim()));
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, "Invalid DayOfWeek value in weekendsString: " + weekend, e);
            }
        }
        return weekends;
    }

}
