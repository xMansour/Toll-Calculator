package com.fawry.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.fawry.utils.DateHelper;

/**
 * Implementation of the {@link TollFreeDatesService} interface, responsible for
 * determining whether a
 * specific date is exempt from tolls.
 */
public class TollFreeDatesServiceImpl implements TollFreeDatesService {
    private static final Logger LOGGER = Logger.getLogger(TollFreeDatesServiceImpl.class.getName());

    @Value("${tollFreeDays:None}") // could use .split(',') as like that @Value("#{'${tollFreeDays}'.split(',')}")
    private String tollFreeDaysString;
    @Value("${tollFreeMonths:None}")
    private String tollFreeMonthsString;
    @Value("${weekends:None}")
    private String weekendsString;

    // TODO:: This would throw a NullPointerException because beans weren't
    // initialized properly. Weired because @Value should be processed befoare class
    // variables are initialized. Needs research.
    // private Set<Month> tollFreeMonths =
    // DateParser.getTollFreeMonths(tollFreeMonthsString);
    // private Set<String> tollFreeDays =
    // DateParser.getTollFreeDays(tollFreeDaysString);
    // private Set<DayOfWeek> weekends = DateParser.getWeekends(weekendsString);

    /**
     * Checks if the provided date falls on a Saturday or Sunday.
     *
     * This method uses the {@link LocalDate#getDayOfWeek()} method of the LocalDate
     * object to
     * determine the day of the week.
     * It returns true if the day is Saturday or Sunday, and false otherwise.
     *
     * @param date The LocalDate object representing the date to be checked.
     * @return True if the date is a Saturday or Sunday, false otherwise.
     */
    private boolean isWeekend(LocalDate date) {
        return DateHelper.getWeekends(weekendsString).contains(date.getDayOfWeek());
    }

    @Override
    public boolean isTollFreeDate(LocalDateTime date) {
        LocalDate localDate = date.toLocalDate();
        String formattedDate = date.toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd"));
        boolean isTollFree = DateHelper.getTollFreeMonths(tollFreeMonthsString).contains(localDate.getMonth())
                || DateHelper.getTollFreeDays(tollFreeDaysString).contains(formattedDate)
                || isWeekend(localDate);
        LOGGER.log(Level.INFO, "Date: " + formattedDate + ", Toll-free: " + isTollFree);
        return isTollFree;
    }
}
