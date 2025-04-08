package Banking.bean;

import java.util.Date;
import java.time.LocalDateTime;

public class Transaction {
    private Account account;
    private String description;
    private Date dateTime;
    private String transactionType;
    private float transactionAmount;

    public Transaction(Account account, String description, String transactionType, float amount) {
        this.account = account;
        this.description = description;
        this.transactionType = transactionType;
        this.transactionAmount = amount;
        this.dateTime = dateTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
