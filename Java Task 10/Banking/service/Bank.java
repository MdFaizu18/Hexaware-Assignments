package service;

import entity.Account;
import entity.Customer;

public class Bank {
    private Account account; 

    public void createAccount(Customer customer, String accType, double balance) {
        account = new Account(customer, accType, balance);
        System.out.println("\nAccount Created Successfully!");
        account.printAccountDetails();
    }

    public void deposit(long accNo, double amount) {
        if (account != null && account.getAccountNumber() == accNo) {
            account.deposit(amount);
        } else {
            System.out.println(" Account not found.");
        }
    }

    public void withdraw(long accNo, double amount) {
        if (account != null && account.getAccountNumber() == accNo) {
            account.withdraw(amount);
        } else {
            System.out.println(" Account not found.");
        }
    }

    public void transfer(long fromAccNo, long toAccNo, double amount) {
        if (fromAccNo == toAccNo) {
            System.out.println("❌ Cannot transfer to same account");
            return;
        }
    
        if (account != null && account.getAccountNumber() == fromAccNo) {
            if (account.getBalance() >= amount) {
                if (account.withdraw(amount)) {
                    System.out.println("Transfer successful!");
                    System.out.printf(" Transferred: $%.2f from account %d%n", amount, fromAccNo);
                } else {
                    System.out.println(" Transfer failed");
                }
            } else {
                System.out.println(" Insufficient funds for transfer");
            }
        } else {
            System.out.println(" Source account not found");
        }
    }

    public void getAccountDetails(long accNo) {
        if (account != null && account.getAccountNumber() == accNo) {
            account.printAccountDetails();
        } else {
            System.out.println(" Account not found.");
        }
    }
}
