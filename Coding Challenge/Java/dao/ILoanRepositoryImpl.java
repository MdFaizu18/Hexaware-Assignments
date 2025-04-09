package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.exception.InvalidLoanException;
import com.hexaware.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ILoanRepositoryImpl implements ILoanRepository {
    private Connection connection;
    public ILoanRepositoryImpl() throws Exception {
        this.connection = DatabaseContext.getConnection();
    }

    @Override
    public boolean applyLoan(Loan loan) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Loan Application Details:");
        System.out.println(loan);
        System.out.print("Confirm loan application (Yes/No): ");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("Yes")) {
            System.out.println("Loan application cancelled.");
            return false;
        }
        try {
            //ensure the customer exists in the database
            boolean customerExists = checkCustomerExists(loan.getCustomer().getCustomerId());
            if (!customerExists) {
                // Insert customer if not exists
                insertCustomer(loan.getCustomer());
            }

            // Now insert the loan
            String sql = "INSERT INTO Loan (loanId, customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, loan.getLoanId());
                pstmt.setInt(2, loan.getCustomer().getCustomerId());
                pstmt.setDouble(3, loan.getPrincipalAmount());
                pstmt.setDouble(4, loan.getInterestRate());
                pstmt.setInt(5, loan.getLoanTerm());
                pstmt.setString(6, loan.getLoanType());
                pstmt.setString(7, "Pending"); // Initially set to pending

                int rowsAffected = pstmt.executeUpdate();

                // If the loan is a HomeLoan, insert into HomeLoan table
                if (loan instanceof HomeLoan) {
                    HomeLoan homeLoan = (HomeLoan) loan;
                    String homeLoanSql = "INSERT INTO HomeLoan (loanId, propertyAddress, propertyValue) VALUES (?, ?, ?)";

                    try (PreparedStatement homePstmt = connection.prepareStatement(homeLoanSql)) {
                        homePstmt.setInt(1, homeLoan.getLoanId());
                        homePstmt.setString(2, homeLoan.getPropertyAddress());
                        homePstmt.setInt(3, homeLoan.getPropertyValue());
                        homePstmt.executeUpdate();
                    }
                }

                // If the loan is a CarLoan, insert into CarLoan table
                if (loan instanceof CarLoan) {
                    CarLoan carLoan = (CarLoan) loan;
                    String carLoanSql = "INSERT INTO CarLoan (loanId, carModel, carValue) VALUES (?, ?, ?)";

                    try (PreparedStatement carPstmt = connection.prepareStatement(carLoanSql)) {
                        carPstmt.setInt(1, carLoan.getLoanId());
                        carPstmt.setString(2, carLoan.getCarModel());
                        carPstmt.setInt(3, carLoan.getCarValue());
                        carPstmt.executeUpdate();
                    }
                }

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error applying for loan: " + e.getMessage());
            return false;
        }
    }

    private boolean checkCustomerExists(int customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Customer WHERE customerId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (customerId, name, emailAddress, phoneNumber, address, creditScore) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getEmailAddress());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setString(5, customer.getAddress());
            pstmt.setInt(6, customer.getCreditScore());
            pstmt.executeUpdate();
        }
    }

    @Override
    public double calculateInterest(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        return calculateInterest(loan.getPrincipalAmount(), loan.getInterestRate(), loan.getLoanTerm());
    }

    @Override
    public double calculateInterest(double principalAmount, double interestRate, int loanTerm) {
        return (principalAmount * interestRate * loanTerm) / 12;
    }

    @Override
    public boolean loanStatus(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        Customer customer = loan.getCustomer();

        // Check credit score - if above 650, loan is approved
        boolean isApproved = customer.getCreditScore() > 650;
        String status = isApproved ? "Approved" : "Rejected";

        // Update loan status in database
        String sql = "UPDATE Loan SET loanStatus = ? WHERE loanId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, loanId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Loan status updated to " + status);
                return isApproved;
            } else {
                throw new InvalidLoanException("Failed to update loan status.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating loan status: " + e.getMessage());
            throw new InvalidLoanException("Database error while updating loan status.");
        }
    }

    @Override
    public double calculateEMI(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        return calculateEMI(loan.getPrincipalAmount(), loan.getInterestRate(), loan.getLoanTerm());
    }

    @Override
    public double calculateEMI(double principalAmount, double interestRate, int loanTerm) {
        double monthlyRate = interestRate / (12 * 100);

        // EMI = [P * R * (1+R)^N] / [(1+R)^N-1]
        double emi = (principalAmount * monthlyRate * Math.pow(1 + monthlyRate, loanTerm)) / (Math.pow(1 + monthlyRate, loanTerm) - 1);
        return emi;
    }

    @Override
    public boolean loanRepayment(int loanId, double amount) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        double emiAmount = calculateEMI(loanId);

        // Check if amount is less than a single EMI
        if (amount < emiAmount) {
            System.out.println("Payment rejected: Amount is less than a single EMI payment.");
            return false;
        }

        // Calculate how many EMIs can be paid
        int noOfEmis = (int) (amount / emiAmount);

        // Calculate remaining principal after payment
        double totalPayment = noOfEmis * emiAmount;
        double remainingLoanAmount = loan.getPrincipalAmount() - totalPayment;

        // Update the loan principal amount in the database
        String sql = "UPDATE Loan SET principalAmount = ? WHERE loanId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, Math.max(0, remainingLoanAmount));
            pstmt.setInt(2, loanId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Payment of " + totalPayment + " processed. " + noOfEmis + " EMI(s) paid.");
                if (remainingLoanAmount <= 0) {
                    System.out.println("Loan fully repaid!");
                } else {
                    System.out.println("Remaining loan amount: " + remainingLoanAmount);
                }
                return true;
            } else {
                throw new InvalidLoanException("Failed to update loan after repayment.");
            }
        } catch (SQLException e) {
            System.out.println("Error processing repayment: " + e.getMessage());
            throw new InvalidLoanException("Database error while processing repayment.");
        }
    }

    @Override
    public List<Loan> getAllLoan() {
        List<Loan> loans = new ArrayList<>();

        String sql = "SELECT l.*, c.* FROM Loan l JOIN Customer c ON l.customerId = c.customerId";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Create Customer
                Customer customer = createCustomerFromResultSet(rs, "c.");

                // Get loan data
                int loanId = rs.getInt("l.loanId");
                double principalAmount = rs.getDouble("l.principalAmount");
                double interestRate = rs.getDouble("l.interestRate");
                int loanTerm = rs.getInt("l.loanTerm");
                String loanType = rs.getString("l.loanType");
                String loanStatus = rs.getString("l.loanStatus");

                // Create loan based on type and add to list
                Loan loan = createSpecificLoan(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
                if (loan != null) {
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving loans: " + e.getMessage());
        }

        return loans;
    }

    @Override
    public Loan getLoanById(int loanId) throws InvalidLoanException {
        String sql = "SELECT l.*, c.* FROM Loan l JOIN Customer c ON l.customerId = c.customerId WHERE l.loanId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, loanId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Create Customer
                    Customer customer = createCustomerFromResultSet(rs, "c.");

                    // Get loan data
                    double principalAmount = rs.getDouble("l.principalAmount");
                    double interestRate = rs.getDouble("l.interestRate");
                    int loanTerm = rs.getInt("l.loanTerm");
                    String loanType = rs.getString("l.loanType");
                    String loanStatus = rs.getString("l.loanStatus");

                    // Create loan based on type
                    Loan loan = createSpecificLoan(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
                    if (loan != null) {
                        return loan;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving loan: " + e.getMessage());
            throw new InvalidLoanException("Database error while retrieving loan.");
        }

        throw new InvalidLoanException("Loan with ID " + loanId + " not found.");
    }

    // Helper method to create Customer object from ResultSet
    private Customer createCustomerFromResultSet(ResultSet rs, String prefix) throws SQLException {
        return new Customer(
                rs.getInt(prefix + "customerId"),
                rs.getString(prefix + "name"),
                rs.getString(prefix + "emailAddress"),
                rs.getString(prefix + "phoneNumber"),
                rs.getString(prefix + "address"),
                rs.getInt(prefix + "creditScore")
        );
    }

    // Helper method to create specific loan type based on loanType
    private Loan createSpecificLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanType, String loanStatus) throws SQLException {
        if ("HomeLoan".equals(loanType)) {
            // Get home loan specific details
            String homeLoanSql = "SELECT * FROM HomeLoan WHERE loanId = ?";
            try (PreparedStatement homePstmt = connection.prepareStatement(homeLoanSql)) {
                homePstmt.setInt(1, loanId);
                try (ResultSet homeRs = homePstmt.executeQuery()) {
                    if (homeRs.next()) {
                        return new HomeLoan(loanId, customer, principalAmount, interestRate, loanTerm, loanStatus, homeRs.getString("propertyAddress"), homeRs.getInt("propertyValue")
                        );
                    }
                }
            }
        } else if ("CarLoan".equals(loanType)) {
            // Get car loan specific details
            String carLoanSql = "SELECT * FROM CarLoan WHERE loanId = ?";
            try (PreparedStatement carPstmt = connection.prepareStatement(carLoanSql)) {
                carPstmt.setInt(1, loanId);
                try (ResultSet carRs = carPstmt.executeQuery()) {
                    if (carRs.next()) {
                        return new CarLoan(loanId, customer, principalAmount, interestRate, loanTerm, loanStatus, carRs.getString("carModel"), carRs.getInt("carValue")
                        );
                    }
                }
            }
        } else {
            return new Loan(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        }
        return null;
    }
}