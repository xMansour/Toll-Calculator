package com.fawry.exception;

/**
 * Custom exception class thrown when no dates are provided for toll
 * calculation.
 *
 * This exception extends {@link RuntimeException} indicating an unexpected
 * condition
 * that should be handled during program execution (not necessarily through
 * checked exceptions).
 * It requires a message to be provided during construction, detailing the
 * error.
 */
public class NoDateEnteredException extends RuntimeException {
    public NoDateEnteredException(String message) {
        super(message);
    }

}
