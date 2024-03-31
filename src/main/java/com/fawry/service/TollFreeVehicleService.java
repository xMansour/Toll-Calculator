package com.fawry.service;

import java.util.Set;

import com.fawry.model.Vehicle;
import com.fawry.model.VehicleType;

public abstract class TollFreeVehicleService {
    private static final Set<String> vehicleTypes = Set.of(VehicleType.MOTORBIKE.getType(),
            VehicleType.TRACTOR.getType(), VehicleType.EMERGENCY.getType(), VehicleType.DIPLOMAT.getType(),
            VehicleType.FOREIGN.getType(), VehicleType.MILITARY.getType());

    public static boolean isTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null)
            return false;
        return vehicleTypes.contains(vehicle.getType());
    }
}
