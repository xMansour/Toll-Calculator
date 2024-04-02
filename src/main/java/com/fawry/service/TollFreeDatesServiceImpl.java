package com.fawry.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

public class TollFreeDatesServiceImpl implements TollFreeDatesService {
    private final int YEAR = 2013;
    private final Set<Month> tollFreeMonths = Set.of(Month.JULY);

    private final Set<LocalDate> tollFreeDays = Set.of(LocalDate.of(YEAR, Month.JANUARY, 1),
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

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public boolean isTollFreeDate(LocalDateTime date) {
        LocalDate localDate = date.toLocalDate();
        return tollFreeMonths.contains(localDate.getMonth()) || tollFreeDays.contains(localDate)
                || isWeekend(localDate);
    }
}
