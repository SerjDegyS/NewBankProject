package DAO;

import Entity.Account;
import Entity.CurrencyType;
import Entity.Transaction;

import java.util.List;

public interface AccountDAO {

    boolean addAccount(Account account);

    Account getAccount(Long idClient, CurrencyType currencyType);

    List<Account> getAll();

    List<Account> getAllAccountsByClientId(Long id);

    boolean deleteAccount(Account account);
}
