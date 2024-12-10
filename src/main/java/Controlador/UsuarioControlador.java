package Controlador;

import Modelo.dao.UsuarioDAO;
import Modelo.dto.Usuario;
import Modelo.validacion.UsuarioValidacion;
import Vista.paneles.PanelUsuario;

import javax.swing.*;
import java.util.List;

public class UsuarioControlador {

    private final UsuarioDAO usuarioDAO;
    private final PanelUsuario panelUsuario;

    public UsuarioControlador(PanelUsuario panelUsuario) {
        this.usuarioDAO = new UsuarioDAO();
        this.panelUsuario = panelUsuario;

        inicializarEventos();
    }

    private void inicializarEventos() {
        panelUsuario.getBotonAgregar().addActionListener(e -> agregarUsuario());
        panelUsuario.getBotonListar().addActionListener(e -> listarUsuarios());
        panelUsuario.getBotonEliminar().addActionListener(e -> eliminarUsuario());
    }

    private void agregarUsuario() {
        try {
            String dni = panelUsuario.getCampoDNI().getText();
            String nombre = panelUsuario.getCampoNombre().getText();
            String email = panelUsuario.getCampoEmail().getText();
            String password = panelUsuario.getCampoPassword().getText();
            String tipo = panelUsuario.getCampoTipo().getText();

            Usuario usuario = new Usuario();
            usuario.setDni(dni);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setTipo(tipo);

            if (!UsuarioValidacion.validarUsuario(usuario)) {
                JOptionPane.showMessageDialog(panelUsuario, "Datos inválidos. Por favor revisa los campos.");
                return;
            }

            usuarioDAO.guardar(usuario);
            JOptionPane.showMessageDialog(panelUsuario, "Usuario agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelUsuario, "Error al agregar usuario: " + ex.getMessage());
        }
    }

    private void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            panelUsuario.mostrarUsuarios(usuarios);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelUsuario, "Error al listar usuarios: " + ex.getMessage());
        }
    }

    private void eliminarUsuario() {
        try {
            int id = Integer.parseInt(panelUsuario.getCampoID().getText());
            usuarioDAO.eliminar(id);
            JOptionPane.showMessageDialog(panelUsuario, "Usuario eliminado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelUsuario, "Error al eliminar usuario: " + ex.getMessage());
        }
    }
}
