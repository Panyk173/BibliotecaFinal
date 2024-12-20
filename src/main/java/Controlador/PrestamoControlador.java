package Controlador;

import Modelo.dao.EjemplarDAO;
import Modelo.dao.PrestamoDAO;
import Modelo.dao.UsuarioDAO;
import Modelo.dto.Prestamo;
import Modelo.dto.Usuario;
import Modelo.dto.Ejemplar;
import Modelo.validacion.PrestamoValidacion;
import Vista.paneles.PanelPrestamo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class PrestamoControlador {

    private final PrestamoDAO prestamoDAO;
    private final UsuarioDAO usuarioDAO;
    private final EjemplarDAO ejemplarDAO;
    private final PanelPrestamo panelPrestamo;

    public PrestamoControlador(final PanelPrestamo panelPrestamo) {
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
            final int usuarioId = Integer.parseInt(panelPrestamo.getCampoUsuarioID().getText().trim());
            final int ejemplarId = Integer.parseInt(panelPrestamo.getCampoEjemplarID().getText().trim());

            final Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
            final Ejemplar ejemplar = ejemplarDAO.buscarPorId(ejemplarId);

            if (usuario == null || ejemplar == null || !ejemplar.getEstado().equalsIgnoreCase("Disponible")) {
                JOptionPane.showMessageDialog(panelPrestamo, "Datos inválidos o ejemplar no disponible.");
                return;
            }

            final Prestamo prestamo = new Prestamo();
            prestamo.setUsuario(usuario);
            prestamo.setEjemplar(ejemplar);
            prestamo.setFechaInicio(LocalDate.now());

            if (!PrestamoValidacion.validarPrestamo(prestamo)) {
                JOptionPane.showMessageDialog(panelPrestamo, "Préstamo inválido.");
                return;
            }

            prestamoDAO.guardar(prestamo);
            ejemplar.setEstado("Prestado");
            ejemplarDAO.actualizar(ejemplar);
            JOptionPane.showMessageDialog(panelPrestamo, "Préstamo registrado con éxito.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "IDs deben ser números válidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "Error al registrar préstamo: " + ex.getMessage());
        }
    }

    private void listarPrestamos() {
        try {
            final List<Prestamo> prestamos = prestamoDAO.obtenerTodos();
            panelPrestamo.mostrarPrestamos(prestamos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "Error al listar préstamos: " + ex.getMessage());
        }
    }

    private void registrarDevolucion() {
        try {
            final int prestamoId = Integer.parseInt(panelPrestamo.getCampoPrestamoID().getText().trim());
            final Prestamo prestamo = prestamoDAO.buscarPorId(prestamoId);
            if (prestamo == null || prestamo.getFechaDevolucion() != null) {
                JOptionPane.showMessageDialog(panelPrestamo, "Préstamo no válido.");
                return;
            }

            prestamo.setFechaDevolucion(LocalDate.now());
            prestamoDAO.actualizar(prestamo);

            final Ejemplar ejemplar = prestamo.getEjemplar();
            ejemplar.setEstado("Disponible");
            ejemplarDAO.actualizar(ejemplar);
            JOptionPane.showMessageDialog(panelPrestamo, "Devolución registrada con éxito.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "El ID del préstamo debe ser un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrestamo, "Error al registrar devolución: " + ex.getMessage());
        }
    }
}
