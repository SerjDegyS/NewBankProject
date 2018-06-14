package DAO;

import Entity.Account;
import Entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@SuppressWarnings("ALL")
@NamedQuery(name = "Client.findByPhoneNumber", query = "SELECT c FROM Client c WHERE c.PHONE =:number")
public class ClientDAOIml implements ClientDAO {

    EntityManager em;

    public ClientDAOIml(EntityManager em) {
        this.em = em;
    }


    @Override
    public boolean addClient(Client client) {
        em.getTransaction().begin();
        try {
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public Client getClient(Long id) {
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        return client;
    }

    @Override
    public Client getClientByPhoneNumber(String phoneNmber) {
        Query query = em.createNamedQuery("Client.findByPhoneNumber", Client.class);
        query.setParameter("number", phoneNmber);
        Client client;
        try {
            em.getTransaction().begin();
            client = (Client) query.getSingleResult();
        }catch (Exception ex){
            ex.printStackTrace();
            client = null;
        }
        return client;
    }

    @Override
    public boolean addAccountToClient(Account account, Client client) {

        if (!client.getAccountList().contains(account)) {
            em.getTransaction().begin();
            try {
                client.addAccount(account);
                account.setClient(client);
                em.getTransaction().begin();
                em.persist(client);
                em.persist(account);
                em.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }


    @Override
    public void updateClient(Client client) {
        em.getTransaction().begin();
        try {
            em.merge(client);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public boolean deleteClient(String phoneNmber) {
        Client client = getClientByPhoneNumber(phoneNmber);
        try {
            em.getTransaction().begin();
            em.remove(client);
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}
