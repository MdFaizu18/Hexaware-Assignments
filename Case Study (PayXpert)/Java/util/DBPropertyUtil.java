package com.payxpert.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String propertyFileName) throws Exception {
        Properties prop = new Properties();

        // Load the properties file from the classpath
        try (InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                throw new IOException("❌ Property file '" + propertyFileName + "' not found in the classpath.");
            }

            prop.load(input);

            // Reading DB properties
            String server = prop.getProperty("db.server");        // e.g., localhost:3306
            String database = prop.getProperty("db.database");    // e.g., sample
            String user = prop.getProperty("db.username");        // e.g., root
            String password = prop.getProperty("db.password");    // e.g., your_password

            // MySQL JDBC connection string format
            String connStr = "jdbc:mysql://" + server + "/" + database + "?user=" + user + "&password=" + password;
            return connStr;

        } catch (IOException e) {
            throw new Exception("⚠️ Failed to load DB properties: " + e.getMessage(), e);
        }
    }
}
