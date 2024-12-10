package Vista.paneles;

import Modelo.dto.Ejemplar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEjemplar extends JPanel {

    private final JTextField campoID = new JTextField(10);
    private final JTextField campoISBN = new JTextField(15);
    private final JTextField campoEstado = new JTextField(15);

    private final JButton botonAgregar = new JButton("Agregar Ejemplar");
    private final JButton botonListar = new JButton("Listar Ejemplares");
    private final JButton botonEliminar = new JButton("Eliminar Ejemplar");

    private final JTextArea areaResultado = new JTextArea(10, 40);

    public PanelEjemplar() {
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Ejemplar"));
        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(campoID);
        panelFormulario.add(new JLabel("ISBN:"));
        panelFormulario.add(campoISBN);
        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(campoEstado);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAgregar);
        panelBotones.add(botonListar);
        panelBotones.add(botonEliminar);

        // Área de resultado
        areaResultado.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollResultado, BorderLayout.SOUTH);
    }

    // Métodos para acceder a los componentes
    public JTextField getCampoID() {
        return campoID;
    }

    public JTextField getCampoISBN() {
        return campoISBN;
    }

    public JTextField getCampoEstado() {
        return campoEstado;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonListar() {
        return botonListar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void mostrarEjemplares(List<Ejemplar> ejemplares) {
        areaResultado.setText("");
        ejemplares.forEach(ejemplar -> areaResultado.append(
                "ID: " + ejemplar.getId() + "\n" +
                        "ISBN: " + ejemplar.getIsbn().getIsbn() + "\n" +
                        "Estado: " + ejemplar.getEstado() + "\n\n"
        ));
    }
}
