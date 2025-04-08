package Banking.service;

import Banking.bean.*;
import Banking.service.IBankServiceProvider;

import java.util.List;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {
    private String branchName;
    private String branchAddress;

    public BankServiceProviderImpl(String name, String address) {
        this.branchName = name;
        this.branchAddress = address;
    }

    public Account createAccount(Customer customer, String accType, float balance) {
        Account acc = null;
        switch (accType) {
            case "Savings" -> acc = new SavingsAccount(balance, customer, 3.5f);
            case "Current" -> acc = new CurrentAccount(balance, customer, 10000);
            case "ZeroBalance" -> acc = new ZeroBalanceAccount(customer);
            default -> throw new IllegalArgumentException("Invalid Account Type");
        }
        accountList.add(acc);
        return acc;
    }

    public List<Account> listAccounts() {
        return accountList;
    }

    public Account getAccountDetails(long accNo) {
        return getAccount(accNo);
    }

    public void calculateInterest() {
        for (Account acc : accountList) {
            float interest = acc.calculateInterest();
            acc.accountBalance += interest;
        }
    }
}
