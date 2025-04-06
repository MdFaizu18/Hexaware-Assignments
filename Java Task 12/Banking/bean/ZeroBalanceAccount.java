package bean;

public class ZeroBalanceAccount extends Account{

    public ZeroBalanceAccount(Customer customer){
       super("ZeroBalance",0,customer);
    }

    @Override
    public void withdraw(double amount){
        if (balance - amount >= 0) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance! Minimum balance required: 500");
        }
    }
    
}
