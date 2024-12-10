package Vista;

import Modelo.dao.UsuarioDAO;
import Modelo.dto.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginDialogo extends JDialog {

    private boolean autenticado = false;
    private Usuario usuarioAutenticado;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private JTextField campoDNI;
    private JPasswordField campoPassword;

    public LoginDialogo() {
        configurarVentana();
        JPanel panelPrincipal = configurarPanelPrincipal();
        agregarCamposUsuarioYContrasena(panelPrincipal);
        agregarBotones(panelPrincipal);
        agregarPanelAlDialogo(panelPrincipal);
    }

    private void configurarVentana() {
        setTitle("Inicio de Sesión - Biblioteca");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private void agregarPanelAlDialogo(JPanel panel) {
        add(panel); // Agrega el panel principal al contenido del JDialog
    }

    private JPanel configurarPanelPrincipal() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private void agregarCamposUsuarioYContrasena(JPanel panel) {
        panel.add(new JLabel("DNI:"));
        campoDNI = new JTextField(15);
        panel.add(campoDNI);

        panel.add(new JLabel("Contraseña:"));
        campoPassword = new JPasswordField(15);
        panel.add(campoPassword);
    }

    private void agregarBotones(JPanel panel) {
        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.addActionListener(e -> manejarLogin());
        panel.add(botonLogin);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(e -> dispose());
        panel.add(botonCancelar);
    }

    private void manejarLogin() {
        String dni = campoDNI.getText().trim();
        String password = new String(campoPassword.getPassword()).trim();

        if (dni.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Optional<Usuario> usuarioOpt = usuarioDAO.obtenerTodos()
                    .stream()
                    .filter(usuario -> usuario.getDni().equals(dni) && usuario.getPassword().equals(password))
                    .findFirst();

            if (usuarioOpt.isPresent()) {
                usuarioAutenticado = usuarioOpt.get();
                autenticado = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar autenticar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
