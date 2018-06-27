package Services;

import Entity.Account;
import Entity.Client;
import Entity.CurrencyType;

public interface ManagerService {



    void createNewClient();
    Client findClient(String phoneNumberClient);
    void addAccountToClient();
    void updateClient(String phoneNumberClient);
    void deleteClient(String phoneNumberClient);

    void deleteAccount(String PhoneNumberClient, CurrencyType currencyTypeOfAccount);

    void getAccountsByClient(Client client);

}
