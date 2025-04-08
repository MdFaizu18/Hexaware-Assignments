package Banking.service;

import java.util.List;
import java.util.Date;

import Banking.bean.Account;
import Banking.bean.Transaction;

public interface ICustomerServiceProvider {
     float getAccountBalance(long accountNumber) throws Exception;
    float deposit(long accountNumber, float amount) throws Exception;
    float withdraw(long accountNumber, float amount) throws Exception;
    float transfer(long fromAccNo, long toAccNo, float amount) throws Exception;
    Account getAccountDetails(long accountNumber) throws Exception;
    List<Transaction> getTransactions(long accountNumber, Date from, Date to) throws Exception;
}
