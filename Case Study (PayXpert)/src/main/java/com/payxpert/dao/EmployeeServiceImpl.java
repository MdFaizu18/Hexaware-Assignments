package com.payxpert.dao;

import com.payxpert.entity.Employee;
import com.payxpert.exception.EmployeeNotFoundException;
import com.payxpert.exception.InvalidInputException;
import com.payxpert.util.DatabaseContext;
import com.payxpert.util.ValidationService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {

    @Override
    public Employee getEmployeeById(int employeeId) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                    emp.setGender(rs.getString("gender"));
                    emp.setEmail(rs.getString("email"));
                    emp.setPhoneNumber(rs.getString("phone_number"));
                    emp.setAddress(rs.getString("address"));
                    emp.setPosition(rs.getString("job_position"));
                    emp.setJoiningDate(rs.getDate("joining_date").toLocalDate());
                    java.sql.Date termDate = rs.getDate("termination_date");
                    if (termDate != null) {
                        emp.setTerminationDate(termDate.toLocalDate());
                    }
                    return emp;
                } else {
                    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
                }
            }
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM Employee";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                    emp.setGender(rs.getString("gender"));
                    emp.setEmail(rs.getString("email"));
                    emp.setPhoneNumber(rs.getString("phone_number"));
                    emp.setAddress(rs.getString("address"));
                    emp.setPosition(rs.getString("job_position"));
                    emp.setJoiningDate(rs.getDate("joining_date").toLocalDate());
                    java.sql.Date termDate = rs.getDate("termination_date");
                    if (termDate != null) {
                        emp.setTerminationDate(termDate.toLocalDate());
                    }
                    employeeList.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void addEmployee(Employee employee) throws Exception {
        StringBuilder errorMessages = new StringBuilder();

        if (ValidationService.isEmployeeExists(employee.getEmployeeId())) {}
        if (!ValidationService.isValidEmail(employee.getEmail())) {
            errorMessages.append("Invalid email address provided.\n");
        }
        if (!ValidationService.validatePhoneNumber(employee.getPhoneNumber())) {
            errorMessages.append("Invalid phone number provided.\n");
        }
        if (!ValidationService.validateGender(employee.getGender())) {
            errorMessages.append("Invalid gender provided.\n");
        }
        if (!ValidationService.validateDateOfBirth(employee.getDateOfBirth())) {
            errorMessages.append("Invalid DOB provided.\n");
        }
        if (!ValidationService.validateJoiningDate(employee.getJoiningDate())) {
            errorMessages.append("Joining date can't be in the future.\n");
        }

        if (errorMessages.length() > 0) {
            throw new InvalidInputException(errorMessages.toString().trim());
        }

        String sql = "INSERT INTO Employee (employee_id, first_name, last_name, date_of_birth, gender, email, phone_number, address, job_position, joining_date, termination_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employee.getEmployeeId());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
            ps.setDate(4, java.sql.Date.valueOf(employee.getDateOfBirth()));
            ps.setString(5, employee.getGender());
            ps.setString(6, employee.getEmail());
            ps.setString(7, employee.getPhoneNumber());
            ps.setString(8, employee.getAddress());
            ps.setString(9, employee.getPosition());
            ps.setDate(10, java.sql.Date.valueOf(employee.getJoiningDate()));
            if (employee.getTerminationDate() != null) {
                ps.setDate(11, java.sql.Date.valueOf(employee.getTerminationDate()));
            } else {
                ps.setNull(11, java.sql.Types.DATE);
            }

            ps.executeUpdate();
        }
    }


    @Override
    public void updateEmployee(Employee employee) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "UPDATE Employee SET first_name=?, last_name=?, date_of_birth=?, gender=?, email=?, phone_number=?, address=?, job_position=?, joining_date=?, termination_date=? WHERE employee_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDate(3, java.sql.Date.valueOf(employee.getDateOfBirth()));
            ps.setString(4, employee.getGender());
            ps.setString(5, employee.getEmail());
            ps.setString(6, employee.getPhoneNumber());
            ps.setString(7, employee.getAddress());
            ps.setString(8, employee.getPosition());
            ps.setDate(9, java.sql.Date.valueOf(employee.getJoiningDate()));
            if(employee.getTerminationDate() != null) {
                ps.setDate(10, java.sql.Date.valueOf(employee.getTerminationDate()));
            } else {
                ps.setNull(10, java.sql.Types.DATE);
            }
            ps.setInt(11, employee.getEmployeeId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeId() + " not found for update.");
            }
        }
    }

    @Override
    public void removeEmployee(int employeeId) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "DELETE FROM Employee WHERE employee_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found for deletion.");
            }
        }
    }
}
