package Modelo.dao;

import Modelo.dto.Ejemplar;
import Modelo.dto.Libro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EjemplarDAO extends GenericoDAO<Ejemplar> {

    public EjemplarDAO() {
        super(Ejemplar.class);
    }

    public List<Ejemplar> obtenerDisponiblesPorLibro(final Libro libro) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT e FROM Ejemplar e WHERE e.isbn = :libro AND e.estado = 'Disponible'",
                            Ejemplar.class)
                    .setParameter("libro", libro)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ejemplar> obtenerEjemplaresPorEstado(final String estado) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Ejemplar e WHERE e.estado = :estado", Ejemplar.class)
                    .setParameter("estado", estado)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
