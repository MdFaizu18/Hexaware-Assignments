import java.util.Scanner;

public class ATMTransaction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Please Enter Your Current Balance: $ ");
        double currentBalance = sc.nextDouble();

        while (true) { 
            System.out.println("\n ******* WELCOME TO BANK OF BARODA ATM TRANSACTION *******\n");
            System.out.println("1. Withdraw Cash");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("\nPlease Enter Your Choice: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1: // Withdraw Cash
                    while (true) { 
                        System.out.print("Please Enter Amount to Withdraw (Multiples of 100 or 500): $ ");
                        double withdrawAmount = sc.nextDouble();

                        if (withdrawAmount > currentBalance) {
                            System.out.println("Insufficient Balance. Please Enter a Valid Amount.");
                        } else if (withdrawAmount % 100 != 0 && withdrawAmount % 500 != 0) {
                            System.out.println("!!! Invalid Amount. Please enter multiples of 100 or 500.");
                        } else {
                            currentBalance -= withdrawAmount;
                            System.out.println("..\nProcessing Your Transaction...\n..");
                            System.out.println("Please Collect Your Cash: $ " + withdrawAmount);
                            System.out.println("Your Available Current Balance is: $ " + currentBalance);
                            break; 
                        }
                    }
                    break;

                case 2: // Deposit Cash
                    while (true) { 
                        System.out.print("Please Enter the Amount to Deposit: $ ");
                        double depositAmount = sc.nextDouble();

                        if (depositAmount > 0) {
                            currentBalance += depositAmount;
                            System.out.println("..\nProcessing Your Transaction...\n..");
                            System.out.println("Your Amount has been Deposited Successfully: $ " + depositAmount);
                            System.out.println("Your Available Current Balance is: $ " + currentBalance);
                            break; 
                        } else {
                            System.out.println("Invalid Deposit Amount. Please Enter a Positive Value.");
                        }
                    }
                    break;

                case 3: // Check Balance
                    System.out.println("\nYour Available Current Balance is: $ " + currentBalance);
                    break;

                case 4: // Exit
                    System.out.println("\nThank You For Using BOB ATM! Have a Great Day. ('^_^')\n");
                    sc.close();
                    System.exit(0); // Exit the program

                default:
                    System.out.println("Invalid Choice. Please Select a Valid Option.");
            }
        }
    }
}
