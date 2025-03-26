import java.util.Scanner;

public class BankTransactions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 0.0;
        String transactionHistory = ""; 

        System.out.println("Welcome to Bank Transactions System!");

        while (true) {
            System.out.println("\n1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Exit and Show Transactions");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter deposit amount: $ ");
                double deposit = sc.nextDouble();
                if (deposit > 0) {
                    balance += deposit;
                    transactionHistory += "Deposited: $ " + deposit + "\n"; // Append transaction
                    System.out.println("Deposit successful! New Balance: $ " + balance);
                } else {
                    System.out.println("Invalid deposit amount.");
                }
            } 
            else if (choice == 2) {
                System.out.print("Enter withdrawal amount: $ ");
                double withdraw = sc.nextDouble();
                if (withdraw > 0 && withdraw <= balance) {
                    balance -= withdraw;
                    transactionHistory += "Withdrew: $ " + withdraw + "\n"; // Append transaction
                    System.out.println("Withdrawal successful! New Balance: $ " + balance);
                } else {
                    System.out.println("Invalid withdrawal amount or insufficient balance.");
                }
            } 
            else if (choice == 3) {
                System.out.println("\nTransaction History:");
                System.out.println(transactionHistory.isEmpty() ? "No transactions yet." : transactionHistory);
                System.out.println("Final Balance: $ " + balance);
                System.out.println("Thank you for using our banking system!");
                break; // Exit the loop
            } 
            else {
                System.out.println(" Invalid choice. Please select again.");
            }
        }

        sc.close();
    }
}
