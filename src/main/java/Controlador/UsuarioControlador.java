// Clase UsuarioControlador actualizada para manejar el JTextArea
package Controlador;

import Modelo.dao.GenericoDAO;
import Modelo.dto.Usuario;
import Modelo.validacion.UsuarioValidacion;
import Vista.paneles.PanelUsuario;

import javax.swing.*;
import java.util.List;

public class UsuarioControlador {

    private final GenericoDAO<Usuario> usuarioDAO;
    private final PanelUsuario panelUsuario;

    public UsuarioControlador(PanelUsuario panelUsuario) {
        this.usuarioDAO = new GenericoDAO<>(Usuario.class);
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
                panelUsuario.mostrarMensaje("Datos inválidos. Por favor revisa los campos.");
                return;
            }

            usuarioDAO.guardar(usuario);
            panelUsuario.mostrarMensaje("Usuario agregado con éxito.");
        } catch (Exception e) {
            panelUsuario.mostrarMensaje("Error al agregar usuario: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            if (usuarios.isEmpty()) {
                panelUsuario.mostrarMensaje("No hay usuarios para mostrar.");
            } else {
                panelUsuario.mostrarUsuarios(usuarios);
            }
        } catch (Exception e) {
            panelUsuario.mostrarMensaje("Error al listar usuarios: " + e.getMessage());
        }
    }

    private void eliminarUsuario() {
        try {
            int id = Integer.parseInt(panelUsuario.getCampoID().getText());
            Usuario usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null) {
                panelUsuario.mostrarMensaje("Usuario no encontrado.");
                return;
            }
            usuarioDAO.eliminar(id);
            panelUsuario.mostrarMensaje("Usuario eliminado con éxito.");
        } catch (NumberFormatException e) {
            panelUsuario.mostrarMensaje("El ID ingresado no es válido.");
        } catch (Exception e) {
            panelUsuario.mostrarMensaje("Error al eliminar usuario: " + e.getMessage());
        }
    }
}