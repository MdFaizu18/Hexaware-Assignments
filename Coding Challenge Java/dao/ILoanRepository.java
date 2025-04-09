package com.hexaware.dao;

import com.hexaware.entity.Loan;
import com.hexaware.exception.InvalidLoanException;

import java.util.List;

public interface ILoanRepository {
    boolean applyLoan(Loan loan);
    double calculateInterest(int loanId) throws InvalidLoanException;
    double calculateInterest(double principalAmount,double interestRate,int loanTerm);
    boolean loanStatus(int loanId) throws InvalidLoanException;
    double calculateEMI(int loanId) throws InvalidLoanException;
    double calculateEMI(double principalAmount,double interestRate,int loanTerm);
    boolean loanRepayment(int loanId, double amount) throws InvalidLoanException;
    List<Loan> getAllLoan();
    Loan getLoanById(int loanId) throws InvalidLoanException;
}
