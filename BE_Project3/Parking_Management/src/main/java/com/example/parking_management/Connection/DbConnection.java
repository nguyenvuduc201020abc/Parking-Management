package com.example.parking_management.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String HOST_NAME = "LAPTOP-0AJO77F6\\SQLEXPRESS";
    private static final String DB_NAME = "MANAGE_PARKING";
    private static final String USER_NAME = "sa";
    private static final String PASS_WORD = "12345678";

    private static final String CONNECTION_URL = "jdbc:sqlserver://" + HOST_NAME + ":1433;" + "databaseName=" + DB_NAME;

    public static Connection connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASS_WORD);
        return conn;
    }
}
