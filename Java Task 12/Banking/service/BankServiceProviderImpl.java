package service;

import bean.Account;
import bean.SavingsAccount;
import bean.CurrentAccount;
import bean.ZeroBalanceAccount;
import bean.Customer;

import java.util.ArrayList;
import java.util.List;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {
    private List<Account> accountList = new ArrayList<>();
    private String branchName;
    private String branchAddress;

    public void addAccount(Account account) {
        accountList.add(account);
        System.out.println("Account added successfully: " + account.getAccountNumber());
    }

    // Constructor
    public BankServiceProviderImpl(String branchName, String branchAddress) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    // Implementing create_account method
    @Override
    public void create_account(Customer customer, long accNo, String accType, float balance) {
    Account account;
    
    switch (accType.toLowerCase()) {
        case "savings":
            if (balance < 500) {
                System.out.println("Minimum balance for a Savings Account is 500.");
                return;
            }
            account = new SavingsAccount(balance, customer);
            break;
            
        case "current":
            account = new CurrentAccount(balance, customer); 
            break;
            
        case "zerobalance":
            account = new ZeroBalanceAccount(customer);
            break;
            
        default:
            System.out.println("Invalid account type. Choose Savings, Current, or ZeroBalance.");
            return;
    }
    
    accountList.add(account);
    System.out.println("\n=== Account Created Successfully ===");
    System.out.println("Account Number: " + accNo);
    System.out.println("Account Type: " + accType.toUpperCase());
    System.out.println("Initial Balance: $" + balance);
    System.out.println("\nCustomer Details:");
    System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
    System.out.println("Email: " + customer.getEmail());
    System.out.println("Phone: " + customer.getPhone());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("================================");
}

    // Implementing listAccounts method
    @Override
    public Account[] listAccounts() {
        return accountList.toArray(new Account[0]);
    }

    // Implementing calculateInterest method
    @Override
    public void calculateInterest() {
        for (Account account : accountList) {
            if (account instanceof SavingsAccount) {
                SavingsAccount savings = (SavingsAccount) account;
                double interest = savings.getBalance() * (savings.calculate_interest() / 100);
                savings.deposit(interest);
                System.out.println("Interest of " + interest + " added to Savings Account: " + savings.getAccountNumber());
            }
        }
    }
}
