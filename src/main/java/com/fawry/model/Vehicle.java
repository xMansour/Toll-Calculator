package com.fawry.model;

/**
 * This interface defines a basic contract for a vehicle object.
 * 
 * Implementations of this interface should represent different types of
 * vehicles
 * used in the toll calculation system.
 * 
 * This interface provides a way to retrieve the vehicle type information,
 * which might be used for toll fee calculations based on vehicle
 * classification.
 * 
 * @see Car (possible implementation)
 * @see Motorbike (possible implementation)
 */
public interface Vehicle {

    /**
     * Retrieves the type of the vehicle.
     * 
     * The specific meaning of "type" depends on the implementation, but it should
     * uniquely identify the vehicle category relevant to toll calculations.
     * (e.g., "car", "motorbike", "truck", "emergency")
     * 
     * @return The vehicle type as a String.
     */
    public String getType();
}
