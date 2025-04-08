package Banking.service;

import Banking.bean.Account;
import Banking.bean.Customer;

import java.util.List;

public interface IBankServiceProvider {
    Account createAccount(Customer customer, String accType, float balance) throws Exception;
    List<Account> listAccounts() throws Exception;
    Account getAccountDetails(long accNo) throws Exception;
    void calculateInterest();
}
