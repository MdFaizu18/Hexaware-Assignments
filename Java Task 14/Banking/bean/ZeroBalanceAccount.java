package Banking.bean;

public class ZeroBalanceAccount extends Account {
    public ZeroBalanceAccount(Customer customer) {
        super("ZeroBalance", 0, customer);
    }

    public float calculateInterest() {
        return 0;
    }
}
