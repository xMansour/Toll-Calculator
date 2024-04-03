package com.fawry.service;

import com.fawry.model.Vehicle;

/**
 * This interface defines the contract for a service that determines whether a
 * vehicle is exempt from tolls.
 *
 * Implementations of this interface should check if the provided `Vehicle`
 * object represents a type that is:
 * - Configured as toll-free (e.g., emergency vehicles, specific vehicle
 * classes).
 * - Exempt based on other criteria (e.g., emissions standards, temporary
 * permits).
 *
 * @see TollFreeVehicleServiceImpl (possible implementation)
 */
public interface TollFreeVehicleService {

    /**
     * Checks if a specific vehicle is considered toll-free.
     * 
     * @param vehicle The Vehicle object representing the type of vehicle to be
     *                checked.
     * @return True if the vehicle is toll-free, false otherwise.
     */
    boolean isTollFreeVehicle(Vehicle vehicle);

}