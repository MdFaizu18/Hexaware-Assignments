package com.payxpert.dao;

import com.payxpert.entity.FinancialRecord;
import com.payxpert.exception.FinancialRecordException;
import com.payxpert.util.DatabaseContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordServiceImpl implements IFinancialRecordService {

    @Override
    public FinancialRecord addFinancialRecord(int employeeId, String description, double amount, String recordType) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "INSERT INTO financial_record (employee_id, record_date, description, amount, record_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, employeeId);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(3, description);
            ps.setDouble(4, amount);
            ps.setString(5, recordType);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int recordId = rs.getInt(1);
                    return new FinancialRecord(recordId, employeeId, LocalDate.now(), description, amount, recordType);
                } else {
                    throw new FinancialRecordException("Failed to add financial record, no ID obtained.");
                }
            }
        } catch (Exception e) {
            throw new FinancialRecordException("Error adding financial record for employee ID " + employeeId, e);
        }
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) throws Exception {
        Connection conn = DatabaseContext.getConnection();
        String sql = "SELECT * FROM financial_record WHERE record_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recordId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FinancialRecord record = new FinancialRecord();
                    record.setRecordId(rs.getInt("record_id"));
                    record.setEmployeeId(rs.getInt("employee_id"));
                    record.setRecordDate(rs.getDate("record_date").toLocalDate());
                    record.setDescription(rs.getString("description"));
                    record.setAmount(rs.getDouble("amount"));
                    record.setRecordType(rs.getString("record_type"));
                    return record;
                } else {
                    throw new FinancialRecordException("Financial record with ID " + recordId + " not found.");
                }

            }
        }
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> recordList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM financial_record WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, employeeId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        FinancialRecord record = new FinancialRecord();
                        record.setRecordId(rs.getInt("record_id"));
                        record.setEmployeeId(rs.getInt("employee_id"));
                        record.setRecordDate(rs.getDate("record_date").toLocalDate());
                        record.setDescription(rs.getString("description"));
                        record.setAmount(rs.getDouble("amount"));
                        record.setRecordType(rs.getString("record_type"));
                        recordList.add(record);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordList;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate) {
        List<FinancialRecord> recordList = new ArrayList<>();
        try {
            Connection conn = DatabaseContext.getConnection();
            String sql = "SELECT * FROM financial_record WHERE record_date = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDate(1, java.sql.Date.valueOf(recordDate));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        FinancialRecord record = new FinancialRecord();
                        record.setRecordId(rs.getInt("record_id"));
                        record.setEmployeeId(rs.getInt("employee_id"));
                        record.setRecordDate(rs.getDate("record_date").toLocalDate());
                        record.setDescription(rs.getString("description"));
                        record.setAmount(rs.getDouble("amount"));
                        record.setRecordType(rs.getString("record_type"));
                        recordList.add(record);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordList;
    }
}
