package com.example.parking_management.Dao;

import com.example.parking_management.Connection.DbConnection;
import com.example.parking_management.Json.Statistic;
import com.example.parking_management.Model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao{
    @Override
    public boolean checkManager(String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DbConnection.connect();
            Statement statement = conn.createStatement();
            String queryLogin = "SELECT * FROM dbo.Managers WHERE username = \'" + username + "\' AND password = \'" + password + "\'";
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
    public Bill exportBill(String licenseVehicle) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call addBill(?)}");
            callableStatement.setString(1, licenseVehicle);
            int count = callableStatement.executeUpdate();
            System.out.println(count);
            if (count > 0) {
                Statement statement = conn.createStatement();
                String query = "SELECT TOP 1 * FROM dbo.Bill WHERE license_vehicle = \'"+ licenseVehicle +"\' ORDER BY out_time DESC";
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                Bill bill = new Bill(rs.getInt(1), rs.getString(2),rs.getTimestamp(3), rs.getString(4), rs.getTimestamp(5), rs.getInt(6));
                System.out.println(bill.toString());
                return bill;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    @Override
    public Statistic statisticByTime(Date from, Date to) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            System.out.println("alo alo");
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call Statistic(?, ?)}");
            callableStatement.setDate(1, from);
            callableStatement.setDate(2, to);
            ResultSet rs = callableStatement.executeQuery();
            rs.next();
            Statistic statistic = new Statistic(rs.getInt(1), rs.getInt(2));
            return statistic;
        } catch (SQLException e) {
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

    @Override
    public boolean addMap(String locate, String area) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DbConnection.connect();
            CallableStatement callableStatement = conn.prepareCall("{call AddMap(?, ?)}");
            callableStatement.setString(1, locate);
            callableStatement.setString(2, area);
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
}
