package Vista.paneles;

import Modelo.dto.Prestamo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelPrestamo extends JPanel {

    private final JTextField campoUsuarioID = new JTextField(10);
    private final JTextField campoEjemplarID = new JTextField(10);
    private final JTextField campoPrestamoID = new JTextField(10);

    private final JButton botonRegistrar = new JButton("Registrar Préstamo");
    private final JButton botonListar = new JButton("Listar Préstamos");
    private final JButton botonDevolucion = new JButton("Registrar Devolución");

    private final JTextArea areaResultado = new JTextArea(10, 40);

    public PanelPrestamo() {
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Préstamo"));
        panelFormulario.add(new JLabel("Usuario ID:"));
        panelFormulario.add(campoUsuarioID);
        panelFormulario.add(new JLabel("Ejemplar ID:"));
        panelFormulario.add(campoEjemplarID);
        panelFormulario.add(new JLabel("Préstamo ID:"));
        panelFormulario.add(campoPrestamoID);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonListar);
        panelBotones.add(botonDevolucion);

        // Área de resultado
        areaResultado.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollResultado, BorderLayout.SOUTH);
    }

    // Métodos para acceder a los componentes
    public JTextField getCampoUsuarioID() {
        return campoUsuarioID;
    }

    public JTextField getCampoEjemplarID() {
        return campoEjemplarID;
    }

    public JTextField getCampoPrestamoID() {
        return campoPrestamoID;
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonListar() {
        return botonListar;
    }

    public JButton getBotonDevolucion() {
        return botonDevolucion;
    }

    public void mostrarPrestamos(List<Prestamo> prestamos) {
        areaResultado.setText("");
        prestamos.forEach(prestamo -> areaResultado.append(
                "ID Préstamo: " + prestamo.getId() + "\n" +
                        "Usuario: " + prestamo.getUsuario().getNombre() + "\n" +
                        "Ejemplar ID: " + prestamo.getEjemplar().getId() + "\n" +
                        "Fecha Inicio: " + prestamo.getFechaInicio() + "\n" +
                        "Fecha Devolución: " + (prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion() : "No devuelto") + "\n\n"
        ));
    }
}
