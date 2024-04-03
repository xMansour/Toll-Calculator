package com.fawry.service;

import java.time.LocalDateTime;

/**
 * This interface defines the contract for a service that determines whether a
 * specific date is exempt from tolls.
 * 
 * Implementations of this interface should check if the provided LocalDateTime
 * object represents a date:
 * - Defined as toll-free (e.g., weekends, specific holidays).
 * - Falling within a pre-defined toll-free period.
 * 
 * @see TollFreeDatesServiceImpl (possible implementation)
 */
public interface TollFreeDatesService {

    /**
     * Checks if a specific date is considered toll-free.
     * 
     * @param date The LocalDateTime object representing the date to be checked.
     * @return True if the date is toll-free, false otherwise.
     */
    boolean isTollFreeDate(LocalDateTime date);

}