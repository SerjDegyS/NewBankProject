package DAO;

import Entity.Client;

import java.util.List;

public interface ClientDAO {

    boolean addClient(Client client);

    List<Client> getAll();

    Client getClientByPhoneNumber(String phoneNumber);

    void updateClient(Client client);

    boolean deleteClient(String phoneNumber);

}
