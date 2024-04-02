package com.fawry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

@Configuration
public class AppConfig {
    @Bean
    public TimeBasedFeeService timeBasedFeeService() {
        return new TimeBasedFeeServiceImpl(tollFreeDatesService(), tollFreeVehicleService());
    }

    @Bean
    public TollFreeDatesService tollFreeDatesService() {
        return new TollFreeDatesServiceImpl();
    }

    @Bean
    public TollFreeVehicleService tollFreeVehicleService() {
        return new TollFreeVehicleServiceImpl();
    }

    @Bean
    public TollCalculator tollCalculator() {
        return new TollCalculatorImpl(timeBasedFeeService());
    }

    @Bean("car")
    public Vehicle car() {
        return new Car();
    }

    @Bean("motorbike")
    public Vehicle motorBike() {
        return new Motorbike();
    }
}
