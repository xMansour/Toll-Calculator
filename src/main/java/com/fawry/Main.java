package com.fawry;

import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.model.Vehicle;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        TollCalculator tollCalculator = context.getBean(TollCalculator.class);
        Vehicle car = context.getBean("car", Vehicle.class);
        
        System.out.println("TOTAL FEES: " + tollCalculator.calculateTotalFees(car,
                LocalDateTime.of(2018, Month.JUNE, 25, 15, 15)));

    }
}