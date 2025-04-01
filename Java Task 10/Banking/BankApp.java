import entity.Customer;
import service.Bank;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        // initialzing bank object
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command:");
            System.out.println("Options: create_account | deposit | withdraw | get_balance | transfer | getAccountDetails | exit");

            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "create_account":
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

                    System.out.print("Enter Account Type (Savings/Current): ");
                    String accType = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    bank.createAccount(customer, accType, balance);
                    break;

                case "deposit":
                    System.out.print("Enter Account Number: ");
                    long depositAccNo = scanner.nextLong();
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.deposit(depositAccNo, depositAmount);
                    break;

                case "withdraw":
                    System.out.print("Enter Account Number: ");
                    long withdrawAccNo = scanner.nextLong();
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.withdraw(withdrawAccNo, withdrawAmount);
                    break;

                case "get_balance":
                    System.out.print("Enter Account Number: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    bank.getAccountDetails(accNo);
                    break;

                case "transfer":
                    System.out.print("Enter Sender's Account Number: ");
                    long senderAccNo = scanner.nextLong();
                    System.out.print("Enter Receiver's Account Number: ");
                    long receiverAccNo = scanner.nextLong();
                    System.out.print("Enter Amount to Transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.transfer(senderAccNo, receiverAccNo, transferAmount);
                    break;

                case "getaccountdetails":
                    System.out.print("Enter Account Number: ");
                    long detailsAccNo = scanner.nextLong();
                    scanner.nextLine();
                    bank.getAccountDetails(detailsAccNo);
                    break;

                case "exit":
                    System.out.println("Thank you for using the banking system!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command! Please enter a valid option.");
            }
        }
    }
}
