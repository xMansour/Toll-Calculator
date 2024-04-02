package com.fawry.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.model.Vehicle;

public class TollFreeVehicleServiceTest {

    private static TollFreeVehicleService tollFreeDatesService;
    private static Vehicle car;
    private static Vehicle motorbike;

    @BeforeClass
    public static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        tollFreeDatesService = context.getBean(TollFreeVehicleService.class);
        car = context.getBean("car", Vehicle.class);
        motorbike = context.getBean("motorbike", Vehicle.class);
    }

    @Test
    public void motorBikeShouldReturnTrue() {
        assertTrue(tollFreeDatesService.isTollFreeVehicle(motorbike));
    }

    @Test
    public void carShouldReturnFalse() {
        assertFalse(tollFreeDatesService.isTollFreeVehicle(car));
    }

    @Test
    public void nullShouldReturnFalse() {
        assertFalse(tollFreeDatesService.isTollFreeVehicle(null));
    }
}
