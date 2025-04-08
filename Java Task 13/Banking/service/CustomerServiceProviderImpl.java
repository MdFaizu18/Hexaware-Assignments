package service;

import bean.Account;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
    protected Account[] accounts = new Account[100]; 

        // Method to add accounts
        public void addAccount(Account account) {
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i] == null) {
                    accounts[i] = account;
                    System.out.println("Account added successfully: " + account.getAccountNumber());
                    return;
                }
            }
            System.out.println("Account storage is full!");
        }
    

    public double get_account_balance(long accountNumber) {
        for (Account acc : accounts) {
            if (acc != null && acc.getAccountNumber() == accountNumber) {
                return acc.getBalance();
            }
        }
        System.out.println("Account not found!");
        return 0;
    }

  
    public void deposit(long accountNumber, float amount) {
        for (Account acc : accounts) {  // Changed from accounts to accountList
            if (acc != null && acc.getAccountNumber() == accountNumber) {
                acc.deposit(amount);
                System.out.println("Successfully deposited $" + amount);
                System.out.println("New balance: $" + acc.getBalance());
                return;
            }
        }
        System.out.println("Account not found!");
    }

   
    public void withdraw(long accountNumber, float amount) {
        for (Account acc : accounts) {
            if (acc != null && acc.getAccountNumber() == accountNumber) {
                acc.withdraw(amount);
                return;
            }
        }
        System.out.println("Account not found!");
    }


    public void transfer(long fromAccountNumber, long toAccountNumber, float amount) {
        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);
    }

    public void getAccountDetails(long accountNumber) {
        for (Account acc : accounts) {
            if (acc != null && acc.getAccountNumber() == accountNumber) {
                acc.displayAccountInfo();
                return;
            }
        }
        System.out.println("Account not found!");
    }
}
