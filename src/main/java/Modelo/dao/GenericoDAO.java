package Modelo.dao;

import Modelo.dao.EntityManagerFactoryConnector;
import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class GenericoDAO<T> {
    private final Class<T> entityClass;

    public GenericoDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void guardar(T entidad) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void actualizar(T entidad) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminar(Object id) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
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
        } finally {
            em.close();
        }
    }

    public T buscarPorId(Object id) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public List<T> obtenerTodos() {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        } finally {
            em.close();
        }
    }
}
