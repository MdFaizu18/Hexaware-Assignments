import java.util.Scanner;

public class CalculateCompoundInterest {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    //To enter the no of cutomers (to loop till that)
    System.out.print("\nEnter the No of Customers: ");
    int numberOfCustomers = sc.nextInt();

    for(int i =0;i<numberOfCustomers;i++){
        System.out.println("\nCustomer " + (i+1) + ": ");

        //To enter the initial balance
        System.out.print("Please Enter the Initial Balance: $ ");
        double initialBalance = sc.nextDouble();
        if(initialBalance <=0){
            System.out.println("Initial Amount Should be Greater than 0");
            System.exit(0);
        }

        //To enter the annual interest rate
        System.out.print("Please Enter Annual Interest Rate: % ");
        double annualInterestRate = sc.nextDouble();
        if(annualInterestRate <=0){
            System.out.println("Annual Interest Should be Greater than 0");
            System.exit(0);
        }

        //To enter the no of years
        System.out.print("Please Enter the Number of Years: ");
        int numberOfYears = sc.nextInt();
        if(numberOfYears <=0){
            System.out.println("Number of Years Should be Greater than 0");
            System.exit(0);
        }

        double futureBalance  = initialBalance * Math.pow((1 + annualInterestRate/100),numberOfYears);
        System.out.printf("Future Balance after %d years: $ %.2f\n", numberOfYears, futureBalance);
    }
    sc.close();
}
}
