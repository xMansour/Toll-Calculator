package com.fawry.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;

public class TollFreeDatesServiceTest {
    private static TollFreeDatesServiceImpl tollFreeDatesServiceImpl;

    @BeforeClass
    public static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        tollFreeDatesServiceImpl = context.getBean(TollFreeDatesServiceImpl.class);
    }

    @Test
    public void saturdayShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 30, 6, 0);
        assertTrue(tollFreeDatesServiceImpl.isTollFreeDate(date));
    }

    @Test
    public void sundayShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertTrue(tollFreeDatesServiceImpl.isTollFreeDate(date));
    }

    @Test
    public void march28th2013ShouldReturnTrue() {
        LocalDateTime date = LocalDateTime.of(2013, Month.MARCH, 28, 6, 0);
        assertTrue(tollFreeDatesServiceImpl.isTollFreeDate(date));
    }

    @Test
    public void march27th2013ShouldReturnFalse() {
        LocalDateTime date = LocalDateTime.of(2013, Month.MARCH, 27, 6, 0);
        assertFalse(tollFreeDatesServiceImpl.isTollFreeDate(date));
    }
}
