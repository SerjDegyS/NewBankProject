package DAO;

import Entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("ALL")
@NamedQuery(name = "Client.findByPhoneNumber", query = "SELECT c FROM Client c WHERE c.PHONE =:number")
public class ClientDAOImpl extends GenericGetAllEntity implements ClientDAO {

    EntityManager em;

    public ClientDAOImpl(EntityManager em) {
        super(em, Client.class);
        this.em = em;
    }


    @Override
    public boolean addClient(Client client) {
        this.em.getTransaction().begin();
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
    public Client getClientByPhoneNumber(String phoneNmber) {
        Query query = em.createNamedQuery("Client.findByPhoneNumber", Client.class);
        query.setParameter("number", phoneNmber);
        Client client;
        try {
            client = (Client) query.getSingleResult();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Dont find client with phone number: " + phoneNmber);
            client = null;
        }
        return client;
    }



    @Override
    public void updateClient(Client client) {
        em.getTransaction().begin();
        try {
            em.merge(client);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Client not update!");
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public boolean deleteClient(String phoneNmber) {
        Client client = getClientByPhoneNumber(phoneNmber);
        em.getTransaction().begin();
        try {
            em.remove(client);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}
