package app;

import bean.Account;
import bean.Customer;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.OverDraftLimitExcededException;
import service.BankServiceProviderImpl;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankServiceProviderImpl bank = new BankServiceProviderImpl("Main Branch", "123 Bank Street");

        boolean exit = false;

        while (!exit) {
            System.out.println("\n======== Banking System ========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Get Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Get Account Details");
            System.out.println("7. List All Accounts");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        createAccount(bank, scanner);
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        long depositAccNo = scanner.nextLong();
                        System.out.print("Enter Amount to Deposit: ");
                        float depositAmount = scanner.nextFloat();
                        scanner.nextLine();
                        bank.deposit(depositAccNo, depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        long withdrawAccNo = scanner.nextLong();
                        System.out.print("Enter Amount to Withdraw: ");
                        float withdrawAmount = scanner.nextFloat();
                        scanner.nextLine();
                        bank.withdraw(withdrawAccNo, withdrawAmount);
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        long balanceAccNo = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Current Balance: " + bank.get_account_balance(balanceAccNo));
                        break;

                    case 5:
                        System.out.print("Enter Sender's Account Number: ");
                        long senderAccNo = scanner.nextLong();
                        System.out.print("Enter Receiver's Account Number: ");
                        long receiverAccNo = scanner.nextLong();
                        System.out.print("Enter Amount to Transfer: ");
                        float transferAmount = scanner.nextFloat();
                        scanner.nextLine();
                        bank.transfer(senderAccNo, receiverAccNo, transferAmount);
                        break;

                    case 6:
                        System.out.print("Enter Account Number: ");
                        long detailsAccNo = scanner.nextLong();
                        scanner.nextLine();
                        bank.getAccountDetails(detailsAccNo);
                        break;

                    case 7:
                        System.out.println("Listing All Accounts:");
                        for (var acc : bank.listAccounts()) {
                            System.out.println(acc);
                        }
                        break;

                    case 8:
                        exit = true;
                        System.out.println("Thank you for using the banking system!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 8.");
                }
            } catch (InsufficientFundException | InvalidAccountException | OverDraftLimitExcededException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Error: Null value encountered. Please check the inputs.");
            }
        }

        scanner.close();
    }

    private static void createAccount(BankServiceProviderImpl bank, Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, email, phone, address);
        long accNo = 1000;
        while (true) {
            System.out.println("\nChoose Account Type:");
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.println("3. Zero Balance Account");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter choice: ");

            int accChoice = scanner.nextInt();
            scanner.nextLine();

            String accType;
            switch (accChoice) {
                case 1:
                    accType = "savings";
                    break;
                case 2:
                    accType = "current";
                    break;
                case 3:
                    accType = "zerobalance";
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please select again.");
                    continue;
            }

            System.out.print("Enter Initial Balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            Account newAccount = bank.create_account(customer, accNo++, accType, balance);
            bank.addAccount(newAccount);

            System.out.println("Do you want to create another account? (yes/no)");
            String repeat = scanner.nextLine().trim().toLowerCase();
            if (!repeat.equals("yes")) {
                break;
            }
        }
    }
}
