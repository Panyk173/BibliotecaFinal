package Modelo.dao;

import  Modelo.dto.Prestamo;

import java.util.List;

public class PrestamoDAO extends GenericoDAO<Prestamo> {

    public PrestamoDAO() {
        super(Prestamo.class);
    }

    public List<Prestamo> obtenerPrestamosActivosPorUsuario(Integer usuarioId) {
        return em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario.id = :usuarioId AND p.fechaDevolucion IS NULL", Prestamo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }
}
