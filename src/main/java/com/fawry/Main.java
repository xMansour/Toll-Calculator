package com.fawry;

import java.time.LocalDateTime;
import java.time.Month;

import com.fawry.model.Car;
import com.fawry.model.Vehicle;

public class Main {
    public static void main(String[] args) {
        TollCalculator tollCalculator = new TollCalculator();
        Vehicle car = new Car();

        System.out.println("TOTAL FEES: " + tollCalculator.calculateTotalFees(car,
                LocalDateTime.of(2018, Month.JUNE, 25, 6, 0), LocalDateTime.of(2018, Month.JUNE, 25, 6, 15),
                LocalDateTime.of(2018, Month.JUNE, 25, 6, 30), LocalDateTime.of(2018, Month.JUNE, 25, 7, 30),
                LocalDateTime.of(2018, Month.JUNE, 25, 8, 15)));

        // System.out.println("TOTAL FEES: " + tollCalculator.calculateTotalFees(car,
        // LocalDateTime.of(2018, Month.JUNE, 25, 15, 15)));

        // System.out.println("TOTAL FEES: " + tollCalculator.calculateTotalFees(car,
        // LocalDateTime.of(2018, Month.JUNE, 25, 15, 45)));

        // System.out.println("TOTAL FEES: " + tollCalculator.calculateTotalFees(car,
        // LocalDateTime.of(2018, Month.JUNE, 25, 18, 10)));
    }
}