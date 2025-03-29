package entity;

public class Account{
    // Attributes
    private int accountNumber;
    private String accountType;
    protected double balance;
 
    // default constructor
    public Account(){
        this.accountNumber =0;
        this.accountType = "Unknown";
        this.balance = 0.0;
    }
    // parameterized constructor
    public Account(int accountNumber,String accountType,double balance){
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    // getter methods
    public int getAccountNumber() {
        return accountNumber;
    }
    public String getAccountType(){
        return accountType;
    }
    public double getBalance(){
        return balance;
    }

    // setter methods
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }


    // method to deposit amount
    public void deposit (float amount){
        if(amount >0){
            this.balance+=amount;
            System.out.println("Deposited: "+amount);
        }
        else{
            System.out.println("Deposit amount must be greater than 0.");
        }
    }
    public void deposit (int amount){
        if(amount>0){
            this.balance+=amount;
            System.out.println("Deposited: "+amount);
        }
        else{
            System.out.println("Deposit amount must be greater than 0.");
        }
    }
    public void deposit (double amount){
        if(amount >0){
            this.balance+=amount;
            System.out.println("Deposited: "+amount);
        }
        else{
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    // method to withdraw amount
    public void withdraw(float amount){
        if(amount>0 && amount <=this.balance){
            this.balance -= amount;
            System.out.println("Withdrawn: "+amount);
        }
        else if(amount > this.balance){
            System.out.println("Insufficient balance for withdrawal.");
        }
        else{
            System.out.println("Withdrawal amount must be greater than 0.");
        }
    }
    public void withdraw(int amount){
        if(amount>0 && amount <=this.balance){
            this.balance -= amount;
            System.out.println("Withdrawn: "+amount);
        }
        else if(amount > this.balance){
            System.out.println("Insufficient balance for withdrawal.");
        }
        else{
            System.out.println("Withdrawal amount must be greater than 0.");
        }
    }
    public void withdraw(double amount){
        if(amount > 0 && amount <= this.balance){
            this.balance -=amount;
            System.out.println("Withdrawn: "+amount);
        }
        else if(amount > this.balance){
            System.out.println("Insufficient balance for withdrawal.");
        }
        else{
            System.out.println("Withdrawal amount must be greater than 0.");
        }
    }

    // method to calculate interest
    public void calculate_interest(){
      System.out.println("Interest calculations are not available for the account class");
    }

    // method to display account details
    public void displayAccountDetails(){
        System.out.println("------------------------------");
        System.out.println("Account Details:");
        System.out.println("------------------------------");
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Account Type: "+accountType);; // Assuming interest is added to balance
        System.out.println("Balance: "+balance);
        System.out.println("------------------------------");
    }

}