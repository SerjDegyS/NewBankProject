package DAO;

import Entity.Account;
import Entity.Client;
import Entity.Transaction;

import java.math.BigDecimal;

public interface ClientDAO {

    boolean addClient(Client client);
    Client getClient(Long id);
    Client getClientByPhoneNumber(String phoneNumber);
    boolean addAccountToClient(Account account, Client client);

    void updateClient(Client client);
    boolean deleteClient(String phoneNumber);

}
