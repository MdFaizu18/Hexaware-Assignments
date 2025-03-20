import java.util.Scanner;

public class LoanEligibility {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your credit score: ");
        int creditScore = sc.nextInt();
        System.out.println("Enter your annual income: ");
        int annualIncome = sc.nextInt();
        if (creditScore > 700 && annualIncome >= 50000) {
            System.out.println("Congratulations! You are eligible for the loan.");
        } else {
            System.out.println("Sorry, you are not eligible for the loan.");
        }
        sc.close();
    }
}