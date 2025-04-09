package com.hexaware.util;
import java.sql.Connection;

public class DatabaseContext {
    public static Connection getConnection() throws Exception {
        try {
            String connectionString = DBPropertyUtil.getConnectionString("db.properties");
            return DBConnUtil.getConnection(connectionString);
        } catch (Exception e) {
            System.out.println("Error obtaining database connection" + e);
            throw e;
        }
    }
}
