package com.fawry.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;


import com.fawry.model.Vehicle;

public class TimeBasedFeeServiceImpl implements TimeBasedFeeService {

    private final TollFreeDatesService tollFreeDatesService;
    private final TollFreeVehicleService tollFreeVehicleService;

    public TimeBasedFeeServiceImpl(TollFreeDatesService tollFreeDatesService,
            TollFreeVehicleService tollFreeVehicleService) {
        this.tollFreeDatesService = tollFreeDatesService;
        this.tollFreeVehicleService = tollFreeVehicleService;
    }

    private final Map<LocalTime, Integer> TIME_BASED_FEE = Map.ofEntries(
            entry(LocalTime.of(6, 0), 8),
            entry(LocalTime.of(6, 30), 13),
            entry(LocalTime.of(7, 0), 18),
            entry(LocalTime.of(8, 0), 13),
            entry(LocalTime.of(8, 30), 8),
            entry(LocalTime.of(15, 0), 13),
            entry(LocalTime.of(15, 30), 18),
            entry(LocalTime.of(17, 0), 13),
            entry(LocalTime.of(18, 0), 8));

    private final NavigableMap<LocalTime, Integer> TIME_BASED_FEE_SORTED = new TreeMap<>(TIME_BASED_FEE);

    private Map.Entry<LocalTime, Integer> entry(LocalTime key, Integer value) {
        return Map.entry(key, value);
    }

    @Override
    public int getTollFee(final LocalDateTime date, Vehicle vehicle) {
        if (tollFreeDatesService.isTollFreeDate(date) || tollFreeVehicleService.isTollFreeVehicle(vehicle))
            return 0;
        LocalTime time = date.toLocalTime();
        return Optional.of(TIME_BASED_FEE_SORTED.floorEntry(time).getValue()).orElse(0);
    }
}
