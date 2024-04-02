package com.fawry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import com.fawry.exception.NoDateEnteredException;
import com.fawry.exception.NoVehicleEnteredException;
import com.fawry.model.Car;
import com.fawry.model.Vehicle;

public class TollCalculatorTest {
    private TollCalculatorImpl tollCalculator = new TollCalculatorImpl();

    @Test
    public void emptyDatesShouldThrowNoDateEnteredException() {
        Car car = new Car();
        assertThrows(NoDateEnteredException.class, () -> {
            tollCalculator.calculateTotalFees(car);
        });
    }

    @Test
    public void nullVehicleShouldThrowNoVehicleEnteredException() {
        Vehicle vehicle = null;
        LocalDateTime date = LocalDateTime.of(2018, Month.JUNE, 25, 6, 0);
        assertThrows(NoVehicleEnteredException.class, () -> {
            tollCalculator.calculateTotalFees(vehicle, date);
        });
    }

    @Test
    public void carAtApril2nd6AmShouldReturn8SEKFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(8, tollCalculator.calculateTotalFees(vehicle, date));
    }

    @Test
    public void carAtWeekendShouldReturnZeroFee() {
        Vehicle vehicle = new Car();
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertEquals(0, tollCalculator.calculateTotalFees(vehicle, date));
    }

}