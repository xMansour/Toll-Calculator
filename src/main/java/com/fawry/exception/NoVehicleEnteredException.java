package com.fawry.exception;

/**
 * Custom exception class thrown when no vehicle are provided for toll calculation.
 *
 * This exception extends {@link RuntimeException} indicating an unexpected condition 
 * that should be handled during program execution (not necessarily through checked exceptions).
 * It requires a message to be provided during construction, detailing the error.
 */
public class NoVehicleEnteredException extends RuntimeException {
    public NoVehicleEnteredException(String message) {
        super(message);
    }
}
