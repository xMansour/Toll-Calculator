package com.fawry.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;

/**
 * This Class is used to verify the functionality of the
 * {@link TollFreeDatesServiceImpl}.
 */
public class TollFreeDatesServiceTest {
    private static TollFreeDatesService TollFreeDatesService;

    /**
     * This method is runs before all tests in the class to set up the required
     * dependencies.
     */
    @BeforeClass
    public static void setup() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.refresh();
            TollFreeDatesService = context.getBean(TollFreeDatesService.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the
     * {@link TollFreeDatesServiceImpl#isTollFreeDate(LocalDateTime)}
     * method returns true for a Saturday
     * which is a weekend in sweden.
     */
    @Test
    public void saturdayShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 30, 6, 0);
        assertTrue(TollFreeDatesService.isTollFreeDate(date));
    }

    /**
     * This test verifies that the
     * {@link TollFreeDatesServiceImpl#isTollFreeDate(LocalDateTime)}
     * method returns true for a Sunday
     * which is a weekend in sweden.
     */
    @Test
    public void sundayShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertTrue(TollFreeDatesService.isTollFreeDate(date));
    }

    /**
     * This test verifies that the
     * {@link TollFreeDatesServiceImpl#isTollFreeDate(LocalDateTime)}
     * method returns true for March
     * 28th, 2013.
     * which is a weekend in sweden.
     */
    @Test
    public void march28th2013ShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2013, Month.MARCH, 28, 6, 0);
        assertTrue(TollFreeDatesService.isTollFreeDate(date));
    }

    /**
     * This test verifies that the
     * {@link TollFreeDatesServiceImpl#isTollFreeDate(LocalDateTime)}
     * method returns false for March
     * 27th, 2013.
     * which is not a weekend or toll free date in sweden.
     */
    @Test
    public void march27th2013ShouldReturnFalse() {
        LocalDateTime date = LocalDateTime.of(2013, Month.MARCH, 27, 6, 0);
        assertFalse(TollFreeDatesService.isTollFreeDate(date));
    }

    /**
     * This test verifies that the
     * {@link TollFreeDatesServiceImpl#isTollFreeDate(LocalDateTime)}
     * method returns true for July,
     * 2013.
     * since the whole month is fee free in sweden.
     */
    @Test
    public void july2013ShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2013, Month.JULY, 27, 6, 0);
        assertTrue(TollFreeDatesService.isTollFreeDate(date));
    }
}
