package Modelo.dao;

import Modelo.dto.Libro;

import java.util.List;

public class LibroDAO extends GenericoDAO<Libro> {

    public LibroDAO() {
        super(Libro.class);
    }

    public List<Libro> buscarPorAutor(String autor) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.autor = :autor", Libro.class)
                .setParameter("autor", autor)
                .getResultList();
    }
}
