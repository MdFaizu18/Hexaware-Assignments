package com.payxpert.util;

import java.sql.Connection;
import com.payxpert.exception.DatabaseConnectionException;

public class DatabaseContext {
    private static Connection connection;

    public static Connection getConnection() throws DatabaseConnectionException {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("🔍 Loading connection string from db.properties...");
                String connectionString = DBPropertyUtil.getConnectionString("db.properties");
                System.out.println("🔌 Connection string resolved: " + connectionString);
                connection = DBConnUtil.getConnection(connectionString);
                System.out.println("✅ Successfully connected to the database!");
            }
        } catch (Exception e) {
            System.out.println("❌ Detailed error during DB connection: " + e.getMessage());
            e.printStackTrace();  // Optional: prints full stack trace for debugging
            throw new DatabaseConnectionException("Error obtaining database connection", e);
        }
        return connection;
    }
}


