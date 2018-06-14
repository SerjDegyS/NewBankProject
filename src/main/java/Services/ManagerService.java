package Services;

import Entity.Account;
import Entity.Client;

public interface ManagerService {



    void createNewClient();
    Client findClient(String phoneNumberClient);
    void addAccountToClient();

    void updateClient(String phoneNumberClient);
    void deleteClient(String phoneNumberClient);

}
