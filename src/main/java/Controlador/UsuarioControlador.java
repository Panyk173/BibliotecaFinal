package Controlador;

import modelo.dao.UsuarioDAO;
import modelo.dto.Usuario;
import modelo.validacion.UsuarioValidacion;
import vista.paneles.PanelUsuario;

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

            if (!UsuarioValidacion.validarDNI(dni) || !UsuarioValidacion.validarEmail(email) ||
                    !UsuarioValidacion.validarPassword(password) || !UsuarioValidacion.validarTipo(tipo)) {
                JOptionPane.showMessageDialog(panelUsuario, "Datos inválidos.");
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setDni(dni);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setTipo(tipo);

            usuarioDAO.guardarUsuario(usuario);
            JOptionPane.showMessageDialog(panelUsuario, "Usuario agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelUsuario, "Error al agregar usuario: " + ex.getMessage());
        }
    }

    private void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
            panelUsuario.mostrarUsuarios(usuarios);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelUsuario, "Error al listar usuarios: " + ex.getMessage());
        }
    }

    private void eliminarUsuario() {
        try {
            int id = Integer.parseInt(panelUsuario.getCampoID().getText());
            usuarioDAO.eliminarUsuario(id);
            JOptionPane.showMessageDialog(panelUsuario, "Usuario eliminado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelUsuario, "Error al eliminar usuario: " + ex.getMessage());
        }
    }
}


