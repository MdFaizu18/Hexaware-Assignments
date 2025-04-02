package service;

public interface ICustomerServiceProvider {

    // Method signatures for customer-related operations
    double get_account_balance(long accountNumber);
    void deposit(long accountNumber,float amount);
    void withdraw(long accountNumber,float amount);
    void transfer(long fromAccountNumber,long toAccountNumber,float amount);
    void getAccountDetails(long accountNumber);
}
