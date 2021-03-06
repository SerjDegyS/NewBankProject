package Entity;

import javax.persistence.*;
import java.util.*;


@javax.persistence.Entity
@Table(name = "CLIENT")
@NamedQuery(name = "Client.findByPhoneNumber", query = "SELECT c FROM Client c WHERE c.phone =:number")
public class Client extends Entity {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;
    @Column(name = "EMAIL", unique = true)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,
    orphanRemoval = false)
    private List<Account> accountList = new ArrayList<Account>();

    public Client() { }

    public Client(String name, String surname, String phone,
                  String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.accountList = new ArrayList<>();
    }

    public void addAccount(Account account){
        if (this.accountList.contains(account)) {
            System.out.println(this + " already contains account: " + account.getCurrencyTtype());
            return;
        } else {
            this.accountList.add(account);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void showAccounts() {
        accountList.forEach(System.out::print);
//        for (Account account:accountList) {
//            System.out.print(account.getCurrencyTtype());
//        }

    }

    @Override
    public String toString() {
        return "Client{" +
                "  \n\tname='" + name + '\'' +
                ", \n\tsurname='" + surname + '\'' +
                ", \n\tphone='" + phone + '\'' +
                ", \n\temail='" + email + '\'' +
                '}';
    }
}
