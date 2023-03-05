package com.example.parking_management.Dao;

import com.example.parking_management.Json.Statistic;
import com.example.parking_management.Model.Bill;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface ManagerDao {
    boolean checkManager(String username, String password) throws SQLException, ClassNotFoundException;
    Bill exportBill(String licenseVehicle) throws SQLException, ClassNotFoundException;

    Statistic statisticByTime(Date from, Date to)throws SQLException, ClassNotFoundException;

    List<String> getArea() throws SQLException, ClassNotFoundException;

    boolean addMap(String locate, String area) throws SQLException, ClassNotFoundException;

}
