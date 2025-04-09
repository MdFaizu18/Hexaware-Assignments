package com.payxpert.util;

import com.payxpert.exception.DatabaseConnectionException;

import java.sql.Connection;

public class DatabaseContext {
    public static Connection getConnection() throws DatabaseConnectionException {
        try {
            String connectionString = DBPropertyUtil.getConnectionString("db.properties");
            return DBConnUtil.getConnection(connectionString);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Error obtaining database connection", e);
        }
    }
}