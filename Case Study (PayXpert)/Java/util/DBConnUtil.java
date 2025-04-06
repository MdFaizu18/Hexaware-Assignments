package com.payxpert.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.payxpert.exception.DatabaseConnectionException;

public class DBConnUtil {
    public static Connection getConnection(String connectionString) throws DatabaseConnectionException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage(), e);
        }
    }
}
