CREATE DATABASE loanmanagement;
use loanmanagement;

-- Create Customer table
CREATE TABLE Customer (
    customerId INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    emailAddress VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    creditScore INT NOT NULL
);
-- Create Loan table
CREATE TABLE Loan (
    loanId INT PRIMARY KEY,
    customerId INT NOT NULL,
    principalAmount DOUBLE NOT NULL,
    interestRate DOUBLE NOT NULL,
    loanTerm INT NOT NULL,
    loanType VARCHAR(50) NOT NULL,
    loanStatus VARCHAR(20) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);
-- Create HomeLoan table
CREATE TABLE HomeLoan (
    loanId INT PRIMARY KEY,
    propertyAddress VARCHAR(255) NOT NULL,
    propertyValue INT NOT NULL,
    FOREIGN KEY (loanId) REFERENCES Loan(loanId)
);
-- Create CarLoan table
CREATE TABLE CarLoan (
    loanId INT PRIMARY KEY,
    carModel VARCHAR(100) NOT NULL,
    carValue INT NOT NULL,
    FOREIGN KEY (loanId) REFERENCES Loan(loanId)
    );
select * from customer;
select * from loan;
select * from homeloan;
select * from customer;
