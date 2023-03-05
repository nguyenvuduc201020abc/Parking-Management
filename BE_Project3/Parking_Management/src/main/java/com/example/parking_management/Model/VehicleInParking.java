package com.example.parking_management.Model;

import java.sql.Timestamp;

public class VehicleInParking extends Vehicle{
    private Timestamp entryTime;

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public VehicleInParking(String licenseVehicle, Timestamp entryTime, String typeVehicle, String username, String color, String image, String locate) {
        super(licenseVehicle, typeVehicle, username, color, image, locate);
        this.entryTime = entryTime;
    }
}
