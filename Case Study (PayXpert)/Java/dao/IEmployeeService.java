package com.payxpert.dao;

import com.payxpert.entity.Employee;
import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(int employeeId) throws Exception;
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee) throws Exception;
    void updateEmployee(Employee employee) throws Exception;
    void removeEmployee(int employeeId) throws Exception;
}
