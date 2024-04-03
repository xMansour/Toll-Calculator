package com.fawry.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.model.Motorbike;
import com.fawry.model.Vehicle;

/**
 * This Class is used to verify the functionality of the
 * {@link TollFreeVehicleServiceImpl}.
 */
public class TollFreeVehicleServiceTest {

    private static TollFreeVehicleService tollFreeVehicleService;
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
            tollFreeVehicleService = context.getBean(TollFreeVehicleService.class);
            car = context.getBean("car", Vehicle.class);
            motorbike = context.getBean("motorbike", Vehicle.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the
     * {@link TollFreeVehicleServiceImpl#isTollFreeVehicle(Vehicle)} method returns
     * true for
     * a {@link Motorbike}.
     */
    @Test
    public void motorBikeShouldReturnTrue() {
        assertTrue(tollFreeVehicleService.isTollFreeVehicle(motorbike));
    }

    /**
     * This test verifies that the
     * {@link TollFreeVehicleServiceImpl#isTollFreeVehicle(Vehicle)} method returns
     * false for
     * a {@link Car}.
     */
    @Test
    public void carShouldReturnFalse() {
        assertFalse(tollFreeVehicleService.isTollFreeVehicle(car));
    }

    /**
     * This test verifies that the
     * {@link TollFreeVehicleServiceImpl#isTollFreeVehicle(Vehicle)} method returns
     * false if
     * a null argument was inserted as {@link Vehicle}}.
     */
    @Test
    public void nullShouldReturnFalse() {
        assertFalse(tollFreeVehicleService.isTollFreeVehicle(null));
    }
}
