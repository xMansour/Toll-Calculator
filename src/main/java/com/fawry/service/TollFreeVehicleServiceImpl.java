package com.fawry.service;

import java.util.Set;

import com.fawry.model.Vehicle;
import com.fawry.model.VehicleType;

public class TollFreeVehicleServiceImpl implements TollFreeVehicleService {
    private final Set<String> vehicleTypes = Set.of(VehicleType.MOTORBIKE.getType(),
            VehicleType.TRACTOR.getType(), VehicleType.EMERGENCY.getType(), VehicleType.DIPLOMAT.getType(),
            VehicleType.FOREIGN.getType(), VehicleType.MILITARY.getType());

    @Override
    public boolean isTollFreeVehicle(Vehicle vehicle) {
        return vehicle != null && vehicleTypes.contains(vehicle.getType());
    }
}
