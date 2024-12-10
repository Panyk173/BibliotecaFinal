package Controlador;

import modelo.dao.LibroDAO;
import modelo.dto.Libro;
import modelo.validacion.LibroValidacion;
import vista.paneles.PanelLibro;

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

            if (!LibroValidacion.validarISBN(isbn) || !LibroValidacion.validarTitulo(titulo) ||
                    !LibroValidacion.validarAutor(autor)) {
                JOptionPane.showMessageDialog(panelLibro, "Datos inválidos.");
                return;
            }

            Libro libro = new Libro();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAutor(autor);

            libroDAO.guardarLibro(libro);
            JOptionPane.showMessageDialog(panelLibro, "Libro agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelLibro, "Error al agregar libro: " + ex.getMessage());
        }
    }

    private void listarLibros() {
        try {
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            panelLibro.mostrarLibros(libros);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelLibro, "Error al listar libros: " + ex.getMessage());
        }
    }

    private void eliminarLibro() {
        try {
            String isbn = panelLibro.getCampoISBN().getText();
            libroDAO.eliminarLibro(isbn);
            JOptionPane.showMessageDialog(panelLibro, "Libro eliminado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelLibro, "Error al eliminar libro: " + ex.getMessage());
        }
    }
}


