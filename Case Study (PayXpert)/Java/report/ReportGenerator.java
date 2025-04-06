package com.payxpert.report;

import com.payxpert.entity.Payroll;
import com.payxpert.entity.Tax;
import com.payxpert.entity.FinancialRecord;
import java.util.List;

public class ReportGenerator {
    public static void generatePayrollReport(List<Payroll> payrolls) {
        System.out.println("Payroll Report:");
        for (Payroll p : payrolls) {
            System.out.println(p);
        }
    }

    public static void generateTaxReport(List<Tax> taxes) {
        System.out.println("Tax Report:");
        for (Tax t : taxes) {
            System.out.println(t);
        }
    }

    public static void generateFinancialReport(List<FinancialRecord> records) {
        System.out.println("Financial Report:");
        for (FinancialRecord r : records) {
            System.out.println(r);
        }
    }
}
