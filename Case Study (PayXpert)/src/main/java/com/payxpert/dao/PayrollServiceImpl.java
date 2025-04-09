package com.payxpert.dao;

import com.payxpert.entity.Payroll;
import com.payxpert.exception.PayrollGenerationException;
import com.payxpert.util.DatabaseContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollServiceImpl implements IPayrollService {

    @Override
    public Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate, double basicSalary, double overtimePay, double deductions) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        double netSalary = basicSalary + overtimePay - deductions;
        String sql = "INSERT INTO payroll (employee_id, pay_period_start_date, pay_period_end_date, base_salary, overtime_pay, deductions, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, employeeId);
            ps.setDate(2, java.sql.Date.valueOf(startDate));
            ps.setDate(3, java.sql.Date.valueOf(endDate));
            ps.setDouble(4, basicSalary);
            ps.setDouble(5, overtimePay);
            ps.setDouble(6, deductions);
            ps.setDouble(7, netSalary);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int payrollId = rs.getInt(1);
                    return new Payroll(payrollId, employeeId, startDate, endDate, basicSalary, overtimePay, deductions, netSalary);
                } else {
                    throw new PayrollGenerationException("Failed to generate payroll, no ID obtained.");
                }
            }
        } catch (Exception e) {
            throw new PayrollGenerationException("Error generating payroll for employee ID " + employeeId, e);
        }
    }

    @Override
    public Payroll getPayrollById(int payrollId) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "SELECT * FROM payroll WHERE payroll_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payrollId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Payroll payroll = new Payroll();
                    payroll.setPayrollId(rs.getInt("payroll_id"));
                    payroll.setEmployeeId(rs.getInt("employee_id"));
                    payroll.setPayPeriodStartDate(rs.getDate("pay_period_start_date").toLocalDate());
                    payroll.setPayPeriodEndDate(rs.getDate("pay_period_end_date").toLocalDate());
                    payroll.setBasicSalary(rs.getDouble("base_salary"));
                    payroll.setOvertimePay(rs.getDouble("overtime_pay"));
                    payroll.setDeductions(rs.getDouble("deductions"));
                    payroll.setNetSalary(rs.getDouble("net_salary"));
                    return payroll;
                } else {
                    throw new PayrollGenerationException("Payroll with ID " + payrollId + " not found.");
                }
            }
        }
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrollList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM payroll WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, employeeId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Payroll payroll = new Payroll();
                        payroll.setPayrollId(rs.getInt("payroll_id"));
                        payroll.setEmployeeId(rs.getInt("employee_id"));
                        payroll.setPayPeriodStartDate(rs.getDate("pay_period_start_date").toLocalDate());
                        payroll.setPayPeriodEndDate(rs.getDate("pay_period_end_date").toLocalDate());
                        payroll.setBasicSalary(rs.getDouble("base_salary"));
                        payroll.setOvertimePay(rs.getDouble("overtime_pay"));
                        payroll.setDeductions(rs.getDouble("deductions"));
                        payroll.setNetSalary(rs.getDouble("net_salary"));
                        payrollList.add(payroll);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payrollList;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> payrollList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM payroll WHERE pay_period_start_date >= ? AND pay_period_end_date <= ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDate(1, java.sql.Date.valueOf(startDate));
                ps.setDate(2, java.sql.Date.valueOf(endDate));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Payroll payroll = new Payroll();
                        payroll.setPayrollId(rs.getInt("payroll_id"));
                        payroll.setEmployeeId(rs.getInt("employee_id"));
                        payroll.setPayPeriodStartDate(rs.getDate("pay_period_start_date").toLocalDate());
                        payroll.setPayPeriodEndDate(rs.getDate("pay_period_end_date").toLocalDate());
                        payroll.setBasicSalary(rs.getDouble("base_salary"));
                        payroll.setOvertimePay(rs.getDouble("overtime_pay"));
                        payroll.setDeductions(rs.getDouble("deductions"));
                        payroll.setNetSalary(rs.getDouble("net_salary"));
                        payrollList.add(payroll);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payrollList;
    }
}
