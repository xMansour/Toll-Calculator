package com.fawry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fawry.exception.NoDateEnteredException;
import com.fawry.exception.NoVehicleEnteredException;
import com.fawry.model.Vehicle;
import com.fawry.service.TimeBasedFeeService;

/**
 * Implementation of the {@link TollCalculator} interface, responsible for
 * calculating toll fees for vehicles.
 */
public class TollCalculatorImpl implements TollCalculator {
    private static final int DAILY_MAXIMUM_FEE = 60; // SEK (Swedish Krona)
    private static final int FEE_INTERVAL = 60; // An hour
    private static final Logger LOGGER = Logger.getLogger(TollCalculatorImpl.class.getName());

    private final TimeBasedFeeService timeBasedFeeService;

    public TollCalculatorImpl(TimeBasedFeeService timeBasedFeeService) {
        this.timeBasedFeeService = timeBasedFeeService;
    }

    @Override
    public int calculateTotalFees(Vehicle vehicle, LocalDateTime... dates) {
        int totalFee = 0;
        if (dates.length == 0) {
            LOGGER.severe("No Date Entered Exception - No dates provided for calculation");
            throw new NoDateEnteredException("No Date Entered");
        }

        if (vehicle == null) {
            LOGGER.severe("No Vehicle Entered Exception - No vehicle provided for calculation");
            throw new NoVehicleEnteredException("No Vehicle Entered");
        }
        int currentFee = timeBasedFeeService.getTollFee(dates[0], vehicle); // Set currentFee with the initial fee
        LOGGER.log(Level.INFO, "Initial Fee set to: " + currentFee);

        for (LocalDateTime date : dates) {
            int nextFee = timeBasedFeeService.getTollFee(date, vehicle);
            long minutesSinceStart = Duration.between(dates[0], date).toMinutes(); // Duration between the first date
                                                                                   // and the current one in minutes
            if (minutesSinceStart <= FEE_INTERVAL) {
                currentFee = Math.max(currentFee, nextFee); // Tracks the maximum fee within an hour
                LOGGER.log(Level.INFO, "Keeping maximum fee within interval: " + currentFee);
            } else {
                totalFee += currentFee; // Adds previous interval's fee
                LOGGER.log(Level.INFO, "Adding fee for previous interval: " + currentFee + ", Total Fee: " + totalFee);
                currentFee = nextFee; // Initialize with first fee of the new interval
                LOGGER.log(Level.INFO, "Initializing fee for new interval: " + currentFee);
            }
        }

        // Adds last interval's fee
        totalFee += currentFee;
        LOGGER.log(Level.INFO, "Adding fee for last interval: " + currentFee + ", Total Fee before cap: " + totalFee);

        // Return 6o if totalFee exceeds 60 which voilates the requirements
        return Math.min(totalFee, DAILY_MAXIMUM_FEE);
    }
}
