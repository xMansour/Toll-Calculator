package com.fawry.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This Class is used to verify the functionality of the
 * {@link NoDateEnteredException}.
 */
public class NoDateEnteredExceptionTest {

    /**
     * This test verifies that the getMessage method returns the message set in the
     * exception's constructor
     */
    @Test
    public void testExceptionConstructorSetsMessage() {
        String expectedMessage = "No Date Was Entered";
        try {
            throw new NoDateEnteredException(expectedMessage);
        } catch (NoDateEnteredException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
