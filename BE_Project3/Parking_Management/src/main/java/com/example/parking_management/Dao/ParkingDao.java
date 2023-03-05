package com.example.parking_management.Dao;

import com.example.parking_management.Model.Bill;
import com.example.parking_management.Model.Parking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ParkingDao {
    ArrayList<Parking> getParking(String area)throws SQLException, ClassNotFoundException;
    List<String> getArea() throws SQLException, ClassNotFoundException;
}
