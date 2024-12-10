package Controlador;

import Modelo.dao.EjemplarDAO;
import Modelo.dao.LibroDAO;
import Modelo.dto.Ejemplar;
import Modelo.dto.Libro;
import Modelo.validacion.EjemplarValidacion;
import Vista.paneles.PanelEjemplar;

import javax.swing.*;
import java.util.List;

public class EjemplarControlador {

    private final EjemplarDAO ejemplarDAO;
    private final LibroDAO libroDAO;
    private final PanelEjemplar panelEjemplar;

    public EjemplarControlador(PanelEjemplar panelEjemplar) {
        this.ejemplarDAO = new EjemplarDAO();
        this.libroDAO = new LibroDAO();
        this.panelEjemplar = panelEjemplar;

        inicializarEventos();
    }

    private void inicializarEventos() {
        panelEjemplar.getBotonAgregar().addActionListener(e -> agregarEjemplar());
        panelEjemplar.getBotonListar().addActionListener(e -> listarEjemplares());
        panelEjemplar.getBotonEliminar().addActionListener(e -> eliminarEjemplar());
    }

    private void agregarEjemplar() {
        try {
            int id = Integer.parseInt(panelEjemplar.getCampoID().getText());
            String isbn = panelEjemplar.getCampoISBN().getText();
            String estado = panelEjemplar.getCampoEstado().getText();

            Libro libro = libroDAO.buscarPorId(isbn);
            if (libro == null) {
                JOptionPane.showMessageDialog(panelEjemplar, "El libro con ISBN " + isbn + " no existe.");
                return;
            }

            Ejemplar ejemplar = new Ejemplar();
            ejemplar.setId(id);
            ejemplar.setIsbn(libro);
            ejemplar.setEstado(estado);

            if (!EjemplarValidacion.validarEjemplar(ejemplar)) {
                JOptionPane.showMessageDialog(panelEjemplar, "Datos inválidos. Por favor revisa los campos.");
                return;
            }

            ejemplarDAO.guardar(ejemplar);
            JOptionPane.showMessageDialog(panelEjemplar, "Ejemplar agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelEjemplar, "Error al agregar ejemplar: " + ex.getMessage());
        }
    }

    private void listarEjemplares() {
        try {
            List<Ejemplar> ejemplares = ejemplarDAO.obtenerTodos();
            panelEjemplar.mostrarEjemplares(ejemplares);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelEjemplar, "Error al listar ejemplares: " + ex.getMessage());
        }
    }

    private void eliminarEjemplar() {
        try {
            int id = Integer.parseInt(panelEjemplar.getCampoID().getText());
            ejemplarDAO.eliminar(id);
            JOptionPane.showMessageDialog(panelEjemplar, "Ejemplar eliminado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelEjemplar, "Error al eliminar ejemplar: " + ex.getMessage());
        }
    }
}
