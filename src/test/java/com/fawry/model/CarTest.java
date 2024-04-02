package com.fawry.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;

public class CarTest {
    private static Vehicle car;

    @BeforeClass
    public static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        car = context.getBean("car", Vehicle.class);
    }

    @Test
    public void carTypeShouldReturnCar() {
        assertEquals("Car", car.getType());
    }

}
