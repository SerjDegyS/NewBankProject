package Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "ACCOUNT")
@NamedQuery(name = "Account.getAccountsByClientId", query = "SELECT a FROM Account a WHERE a.client.id =:id")
public class Account extends Entity {

//    @Id
//    @GeneratedValue
//    private long id;

    @Column(name = "CURRENCY_TYPE", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "BALANCE", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToMany(mappedBy = "transactionAccounts", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return currencyType == account.currencyType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(currencyType);
    }

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

    public void setCurrencyTtype(CurrencyType currencyType) {
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
                "currencyType=" + currencyType +
                ", balance=" + balance +
//                ", client=" + client +
//                ", transactionList=" + transactionList +
                '}';
    }
}
