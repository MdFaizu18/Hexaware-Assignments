package com.payxpert.dao;

import com.payxpert.entity.Tax;
import java.util.List;

public interface ITaxService {
    Tax calculateTax(int employeeId, int taxYear, double taxableIncome) throws Exception;
    Tax getTaxById(int taxId) throws Exception;
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}
