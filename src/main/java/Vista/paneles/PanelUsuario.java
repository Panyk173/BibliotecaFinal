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

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Usuario"));
        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(campoID);
        panelFormulario.add(new JLabel("DNI:"));
        panelFormulario.add(campoDNI);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(campoEmail);
        panelFormulario.add(new JLabel("Password:"));
        panelFormulario.add(campoPassword);
        panelFormulario.add(new JLabel("Tipo:"));
        panelFormulario.add(campoTipo);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAgregar);
        panelBotones.add(botonListar);
        panelBotones.add(botonEliminar);

        // Area de resultado
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

    public JTextField getCampoDNI() {
        return campoDNI;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoEmail() {
        return campoEmail;
    }

    public JTextField getCampoPassword() {
        return campoPassword;
    }

    public JTextField getCampoTipo() {
        return campoTipo;
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

    public void mostrarUsuarios(List<Usuario> usuarios) {
        areaResultado.setText("");
        usuarios.forEach(usuario -> areaResultado.append(
                "ID: " + usuario.getId() + "\n" +
                        "DNI: " + usuario.getDni() + "\n" +
                        "Nombre: " + usuario.getNombre() + "\n" +
                        "Email: " + usuario.getEmail() + "\n" +
                        "Tipo: " + usuario.getTipo() + "\n\n"
        ));
    }
}
