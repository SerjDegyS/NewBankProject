package Services;

import DAO.ClientDAO;
import Entity.Account;
import Entity.Client;
import Entity.CurrencyType;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleManagerServise implements ManagerService {
    private Scanner scan;
    private ClientDAO clientDAO;

    public ConsoleManagerServise(Scanner scan, ClientDAO clientDAO) {
        this.scan = scan;
        this.clientDAO = clientDAO;
    }

    @Override
    public void createNewClient() {
        Client client = new Client();
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
            return;
        }
        if (clientDAO.addClient(client))
            System.out.println("Client add successful!");
        else System.out.println("Client not add!");
    }

    @Override
    public Client findClient(String phoneNumberClient) {
        return null;
    }

    @Override
    public void addAccountToClient() {
        Account account = new Account();
        Client client;
        System.out.println("Enter account data like" +
                "\n (currency type(UAH or USD, or EUR); balance, phone client;):");
        String[] stringData = scan.nextLine().split(";");
        if (stringData.length == 3) {
            account.setCurrencyTtype(CurrencyType.valueOf(stringData[0].trim()));
            account.setBalance(BigDecimal.valueOf(Long.parseLong(stringData[1].trim())));
            client = clientDAO.getClientByPhoneNumber(stringData[2].trim());
        } else {
            System.out.println("Wrong input! Try again:");
            return;
        }
        if (clientDAO.addAccountToClient(account, client))
            System.out.println("Client add successful!");
        else System.out.println("Client not add!");

    }

    @Override
    public void updateClient(String phoneNumberClient) {

    }

    @Override
    public void deleteClient(String phoneNumberClient) {

    }
}
