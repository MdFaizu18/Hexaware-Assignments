package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {
    public static Connection getConnection(String connectionString) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            System.out.println("Getting error from DBConutil" + e);
            throw e;
        }
    }
}