package com.fawry.model;

/**
 * Enumeration representing different vehicle types used in the toll calculation
 * system.
 */
public enum VehicleType {
    MOTORBIKE("Motorbike"),
    TRACTOR("Tractor"),
    EMERGENCY("Emergency"),
    DIPLOMAT("Diplomat"),
    FOREIGN("Foreign"),
    MILITARY("Military"),
    CAR("Car");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the value of the type property of the vehicle.
     * 
     * @return The type as a String.
     */
    public String getType() {
        return type;
    }
}
