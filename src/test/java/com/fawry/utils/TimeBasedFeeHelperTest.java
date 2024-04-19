package com.fawry.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeBasedFeeHelperTest {
    @Test
    public void shouldReturnMapOfSizeThreeContainingTimeAndCrosspondingFeeValues() {
        String timeBasedFee = "06:00,10; 06:30,15; 07:00,20";
        assertEquals(3, TimeBasedFeeHelper.getTimeBasedFeeMap(timeBasedFee).size());
    }

    @Test
    public void shouldReturnEmptyMapForEmptyTimeBasedFees() {
        String timeBasedFee = "";
        assertEquals(0, TimeBasedFeeHelper.getTimeBasedFeeMap(timeBasedFee).size());
    }
}
