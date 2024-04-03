package com.fawry.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;

/**
 * This Class is used to verify the functionality of the {@link Car}.
 */
public class CarTest {
    private static Vehicle car;

    /**
     * This method is runs before all tests in the class to set up the required
     * dependencies.
     */
    @BeforeClass
    public static void setup() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.refresh();

            car = context.getBean("car", Vehicle.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the {@link Vehicle#getType()} method returns "Car"
     * for an
     * object
     * of type {@link Car}.
     */
    @Test
    public void carTypeShouldReturnCar() {
        assertEquals("Car", car.getType());
    }

}
