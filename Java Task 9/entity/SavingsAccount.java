package entity;

public class SavingsAccount extends BankAccount {
   //  additional attribute for interest rate 
   private static final double interestRate = 3.5;

    // constructor
    public SavingsAccount(){
          super();
    }
   public SavingsAccount(int accountNumber, String customerName, double balance){
    super(accountNumber,customerName,balance);
   }

   @Override
    public void deposit(float amount){
        if(amount>0){
            balance+= amount;
            System.out.println("Deposited: $" + amount);
        }
        else{
            System.out.println("Invalid deposit amount.");
        }
    }
    @Override
    public void withdraw(float amount){
        if(amount>0 && amount<=balance){
            balance-= amount;
            System.out.println("Withdrawn: $" + amount);
        }
        else{
            System.out.println("Invalid withdraw amount.");
        }
    }
    @Override
    public void calculateInterest(){
        double interest = ( balance * interestRate)/100;
        System.out.println("Interest earned: $" + interest);
    }


}
