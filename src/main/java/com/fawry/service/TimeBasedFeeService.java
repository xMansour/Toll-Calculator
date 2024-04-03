package com.fawry.service;

import java.time.LocalDateTime;

import com.fawry.model.Vehicle;

/**
 * This interface defines the contract for a service that provides toll fees
 * based on date and vehicle type.
 * 
 * Implementations of this interface should retrieve or calculate toll fees
 * considering:
 * - The date and time provided.
 * - The type of vehicle (e.g., car, motorbike, etc.).
 * 
 * @see TimeBasedFeeServiceImpl (possible implementation)
 */
public interface TimeBasedFeeService {

    /**
     * Retrieves or calculates the toll fee for a specific date and vehicle type.
     * 
     * @param date    The LocalDateTime object representing the date for which to
     *                get the fee.
     * @param vehicle The Vehicle object representing the type of vehicle.
     * @return The toll fee amount for the given date and vehicle.
     */
    int getTollFee(LocalDateTime date, Vehicle vehicle);

}