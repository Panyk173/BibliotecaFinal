package Controlador;

import Modelo.dao.LibroDAO;
import Modelo.dto.Libro;
import Modelo.validacion.LibroValidacion;
import Vista.paneles.PanelLibro;

import javax.swing.*;
import java.util.List;

public class LibroControlador {

    private final LibroDAO libroDAO;
    private final PanelLibro panelLibro;

    public LibroControlador(PanelLibro panelLibro) {
        this.libroDAO = new LibroDAO();
        this.panelLibro = panelLibro;

        inicializarEventos();
    }

    private void inicializarEventos() {
        panelLibro.getBotonAgregar().addActionListener(e -> agregarLibro());
        panelLibro.getBotonListar().addActionListener(e -> listarLibros());
        panelLibro.getBotonEliminar().addActionListener(e -> eliminarLibro());
    }

    private void agregarLibro() {
        try {
            String isbn = panelLibro.getCampoISBN().getText();
            String titulo = panelLibro.getCampoTitulo().getText();
            String autor = panelLibro.getCampoAutor().getText();

            Libro libro = new Libro();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAutor(autor);

            if (!LibroValidacion.validarLibro(libro)) {
                JOptionPane.showMessageDialog(panelLibro, "Datos inválidos. Por favor revisa los campos.");
                return;
            }

            libroDAO.guardar(libro);
            JOptionPane.showMessageDialog(panelLibro, "Libro agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelLibro, "Error al agregar libro: " + ex.getMessage());
        }
    }

    private void listarLibros() {
        try {
            List<Libro> libros = libroDAO.obtenerTodos();
            panelLibro.mostrarLibros(libros);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelLibro, "Error al listar libros: " + ex.getMessage());
        }
    }

    private void eliminarLibro() {
        try {
            String isbn = panelLibro.getCampoISBN().getText();
            libroDAO.eliminar(isbn);
            JOptionPane.showMessageDialog(panelLibro, "Libro eliminado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelLibro, "Error al eliminar libro: " + ex.getMessage());
        }
    }
}
