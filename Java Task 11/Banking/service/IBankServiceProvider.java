package service;
import bean.Account;
import bean.Customer;

public interface IBankServiceProvider {

    // Method to create a new account
    void create_account(Customer customer,long accountNumber,String accountType,float balance);
    Account[] listAccounts();
    void calculateInterest();
}
