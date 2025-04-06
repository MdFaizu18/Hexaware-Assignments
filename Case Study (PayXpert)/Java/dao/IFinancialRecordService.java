package com.payxpert.dao;

import com.payxpert.entity.FinancialRecord;
import java.time.LocalDate;
import java.util.List;

public interface IFinancialRecordService {
    FinancialRecord addFinancialRecord(int employeeId, String description, double amount, String recordType) throws Exception;
    FinancialRecord getFinancialRecordById(int recordId) throws Exception;
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate);


}
