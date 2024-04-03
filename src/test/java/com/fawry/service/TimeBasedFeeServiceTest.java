package com.fawry.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.model.Car;
import com.fawry.model.Motorbike;
import com.fawry.model.Vehicle;

/**
 * This Class is used to verify the functionality of the
 * {@link TimeBasedFeeServiceImpl}.
 */
public class TimeBasedFeeServiceTest {
    private static TimeBasedFeeService timeBasedFeeService;
    private static Vehicle car;
    private static Vehicle motorbike;

    /**
     * This method is runs before all tests in the class to set up the required
     * dependencies.
     */
    @BeforeClass
    public static void setup() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.refresh();
            timeBasedFeeService = context.getBean(TimeBasedFeeService.class);
            car = context.getBean("car", Vehicle.class);
            motorbike = context.getBean("motorbike", Vehicle.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the
     * {@link TimeBasedFeeServiceImpl#getTollFee(LocalDateTime, Vehicle)} method
     * returns 8 for a car in april
     * 2nd, 2024 at 6 AM
     */
    @Test
    public void carAtSixAmShouldReturn8SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(8, timeBasedFeeService.getTollFee(date, car));
    }

    /**
     * This test verifies that the
     * {@link TimeBasedFeeServiceImpl#getTollFee(LocalDateTime, Vehicle)} method
     * returns 13 for {@link Car} in april
     * 2nd, 2024 at 6:45 AM
     */
    @Test
    public void carAtSixFortyFiveShouldReturn13SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 45);
        assertEquals(13, timeBasedFeeService.getTollFee(date, car));
    }

    /**
     * This test verifies that the
     * {@link TimeBasedFeeServiceImpl#getTollFee(LocalDateTime, Vehicle)} method
     * returns 18 for a {@link Car} in april
     * 2nd, 2024 at 4:45 PM
     */
    @Test
    public void carAtSixTeenThrityShouldReturn18SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 16, 45);
        assertEquals(18, timeBasedFeeService.getTollFee(date, car));
    }

    /**
     * This test verifies that the
     * {@link TimeBasedFeeServiceImpl#getTollFee(LocalDateTime, Vehicle)} method
     * returns 0 for a {@link Motorbike} in
     * april 2nd, 2024 at 4:45 PM since motorbikes are fee free.
     */
    @Test
    public void sixTeenThrityShouldReturn18SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 16, 45);
        assertEquals(0, timeBasedFeeService.getTollFee(date, motorbike));
    }
}