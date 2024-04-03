package com.fawry.config;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fawry.TollCalculator;
import com.fawry.model.Vehicle;
import com.fawry.service.TimeBasedFeeService;
import com.fawry.service.TollFreeDatesService;
import com.fawry.service.TollFreeVehicleService;

/**
 * This Class is used to verify the functionality of the {@link AppConfig}.
 */
public class AppConfigTest {
    private static TimeBasedFeeService timeBasedFeeService;
    private static TollFreeDatesService tollFreeDatesService;
    private static TollFreeVehicleService tollFreeVehicleService;
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
            timeBasedFeeService = context.getBean(TimeBasedFeeService.class);
            tollFreeDatesService = context.getBean(TollFreeDatesService.class);
            tollFreeVehicleService = context.getBean(TollFreeVehicleService.class);
            tollCalculator = context.getBean(TollCalculator.class);
            car = context.getBean("car", Vehicle.class);
            motorbike = context.getBean("motorbike", Vehicle.class);
        } catch (BeansException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test verifies that the all the beans are created by the application
     * context and aren't null.
     */
    @Test
    public void testBeanCreation() {
        assertNotNull(timeBasedFeeService);
        assertNotNull(tollFreeDatesService);
        assertNotNull(tollFreeVehicleService);
        assertNotNull(tollCalculator);
        assertNotNull(car);
        assertNotNull(motorbike);
    }

}
