package com.fawry.model;

/**
 * Implementation of the {@link Vehicle} interface representing a motorbike.
 *
 * This class provides the basic functionality for a motorbike object used in
 * the toll
 * calculation system.
 * It overrides the {@link Vehicle#getType()} method from the {@link Vehicle}
 * interface to return the
 * string "Motorbike".
 */
public class Motorbike implements Vehicle {
    @Override
    public String getType() {
        return "Motorbike";
    }
}
