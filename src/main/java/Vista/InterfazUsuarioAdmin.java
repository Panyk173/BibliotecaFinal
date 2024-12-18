package Vista;

import Modelo.dto.Usuario;
import Vista.paneles.PanelEjemplar;
import Vista.paneles.PanelLibro;
import Vista.paneles.PanelPrestamo;
import Vista.paneles.PanelUsuario;
import Controlador.UsuarioControlador;
import Controlador.LibroControlador;
import Controlador.EjemplarControlador;
import Controlador.PrestamoControlador;

import javax.swing.*;
import java.awt.*;

public class InterfazUsuarioAdmin extends JFrame {

    private final Usuario usuarioAutenticado;

    public InterfazUsuarioAdmin(Usuario usuario) {
        if (usuario == null || usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El usuario autenticado no es válido.");
        }
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

        PanelUsuario panelUsuario = new PanelUsuario();
        PanelLibro panelLibro = new PanelLibro();
        PanelEjemplar panelEjemplar = new PanelEjemplar();
        PanelPrestamo panelPrestamo = new PanelPrestamo();

        new UsuarioControlador(panelUsuario);
        new LibroControlador(panelLibro);
        new EjemplarControlador(panelEjemplar);
        new PrestamoControlador(panelPrestamo);

        pestanas.addTab("Gestión de Usuarios", panelUsuario);
        pestanas.addTab("Gestión de Libros", panelLibro);
        pestanas.addTab("Gestión de Ejemplares", panelEjemplar);
        pestanas.addTab("Gestión de Préstamos", panelPrestamo);

        JLabel labelBienvenida = new JLabel("Bienvenido, Administrador: " + usuarioAutenticado.getNombre(), JLabel.CENTER);
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 16));

        panelPrincipal.add(labelBienvenida, BorderLayout.NORTH);
        panelPrincipal.add(pestanas, BorderLayout.CENTER);

        add(panelPrincipal);
    }
}
