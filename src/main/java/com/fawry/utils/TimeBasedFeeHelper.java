package com.fawry.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeBasedFeeHelper {
    private static final Logger LOGGER = Logger.getLogger(TimeBasedFeeHelper.class.getName());

    public static NavigableMap<LocalTime, Integer> getTimeBasedFeeMap(String timeBasedFeeString) {
        Map<LocalTime, Integer> timeBasedFeeMap = new HashMap<>();
        if (timeBasedFeeString == null || timeBasedFeeString.isBlank() || timeBasedFeeString.equals("None")) {
            LOGGER.log(Level.INFO, "Empty or 'None' timeBasedFeeString provided, returning empty map");
            return new TreeMap<>(timeBasedFeeMap);
        }

        LOGGER.log(Level.INFO, "Parsing timeBasedFeeString: " + timeBasedFeeString);
        String[] timeBasedFeeSplitted = timeBasedFeeString.split(";");
        for (String timeBasedFee : timeBasedFeeSplitted) {
            try {
                String[] timeValueSplitted = timeBasedFee.split(",");
                LocalTime time = LocalTime.parse(timeValueSplitted[0].trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                Integer fee = Integer.valueOf(timeValueSplitted[1].trim());
                timeBasedFeeMap.put(time, fee);
                LOGGER.log(Level.INFO, "Successfully added fee for " + time + ": " + fee);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error parsing time-based fee entry: " + timeBasedFee, e);
            }
        }
        return new TreeMap<>(timeBasedFeeMap);
    }

}
