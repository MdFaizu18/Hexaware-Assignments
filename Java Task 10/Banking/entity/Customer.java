package entity;

public class Customer {
    private static int customerCounter = 1001; // Auto-increment ID
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Customer(String firstName, String lastName, String email, String phone, String address) {
        this.customerId = customerCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = validateEmail(email) ? email : "Invalid Email";
        this.phone = validatePhone(phone) ? phone : "Invalid Phone";
        this.address = address;
    }

    // Email Validation
    private boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Phone Validation (10-digit)
    private boolean validatePhone(String phone) {
        return phone.matches("\\d{10}");
    }

    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void printCustomerDetails() {
        System.out.println("\nCustomer Details:");
        System.out.println("ID: " + customerId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }
}
