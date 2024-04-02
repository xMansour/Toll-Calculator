package com.fawry.service;

import java.time.LocalDateTime;

import com.fawry.model.Vehicle;

public interface TimeBasedFeeService {

    int getTollFee(LocalDateTime date, Vehicle vehicle);

}