package Controlador;

import modelo.dao.EjemplarDAO;
import modelo.dao.PrestamoDAO;
import modelo.dao.UsuarioDAO;
import modelo.dto.Ejemplar;
import modelo.dto.Prestamo;
import modelo.dto.Usuario;
import modelo.validacion.PrestamoValidacion;
import vista.paneles.PanelPrestamo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class PrestamoControlador {

    private final PrestamoDAO prestamoDAO;
    private final UsuarioDAO usuarioDAO;
    private final EjemplarDAO ejemplarDAO;
    private final PanelPrestamo panelPrestamo;

    public PrestamoControlador(PanelPrestamo panelPrestamo) {
        this.prestamoDAO = new PrestamoDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.ejemplarDAO = new EjemplarDAO();
        this.panelPrestamo = panelPrestamo;

        inicializarEventos();
    }

    private void inicializarEventos() {
        panelPrestamo.getBotonRegistrar().addActionListener(e -> registrarPrestamo());
        panelPrestamo.getBotonListar().addActionListener(e -> listarPrestamos());
        panelPrestamo.getBotonDevolucion().addActionListener(e -> registrarDevolucion());
    }

    private void registrarPrestamo() {
        try {
            int usuarioId = Integer.parseInt(panelPrestamo.getCampoUsuarioID().getText());
            int ejemplarId = Integer.parseInt(panelPrestamo.getCampoEjemplarID().getText());

            Usuario usuario = usuarioDAO.buscarUsuarioPorId(usuarioId);
            if (usuario == null) {
                JOptionPane.showMessageDialog(panelPrestamo, "Usuario no encontrado.");
                return;
            }

            Ejemplar ejemplar = ejemplarDAO.buscarEjemplarPorId(ejemplarId);
            if (ejemplar == null || !ejemplar.getEstado().equals("Disponible")) {
                JOptionPane.showMessageDialog(panelPrestamo, "Ejemplar no encontrado o no disponible.");
                return;
            }

            if (prestamoDAO.obtenerPrestamosActivos(usuarioId).size() >= 3) {
                JOptionPane.showMessageDialog(panelPrestamo, "El usuario ya tiene 3 préstamos activos.");
                return;
            }

            Prestamo prestamo = new Prestamo();
            prestamo.setUsuario(usuario);
            prestamo.setEjemplar(ejemplar);
            prestamo.setFechaInicio(LocalDate.now());

            prestamoDAO.guardarPrestamo(prestamo);

            ejemplar.setEstado("Prestado");
            ejemplarDAO.actualizarEjemplar(ejemplar);

            JOptionPane.showMessageDialog(panelPrestamo, "Préstamo registrado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "Error al registrar préstamo: " + ex.getMessage());
        }
    }

    private void listarPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.obtenerTodosLosPrestamos();
            panelPrestamo.mostrarPrestamos(prestamos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "Error al listar préstamos: " + ex.getMessage());
        }
    }

    private void registrarDevolucion() {
        try {
            int prestamoId = Integer.parseInt(panelPrestamo.getCampoPrestamoID().getText());

            Prestamo prestamo = prestamoDAO.buscarPrestamoPorId(prestamoId);
            if (prestamo == null) {
                JOptionPane.showMessageDialog(panelPrestamo, "Préstamo no encontrado.");
                return;
            }

            prestamo.setFechaDevolucion(LocalDate.now());
            prestamoDAO.actualizarPrestamo(prestamo);

            Ejemplar ejemplar = prestamo.getEjemplar();
            ejemplar.setEstado("Disponible");
            ejemplarDAO.actualizarEjemplar(ejemplar);

            JOptionPane.showMessageDialog(panelPrestamo, "Devolución registrada con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "Error al registrar devolución: " + ex.getMessage());
        }
    }
}
