package com.fawry.service;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fawry.model.Vehicle;
import com.fawry.model.VehicleType;

/**
 * Implementation of the {@link TollFreeVehicleService} interface, responsible
 * for
 * determining whether a
 * vehicle is exempt from tolls.
 */
public class TollFreeVehicleServiceImpl implements TollFreeVehicleService {
    private static final Logger LOGGER = Logger.getLogger(TollFreeVehicleServiceImpl.class.getName());

    private final Set<String> vehicleTypes = Set.of(VehicleType.MOTORBIKE.getType(),
            VehicleType.TRACTOR.getType(), VehicleType.EMERGENCY.getType(), VehicleType.DIPLOMAT.getType(),
            VehicleType.FOREIGN.getType(), VehicleType.MILITARY.getType());

    @Override
    public boolean isTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            LOGGER.log(Level.INFO, "Vehicle is null, considered not toll-free");
            return false;
        }

        boolean isTollFree = vehicleTypes.contains(vehicle.getType());
        LOGGER.log(Level.INFO, "Checking if vehicle type (" + vehicle.getType() + ") is toll-free: " + isTollFree);
        return isTollFree;
    }
}
