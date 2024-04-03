package com.fawry.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This Class is used to verify the functionality of the
 * {@link NoVehicleEnteredException}.
 */
public class NoVehicleEnteredExceptionTest {
    /**
     * This test verifies that the getMessage method returns the message set in the
     * exception's constructor
     */
    @Test
    public void testExceptionConstructorSetsMessage() {
        String expectedMessage = "No Vehicle Was Entered";
        try {
            throw new NoVehicleEnteredException(expectedMessage);
        } catch (NoVehicleEnteredException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
