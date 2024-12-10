package Vista;
import Modelo.dto.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class LoginDialogo extends JDialog {

    private boolean autenticado = false;
    private Usuario usuarioAutenticado;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginDialogo() {
        configurarVentana();
        JPanel panel = configurarPanelPrincipal();
        agregarCamposUsuarioYContrasena(panel);
        agregarBotones(panel);
        agregarPanelAlDialogo(panel);
    }

    // Configura las propiedades principales del cuadro de dialogo
    private void configurarVentana() {
        setTitle("Inicio de Sesión - Biblioteca");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // Crea el panel principal con el formulario de login
    private JPanel configurarPanelPrincipal() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }

    // Agrega campos de texto para el DNI y la contraseña
    private JTextField campoDNI;
    private JPasswordField campoPassword;

    private void agregarCamposUsuarioYContrasena(JPanel panel) {
        panel.add(new JLabel("DNI:"));
        campoDNI = new JTextField(15);
        panel.add(campoDNI);

        panel.add(new JLabel("Contraseña:"));
        campoPassword = new JPasswordField(15);
        panel.add(campoPassword);
    }

    // Agrega botones de login y cancelar
    private void agregarBotones(JPanel panel) {
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(this::manejarLogin);
        panel.add(loginButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);
    }

    // Maneja el proceso de autenticación
    private void manejarLogin(ActionEvent e) {
        String dni = campoDNI.getText().trim();
        String password = new String(campoPassword.getPassword()).trim();

        if (dni.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Optional<Usuario> usuarioOpt = usuarioDAO.obtenerTodosLosUsuarios().stream()
                .filter(usuario -> usuario.getDni().equals(dni) && usuario.getPassword().equals(password))
                .findFirst();

        if (usuarioOpt.isPresent()) {
            usuarioAutenticado = usuarioOpt.get();
            autenticado = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Agrega el panel principal al cuadro de dialogo
    private void agregarPanelAlDialogo(JPanel panel) {
        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    // Devuelve si el usuario está autenticado
    public boolean isAutenticado() {
        return autenticado;
    }

    // Devuelve el usuario autenticado
    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
