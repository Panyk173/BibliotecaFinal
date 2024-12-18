package Modelo.dao;

import jakarta.persistence.EntityManager;
import java.util.List;

public class GenericoDAO<T> {
    private final Class<T> entityClass;

    public GenericoDAO(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void guardar(final T entidad) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al guardar la entidad " + entityClass.getSimpleName(), e);
        } finally {
            em.close();
        }
    }

    public void actualizar(final T entidad) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar la entidad " + entityClass.getSimpleName(), e);
        } finally {
            em.close();
        }
    }

    public void eliminar(final Object id) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            em.getTransaction().begin();
            final T entidad = em.find(entityClass, id);
            if (entidad == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entidad con ID " + id + " no encontrada.");
            }
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la entidad " + entityClass.getSimpleName(), e);
        } finally {
            em.close();
        }
    }

    public T buscarPorId(final Object id) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public List<T> obtenerTodos() {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
