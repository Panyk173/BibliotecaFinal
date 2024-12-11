// Clase UsuarioDAO actualizada
package Modelo.dao;

import Modelo.dto.Usuario;
import Modelo.dao.EntityManagerFactoryConnector;
import jakarta.persistence.EntityManager;
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
        } catch (Exception e) {
            System.err.println("Error al buscar usuario por DNI: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerUsuariosConPenalizacionActiva() {
        EntityManager em = EntityManagerFactoryConnector.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.penalizacionHasta > CURRENT_DATE", Usuario.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios con penalización activa: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}