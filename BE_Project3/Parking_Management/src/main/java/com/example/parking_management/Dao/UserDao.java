package com.example.parking_management.Dao;

import com.example.parking_management.Model.Bill;
import com.example.parking_management.Model.Vehicle;
import com.example.parking_management.Model.VehicleInParking;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException;
    boolean addUser(String username, String password)throws SQLException, ClassNotFoundException;
    List<Bill> getBillByUsername(String username) throws SQLException, ClassNotFoundException;
    List<VehicleInParking> getVehicleByUsername(String username) throws SQLException, ClassNotFoundException;
}
