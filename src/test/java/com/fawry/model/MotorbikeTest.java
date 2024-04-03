package com.fawry.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;

/**
 * This Class is used to verify the functionality of the {@link Motorbike}.
 */
public class MotorbikeTest {
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

            motorbike = context.getBean("motorbike", Vehicle.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the {@link Vehicle#getType()} method returns
     * "Motorbike" for an object
     * of type {@link Motorbike}.
     */
    @Test
    public void motorbikeTypeShouldReturnMotorbike() {
        assertEquals("Motorbike", motorbike.getType());
    }
}
