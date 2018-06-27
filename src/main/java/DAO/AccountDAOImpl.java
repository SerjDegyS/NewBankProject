package DAO;

import Entity.Account;
import Entity.Client;
import Entity.CurrencyType;
import Entity.Transaction;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.List;

public class AccountDAOImpl extends GenericGetAllEntity implements AccountDAO {

    EntityManager em;

    public AccountDAOImpl(EntityManager em) {
        super(em, Account.class);
        this.em = em;
    }

    @Override
    public boolean addAccount(Account account) {
        em.getTransaction().begin();
        try {
            em.persist(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccount(Long idClient, CurrencyType currencyType) {
        Account account = null;
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.client.id =:clientId AND a.currencyType =:currencyType", Account.class);
            query.setParameter("clientId", idClient);
            query.setParameter("currencyType", currencyType);
            account = (Account) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            System.out.println("Account not found!");
        }

        return account;
    }

    @Override
    public List<Account> getAllAccountsByClientId(Long id) {
        Query query = em.createNamedQuery("Account.getAccountsByClientId", Account.class);
        query.setParameter("id", id);
        List<Account> list = query.getResultList();
        return list;
    }

    @Override
    public boolean deleteAccount(Account account) {
        return false;
    }


}
