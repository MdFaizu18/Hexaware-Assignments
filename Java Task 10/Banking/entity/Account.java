package entity;

public class Account {
    private static long accountCounter = 1001; 
    private long accountNumber;
    private String accountType;
    private double balance;
    private Customer customer;

    public Account(Customer customer, String accountType, double balance) {
        this.accountNumber = accountCounter++;
        this.customer = customer;
        this.accountType = accountType;
        this.balance = balance;
    }

    public long getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public Customer getCustomer() { return customer; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited: $" + amount + " | New Balance: $" + balance);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("✅ Withdrawn: $" + amount + " | Remaining Balance: $" + balance);
            return true;
        } else {
            System.out.println("❌ Insufficient funds or invalid amount.");
            return false;
        }
    }

    public void printAccountDetails() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Type: " + accountType);
        System.out.println("Balance: $" + balance);
        customer.printCustomerDetails();
    }
}
