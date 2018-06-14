package Services;

import Entity.Account;
import Entity.Client;
import Entity.Transaction;

import java.math.BigDecimal;

public interface ClientService {

    boolean replenishAccount(Account account, Transaction transaction);
    boolean transferFundsFromAccountToAccoun(Account accountFrom,
                                             Account accountTo,
                                             Transaction transaction);

    boolean transferFundsFromClientToClent(Client clientFrom,
                                           Client clientTo,
                                           Transaction transaction);

    BigDecimal allFunndsUserAccountInUAH();
}
