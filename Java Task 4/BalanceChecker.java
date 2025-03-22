import java.util.Scanner;

public class BalanceChecker{
    public static void main (String[] args){
       Scanner sc = new Scanner(System.in);

       int accountNumbers[] = {1111,2222,3333,4444,5555};
       double balances[] = {1050.50,2010.00,1500.00,500.00,1000.00};

       System.out.println("Welcome to the BOB ATM Balance Checker");

       do {

        System.out.print("\nPlease enter your account number: ");
        int customerAccountNumber = sc.nextInt();
        boolean accountFound = false;
        for(int i=0;i<accountNumbers.length;i++){
            if(customerAccountNumber == accountNumbers[i]){
                System.out.println("..\n Processing your request...\n..");
                System.out.println("Your balance is: " + balances[i]);
                System.out.println("Thanks for choosing our ATM service.");
                accountFound = true;
                System.exit(0);
            }
        }
        if(!accountFound){
            System.out.println("Account not found. Please try again.");
        }
       } while (true);
      
    }
}