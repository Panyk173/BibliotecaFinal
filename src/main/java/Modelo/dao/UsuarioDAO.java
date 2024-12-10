package Modelo.dao;

import jakarta.persistence.EntityManager;
import Modelo.dto.Usuario;
import java.util.List;

public class UsuarioDAO extends GenericoDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario buscarPorDNI(String dni) {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerUsuariosConPenalizacionActiva() {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.penalizacionHasta > CURRENT_DATE", Usuario.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
