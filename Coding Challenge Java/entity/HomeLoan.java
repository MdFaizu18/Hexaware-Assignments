package com.hexaware.entity;

public class HomeLoan extends Loan{
    // attributes for HomeLoan
    private String propertyAddress;
    private int propertyValue;

    // default constructor
    public HomeLoan(){
        super();
        setLoanType("HomeLoan");
    }

    // parameterized constructor
    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanStatus, String propertyAddress, int propertyValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "HomeLoan", loanStatus);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    //getters and setters


    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n--- Home Loan Details ---" +
                "\nProperty Address : " + propertyAddress +
                "\nProperty Value   : ₹" + propertyValue +
                "\n--------------------------";
    }

}
