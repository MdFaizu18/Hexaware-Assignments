package entity;

public class CurrentAccount extends BankAccount{
    // additonal attribute for overdraft limit
    private static final double overDraftLimit = 2000.00;
    
    // constructor 
    public CurrentAccount(){
        super();
    }
    // parameterized constructor
    public CurrentAccount(int accountNumber,String customerName,double balance){
        super(accountNumber, customerName, balance);
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
        if(amount >0 && amount <= (balance + overDraftLimit)){
            balance-= amount;
            System.out.println("Withdrawn: $" + amount);
        }
        else{
            System.out.println("Invalid withdraw amount.");
        }
    }
    @Override
    public void calculateInterest(){
        System.out.println("Current accounts do not earn interest.");
    }
}
