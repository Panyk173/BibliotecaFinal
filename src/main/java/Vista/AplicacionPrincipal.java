package Vista;

import Modelo.dao.EntityManagerFactoryConnector;
import Modelo.dto.Usuario;

import javax.swing.SwingUtilities;

public class AplicacionPrincipal {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                LoginDialogo loginDialogo = new LoginDialogo();
                loginDialogo.setVisible(true);

                if (loginDialogo.isAutenticado()) {
                    Usuario usuario = loginDialogo.getUsuarioAutenticado();
                    if ("administrador".equalsIgnoreCase(usuario.getTipo())) {
                        InterfazUsuarioAdmin interfazAdministrador = new InterfazUsuarioAdmin(usuario);
                        interfazAdministrador.setVisible(true);
                    } else {
                        InterfazUsuarioNormal interfazUsuarioNormal = new InterfazUsuarioNormal(usuario);
                        interfazUsuarioNormal.setVisible(true);
                    }
                }
            });
        } finally {
            EntityManagerFactoryConnector.closeEntityManagerFactory();
        }
    }
}
