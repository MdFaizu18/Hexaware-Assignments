package com.payxpert.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sample";
        String username = "root";
        String password = "Mdfaizu*18";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection con = DriverManager.getConnection(url, username, password);

            if (con != null) {
                System.out.println("✅ Database connected successfully!");
            } else {
                System.out.println("❌ Failed to connect to the database.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("❌ Error while connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
