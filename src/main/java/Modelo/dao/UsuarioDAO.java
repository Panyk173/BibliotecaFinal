package Modelo.dao;

import Modelo.dto.Usuario;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO extends GenericoDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario buscarPorDNI(final String dni) {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerUsuariosConPenalizacionActiva() {
        final EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.penalizacionHasta > CURRENT_DATE", Usuario.class)
                    .getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
