package com.example.parking_management.Dao;

import com.example.parking_management.Connection.DbConnection;
import com.example.parking_management.Model.Bill;
import com.example.parking_management.Model.Vehicle;
import com.example.parking_management.Model.VehicleInParking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DbConnection.connect();
            Statement statement = conn.createStatement();
            String queryLogin = "SELECT * FROM dbo.Users WHERE username = \'" + username + "\' AND password = \'" + password + "\'";
            ResultSet rs = statement.executeQuery(queryLogin);
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            return false;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean addUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call addUser(?, ?)}");
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            int count = callableStatement.executeUpdate();
            if (count <= 0) {
                return false;
            } else return true;
        } catch (SQLException e) {
            return false;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Bill> getBillByUsername(String username) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Bill bill = null;
        List<Bill> bills = new ArrayList<Bill>();
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call GetBillByUsername(?)}");
            callableStatement.setString(1, username);
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                bill = new Bill(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), rs.getTimestamp(5), rs.getInt(6));
                bills.add(bill);
            }
            return bills;
        } catch (SQLException e) {
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<VehicleInParking> getVehicleByUsername(String username) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        VehicleInParking vehicleInParking = null;
        List<VehicleInParking> vehicles = new ArrayList<VehicleInParking>();
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call GetVehicleInParking(?)}");
            callableStatement.setString(1, username);
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                vehicleInParking = new VehicleInParking(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                vehicles.add(vehicleInParking);
            }
            return vehicles;
        } catch (SQLException e) {
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
