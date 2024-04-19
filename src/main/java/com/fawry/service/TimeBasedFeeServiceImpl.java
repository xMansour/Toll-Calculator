package com.fawry.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

import com.fawry.model.Vehicle;
import com.fawry.utils.TimeBasedFeeHelper;

/**
 * Implementation of the {@link TimeBasedFeeService} interface, responsible for
 * providing toll fees
 * based on date and vehicle type.
 */
public class TimeBasedFeeServiceImpl implements TimeBasedFeeService {
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
        if (tollFreeDatesService.isTollFreeDate(date) || tollFreeVehicleService.isTollFreeVehicle(vehicle))
            return 0;
        LocalTime time = date.toLocalTime();
        return Optional.of(TimeBasedFeeHelper
                .getTimeBasedFeeMap(timeBasedFeeString).floorEntry(time).getValue()).orElse(0);
    }
}
