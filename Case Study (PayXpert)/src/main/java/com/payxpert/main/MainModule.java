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

    public static Scanner scanner = new Scanner(System.in);
    //helper methods
    private static int getIntegerInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== PayXpert Payroll Management System ===");
            System.out.println("1. Employee Management");
            System.out.println("2. Payroll Processing");
            System.out.println("3. Tax Management");
            System.out.println("4. Financial Records");
            System.out.println("5. Reports Generation");
            System.out.println("6. Exit");
            System.out.print("Select an option : ");

            try {
                int choice = getIntegerInput();
                switch (choice) {
                    case 1 -> employeeManagementSubmenu();
                    case 2 -> payrollProcessingSubmenu();
                    case 3 -> taxManagementSubmenu();
                    case 4 -> financialRecordsSubmenu();
                    case 5 -> reportGenerationSubmenu();
                    case 6 -> exit = true;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();

    }
    private static void employeeManagementSubmenu() throws Exception {
        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee Details");
            System.out.println("4. Update Employee");
            System.out.println("5. Remove Employee");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option : ");
            int choice = getIntegerInput();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewAllEmployees();
                case 3 -> getEmployeeById();
                case 4 -> updateEmployee();
                case 5 -> removeEmployee();
                case 6 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }
    private static void addEmployee() throws Exception {
        System.out.println("\n--- Add New Employee ---");
        System.out.print("Employee ID: ");
        int employeeId =getIntegerInput();
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
//            employees.forEach(System.out::println);
            for (Employee emp : employees) {
                System.out.println(emp);
            }

        }
    }
    private static void getEmployeeById() throws Exception {
        System.out.println("\n--- Get Employee By ID");
        System.out.print("Enter the Employee ID: ");
        int empId = getIntegerInput();
        Employee emp = employeeService.getEmployeeById(empId);
        System.out.println(emp);

    }
    private static void updateEmployee() throws Exception {
        System.out.println("\n--- Update Employee ---");
        System.out.print("Enter Employee ID: ");
        int empId = getIntegerInput();
        Employee emp = employeeService.getEmployeeById(empId);
        if (emp == null) throw new EmployeeNotFoundException("Employee not found.");

        System.out.print("New Phone Number: ");
        emp.setPhoneNumber(scanner.nextLine());
        System.out.print("New Address: ");
        emp.setAddress(scanner.nextLine());
        employeeService.updateEmployee(emp);
        System.out.println("Employee updated successfully.");
    }
    private static void removeEmployee() throws Exception {
        System.out.println("\n--- Remove Employee ---");
        System.out.print("Enter Employee ID: ");
        int empId = getIntegerInput();
        employeeService.removeEmployee(empId);
        System.out.println("Employee removed successfully.");
    }


    private static void payrollProcessingSubmenu() throws Exception {
        while (true) {
            System.out.println("\n=== Payroll Processing ===");
            System.out.println("1. Generate Payroll");
            System.out.println("2. Get Payroll by ID");
            System.out.println("3. Get Payrolls for Employee");
            System.out.println("4. Get Payrolls for Period");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntegerInput();

            switch (choice) {
                case 1 -> generatePayroll();
                case 2 -> getPayrollById();
                case 3 -> getPayrollsForEmployee();
                case 4 -> getPayrollsForPeriod();
                case 5 -> {return;}
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void generatePayroll() throws Exception {
        System.out.println("\n--- Generate Payroll ---");
        System.out.print("Employee ID: ");
        int employeeId = getIntegerInput();
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
    private static void getPayrollById() throws Exception{
        System.out.print("Enter the Payroll ID: ");
        int payrollId = getIntegerInput();
        Payroll payroll = payrollService.getPayrollById(payrollId);
        System.out.println(payroll);
    }
    private static void getPayrollsForEmployee() throws Exception {
        System.out.print("Enter Employee ID: ");
        int empId = getIntegerInput();
        List<Payroll> payrolls = payrollService.getPayrollsForEmployee(empId);
        payrolls.forEach(System.out::println);
    }
    private static void getPayrollsForPeriod() {
        System.out.println("\n--- Payroll Report ---");
        System.out.println("Enter the Start Date(YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter the End Date(YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        List<Payroll> payrolls = payrollService.getPayrollsForPeriod(startDate,endDate);
        ReportGenerator.generatePayrollReport(payrolls);
    }


    // Submenu for Tax Calculation
    private static void taxManagementSubmenu() throws Exception {
        while (true) {
            System.out.println("\n=== Tax Calculation ===");
            System.out.println("1. Calculate Tax for Employee");
            System.out.println("2. Get Tax by ID");
            System.out.println("3. Get Taxes for Employee");
            System.out.println("4. Get Taxes for Year");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntegerInput();

            switch (choice) {
                case 1 -> calculateTax();
                case 2 -> getTaxById();
                case 3 -> getTaxesForEmployee();
                case 4 -> getTaxesForYear();
                case 5 -> {return;}
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void calculateTax() throws Exception {
        System.out.println("\n--- Calculate Tax ---");
        System.out.print("Employee ID: ");
        int employeeId = getIntegerInput();
        System.out.print("Tax Year: ");
        int taxYear = getIntegerInput();
        System.out.print("Taxable Income: ");
        double taxableIncome = Double.parseDouble(scanner.nextLine());

        Tax tax = taxService.calculateTax(employeeId, taxYear, taxableIncome);
        System.out.println("Tax calculated: " + tax);
    }
    private static void getTaxById() throws Exception{
        System.out.println("\n--- Get Tax By ID ---");
        System.out.print("Enter Tax ID : ");
        int taxId = getIntegerInput();

        Tax tax = taxService.getTaxById(taxId);
        System.out.println(tax);
    }
    private static void getTaxesForEmployee() throws Exception{
        System.out.println("\n--- Get Taxes For Employee ---");
        System.out.print("Enter the Employee ID : ");
        int empId = getIntegerInput();

        List<Tax> tax = taxService.getTaxesForEmployee(empId);
        for(Tax taxes: tax){
            System.out.println(taxes);
        }
    }
    private static void getTaxesForYear() throws Exception{
        System.out.println("\n--- Get Taxes For Year ---");
        System.out.print("Enter the Year : ");
        int taxYear =getIntegerInput();

        List<Tax> tax = taxService.getTaxesForYear(taxYear);
        for(Tax taxes : tax){
            System.out.println(taxes);
        }
    }


    private static void financialRecordsSubmenu() throws Exception {
        while (true) {
            System.out.println("\n=== Financial Records ===");
            System.out.println("1. Add Financial Record");
            System.out.println("2. Get Financial Record by ID");
            System.out.println("3. Get Records for Employee");
            System.out.println("4. Get Records by Date");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntegerInput();

            switch (choice) {
                case 1 -> addFinancialRecord();
                case 2 -> getFinancialRecordById();
                case 3 -> getFinancialRecordsForEmployee();
                case 4 -> getFinancialRecordsForDate();
                case 5 -> { return ;}
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addFinancialRecord() throws Exception {
        System.out.println("--- Add Financial Record ---");
        System.out.print("Employee ID: ");
        int empId = getIntegerInput();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Type (income/expense/tax): ");
        String type = scanner.nextLine();

        FinancialRecord record = financialRecordService.addFinancialRecord(empId, desc, amount, type);
        System.out.println(" Financial record added: " + record);
    }
    private static void getFinancialRecordById()throws Exception{
        System.out.println("\n--- Get Financial Record By ID ---");
        System.out.print("Enter the Record ID : ");
        int recordId = getIntegerInput();

        FinancialRecord record = financialRecordService.getFinancialRecordById(recordId);
        System.out.println(record);

    }
    private static void getFinancialRecordsForEmployee() throws Exception {
        System.out.print("Enter Employee ID: ");
        int empId = getIntegerInput();
        List<FinancialRecord> records = financialRecordService.getFinancialRecordsForEmployee(empId);
        for(FinancialRecord fr : records){
            System.out.println(fr);
        }
    }
    private static void getFinancialRecordsForDate() throws Exception {
        System.out.print("Enter Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        List<FinancialRecord> records = financialRecordService.getFinancialRecordsForDate(date);
        records.forEach(System.out::println);
    }

    private static void reportGenerationSubmenu() {
        ReportGenerator reportGenerator = new ReportGenerator();
        while (true) {
            System.out.println("\n=== Financial Reports ===");
            System.out.println("1. Generate Income Statement");
            System.out.println("2. Generate Tax Summary");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntegerInput();

            switch (choice) {
                case 1 -> generateIncomeStatement(reportGenerator);
                case 2 -> generateTaxSummary(reportGenerator);
                case 3 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private static void generateIncomeStatement(ReportGenerator reportGenerator) {
        try {
            System.out.println("\n--- Generate Income Statement ---");
            System.out.print("Enter the date (yyyy-MM-dd): ");
            LocalDate recordDate = LocalDate.parse(scanner.nextLine());


            reportGenerator.generateIncomeStatement(recordDate);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void generateTaxSummary( ReportGenerator reportGenerator) {
        try {
            System.out.println("\n--- Generate Tax Summary ---");
            System.out.print("Enter tax year (YYYY): ");
            int taxYear = getIntegerInput();

            reportGenerator.generateTaxSummary(taxYear);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

