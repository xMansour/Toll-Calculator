package com.fawry.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TimeBasedFeeHelper {
    public static NavigableMap<LocalTime, Integer> getTimeBasedFeeMap(String timeBasedFeeString) {
        Map<LocalTime, Integer> timeBasedFeeMap = new HashMap<>();
        if (timeBasedFeeString == null || timeBasedFeeString.isBlank() || timeBasedFeeString.equals("None"))
            return new TreeMap<>(timeBasedFeeMap);

        String[] timeBasedFeeSplitted = timeBasedFeeString.split(";");
        for (String timeBasedFee : timeBasedFeeSplitted) {
            String[] timeValueSplitted = timeBasedFee.split(",");
            timeBasedFeeMap.put(LocalTime.parse(timeValueSplitted[0].trim(),
                    DateTimeFormatter.ofPattern("HH:mm")), Integer.valueOf(timeValueSplitted[1].trim()));
        }
        return new TreeMap<>(timeBasedFeeMap);
    }

}
