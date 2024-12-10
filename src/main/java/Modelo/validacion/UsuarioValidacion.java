package Modelo.validacion;

import Modelo.dto.Usuario;

public class UsuarioValidacion {

    public static boolean validarDNI(String dni) {
        return dni != null && dni.matches("\\d{8}[A-Z]");
    }

    public static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty() && nombre.length() <= 100;
    }

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    public static boolean validarPassword(String password) {
        return password != null && password.length() >= 6;
    }

    public static boolean validarTipo(String tipo) {
        return tipo != null && (tipo.equalsIgnoreCase("normal") || tipo.equalsIgnoreCase("administrador"));
    }

    public static boolean validarUsuario(Usuario usuario) {
        return validarDNI(usuario.getDni()) &&
                validarNombre(usuario.getNombre()) &&
                validarEmail(usuario.getEmail()) &&
                validarPassword(usuario.getPassword()) &&
                validarTipo(usuario.getTipo());
    }
}
