package com.fawry.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

public abstract class TollFreeDatesService {
    private static final int YEAR = 2013;
    private static final Set<Month> tollFreeMonths = Set.of(Month.JULY);

    private static final Set<LocalDate> tollFreeDays = Set.of(LocalDate.of(YEAR, Month.JANUARY, 1),
            LocalDate.of(YEAR, Month.MARCH, 28),
            LocalDate.of(YEAR, Month.MARCH, 29),
            LocalDate.of(YEAR, Month.APRIL, 1),
            LocalDate.of(YEAR, Month.APRIL, 30),
            LocalDate.of(YEAR, Month.MAY, 1),
            LocalDate.of(YEAR, Month.MAY, 8),
            LocalDate.of(YEAR, Month.MAY, 9),
            LocalDate.of(YEAR, Month.JUNE, 5),
            LocalDate.of(YEAR, Month.JUNE, 6),
            LocalDate.of(YEAR, Month.JUNE, 21),
            LocalDate.of(YEAR, Month.NOVEMBER, 1),
            LocalDate.of(YEAR, Month.DECEMBER, 24),
            LocalDate.of(YEAR, Month.DECEMBER, 25),
            LocalDate.of(YEAR, Month.DECEMBER, 26),
            LocalDate.of(YEAR, Month.DECEMBER, 31));

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static boolean isTollFreeDate(LocalDateTime date) {
        LocalDate localDate = date.toLocalDate();
        return tollFreeMonths.contains(localDate.getMonth()) || tollFreeDays.contains(localDate)
                || isWeekend(localDate);
    }
}
