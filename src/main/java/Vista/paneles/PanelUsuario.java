package Vista.paneles;

import Modelo.dto.Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelUsuario extends JPanel {

    private final JTextField campoID = new JTextField(10);
    private final JTextField campoDNI = new JTextField(15);
    private final JTextField campoNombre = new JTextField(15);
    private final JTextField campoEmail = new JTextField(15);
    private final JTextField campoPassword = new JTextField(15);
    private final JTextField campoTipo = new JTextField(10);

    private final JButton botonAgregar = new JButton("Agregar Usuario");
    private final JButton botonListar = new JButton("Listar Usuarios");
    private final JButton botonEliminar = new JButton("Eliminar Usuario");

    private final JTextArea areaResultado = new JTextArea(10, 40);

    public PanelUsuario() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(6, 2, 5, 5));
        formulario.add(new JLabel("ID:"));
        formulario.add(campoID);
        formulario.add(new JLabel("DNI:"));
        formulario.add(campoDNI);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(campoNombre);
        formulario.add(new JLabel("Email:"));
        formulario.add(campoEmail);
        formulario.add(new JLabel("Password:"));
        formulario.add(campoPassword);
        formulario.add(new JLabel("Tipo:"));
        formulario.add(campoTipo);

        JPanel botones = new JPanel(new FlowLayout());
        botones.add(botonAgregar);
        botones.add(botonListar);
        botones.add(botonEliminar);

        areaResultado.setEditable(false);
        add(formulario, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
        add(new JScrollPane(areaResultado), BorderLayout.SOUTH);
    }

    public JButton getBotonAgregar() { return botonAgregar; }
    public JButton getBotonListar() { return botonListar; }
    public JButton getBotonEliminar() { return botonEliminar; }

    public JTextField getCampoID() { return campoID; }
    public JTextField getCampoDNI() { return campoDNI; }
    public JTextField getCampoNombre() { return campoNombre; }
    public JTextField getCampoEmail() { return campoEmail; }
    public JTextField getCampoPassword() { return campoPassword; }
    public JTextField getCampoTipo() { return campoTipo; }

    public void mostrarUsuarios(List<Usuario> usuarios) {
        areaResultado.setText("");
        if (usuarios == null || usuarios.isEmpty()) {
            areaResultado.append("No hay usuarios para mostrar.\n");
            return;
        }
        for (Usuario usuario : usuarios) {
            areaResultado.append(usuario.toString() + "\n");
        }
    }

    public void mostrarMensaje(String mensaje) {
        areaResultado.setText(mensaje);
    }
}
