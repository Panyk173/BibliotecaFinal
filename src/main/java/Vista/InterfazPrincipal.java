package Vista;

import modelo.dto.Usuario;
import vista.paneles.PanelUsuario;
import vista.paneles.PanelLibro;
import vista.paneles.PanelEjemplar;
import vista.paneles.PanelPrestamo;
import Controlador.UsuarioControlador;
import Controlador.LibroControlador;
import Controlador.EjemplarControlador;
import Controlador.PrestamoControlador;

import javax.swing.*;
import java.awt.*;

public class InterfazPrincipal extends JFrame {

    private final Usuario usuarioAutenticado;

    // Constructor principal
    public InterfazPrincipal(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
        configurarVentana();
        inicializarComponentes();
    }

    // Configura las propiedades principales de la ventana
    private void configurarVentana() {
        setTitle("Biblioteca - Interfaz Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Inicializa los componentes de la ventana
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear el menú lateral
        JPanel menuLateral = crearMenuLateral();

        // Crear el contenedor para los paneles
        JPanel contenedorPaneles = new JPanel(new CardLayout());

        // Instanciar los paneles y sus controladores
        PanelUsuario panelUsuario = new PanelUsuario();
        PanelLibro panelLibro = new PanelLibro();
        PanelEjemplar panelEjemplar = new PanelEjemplar();
        PanelPrestamo panelPrestamo = new PanelPrestamo();

        new UsuarioControlador(panelUsuario);
        new LibroControlador(panelLibro);
        new EjemplarControlador(panelEjemplar);
        new PrestamoControlador(panelPrestamo);

        // Agregar los paneles al contenedor
        contenedorPaneles.add(panelUsuario, "Usuarios");
        contenedorPaneles.add(panelLibro, "Libros");
        contenedorPaneles.add(panelEjemplar, "Ejemplares");
        contenedorPaneles.add(panelPrestamo, "Préstamos");

        // Agregar el menú y los paneles al panel principal
        panelPrincipal.add(menuLateral, BorderLayout.WEST);
        panelPrincipal.add(contenedorPaneles, BorderLayout.CENTER);

        // Mostrar solo las opciones según el tipo de usuario
        if (usuarioAutenticado.getTipo().equalsIgnoreCase("normal")) {
            ocultarOpcionesParaUsuariosNormales(menuLateral);
        }

        // Configurar acción de los botones del menú
        configurarAccionesMenu(menuLateral, contenedorPaneles);

        // Agregar el panel principal a la ventana
        add(panelPrincipal);
    }

    // Crea el menú lateral
    private JPanel crearMenuLateral() {
        JPanel menu = new JPanel(new GridLayout(5, 1, 10, 10));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        menu.add(crearBotonMenu("Usuarios"));
        menu.add(crearBotonMenu("Libros"));
        menu.add(crearBotonMenu("Ejemplares"));
        menu.add(crearBotonMenu("Préstamos"));
        menu.add(crearBotonMenu("Salir"));

        return menu;
    }

    // Crea botones para el menú lateral
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setActionCommand(texto);
        return boton;
    }

    // Configura las acciones de los botones del menú
    private void configurarAccionesMenu(JPanel menuLateral, JPanel contenedorPaneles) {
        CardLayout cardLayout = (CardLayout) contenedorPaneles.getLayout();

        for (Component componente : menuLateral.getComponents()) {
            if (componente instanceof JButton boton) {
                boton.addActionListener(e -> {
                    String actionCommand = e.getActionCommand();
                    if ("Salir".equals(actionCommand)) {
                        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (confirmacion == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    } else {
                        cardLayout.show(contenedorPaneles, actionCommand);
                    }
                });
            }
        }
    }

    // Oculta las opciones no permitidas para usuarios normales
    private void ocultarOpcionesParaUsuariosNormales(JPanel menuLateral) {
        for (Component componente : menuLateral.getComponents()) {
            if (componente instanceof JButton boton) {
                if (boton.getActionCommand().equalsIgnoreCase("Usuarios") || boton.getActionCommand().equalsIgnoreCase("Ejemplares")) {
                    boton.setVisible(false);
                }
            }
        }
    }
}
