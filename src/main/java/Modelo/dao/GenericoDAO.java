package Modelo.dao;

import Modelo.dao.EntityManagerFactoryConnector;
import jakarta.persistence.EntityManager;
import java.util.List;

public class GenericoDAO<T> {
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
            System.err.println("Error al guardar la entidad: " + e.getMessage());
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
            System.err.println("Error al actualizar la entidad: " + e.getMessage());
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
            if (entidad == null) {
                System.err.println("Entidad con ID " + id + " no encontrada para eliminar.");
                return;
            }
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al eliminar la entidad: " + e.getMessage());
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
            System.out.println("Ejecutando consulta: SELECT e FROM " + entityClass.getSimpleName() + " e");
            List<T> resultados = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron resultados para " + entityClass.getSimpleName());
            }
            return resultados;
        } finally {
            em.close();
        }
    }
}
