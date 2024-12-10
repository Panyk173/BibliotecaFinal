package Vista;

import Modelo.dto.Usuario;
import Vista.paneles.PanelEjemplar;
import Vista.paneles.PanelLibro;
import Vista.paneles.PanelPrestamo;
import Vista.paneles.PanelUsuario;

import javax.swing.*;
import java.awt.*;

public class InterfazUsuarioAdmin extends JFrame {

    private final Usuario usuarioAutenticado;

    public InterfazUsuarioAdmin(Usuario usuario) {
        this.usuarioAutenticado = usuario;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Gestión Administrativa - Biblioteca");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JTabbedPane pestanas = new JTabbedPane();

        pestanas.addTab("Gestión de Usuarios", new PanelUsuario());
        pestanas.addTab("Gestión de Libros", new PanelLibro());
        pestanas.addTab("Gestión de Ejemplares", new PanelEjemplar());
        pestanas.addTab("Gestión de Préstamos", new PanelPrestamo());

        panelPrincipal.add(pestanas, BorderLayout.CENTER);
        add(panelPrincipal);

        JLabel labelBienvenida = new JLabel("Bienvenido, Administrador: " + usuarioAutenticado.getNombre(), JLabel.CENTER);
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(labelBienvenida, BorderLayout.NORTH);
    }
}
