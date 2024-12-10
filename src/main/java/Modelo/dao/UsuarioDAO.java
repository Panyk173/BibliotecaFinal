package Modelo.dao;

import Modelo.dto.Usuario;

import java.util.List;

public class UsuarioDAO extends GenericoDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario buscarPorDNI(String dni) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", Usuario.class)
                .setParameter("dni", dni)
                .getSingleResult();
    }

    public List<Usuario> obtenerUsuariosConPenalizacionActiva() {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.penalizacionHasta > CURRENT_DATE", Usuario.class)
                .getResultList();
    }
}
