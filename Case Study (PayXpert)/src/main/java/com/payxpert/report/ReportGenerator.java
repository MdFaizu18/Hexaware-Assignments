package com.payxpert.report;

import com.payxpert.entity.FinancialRecord;
import com.payxpert.entity.Payroll;
import com.payxpert.entity.Tax;
import com.payxpert.dao.FinancialRecordServiceImpl;
import com.payxpert.dao.TaxServiceImpl;
import com.payxpert.exception.InvalidInputException;
import java.time.LocalDate;
import java.util.List;

public class ReportGenerator {
    private final FinancialRecordServiceImpl financialRecordService;
    private final TaxServiceImpl taxService;

    public ReportGenerator() {
        this.financialRecordService = new FinancialRecordServiceImpl();
        this.taxService = new TaxServiceImpl();
    }

    public void generateIncomeStatement(LocalDate recordDate) {
        try {

            List<FinancialRecord> records = financialRecordService.getFinancialRecordsForDate(recordDate);

            if (records.isEmpty()) {
                System.out.println("No financial records found for the selected period.");
                return;
            }

            double totalIncome = 0;
            double totalExpenses = 0;
            double totalTaxPayments = 0;

            for (FinancialRecord record : records) {
                switch (record.getRecordType().toLowerCase()) {
                    case "income" -> totalIncome += record.getAmount();
                    case "expense" -> totalExpenses += record.getAmount();
                    case "tax" -> totalTaxPayments += record.getAmount();
                }
            }

            double netProfit = totalIncome - totalExpenses - totalTaxPayments;

            System.out.println("\n=== Income Statement ===");
            System.out.println("Period: " + recordDate);
            System.out.printf("Total Income: $%,.2f%n", totalIncome);
            System.out.printf("Total Expenses: $%,.2f%n", totalExpenses);
            System.out.printf("Tax Payments: $%,.2f%n", totalTaxPayments);
            System.out.println("------------------------------------");
            System.out.printf("Net Profit: $%,.2f%n", netProfit);

        } catch (Exception e) {
            System.out.println("Error generating income statement: " + e.getMessage());
        }
    }

    public void generateTaxSummary(int taxYear) {
        try {
            // Validate year input
            if (taxYear < 2000 || taxYear > LocalDate.now().getYear() + 1) {
                throw new InvalidInputException("Invalid tax year");
            }

            List<Tax> taxes = taxService.getTaxesForYear(taxYear);

            if (taxes.isEmpty()) {
                System.out.println("No tax records found for " + taxYear);
                return;
            }

            double totalTaxes = taxes.stream()
                    .mapToDouble(Tax::getTaxAmount)
                    .sum();

            System.out.println("\n=== Tax Summary for " + taxYear + " ===");
            System.out.printf("Total Taxes Paid: $%,.2f%n", totalTaxes);
            System.out.println("------------------------------------");
            System.out.println("Detailed Breakdown:");

            taxes.forEach(tax -> System.out.printf(
                    "Employee %d: $%,.2f%n",
                    tax.getEmployeeId(),
                    tax.getTaxAmount()
            ));

        } catch (Exception e) {
            System.out.println("Error generating tax summary: " + e.getMessage());
        }
    }
    public static void generatePayrollReport(List<Payroll> payrolls) {
        System.out.println("Payroll Report:");
        for (Payroll p : payrolls) {
            System.out.println(p);
        }
    }

}