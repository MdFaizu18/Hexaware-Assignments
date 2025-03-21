import java.util.Scanner;

// To check the loan eligibility with the credit score and annual income of the customer
public class LoanEligibility {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //To enter the credit score
        System.out.println("Enter your credit score: ");
        int creditScore = sc.nextInt();
        //TO enter the annual income
        System.out.println("Enter your annual income: ");
        int annualIncome = sc.nextInt();
        //Checking for eligibility for the loan
        if (creditScore > 700 && annualIncome >= 50000) {
            System.out.println("Congratulations! You are eligible for the loan.");
        } else {
            System.out.println("Sorry, you are not eligible for the loan.");
        }
        sc.close();
    }
}
