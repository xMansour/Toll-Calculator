package com.fawry.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.model.Car;
import com.fawry.model.Vehicle;

public class TimeBasedFeeServiceTest {
    private static TimeBasedFeeServiceImpl timeBasedFeeServiceImpl;

    @BeforeClass
    public static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        timeBasedFeeServiceImpl = context.getBean(TimeBasedFeeServiceImpl.class);
    }

    @Test
    public void sixAmShouldReturn8SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(8, timeBasedFeeServiceImpl.getTollFee(date, vehicle));
    }

    @Test
    public void SixFortyFiveShouldReturn13SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 45);
        assertEquals(13, timeBasedFeeServiceImpl.getTollFee(date, vehicle));
    }

    @Test
    public void sixTeenThrityShouldReturn18SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 16, 45);
        assertEquals(18, timeBasedFeeServiceImpl.getTollFee(date, vehicle));
    }
}