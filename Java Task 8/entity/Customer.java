package entity;
public class Customer {
    // attributes
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    // default constructor 
    public Customer() {
        this.customerID = 0;
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.email = "Not Provided";
        this.phoneNumber = "Not Provided";
        this.address = "Not Provided";
    }

    // parameterized constructor
    public Customer(int customerID,String firstName,String lastName,String email,String phoneNumber,String address){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // getter methods
    public int getCustomerID() {
        return customerID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }

    // setter methods
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address){
        this.address = address;
    }

    // method to display customer details
    public void displayCustomerDetails(){
        System.out.println("\n Customer Details");
        System.out.println("Customer ID: " + customerID);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
    }
}