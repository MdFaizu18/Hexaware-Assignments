import entity.Account;
import entity.SavingsAccount;
import entity.CurrentAccount;

import java.util.Scanner;


public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // intializing the account class object 
        Account account = null;

        System.out.println("Welcome to Hexaware Bank!");
        System.out.println("Select Account Type:\n1. Savings Account\n2. Current Account");
        int choice = scanner.nextInt();

        System.out.print("Enter Account Number: ");
        int accNumber = scanner.nextInt();
        
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        // Create Account Based on User Choice
        switch (choice) {
            case 1:
                // if user choose savings account it will use this object as further 
                account = new SavingsAccount(accNumber, balance);
                break;
            case 2:
                // if user choose current account it will use this object as further 
                account = new CurrentAccount(accNumber, balance);
                break;
            default:
                System.out.println("Invalid option! Exiting.");
                scanner.close();
                return;
        }

        // Banking Menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. Calculate Interest\n5. Exit");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Amount Deposited Successfully!");
                    break;

                case 2:
                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    account.displayAccountDetails();
                    break;

                case 4:
                    //it will override the calculate interest from the sub class 
                    account.calculate_interest();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Thank you for banking with us!");
                    break;

                default:
                    System.out.println("Invalid Choice! Try again.");
            }
        }
        scanner.close();
    }
}
