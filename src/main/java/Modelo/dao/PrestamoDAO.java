package Modelo.dao;

import Modelo.dto.Prestamo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PrestamoDAO extends GenericoDAO<Prestamo> {

    public PrestamoDAO() {
        super(Prestamo.class);
    }

    public List<Prestamo> obtenerPrestamosActivosPorUsuario(Integer usuarioId) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario.id = :usuarioId AND p.fechaDevolucion IS NULL", Prestamo.class)
                    .setParameter("usuarioId", usuarioId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Prestamo> obtenerPrestamosPorEjemplar(Integer ejemplarId) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Prestamo p WHERE p.ejemplar.id = :ejemplarId", Prestamo.class)
                    .setParameter("ejemplarId", ejemplarId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
