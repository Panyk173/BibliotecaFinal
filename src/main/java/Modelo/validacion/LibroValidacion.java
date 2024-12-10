package Modelo.validacion;

import Modelo.dto.Libro;

public class LibroValidacion {

    public static boolean validarISBN(String isbn) {
        return isbn != null && isbn.matches("\\d{13}");
    }

    public static boolean validarTitulo(String titulo) {
        return titulo != null && !titulo.trim().isEmpty() && titulo.length() <= 200;
    }

    public static boolean validarAutor(String autor) {
        return autor != null && !autor.trim().isEmpty() && autor.length() <= 100;
    }

    public static boolean validarLibro(Libro libro) {
        return validarISBN(libro.getIsbn()) &&
                validarTitulo(libro.getTitulo()) &&
                validarAutor(libro.getAutor());
    }
}
