package com.application.cab_application.Models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ride {
    private int id;
    private Integer RiderId;
    private Integer DriverId;

    public Ride(int id, int riderId, int driverId) {
        this.id = id;
        RiderId = riderId;
        DriverId = driverId;
    }

    public void setRiderId(int riderId) {
        RiderId = riderId;
    }

    public Ride() {

    }

    public int getId() {
        return id;
    }

    public int getRiderId() {
        return RiderId;
    }

    public int getDriverId() {
        return DriverId;
    }

    public Map<String, Object> rideTableMapper(){
        Map<String, Object> rideMapper = new LinkedHashMap<>();
        rideMapper.put("driver_id", DriverId);
        rideMapper.put("rider_id", RiderId);
        return rideMapper ;
    }
}
