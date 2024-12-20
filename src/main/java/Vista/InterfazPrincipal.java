package Vista;

import Modelo.dto.Usuario;
import Vista.paneles.PanelUsuario;
import Vista.paneles.PanelLibro;
import Vista.paneles.PanelEjemplar;
import Vista.paneles.PanelPrestamo;
import Controlador.UsuarioControlador;
import Controlador.LibroControlador;
import Controlador.EjemplarControlador;
import Controlador.PrestamoControlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfazPrincipal extends JFrame {

    private final Usuario usuarioAutenticado;
    private JPanel contenedorPaneles;

    public InterfazPrincipal(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Biblioteca - Interfaz Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel menuLateral = crearMenuLateral();

        contenedorPaneles = new JPanel(new CardLayout());

        PanelUsuario panelUsuario = new PanelUsuario();
        PanelLibro panelLibro = new PanelLibro();
        PanelEjemplar panelEjemplar = new PanelEjemplar();
        PanelPrestamo panelPrestamo = new PanelPrestamo();

        new UsuarioControlador(panelUsuario);
        new LibroControlador(panelLibro);
        new EjemplarControlador(panelEjemplar);
        new PrestamoControlador(panelPrestamo);

        contenedorPaneles.add(panelUsuario, "Usuarios");
        contenedorPaneles.add(panelLibro, "Libros");
        contenedorPaneles.add(panelEjemplar, "Ejemplares");
        contenedorPaneles.add(panelPrestamo, "Préstamos");

        panelPrincipal.add(menuLateral, BorderLayout.WEST);
        panelPrincipal.add(contenedorPaneles, BorderLayout.CENTER);

        if (usuarioAutenticado.getTipo().equalsIgnoreCase("normal")) {
            ocultarOpcionesParaUsuariosNormales(menuLateral);
        }

        add(panelPrincipal);
    }

    private JPanel crearMenuLateral() {
        JPanel menu = new JPanel(new GridLayout(5, 1, 10, 10));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        menu.add(crearBotonMenu("Usuarios", e -> cambiarPanel("Usuarios")));
        menu.add(crearBotonMenu("Libros", e -> cambiarPanel("Libros")));
        menu.add(crearBotonMenu("Ejemplares", e -> cambiarPanel("Ejemplares")));
        menu.add(crearBotonMenu("Préstamos", e -> cambiarPanel("Préstamos")));
        menu.add(crearBotonMenu("Salir", e -> salirAplicacion()));

        return menu;
    }

    private JButton crearBotonMenu(String texto, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.setActionCommand(texto);
        boton.addActionListener(listener);
        return boton;
    }

    private void cambiarPanel(String nombrePanel) {
        CardLayout layout = (CardLayout) contenedorPaneles.getLayout();
        layout.show(contenedorPaneles, nombrePanel);
    }

    private void ocultarOpcionesParaUsuariosNormales(JPanel menuLateral) {
        Component[] componentes = menuLateral.getComponents();
        if (componentes.length > 1) {
            componentes[0].setEnabled(false); // Deshabilitar "Usuarios"
        }
    }

    private void salirAplicacion() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
