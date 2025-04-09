package com.payxpert.util;

import com.payxpert.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationService {

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+\\d{1,3})?\\d{10}$";
        return phoneNumber != null && Pattern.matches(phoneRegex, phoneNumber);
    }

    public static boolean validateString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean validateAmount(double amount) {
        return amount >= 0;
    }

    public static boolean isEmployeeExists(int employeeId) throws DatabaseConnectionException {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM employee WHERE employee_id = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Employee ID already exists in the system.");
                exists = true;
            }

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return exists;
    }



    public static boolean validateGender(String gender) {
        return gender != null &&
                (gender.equalsIgnoreCase("Male") ||
                        gender.equalsIgnoreCase("Female") ||
                        gender.equalsIgnoreCase("Other"));
    }

    public static boolean validateJoiningDate(LocalDate joiningDate) {
        return joiningDate != null && !joiningDate.isAfter(LocalDate.now());
    }

    public static boolean validateDateOfBirth(LocalDate dob) {
        return dob != null && dob.isBefore(LocalDate.now());
    }
}
