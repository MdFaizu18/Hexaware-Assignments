package com.hexaware;
import com.hexaware.dao.*;
import com.hexaware.entity.*;
import com.hexaware.exception.InvalidLoanException;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static ILoanRepository loanRepository;
    static {
        try {
            loanRepository = new ILoanRepositoryImpl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("=== Loan Management System ===");
        while (!exit) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Apply for a Loan");
            System.out.println("2. View All Loans");
            System.out.println("3. View Specific Loan");
            System.out.println("4. Loan Repayment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntegerInput();

            switch (choice) {
                case 1:
                    applyForLoan();
                    break;
                case 2:
                    getAllLoans();
                    break;
                case 3:
                    getLoanById();
                    break;
                case 4:
                    loanRepayment();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using Loan Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void applyForLoan() {
        System.out.println("\n=== Apply for a Loan ===");
        System.out.println("Select Loan Type:");
        System.out.println("1. Home Loan");
        System.out.println("2. Car Loan");
        System.out.print("Enter choice (1-2): ");
        int loanTypeChoice =  getIntegerInput();

        if (loanTypeChoice != 1 && loanTypeChoice != 2) {
            System.out.println("Invalid choice. Returning to main menu.");
            return;
        }

        // Get loan ID
        System.out.print("Enter Loan ID: ");
        int loanId =  getIntegerInput();
        // Get customer details
        Customer customer = createCustomer();
        // Get loan details
        System.out.print("Enter Principal Amount: ");
        double principalAmount = getDoubleInput();
        System.out.print("Enter Interest Rate (% per annum): ");
        double interestRate = getDoubleInput();
        System.out.print("Enter Loan Term (months): ");
        int loanTerm =  getIntegerInput();

        Loan loan;

        if (loanTypeChoice == 1) {
            // Home Loan specific details
            System.out.print("Enter Property Address: ");
            String propertyAddress = scanner.nextLine();
            System.out.print("Enter Property Value: ");
            int propertyValue = getIntegerInput();

            loan = new HomeLoan(loanId, customer, principalAmount, interestRate, loanTerm, "Pending", propertyAddress, propertyValue);
        } else {
            // Car Loan specific details
            System.out.print("Enter Car Model: ");
            String carModel = scanner.nextLine();
            System.out.print("Enter Car Value: ");
            int carValue =  getIntegerInput();

            loan = new CarLoan(loanId, customer, principalAmount, interestRate, loanTerm, "Pending", carModel, carValue);
        }
        double interest = loanRepository.calculateInterest(principalAmount, interestRate, loanTerm);
        double emi = loanRepository.calculateEMI(principalAmount, interestRate, loanTerm);

        System.out.println("\nLoan Summary:");
        System.out.println("Principal Amount: $" + principalAmount);
        System.out.println("Interest Rate: " + interestRate + "% per annum");
        System.out.println("Loan Term: " + loanTerm + " months");
        System.out.println("Total Interest: $" + String.format("%.2f", interest));
        System.out.println("Monthly EMI: $" + String.format("%.2f", emi));

        // Apply for the loan
        boolean success = loanRepository.applyLoan(loan);

        if (success) {
            System.out.println("Loan application submitted successfully!");
        } else {
            System.out.println("Loan application failed.");
        }
    }

    private static Customer createCustomer() {
        System.out.println("\n=== Enter Customer Details ===");

        System.out.print("Enter Customer ID: ");
        int customerId =  getIntegerInput();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Credit Score: ");
        int creditScore =  getIntegerInput();

        return new Customer(customerId, name, email, phone, address, creditScore);
    }

    private static void loanRepayment() {
        System.out.println("\n=== Loan Repayment ===");
        System.out.print("Enter Loan ID: ");
        int loanId =  getIntegerInput();

        try {
            Loan loan = loanRepository.getLoanById(loanId);
            double emi = loanRepository.calculateEMI(loanId);

            System.out.println("Loan Details:");
            System.out.println(loan);
            System.out.println("Monthly EMI: $" + String.format("%.2f", emi));

            System.out.print("Enter payment amount: $");
            double amount = getDoubleInput();

            boolean success = loanRepository.loanRepayment(loanId, amount);

            if (!success) {
                System.out.println("Repayment failed.");
            }
        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void getAllLoans() {
        System.out.println("\n=== All Loans ===");
        List<Loan> loans = loanRepository.getAllLoan();

        if (loans.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            for (Loan loan : loans) {
                System.out.println(loan);
                System.out.println("-----------------------------------");
            }
            System.out.println("Total number of loans: " + loans.size());
        }
    }

    private static void getLoanById() {
        System.out.println("\n=== View Specific Loan ===");
        System.out.print("Enter Loan ID: ");
        int loanId =  getIntegerInput();

        try {
            Loan loan = loanRepository.getLoanById(loanId);
            System.out.println("Loan Details:");
            System.out.println(loan);

            double interest = loanRepository.calculateInterest(loanId);
            double emi = loanRepository.calculateEMI(loanId);

            System.out.println("\nAdditional Information:");
            System.out.println("Total Interest: $" + String.format("%.2f", interest));
            System.out.println("Monthly EMI: $" + String.format("%.2f", emi));

        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Helper method to get  input
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
    private static double getDoubleInput() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}