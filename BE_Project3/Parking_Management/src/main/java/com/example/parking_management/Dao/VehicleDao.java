package com.example.parking_management.Dao;

import com.example.parking_management.Model.Vehicle;

import java.sql.SQLException;

public interface VehicleDao {
    boolean addVehicle(Vehicle vehicle)throws SQLException, ClassNotFoundException;
}
