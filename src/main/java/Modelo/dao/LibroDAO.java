package Modelo.dao;

import Modelo.dto.Libro;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LibroDAO extends GenericoDAO<Libro> {
    public LibroDAO() {
        super(Libro.class);
    }

    public List<Libro> buscarPorAutor(final String autor) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Libro l WHERE l.autor = :autor", Libro.class)
                    .setParameter("autor", autor)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
