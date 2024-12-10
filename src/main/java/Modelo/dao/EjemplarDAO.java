package Modelo.dao;

import  Modelo.dto.Ejemplar;
import Modelo.dto.Libro;

import java.util.List;

public class EjemplarDAO extends GenericoDAO<Ejemplar> {

    public EjemplarDAO() {
        super(Ejemplar.class);
    }

    public List<Ejemplar> obtenerDisponiblesPorLibro(Libro libro) {
        return em.createQuery("SELECT e FROM Ejemplar e WHERE e.isbn = :libro AND e.estado = 'Disponible'", Ejemplar.class)
                .setParameter("libro", libro)
                .getResultList();
    }
}
