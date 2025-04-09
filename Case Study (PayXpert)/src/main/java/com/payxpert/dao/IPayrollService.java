package com.payxpert.dao;

import com.payxpert.entity.Payroll;
import java.time.LocalDate;
import java.util.List;

public interface IPayrollService {
    Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate, double basicSalary, double overtimePay, double deductions) throws Exception;
    Payroll getPayrollById(int payrollId) throws Exception;
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
}
