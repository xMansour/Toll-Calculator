package com.fawry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

import com.fawry.exception.NoDateEnteredException;
import com.fawry.model.Vehicle;
import com.fawry.model.VehicleType;
import com.fawry.service.TimeBasedFeeService;
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
    public int calculateTotalFees(Vehicle vehicle, LocalDateTime... dates) {
        // LocalDateTime firstDate = Arrays.stream(dates).findFirst()
        // .orElseThrow(() -> new NoDateEnteredException("No Date Entered!"));
        // int firstDateFee = TimeBasedFeeService.getTollFee(firstDate, vehicle);

        // int totalFee = firstDateFee;
        // int dailyMaximum = 60; // SEK

        // if (totalFee >= dailyMaximum) {
        // return dailyMaximum;
        // }

        // Arrays.stream(dates).skip(1).forEach(date -> {
        // int fee = TimeBasedFeeService.getTollFee(date, vehicle);
        // });

        LocalDateTime intervalStart = dates[0];
        int totalFee = 0;
        for (LocalDateTime date : dates) {
            int nextFee = TimeBasedFeeService.getTollFee(date, vehicle);
            int tempFee = TimeBasedFeeService.getTollFee(intervalStart, vehicle);
            long minutes = Duration.between(date, intervalStart).toMinutes();

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
    }
}
