package com.fawry;

import java.time.LocalDateTime;

import com.fawry.model.Vehicle;

public interface TollCalculator {

    int calculateTotalFees(Vehicle vehicle, LocalDateTime... dates);

}