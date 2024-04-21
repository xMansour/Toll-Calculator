package com.fawry.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.fawry.model.Vehicle;
import com.fawry.utils.TimeBasedFeeHelper;

/**
 * Implementation of the {@link TimeBasedFeeService} interface, responsible for
 * providing toll fees
 * based on date and vehicle type.
 */
public class TimeBasedFeeServiceImpl implements TimeBasedFeeService {
    private static final Logger LOGGER = Logger.getLogger(TimeBasedFeeServiceImpl.class.getName());

    @Value("${timeBasedFee:None}")
    private String timeBasedFeeString;
    private final TollFreeDatesService tollFreeDatesService;
    private final TollFreeVehicleService tollFreeVehicleService;

    public TimeBasedFeeServiceImpl(TollFreeDatesService tollFreeDatesService,
            TollFreeVehicleService tollFreeVehicleService) {
        this.tollFreeDatesService = tollFreeDatesService;
        this.tollFreeVehicleService = tollFreeVehicleService;
    }

    @Override
    public int getTollFee(final LocalDateTime date, Vehicle vehicle) {
        LOGGER.log(Level.INFO, "Checking toll fee for date: " + date.toString() + ", vehicle: " + vehicle.getType());
        if (tollFreeDatesService.isTollFreeDate(date)) {
            LOGGER.log(Level.INFO, "Date is toll-free");
            return 0;
        }
        if (tollFreeVehicleService.isTollFreeVehicle(vehicle)) {
            LOGGER.log(Level.FINE, "Vehicle is toll-free");
            return 0;

        }
        LocalTime time = date.toLocalTime();
        int fee = Optional.of(TimeBasedFeeHelper
                .getTimeBasedFeeMap(timeBasedFeeString).floorEntry(time).getValue()).orElse(0);
        LOGGER.log(Level.INFO, "Fee for time: " + time + " is: " + fee);

        return fee;
    }
}
