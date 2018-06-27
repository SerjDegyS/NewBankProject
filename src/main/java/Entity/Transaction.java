package Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;


@javax.persistence.Entity
@Table(name = "Transactions")
public class Transaction extends Entity {

//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
//            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "PAYER_ID", referencedColumnName = "id")
//    private Account payerAccount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "TRANSACTION_ACCOUNT",
            joinColumns = {@JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")})
    private Map<String, Account> transactionAccounts = new HashMap<String, Account>();


    @CreationTimestamp
    @Column(name = "TIMESTAMP", nullable = false, updatable = false)
    private ZonedDateTime timestamp;
    @Column(name = "AMOUNT", precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(name = "STATUS")
    private Boolean status;
    @Column(name = "DESCRIPTION", length = 64)
    private String description;

    public Transaction() {    }

    public Transaction(Map<String, Account> transactionAccounts,
                       BigDecimal amount, Boolean status, String description) {
        this.transactionAccounts = transactionAccounts;
        this.timestamp = ZonedDateTime.now();
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    public Account getPayerAccount() {
        return transactionAccounts.get("payer");
    }

    public void setPayerAccount(Account payerAccount) {
        this.transactionAccounts.put("payer", payerAccount);
    }

    public Account getPayeeAccount() {
        return transactionAccounts.get("payee");
    }

    public void setPayeeAccount(Account payeeAccount) {
        this.transactionAccounts.put("payee", payeeAccount);
    }

    public void setTransactionAccounts(Map<String, Account> transactionAccounts) {
        this.transactionAccounts = transactionAccounts;
    }

    public Map<String, Account> getTransactionAccounts() {
        return transactionAccounts;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "  \n\tpayerAccount=" + transactionAccounts.get("payer") +
                ", \n\tpayeeAccount=" + transactionAccounts.get("payee") +
                ", \n\ttimestamp=" + timestamp +
                ", \n\tamount=" + amount +
                ", \n\tstatus=" + status +
                ", \n\tdescription='" + description + '\'' +
                '}';
    }
}
