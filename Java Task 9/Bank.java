import entity.*;
import java.util.Scanner;

public class Bank{
    public static void main(String[] args) {
      try (Scanner sc = new Scanner(System.in)) {
          BankAccount account = null;

          System.out.println("Welcome to the Banking System");
          System.out.println("Choose an account type:");
          System.out.println("1. Savings Account");
          System.out.println("2. Current Account");
          System.out.print("Enter your choice (1 or 2): ");
          int choice = sc.nextInt();
          sc.nextLine(); // Consume the newline character left by nextInt() 

          // Account Details Input
          System.out.print("Enter Account Number: ");
          int accNumber = sc.nextInt();
          sc.nextLine(); 
          System.out.print("Enter Customer Name: ");
          String custName = sc.nextLine();
          System.out.print("Enter Initial Balance: ");
          double balance = sc.nextDouble();

          switch (choice) {
              case 1:
                  account = new SavingsAccount(accNumber,custName,balance);
                  break;
              case 2:
                  account = new CurrentAccount(accNumber,custName,balance);
                  break;
              default:
                  System.out.println("Invalid choice. Please select a valid account type.");
                  return;
          }
          boolean exit = false;
          while (!exit){
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Show Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = sc.nextInt();

            switch(option){
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    float depositAmount = sc.nextFloat();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    float withdrawAmount = sc.nextFloat();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.calculateInterest();
                    break;
                case 4:
                    account.printAccountInfo();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the banking system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
          }
          System.out.println("Thank you for using the banking system!");
          sc.close();
      }
    }
}