package Modelo.validacion;

import Modelo.dto.Prestamo;
import Modelo.dto.Usuario;
import Modelo.dto.Ejemplar;

import java.time.LocalDate;

public class PrestamoValidacion {

    public static boolean validarUsuario(Usuario usuario) {
        return usuario != null && UsuarioValidacion.validarDNI(usuario.getDni());
    }

    public static boolean validarEjemplar(Ejemplar ejemplar) {
        return ejemplar != null && EjemplarValidacion.validarId(ejemplar.getId()) &&
                ejemplar.getEstado().equalsIgnoreCase("Disponible");
    }

    public static boolean validarFechaInicio(LocalDate fechaInicio) {
        return fechaInicio != null && !fechaInicio.isAfter(LocalDate.now());
    }

    public static boolean validarPrestamo(Prestamo prestamo) {
        return validarUsuario(prestamo.getUsuario()) &&
                validarEjemplar(prestamo.getEjemplar()) &&
                validarFechaInicio(prestamo.getFechaInicio());
    }
}
