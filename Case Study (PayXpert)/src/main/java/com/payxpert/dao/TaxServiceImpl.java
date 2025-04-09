package com.payxpert.dao;

import com.payxpert.entity.Tax;
import com.payxpert.exception.TaxCalculationException;
import com.payxpert.util.DatabaseContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaxServiceImpl implements ITaxService {

    @Override
    public Tax calculateTax(int employeeId, int taxYear, double taxableIncome) throws Exception {
        // Progressive tax brackets
        double taxAmount = 0;

        if(taxableIncome > 200_000) {
            taxAmount = (taxableIncome - 200_000) * 0.35 +
                    100_000 * 0.30 +
                    100_000 * 0.25;
        } else if(taxableIncome > 100_000) {
            taxAmount = (taxableIncome - 100_000) * 0.30 +
                    100_000 * 0.25;
        } else {
            taxAmount = taxableIncome * 0.25;
        }
        Connection conn = DatabaseContext.getConnection();
        String sql = "INSERT INTO tax (employee_id, tax_year, taxable_income, tax_amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, employeeId);
            ps.setInt(2, taxYear);
            ps.setDouble(3, taxableIncome);
            ps.setDouble(4, taxAmount);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int taxId = rs.getInt(1);
                    return new Tax(taxId, employeeId, taxYear, taxableIncome, taxAmount);
                } else {
                    throw new TaxCalculationException("Failed to calculate tax, no ID obtained.");
                }
            }
        } catch (Exception e) {
            throw new TaxCalculationException("Error calculating tax for employee ID " + employeeId, e);
        }
    }

    @Override
    public Tax getTaxById(int taxId) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "SELECT * FROM tax WHERE tax_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, taxId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Tax tax = new Tax();
                    tax.setTaxId(rs.getInt("tax_id"));
                    tax.setEmployeeId(rs.getInt("employee_id"));
                    tax.setTaxYear(rs.getInt("tax_year"));
                    tax.setTaxableIncome(rs.getDouble("taxable_income"));
                    tax.setTaxAmount(rs.getDouble("tax_amount"));
                    return tax;
                } else {
                    throw new TaxCalculationException("Tax record with ID " + taxId + " not found.");
                }
            }
        }
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM tax WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, employeeId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Tax tax = new Tax();
                        tax.setTaxId(rs.getInt("tax_id"));
                        tax.setEmployeeId(rs.getInt("employee_id"));
                        tax.setTaxYear(rs.getInt("tax_year"));
                        tax.setTaxableIncome(rs.getDouble("taxable_income"));
                        tax.setTaxAmount(rs.getDouble("tax_amount"));
                        taxList.add(tax);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxList;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM tax WHERE tax_year = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, taxYear);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Tax tax = new Tax();
                        tax.setTaxId(rs.getInt("tax_id"));
                        tax.setEmployeeId(rs.getInt("employee_id"));
                        tax.setTaxYear(rs.getInt("tax_year"));
                        tax.setTaxableIncome(rs.getDouble("taxable_income"));
                        tax.setTaxAmount(rs.getDouble("tax_amount"));
                        taxList.add(tax);
                    }
                }
                if (taxList.isEmpty()) {
                    System.out.println("No tax records found for year " + taxYear);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxList;
    }
}
