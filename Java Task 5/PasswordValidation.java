import java.util.Scanner;

public class PasswordValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Bank Account Password Setup!");
        
        while (true) {
            System.out.print("Please enter your password: ");
            String password = sc.nextLine();
            boolean isValid = true;

            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long.");
                isValid = false;
            }
            if (!password.matches(".*[A-Z].*")) {
                System.out.println("Password must contain at least one uppercase letter.");
                isValid = false;
            }
            if (!password.matches(".*[0-9].*")) {
                System.out.println("Password must contain at least one digit.");
                isValid = false;
            }

            // If all conditions are met, break the loop
            if (isValid) {
                System.out.println("Password is valid... Thank you for setting up your password.");
                break;
            }
            
            System.out.println("Please try again.\n");
        }
        
        sc.close();
    }
}
