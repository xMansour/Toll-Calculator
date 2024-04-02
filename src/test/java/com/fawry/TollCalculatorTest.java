package com.fawry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.exception.NoDateEnteredException;
import com.fawry.exception.NoVehicleEnteredException;
import com.fawry.model.Car;
import com.fawry.model.Vehicle;

public class TollCalculatorTest {
    private static TollCalculator tollCalculator;
    private static Vehicle car;
    private static Vehicle motorBike;

    @BeforeClass
    public static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        tollCalculator = context.getBean(TollCalculator.class);
        car = context.getBean("car", Vehicle.class);
        motorBike = context.getBean("motorBike", Vehicle.class);
    }

    @Test
    public void emptyDatesShouldThrowNoDateEnteredException() {
        Car car = new Car();
        assertThrows(NoDateEnteredException.class, () -> {
            tollCalculator.calculateTotalFees(car);
        });
    }

    @Test
    public void nullVehicleShouldThrowNoVehicleEnteredException() {
        LocalDateTime date = LocalDateTime.of(2018, Month.JUNE, 25, 6, 0);
        assertThrows(NoVehicleEnteredException.class, () -> {
            tollCalculator.calculateTotalFees(null, date);
        });
    }

    @Test
    public void carAtApril2nd6AmShouldReturn8SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(8, tollCalculator.calculateTotalFees(car, date));
    }

    @Test
    public void carAtWeekendShouldReturnZeroFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertEquals(0, tollCalculator.calculateTotalFees(car, date));
    }

}