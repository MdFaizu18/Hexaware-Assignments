package com.hexaware.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String propertyFileName) throws Exception {
        Properties prop = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                throw new Exception("Properties file not found in classpath: " + propertyFileName);
            }
            prop.load(input);
  
            String server = prop.getProperty("db.server");
            String database = prop.getProperty("db.database");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            return "jdbc:mysql://" + server + "/" + database + "?user=" + user + "&password=" + password;
        } catch (Exception e) {
            throw new Exception("Error reading properties file: " + e.getMessage(), e);
        }
    }
}
