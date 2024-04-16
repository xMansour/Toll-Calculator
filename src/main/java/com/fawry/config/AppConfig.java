package com.fawry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fawry.TollCalculator;
import com.fawry.TollCalculatorImpl;
import com.fawry.model.Car;
import com.fawry.model.Motorbike;
import com.fawry.model.Vehicle;
import com.fawry.service.TimeBasedFeeService;
import com.fawry.service.TimeBasedFeeServiceImpl;
import com.fawry.service.TollFreeDatesService;
import com.fawry.service.TollFreeDatesServiceImpl;
import com.fawry.service.TollFreeVehicleService;
import com.fawry.service.TollFreeVehicleServiceImpl;

/**
 * Spring configuration class responsible for creating and wiring beans for the
 * toll calculation application.
 *
 * This class uses Spring's {@link Configuration} and {@link Bean} annotations
 * to define beans and their dependencies for dependency injection.
 * It encapsulates the configuration of core services and components for toll
 * calculation logic.
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "classpath:tollFreeDates-${spring.profiles.active}.properties")
public class AppConfig {

    /**
     * Creates a {@link TimeBasedFeeService} bean, configuring it with dependencies
     * on {@link TollFreeDatesService} and {@link TollFreeVehicleService}.
     *
     * @return The configured {@link TimeBasedFeeServiceImpl} instance.
     */
    @Bean
    public TimeBasedFeeService timeBasedFeeService() {
        return new TimeBasedFeeServiceImpl(tollFreeDatesService(), tollFreeVehicleService());
    }

    /**
     * Creates a {@link TollFreeDatesService} bean.
     * 
     * @return The default {@link TollFreeDatesServiceImpl} instance.
     */
    @Bean
    public TollFreeDatesService tollFreeDatesService() {
        return new TollFreeDatesServiceImpl();
    }

    /**
     * Creates a {@link TollFreeVehicleService} bean.
     *
     * @return The default {@link TollFreeVehicleServiceImpl} instance.
     */
    @Bean
    public TollFreeVehicleService tollFreeVehicleService() {
        return new TollFreeVehicleServiceImpl();
    }

    /**
     * Creates a {@link TollCalculator} bean, injecting a
     * {@link TimeBasedFeeService} dependency.
     *
     * @return The configured {@link TollCalculatorImpl} instance.
     */
    @Bean
    public TollCalculator tollCalculator() {
        return new TollCalculatorImpl(timeBasedFeeService());
    }

    /**
     * Creates a {@link Vehicle} bean representing a car, named "car".
     *
     * @return A {@link Car} instance.
     */
    @Bean("car")
    public Vehicle car() {
        return new Car();
    }

    /**
     * Creates a {@link Vehicle} bean representing a motorbike, named "motorbike".
     *
     * @return A {@link Motorbike} instance.
     */
    @Bean("motorbike")
    public Vehicle motorBike() {
        return new Motorbike();
    }
}
