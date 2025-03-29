package entity;

public class SavingsAccount extends Account{
    private static final double interestRate = 0.25;

    public SavingsAccount(int accountNumber,double balance){
        super(accountNumber,"Savings",balance);
    }
     
    @Override
    public void calculate_interest(){
        double interest = (balance*interestRate)/100;
        balance+=interest;
        System.out.println("Interest added:" + interest);
    }
}
