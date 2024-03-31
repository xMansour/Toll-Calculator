package com.fawry.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public abstract class TollFreeDatesService {
    private static final int YEAR = 2013;
    private static final Set<Integer> tollFreeMonths = Set.of(Calendar.JULY);

    private static final Set<LocalDate> tollFreeDays = Set.of(LocalDate.of(YEAR, Calendar.JANUARY, 1),
            LocalDate.of(YEAR, Calendar.MARCH, 28),
            LocalDate.of(YEAR, Calendar.MARCH, 29),
            LocalDate.of(YEAR, Calendar.APRIL, 1),
            LocalDate.of(YEAR, Calendar.APRIL, 30),
            LocalDate.of(YEAR, Calendar.MAY, 1),
            LocalDate.of(YEAR, Calendar.MAY, 8),
            LocalDate.of(YEAR, Calendar.MAY, 9),
            LocalDate.of(YEAR, Calendar.JUNE, 5),
            LocalDate.of(YEAR, Calendar.JUNE, 6),
            LocalDate.of(YEAR, Calendar.JUNE, 21),
            LocalDate.of(YEAR, Calendar.NOVEMBER, 1),
            LocalDate.of(YEAR, Calendar.DECEMBER, 24),
            LocalDate.of(YEAR, Calendar.DECEMBER, 25),
            LocalDate.of(YEAR, Calendar.DECEMBER, 26),
            LocalDate.of(YEAR, Calendar.DECEMBER, 31));

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static boolean isTollFreeDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return tollFreeMonths.contains(localDate.getMonthValue()) || tollFreeDays.contains(localDate)
                || isWeekend(localDate);
    }
}
