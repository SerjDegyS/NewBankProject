package DAO;

import Entity.Transaction;

import java.util.List;

public interface AccountDAO {

    boolean addTransaction(Transaction transaction);

    boolean setTransaction(List<Transaction> transactions);

    List<Transaction> getTransactionList();
}
