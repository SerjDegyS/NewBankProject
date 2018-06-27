import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import DAO.ClientDAO;
import DAO.ClientDAOImpl;
import Entity.Account;
import Entity.Client;
import Entity.CurrencyType;
import Services.ConsoleManagerService;
import Services.ManagerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AppConsole {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitMenu");
        EntityManager em = emf.createEntityManager();
        Scanner scan = new Scanner(System.in);
        ManagerService managerService = new ConsoleManagerService(scan, em);

//        managerService.createNewClient();

//        Client client = managerService.findClient("0973031591");
//        System.out.println(client.getAccountList());
//        managerService.addAccountToClient();
//        System.out.println(client.getAccountList());
//        System.out.println(managerService.findClient("0973031591").getAccountList().toString());
//        managerService.updateClient("0973031591");

//        System.out.println(managerService.findClient("0973031591"));
//        managerService.deleteClient("098");
//        managerService.deleteAccount("0973031591", CurrencyType.USD);


//        ((ConsoleManagerService) managerService).getAllClient();
//
//        ((ConsoleManagerService) managerService).getAllAccount();
        Client client = managerService.findClient("0973031591");
        managerService.getAccountsByClient(client);
    }
}
