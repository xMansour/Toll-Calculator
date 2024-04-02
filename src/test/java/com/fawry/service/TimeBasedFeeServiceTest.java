package com.fawry.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import com.fawry.model.Car;
import com.fawry.model.Vehicle;

public class TimeBasedFeeServiceTest {
    
    @Test
    public void sixAmShouldReturn8SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(8, TimeBasedFeeServiceImpl.getTollFee(date, vehicle));
    }

    @Test
    public void SixFortyFiveShouldReturn13SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 45);
        assertEquals(13, TimeBasedFeeServiceImpl.getTollFee(date, vehicle));
    }

    @Test
    public void sixTeenThrityShouldReturn18SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 16, 45);
        assertEquals(18, TimeBasedFeeServiceImpl.getTollFee(date, vehicle));
    }
}