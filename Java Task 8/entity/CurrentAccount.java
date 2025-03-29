package entity;

public class CurrentAccount extends Account{
    private static final double overdraftLimit = 5000.00;

    public CurrentAccount(int accountNumber,double balance){
        super(accountNumber,"Current",balance);
    }

    @Override
    public void withdraw(double amount){
       if(balance - amount >= -overdraftLimit){
        balance-= amount;
       }
       else{
        System.out.println("Exceeded overdraft limit");
       }
    }

    @Override
    public void calculate_interest(){
        System.out.println("No interest for current Account");
    }
}
