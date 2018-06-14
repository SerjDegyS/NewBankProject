package Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Table(name = "Transactions")
public class Transaction extends Entyti {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "PAYER_ID")
    private Account payerAccount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "PAYEE_ID")
    private Account payeeAccount;

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

    public Transaction(Account payerAccount, Account payeeAccount,
                       BigDecimal amount, Boolean status, String description) {
        this.payerAccount = payerAccount;
        this.payeeAccount = payeeAccount;
        this.timestamp = ZonedDateTime.now();
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    public Account getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(Account payerAccount) {
        this.payerAccount = payerAccount;
    }

    public Account getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(Account payeeAccount) {
        this.payeeAccount = payeeAccount;
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
                "  \n\tpayerAccount=" + payerAccount +
                ", \n\tpayeeAccount=" + payeeAccount +
                ", \n\ttimestamp=" + timestamp +
                ", \n\tamount=" + amount +
                ", \n\tstatus=" + status +
                ", \n\tdescription='" + description + '\'' +
                '}';
    }
}
