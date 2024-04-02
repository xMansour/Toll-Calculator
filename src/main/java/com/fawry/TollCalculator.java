package com.fawry;

import java.time.Duration;
import java.time.LocalDateTime;
import com.fawry.exception.NoDateEnteredException;
import com.fawry.model.Vehicle;
import com.fawry.service.TimeBasedFeeService;

public class TollCalculator {
    private static final int DAILY_MAXIMUM_FEE = 60; // SEK
    private static final int FEE_INTERVAL = 60;

    public int calculateTotalFees(Vehicle vehicle, LocalDateTime... dates) {
        int totalFee = 0;
        if (dates.length == 0)
            throw new NoDateEnteredException("No Date Entered");
        int currentFee = TimeBasedFeeService.getTollFee(dates[0], vehicle); // Initial fee

        for (LocalDateTime date : dates) {
            int nextFee = TimeBasedFeeService.getTollFee(date, vehicle);
            long minutesSinceStart = Duration.between(dates[0], date).toMinutes();
            if (minutesSinceStart <= FEE_INTERVAL) {
                currentFee = Math.max(currentFee, nextFee); // Tracks the maximum fee within an hour

            } else {
                totalFee += currentFee; // Adds previous interval's fee
                currentFee = nextFee; // Initialize with first fee of the new interval
            }
        }

        // Adds last interval's fee
        totalFee += currentFee;

        // Return 6o if totalFee exceeds 60 which voilates the requirements
        return Math.min(totalFee, DAILY_MAXIMUM_FEE);
    }
}
