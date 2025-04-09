package com.payxpert.util;

import com.payxpert.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {
    public static Connection getConnection(String connectionString) throws DatabaseConnectionException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Error establishing database connection: " + e.getMessage(), e);
        }
    }
}