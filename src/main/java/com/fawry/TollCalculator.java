package com.fawry;

import java.time.LocalDateTime;

import com.fawry.exception.NoDateEnteredException;
import com.fawry.exception.NoVehicleEnteredException;
import com.fawry.model.Vehicle;

/**
 * This interface defines the contract for calculating toll fees for vehicles
 * across multiple dates.
 * 
 * Implementations of this interface should provide logic to:
 * - Determine toll fees based on vehicle type, date, and time.
 * - Handle time-based fee intervals (optional).
 * - Enforce a daily maximum fee (optional).
 * - Interact with external services (optional) for toll fee information.
 * 
 * @see TollCalculatorImpl (possible implementation)
 */
public interface TollCalculator {

    /**
     * Calculates the total toll fees for a given vehicle across multiple dates,
     * considering time-based fee intervals and a daily maximum fee.
     *
     * @param vehicle The vehicle for which to calculate the fees.
     * @param dates   A varargs of LocalDateTime objects representing
     *                the dates for which to calculate fees.
     * @return The total toll fees, capped at the DAILY_MAXIMUM_FEE if necessary.
     * @throws NoDateEnteredException    If no dates are provided.
     * @throws NoVehicleEnteredException If no vehicle is provided.
     */
    int calculateTotalFees(Vehicle vehicle, LocalDateTime... dates);

}