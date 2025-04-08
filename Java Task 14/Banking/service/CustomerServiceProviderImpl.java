package Banking.service;

import Banking.bean.*;
import Banking.service.ICustomerServiceProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
    protected List<Account> accountList = new ArrayList<>();
    protected List<Transaction> transactionList = new ArrayList<>();

    public float getAccountBalance(long accNo) {
        return getAccount(accNo).getAccountBalance();
    }

    public float deposit(long accNo, float amount) {
        Account acc = getAccount(accNo);
        acc.setAccountBalance(acc.getAccountBalance() + amount);
        transactionList.add(new Transaction(acc, "Deposit", "Deposit", amount));
        return acc.getAccountBalance();
    }

    public float withdraw(long accNo, float amount) throws Exception {
        Account acc = getAccount(accNo);
        if (acc instanceof SavingsAccount && acc.getAccountBalance() - amount < 500)
            throw new Exception("Minimum balance required.");
        if (acc instanceof CurrentAccount) {
            float limit = acc.getAccountBalance() + ((CurrentAccount) acc).getOverdraftLimit();
            if (amount > limit) throw new Exception("Overdraft limit exceeded.");
        } else if (acc.getAccountBalance() < amount) {
            throw new Exception("Insufficient balance.");
        }
        acc.setAccountBalance(acc.getAccountBalance() - amount);
        transactionList.add(new Transaction(acc, "Withdraw", "Withdraw", amount));
        return acc.getAccountBalance();
    }

    public float transfer(long from, long to, float amount) throws Exception {
        withdraw(from, amount);
        deposit(to, amount);
        return getAccount(from).getAccountBalance();
    }

    public Account getAccountDetails(long accNo) {
        return getAccount(accNo);
    }

    public List<Transaction> getTransactions(long accNo, Date from, Date to) {
        return transactionList.stream()
                .filter(tx -> tx.getAccount().getAccountNumber() == accNo)
                .collect(Collectors.toList()); // Date range filtering can be added
    }

    protected Account getAccount(long accNo) {
        return accountList.stream().filter(a -> a.getAccountNumber() == accNo).findFirst().orElseThrow();
    }
}
