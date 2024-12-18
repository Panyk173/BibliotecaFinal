package Vista.paneles;

import Modelo.dto.Libro;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelLibro extends JPanel {

    private final JTextField campoISBN = new JTextField(15);
    private final JTextField campoTitulo = new JTextField(20);
    private final JTextField campoAutor = new JTextField(20);

    private final JButton botonAgregar = new JButton("Agregar Libro");
    private final JButton botonListar = new JButton("Listar Libros");
    private final JButton botonEliminar = new JButton("Eliminar Libro");

    private final JTextArea areaResultado = new JTextArea(10, 40);

    public PanelLibro() {
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));
        panelFormulario.add(new JLabel("ISBN:"));
        panelFormulario.add(campoISBN);
        panelFormulario.add(new JLabel("Titulo:"));
        panelFormulario.add(campoTitulo);
        panelFormulario.add(new JLabel("Autor:"));
        panelFormulario.add(campoAutor);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAgregar);
        panelBotones.add(botonListar);
        panelBotones.add(botonEliminar);

        areaResultado.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollResultado, BorderLayout.SOUTH);
    }

    public JTextField getCampoISBN() { return campoISBN; }
    public JTextField getCampoTitulo() { return campoTitulo; }
    public JTextField getCampoAutor() { return campoAutor; }

    public JButton getBotonAgregar() { return botonAgregar; }
    public JButton getBotonListar() { return botonListar; }
    public JButton getBotonEliminar() { return botonEliminar; }

    public void mostrarLibros(List<Libro> libros) {
        areaResultado.setText("");
        libros.forEach(libro -> areaResultado.append(
                "ISBN: " + libro.getIsbn() + "\n" +
                        "Titulo: " + libro.getTitulo() + "\n" +
                        "Autor: " + libro.getAutor() + "\n\n"
        ));
    }
}
