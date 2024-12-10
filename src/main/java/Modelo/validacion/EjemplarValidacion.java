package Modelo.validacion;

import Modelo.dto.Ejemplar;
import Modelo.dto.Libro;

public class EjemplarValidacion {

    public static boolean validarId(Integer id) {
        return id != null && id > 0;
    }

    public static boolean validarEstado(String estado) {
        return estado != null && (estado.equalsIgnoreCase("Disponible") ||
                estado.equalsIgnoreCase("Prestado") ||
                estado.equalsIgnoreCase("Dañado"));
    }

    public static boolean validarLibro(Libro libro) {
        return libro != null && LibroValidacion.validarISBN(libro.getIsbn());
    }

    public static boolean validarEjemplar(Ejemplar ejemplar) {
        return validarId(ejemplar.getId()) &&
                validarEstado(ejemplar.getEstado()) &&
                validarLibro(ejemplar.getIsbn());
    }
}
