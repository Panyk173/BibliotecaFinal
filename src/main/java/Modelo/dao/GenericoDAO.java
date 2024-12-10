package Modelo.dao;

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class GenericoDAO<T> {

    protected final EntityManager em;
    private final Class<T> entityClass;

    public GenericoDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.em = EntityManagerFactoryConnector.getEntityManager();
    }

    public void guardar(T entidad) {
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void actualizar(T entidad) {
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void eliminar(Object id) {
        try {
            em.getTransaction().begin();
            T entidad = em.find(entityClass, id);
            if (entidad != null) {
                em.remove(entidad);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public T buscarPorId(Object id) {
        return em.find(entityClass, id);
    }

    public List<T> obtenerTodos() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }
}
