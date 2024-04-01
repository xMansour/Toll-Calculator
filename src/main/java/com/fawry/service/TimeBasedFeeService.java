package com.fawry.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import com.fawry.model.Vehicle;

public abstract class TimeBasedFeeService {

    private static final Map<LocalTime, Integer> TIME_BASED_FEE = Map.ofEntries(
            entry(LocalTime.of(6, 0), 8),
            entry(LocalTime.of(6, 30), 13),
            entry(LocalTime.of(7, 0), 18),
            entry(LocalTime.of(8, 0), 13),
            entry(LocalTime.of(8, 30), 8),
            entry(LocalTime.of(15, 0), 13),
            entry(LocalTime.of(15, 30), 18),
            entry(LocalTime.of(17, 0), 13),
            entry(LocalTime.of(18, 0), 8));

    private static final NavigableMap<LocalTime, Integer> TIME_BASED_FEE_SORTED = new TreeMap<>(TIME_BASED_FEE);

    private static Map.Entry<LocalTime, Integer> entry(LocalTime key, Integer value) {
        return Map.entry(key, value);
    }

    public static int getTollFee(final LocalDateTime date, Vehicle vehicle) {
        if (TollFreeDatesService.isTollFreeDate(date) || TollFreeVehicleService.isTollFreeVehicle(vehicle))
            return 0;
        LocalTime time = date.toLocalTime();
        return Optional.of(TIME_BASED_FEE_SORTED.floorEntry(time).getValue()).orElse(0);
    }
}
