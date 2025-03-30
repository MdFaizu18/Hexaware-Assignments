package entity;

public abstract class BankAccount {
    // attributes 
    private int accountNumber;
    private String customerName;
    protected double balance;

    // default constructor 
    public BankAccount(){
        this.accountNumber = 00000000;
        this.customerName = "Unknown";
        this.balance = 0.0;
    }

    // parameterized constructor
    public BankAccount(int accountNumber,String customerName,double balance){
        this.accountNumber = accountNumber;
        this.customerName=customerName;
        this.balance=balance;
    }

    // getter methods
    public int getAccountNumber(){
        return accountNumber;
    }
    public String getCustomerName(){
        return customerName;
    }
    public double getBalance(){
        return balance;
    }

    // setter methods
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    // Print account details
    public void printAccountInfo() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: $" + balance);
    }

    // abstract method 
    public abstract void  deposit(float amount);
    public abstract void withdraw(float amount);
    public abstract void calculateInterest();
}
