package Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
public class Account extends Entyti {

    @Column(name = "CURRENCY_TYPE", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "BALANCE", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CLIENT_ID",unique = true)
    private Client client;

    @OneToMany(mappedBy ="PAYER_ID,PAYEE_ID", cascade =CascadeType.PERSIST,orphanRemoval = false)
    private List<Transaction> transactionList;

    public Account() {    }

    public Account(CurrencyType currencyType, BigDecimal balance,
                   Client client) {
        this.currencyType = currencyType;
        this.balance = balance;
        this.client = client;
        this.transactionList = new ArrayList<Transaction>();
    }

    public CurrencyType getCurrencyTtype() {
        return currencyType;
    }

    public void setCurrencyTtype(CurrencyType currencyTtype) {
        this.currencyType = currencyType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "  \n\tcurrencyTtype=" + currencyType +
                ", \n\tbalance=" + balance +
                ", \n\tclient=" + client +
                ", \n\ttransactionList=" + transactionList +
                '}';
    }
}
