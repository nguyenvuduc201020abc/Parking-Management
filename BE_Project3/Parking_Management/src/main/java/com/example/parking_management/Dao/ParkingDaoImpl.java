package com.example.parking_management.Dao;

import com.example.parking_management.Connection.DbConnection;
import com.example.parking_management.Model.Bill;
import com.example.parking_management.Model.Parking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingDaoImpl implements ParkingDao {

    @Override
    public ArrayList<Parking> getParking(String area) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            ArrayList<Parking> parkings = new ArrayList<Parking>();
            conn = DbConnection.connect();
            Statement statement = conn.createStatement();
            String queryLogin = "SELECT * FROM dbo.Parking WHERE area = \'" + area + "\'";
            ResultSet rs = statement.executeQuery(queryLogin);
            Parking parking = null;
            while (rs.next()) {
                parking = new Parking(rs.getString(1), rs.getString(2), rs.getBoolean(3));
                parkings.add(parking);
            }
            return parkings;
        } catch (SQLException e) {
            System.out.println("SQLException");
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    @Override
    public List<String> getArea() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        List<String> strings = new ArrayList<String>();
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call GetArea}");
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                String s1 = rs.getString(1);
                strings.add(s1);
            }
            return strings;
        } catch (SQLException e) {
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
