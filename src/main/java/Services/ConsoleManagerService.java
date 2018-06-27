package Services;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import DAO.ClientDAO;
import DAO.ClientDAOImpl;
import Entity.Account;
import Entity.Client;
import Entity.CurrencyType;
import Entity.Entity;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleManagerService implements ManagerService {
    private Scanner scan;
    private EntityManager em;
    private ClientDAO clientDAO;
    private AccountDAO accountDAO;

    public ConsoleManagerService(Scanner scan, EntityManager em) {
        this.scan = scan;
        this.em = em;
        this.clientDAO = new ClientDAOImpl(em);
        this.accountDAO = new AccountDAOImpl(em);
    }

//    public <T extends Entity> List<T> getAll(Class<T> entity) {
//        return em.createQuery("SELECT t FROM " + entity.getSimpleName() + " t ", entity).getResultList();
//    }

    public void getAllClient() {
        clientDAO.getAll().forEach(System.out::println);
    }

    @Override
    public void createNewClient() {
        Client client = new Client();
        if (enterClientData(client) == null) return;
        if (clientDAO.addClient(client))
            System.out.println("Client add successful!");
        else System.out.println("Client not add!");
    }

    private Client enterClientData(Client client) {
        System.out.println("Enter client data like" +
                "\n (name; surname; phone; email;):");
        String[] stringData = scan.nextLine().split(";");
        if (stringData.length == 4) {
            client.setName(stringData[0].trim());
            client.setSurname(stringData[1].trim());
            client.setPhone(stringData[2].trim());
            client.setEmail(stringData[3].trim());
        } else {
            System.out.println("Wrong input! Try again:");
            return null;
        }
        return client;
    }

    @Override
    public Client findClient(String phoneNumberClient) {
        Client client = clientDAO.getClientByPhoneNumber(phoneNumberClient);
        if (client == null) {
            System.out.println("Client not found with number: " + phoneNumberClient);
            return null;
        }
        return client;
    }

    @Override
    public void addAccountToClient() {
        Account account = new Account();
        Client client;
        System.out.println("Enter account data like" +
                "\n (currency type(UAH or USD, or EUR); balance, phone client;):");
        String[] stringData = scan.nextLine().split(";");
        if (stringData.length == 3) {
            account.setCurrencyTtype(CurrencyType.valueOf(stringData[0].trim().toUpperCase()));
            account.setBalance(BigDecimal.valueOf(Long.parseLong(stringData[1].trim())));
            client = clientDAO.getClientByPhoneNumber(stringData[2].trim());
        } else {
            System.out.println("Wrong input! Try again:");
            return;
        }

        if (!client.getAccountList().contains(account)) {
            client.addAccount(account);
            account.setClient(client);
            if (accountDAO.addAccount(account))
                System.out.println("Account " + account.getCurrencyTtype() + " add to " + client + " successful!");
            else System.out.println("Account not add!");
        } else {
            System.out.println("The client already has this account!");
        }
        client.showAccounts();
    }

    @Override
    public void updateClient(String phoneNumberClient) {
        Client client = findClient(phoneNumberClient);
        if (enterClientData(client) == null) return;
        clientDAO.updateClient(client);
    }

    @Override
    public void deleteClient(String phoneNumberClient) {
        if (clientDAO.deleteClient(phoneNumberClient)) {
            System.out.println("Client delete successful!");
        }
    }

    public void getAllAccount() {
        accountDAO.getAll().forEach(System.out::println);
    }

    @Override
    public void deleteAccount(String phoneNumberClient, CurrencyType currencyTypeOfAccount) {
        Long clientID = clientDAO.getClientByPhoneNumber(phoneNumberClient).getId();
        System.out.println(clientID);
        Account account = accountDAO.getAccount(clientID, currencyTypeOfAccount);
        System.out.println(account);

        if (accountDAO.deleteAccount(account)) {
            System.out.println("Account " + currencyTypeOfAccount + " delete successful!");
        } else System.out.println("Account " + currencyTypeOfAccount + " not delete!");
    }

    @Override
    public void getAccountsByClient(Client client) {
        List<Account> accounts = new ArrayList<Account>();
        accounts = accountDAO.getAllAccountsByClientId(client.getId());
        if (accounts == null)
            System.out.println("Client " + client + "don't have any accounts!");
        else accounts.forEach(System.out::println);
    }


}
