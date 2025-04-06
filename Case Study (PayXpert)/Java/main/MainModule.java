// Updated MainModule.java with full 14 functionalities
package com.payxpert.main;

import com.payxpert.dao.*;
import com.payxpert.entity.*;
import com.payxpert.report.ReportGenerator;
import com.payxpert.exception.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static IEmployeeService employeeService = new EmployeeServiceImpl();
    private static IPayrollService payrollService = new PayrollServiceImpl();
    private static ITaxService taxService = new TaxServiceImpl();
    private static IFinancialRecordService financialRecordService = new FinancialRecordServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- PayXpert Payroll Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Remove Employee");
            System.out.println("5. Generate Payroll");
            System.out.println("6. View Payrolls for Employee");
            System.out.println("7. View Payroll Report (All)");
            System.out.println("8. Calculate Tax");
            System.out.println("9. View Tax Report (Year)");
            System.out.println("10. Add Financial Record");
            System.out.println("11. View Financial Records for Employee");
            System.out.println("12. View Financial Records for Date");
            System.out.println("13. Generate Payroll for All Employees");

            System.out.println("14. Exit");
            System.out.print("Select an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> addEmployee(scanner);
                    case 2 -> viewAllEmployees();
                    case 3 -> updateEmployee(scanner);
                    case 4 -> removeEmployee(scanner);
                    case 5 -> generatePayroll(scanner);
                    case 6 -> viewPayrollsForEmployee(scanner);
                    case 7 -> generatePayrollReport();
                    case 8 -> calculateTax(scanner);
                    case 9 -> generateTaxReport();
                    case 10 -> addFinancialRecord(scanner);
                    case 11 -> viewFinancialRecordsForEmployee(scanner);
                    case 12 -> viewFinancialRecordsForDate(scanner);
                    case 13 -> generatePayrollForAllEmployees();
                    case 14 -> exit = true;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void addEmployee(Scanner scanner) throws Exception {
        System.out.println("\n--- Add New Employee ---");
        System.out.print("Employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Date of Birth (yyyy-MM-dd): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Position: ");
        String position = scanner.nextLine();
        System.out.print("Joining Date (yyyy-MM-dd): ");
        LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Termination Date (yyyy-MM-dd) or leave blank: ");
        String termInput = scanner.nextLine();
        LocalDate terminationDate = termInput.isEmpty() ? null : LocalDate.parse(termInput);

        Employee employee = new Employee(employeeId, firstName, lastName, dob, gender, email, phone, address, position, joiningDate, terminationDate);
        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void viewAllEmployees() {
        System.out.println("\n--- Employee List ---");
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private static void updateEmployee(Scanner scanner) throws Exception {
        System.out.println("\n--- Update Employee ---");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());
        Employee emp = employeeService.getEmployeeById(empId);
        if (emp == null) throw new EmployeeNotFoundException("Employee not found.");

        System.out.print("New Phone Number: ");
        emp.setPhoneNumber(scanner.nextLine());
        System.out.print("New Address: ");
        emp.setAddress(scanner.nextLine());
        employeeService.updateEmployee(emp);
        System.out.println("Employee updated successfully.");
    }

    private static void removeEmployee(Scanner scanner) throws Exception {
        System.out.println("\n--- Remove Employee ---");
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());
        employeeService.removeEmployee(empId);
        System.out.println("Employee removed successfully.");
    }

    private static void generatePayroll(Scanner scanner) throws Exception {
        System.out.println("\n--- Generate Payroll ---");
        System.out.print("Employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.print("Pay Period Start Date (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Pay Period End Date (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Basic Salary: ");
        double basicSalary = Double.parseDouble(scanner.nextLine());
        System.out.print("Overtime Pay: ");
        double overtimePay = Double.parseDouble(scanner.nextLine());
        System.out.print("Deductions: ");
        double deductions = Double.parseDouble(scanner.nextLine());

        Payroll payroll = payrollService.generatePayroll(employeeId, startDate, endDate, basicSalary, overtimePay, deductions);
        System.out.println("Payroll generated: " + payroll);
    }

    private static void viewPayrollsForEmployee(Scanner scanner) throws Exception {
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());
        List<Payroll> payrolls = payrollService.getPayrollsForEmployee(empId);
        payrolls.forEach(System.out::println);
    }

    private static void generatePayrollReport() {
        System.out.println("\n--- Payroll Report ---");
        List<Payroll> payrolls = payrollService.getPayrollsForPeriod(LocalDate.of(2000,1,1), LocalDate.now().plusYears(1));
        ReportGenerator.generatePayrollReport(payrolls);
    }

    private static void calculateTax(Scanner scanner) throws Exception {
        System.out.println("\n--- Calculate Tax ---");
        System.out.print("Employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.print("Tax Year: ");
        int taxYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Taxable Income: ");
        double taxableIncome = Double.parseDouble(scanner.nextLine());

        Tax tax = taxService.calculateTax(employeeId, taxYear, taxableIncome);
        System.out.println("Tax calculated: " + tax);
    }





    private static void generateTaxReport() {
        System.out.println("\n--- Tax Report ---");
        List<Tax> taxes = taxService.getTaxesForYear(LocalDate.now().getYear());
        ReportGenerator.generateTaxReport(taxes);
    }
    private static void addFinancialRecord(Scanner scanner) throws Exception {
        System.out.println("--- Add Financial Record ---");
                System.out.print("Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Type (income/expense/tax): ");
        String type = scanner.nextLine();

        FinancialRecord record = financialRecordService.addFinancialRecord(empId, desc, amount, type);
        System.out.println("✅ Financial record added: " + record);
    }


    private static void viewFinancialRecordsForEmployee(Scanner scanner) throws Exception {
        System.out.print("Enter Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());
        List<FinancialRecord> records = financialRecordService.getFinancialRecordsForEmployee(empId);
        records.forEach(System.out::println);
    }

    private static void viewFinancialRecordsForDate(Scanner scanner) throws Exception {
        System.out.print("Enter Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        List<FinancialRecord> records = financialRecordService.getFinancialRecordsForDate(date);
        records.forEach(System.out::println);
    }

    private static void generatePayrollForAllEmployees() throws Exception {
        System.out.println(" --- Generating Payroll for All Employees ---");
        List<Employee> allEmployees = employeeService.getAllEmployees();

        for (Employee emp : allEmployees) {
            double basicSalary = 50000; // default/test value
            double overtimePay = 5000;  // test value
            double deductions = 3000;   // test value

            Payroll payroll = payrollService.generatePayroll(
                    emp.getEmployeeId(),
                    LocalDate.now().withDayOfMonth(1),
                    LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()),
                    basicSalary,
                    overtimePay,
                    deductions
            );
            System.out.println("✅ Payroll Generated: " + payroll);
        }
    }
}

