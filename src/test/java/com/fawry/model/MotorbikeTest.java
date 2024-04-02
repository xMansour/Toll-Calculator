package com.fawry.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;

public class MotorbikeTest {
    private static Vehicle motorbike;

    @BeforeClass
    public static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        motorbike = context.getBean("motorbike", Vehicle.class);
    }

    @Test
    public void motorbikeTypeShouldReturnMotorbike() {
        assertEquals("Motorbike", motorbike.getType());
    }
}
