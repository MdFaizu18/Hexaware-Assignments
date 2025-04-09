package com.hexaware.entity;

public class CarLoan extends Loan{
    // attributes
    private String carModel;
    private int carValue;

    // default constructor
    public CarLoan(){
        super();
        setLoanType("CarLoan");
    }

    // parameterized constructor
    public CarLoan(int loanId, Customer customer, double principalAmount, double interestRate,int loanTerm, String loanStatus, String carModel, int carValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "CarLoan", loanStatus);
        this.carModel = carModel;
        this.carValue = carValue;
    }

    // getters and setters

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarValue() {
        return carValue;
    }

    public void setCarValue(int carValue) {
        this.carValue = carValue;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n--- Car Loan Details ---" +
                "\nCar Model       : " + carModel +
                "\nCar Value       : ₹" + carValue +
                "\n--------------------------";
    }

}
