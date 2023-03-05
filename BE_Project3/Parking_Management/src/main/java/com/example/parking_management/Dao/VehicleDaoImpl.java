package com.example.parking_management.Dao;

import com.example.parking_management.Connection.DbConnection;
import com.example.parking_management.Model.Vehicle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class VehicleDaoImpl implements  VehicleDao{
    @Override
    public boolean addVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call addVehicleInParking(?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, vehicle.getLicenseVehicle());
            callableStatement.setString(2, vehicle.getTypeVehicle());
            callableStatement.setString(3, vehicle.getUsername());
            callableStatement.setString(4, vehicle.getColor());
            callableStatement.setString(5, vehicle.getImage());
            callableStatement.setString(6, vehicle.getLocate());
            int count = callableStatement.executeUpdate();
            System.out.println(count);
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

}
