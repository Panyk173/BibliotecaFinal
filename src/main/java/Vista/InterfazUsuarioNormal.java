package Vista;

import Modelo.dto.Usuario;
import Modelo.dao.PrestamoDAO;
import Modelo.dto.Prestamo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfazUsuarioNormal extends JFrame {

    private final Usuario usuario;
    private final PrestamoDAO prestamoDAO;
    private JTable tablaPrestamos;

    public InterfazUsuarioNormal(Usuario usuario) {
        this.usuario = usuario;
        this.prestamoDAO = new PrestamoDAO();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Préstamos - Usuario Normal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        tablaPrestamos = new JTable();
        actualizarTablaPrestamos();
        panelPrincipal.add(new JScrollPane(tablaPrestamos), BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void actualizarTablaPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.obtenerPrestamosActivosPorUsuario(usuario.getId());
            String[] columnas = {"ID Préstamo", "Ejemplar ID", "Fecha Inicio", "Fecha Límite"};
            String[][] datos = prestamos.stream()
                    .map(p -> new String[]{
                            String.valueOf(p.getId()),
                            String.valueOf(p.getEjemplar().getId()),
                            String.valueOf(p.getFechaInicio()),
                            String.valueOf(p.getFechaInicio().plusDays(15))
                    })
                    .toArray(String[][]::new);

            tablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar préstamos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
