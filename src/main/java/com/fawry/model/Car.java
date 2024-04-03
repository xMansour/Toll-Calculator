package com.fawry.model;

/**
 * Implementation of the {@link Vehicle} interface representing a car.
 *
 * This class provides the basic functionality for a car object used in the toll
 * calculation system.
 * It overrides the {@link Vehicle#getType()} method from the {@link Vehicle}
 * interface to return the
 * string "Car".
 */
public class Car implements Vehicle {
    @Override
    public String getType() {
        return "Car";
    }

}
