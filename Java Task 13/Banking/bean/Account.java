package bean;

public abstract class Account {
    // attributes
    protected long accountNumber;
    protected String accountType;
    protected double balance;
    protected Customer customer;
    protected static long lastAccNo = 1000;

    // constructor
    public Account(String accountType, double balance, Customer customer) {
        this.accountNumber = ++lastAccNo;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    // getters 
    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    // declaration of the abstract methods
    public abstract void withdraw(double amount);

    // deposit method 
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
        customer.displayCustomerInfo();
    }

}
