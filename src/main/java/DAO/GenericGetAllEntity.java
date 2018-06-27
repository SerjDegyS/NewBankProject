package DAO;

import Entity.Entity;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class GenericGetAllEntity<T extends Entity> {
    private EntityManager em;
    private Class<T> entity;

    public GenericGetAllEntity(EntityManager em, Class<T> entity) {
        this.em = em;
        this.entity = entity;
    }

    public List<T> getAll() {
        return em.createQuery("SELECT t FROM " + entity.getSimpleName() + " t ", entity).getResultList();
    }
}
