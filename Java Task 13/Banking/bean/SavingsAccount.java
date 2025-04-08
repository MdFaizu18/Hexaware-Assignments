package bean;

public class SavingsAccount extends Account {
    private final double interestRate = 2.5;

    public SavingsAccount(double balance, Customer customer) {
        super("Savings", Math.max(balance, 500), customer);
    }
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= 500) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance! Minimum balance required: 500");
        }
    }

    public double calculate_interest() {
        double interest = (balance * interestRate) / 100;
        balance += interest;
        System.out.println("Interest added: " + interest);
        return interest;
    }
    
}
