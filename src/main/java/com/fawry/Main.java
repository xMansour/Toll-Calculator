package com.fawry;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fawry.model.Car;
import com.fawry.model.Vehicle;

public class Main {
    public static void main(String[] args) {
        TollCalculator tollCalculator = new TollCalculator();
        Vehicle car = new Car();
        Date date = new GregorianCalendar(2018, Calendar.JUNE, 25, 7, 0)
                .getTime();
        Date date2 = new GregorianCalendar(2019, Calendar.JUNE, 2, 3, 0)
                .getTime();

        Date date3 = new GregorianCalendar(2020, Calendar.JUNE, 12, 9, 0)
                .getTime();
        System.out.println("TOTAL FEES: " + tollCalculator.calculateTotalFees(car, date, date2, date3));

    }
}