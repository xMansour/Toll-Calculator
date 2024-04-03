package com.fawry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.config.AppConfig;
import com.fawry.exception.NoDateEnteredException;
import com.fawry.exception.NoVehicleEnteredException;
import com.fawry.model.Motorbike;
import com.fawry.model.Car;
import com.fawry.model.Vehicle;

/**
 * This Class is used to verify the functionality of the
 * {@link TollCalculatorImpl}.
 */
public class TollCalculatorTest {
    private static TollCalculator tollCalculator;
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

            tollCalculator = context.getBean(TollCalculator.class);
            car = context.getBean("car", Vehicle.class);
            motorbike = context.getBean("motorbike", Vehicle.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method throws {@link NoDateEnteredException} if no date was entered.
     */
    @Test
    public void emptyDatesShouldThrowNoDateEnteredException() {
        assertThrows(NoDateEnteredException.class, () -> {
            tollCalculator.calculateTotalFees(car);
        });
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method throws {@link NoVehicleEnteredException} if null was used as an
     * argument for a {@link Vehicle}.
     */
    @Test
    public void nullVehicleShouldThrowNoVehicleEnteredException() {
        LocalDateTime date = LocalDateTime.of(2018, Month.JUNE, 25, 6, 0);
        assertThrows(NoVehicleEnteredException.class, () -> {
            tollCalculator.calculateTotalFees(null, date);
        });
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method returns 8 for a {@link Car} on on April 2nd, 2024 at 6:00 AM.
     */
    @Test
    public void carOnApril2nd6AmShouldReturn8SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(8, tollCalculator.calculateTotalFees(car, date));
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method returns 0 for a {@link Motorbike} on on April 2nd, 2024 at 6:00 AM.
     * Because
     * motorbikes are fee free vehicle.
     */
    @Test
    public void motorbikeOnApril2nd6AmShouldReturn8SEKFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0);
        assertEquals(0, tollCalculator.calculateTotalFees(motorbike, date));
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method returns 0 for a {@link Car} on on March 31rd, 2024 at 6:00 AM. Because
     * it was sunday and it's a weekend in sweden.
     */
    @Test
    public void carOnWeekendShouldReturnZeroFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertEquals(0, tollCalculator.calculateTotalFees(car, date));
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method returns 0 for a {@link Motorbike} on on April 2nd, 2024 at 6:00 AM.
     * Because
     * it was sunday and it's a weekend in sweden. And motorbikes are fee free
     * vehicle.
     */
    @Test
    public void motorbikeOnWeekendShouldReturnZeroFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.MARCH, 31, 6, 0);
        assertEquals(0, tollCalculator.calculateTotalFees(motorbike, date));
    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method returns the heighest fee for two fees that happened in the same hour.
     * So, it will return 13 as 6:45 AM is considered in the range of 6:30 to 7:00
     * AM. For a {@link car}
     */
    @Test
    public void carOnMultipleDatesSameHourShouldReturnHighestFee() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0); // The Fee for 6 AM is 8
        LocalDateTime secondDate = LocalDateTime.of(2024, Month.APRIL, 2, 6, 45); // The Fee for 6:45 AM is 13 since
                                                                                  // it's a rush hour
        assertEquals(13, tollCalculator.calculateTotalFees(car, date, secondDate));

    }

    /**
     * This test verifies that the
     * {@link TollCalculatorImpl#calculateTotalFees(Vehicle, LocalDateTime...)}
     * method returns the sum of both fees since they happened in different hours.
     * So, it should return a total of 26 which is the sum of 8 for the fee at 6:00
     * AM and 18 for the fee at 7:30 AM. For a {@link car}
     */
    @Test
    public void carOnMultipleDatesDiffrentHourShouldReturnSumOfFees() {
        LocalDateTime date = LocalDateTime.of(2024, Month.APRIL, 2, 6, 0); // The Fee for 6 AM is 8
        LocalDateTime secondDate = LocalDateTime.of(2024, Month.APRIL, 2, 7, 30); // Has a Fee of 18 since it's a rush
                                                                                  // hour
        assertEquals(26, tollCalculator.calculateTotalFees(car, date, secondDate));
    }

}