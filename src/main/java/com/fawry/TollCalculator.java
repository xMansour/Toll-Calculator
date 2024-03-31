package com.fawry;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

import com.fawry.exception.NoDateEnteredException;
import com.fawry.model.Vehicle;
import com.fawry.model.VehicleType;
import com.fawry.service.TollFreeDatesService;
import com.fawry.service.TollFreeVehicleService;

public class TollCalculator {
    private static final int DAILY_MAXIMUM_FEE = 60; // SEK
    private static final LocalTime MORNING_RUSH_HOUR_START = LocalTime.of(6, 30);
    private static final LocalTime MORNING_RUSH_HOUR_END = LocalTime.of(7, 0);
    private static final LocalTime EVENING_RUSH_HOUR_START = LocalTime.of(15, 30);
    private static final LocalTime EVENING_RUSH_HOUR_END = LocalTime.of(17, 0);

    /**
     * Calculates the total toll fee for a vehicle on a specific day.
     *
     * @param vehicle the vehicle
     * @param dates   the list of dates and times for the vehicle's passes
     * @return the total toll fee for the day
     * @throws NoDateEnteredException if no dates are provided
     */
    public int calculateTotalFees(Vehicle vehicle, Date... dates) {
        Date firstDate = Arrays.stream(dates).findFirst()
                .orElseThrow(() -> new NoDateEnteredException("No Date Entered!"));
        int firstDateFee = getTollFee(firstDate, vehicle);

        int totalFee = firstDateFee;
        int dailyMaximum = 60; // SEK

        // if (totalFee >= dailyMaximum) {
        // return dailyMaximum;
        // }

        Arrays.stream(dates).skip(1).forEach(date -> {
            int fee = getTollFee(date, vehicle);
        });

        for (Date date : dates) {
            int nextFee = getTollFee(date, vehicle);
            TimeUnit timeUnit = TimeUnit.MINUTES;
            long diffInMillies = date.getTime() - intervalStart.getTime();
            long minutes = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (minutes <= 60) {
                if (totalFee > 0)
                    totalFee -= tempFee;
                if (nextFee >= tempFee)
                    tempFee = nextFee;
                totalFee += tempFee;
            } else {
                totalFee += nextFee;
            }
        }
        if (totalFee > 60)
            totalFee = 60;
        return totalFee;
        return 0;
    }

    public int getTollFee(final Date date, Vehicle vehicle) {
        if (TollFreeDatesService.isTollFreeDate(date) || TollFreeVehicleService.isTollFreeVehicle(vehicle))
            return 0;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (hour == 6 && minute >= 0 && minute <= 29)
            return 8;
        else if (hour == 6 && minute >= 30 && minute <= 59)
            return 13;
        else if (hour == 7 && minute >= 0 && minute <= 59)
            return 18;
        else if (hour == 8 && minute >= 0 && minute <= 29)
            return 13;
        else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <= 59)
            return 8;
        else if (hour == 15 && minute >= 0 && minute <= 29)
            return 13;
        else if (hour == 15 && minute >= 0 || hour == 16 && minute <= 59)
            return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59)
            return 13;
        else if (hour == 18 && minute >= 0 && minute <= 29)
            return 8;
        else
            return 0;
    }
}
