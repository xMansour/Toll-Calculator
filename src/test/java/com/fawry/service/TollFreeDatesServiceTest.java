package com.fawry.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

public class TollFreeDatesServiceTest {

    @Test
    public void saturdayShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 30, 6, 0);
        assertTrue(TollFreeDatesServiceImpl.isTollFreeDate(date));
    }

    @Test
    public void sundayShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertTrue(TollFreeDatesServiceImpl.isTollFreeDate(date));
    }

    @Test
    public void march28th2013ShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2013, Month.MARCH, 28, 6, 0);
        assertTrue(TollFreeDatesServiceImpl.isTollFreeDate(date));
    }

    @Test
    public void march27th2013ShouldReturnFalse() {
        LocalDateTime date = LocalDateTime.of(2013, Month.MARCH, 27, 6, 0);
        assertFalse(TollFreeDatesServiceImpl.isTollFreeDate(date));
    }
}
