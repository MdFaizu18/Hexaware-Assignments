package bean;

public class CurrentAccount extends Account{
    private double overDraftLimit = 5000;

    public CurrentAccount(double balance, Customer customer) {
        super("Current", balance, customer);
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overDraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }
    
}
