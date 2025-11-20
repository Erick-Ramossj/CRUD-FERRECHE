package vallegrade.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {

    public MainMenuView() {
        setTitle("Menú Principal - CRUD Tienda");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel principal con diseño mejorado
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // -------- HEADER --------
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 98, 255));
        headerPanel.setPreferredSize(new Dimension(0, 100));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("🏢 Sistema de Gestión", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Valle Grande - Ferretería", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(200, 220, 255));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setBackground(new Color(41, 98, 255));
        textPanel.add(titulo);
        textPanel.add(subtitulo);

        headerPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // -------- BOTONES --------
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(new Color(245, 247, 250));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnCategoria = createMenuButton("📁 CRUD Categorías", new Color(108, 117, 125));
        JButton btnProducto = createMenuButton("📦 CRUD Productos", new Color(40, 167, 69));
        JButton btnUsuario = createMenuButton("👥 CRUD Usuarios", new Color(23, 162, 184));

        buttonPanel.add(btnCategoria);
        buttonPanel.add(btnProducto);
        buttonPanel.add(btnUsuario);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // -------- FOOTER --------
        JLabel footer = new JLabel("Seleccione una opción para continuar", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footer.setForeground(new Color(108, 117, 125));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(footer, BorderLayout.SOUTH);

        // -------- ACCIONES DE LOS BOTONES --------
        btnProducto.addActionListener(e -> {
            this.dispose(); // Cierra el menú principal
            new FrmProducto().setVisible(true); // Abre el CRUD de productos
        });

        btnUsuario.addActionListener(e -> {
            this.dispose(); // Cierra el menú principal
            new FrmCliente().setVisible(true); // Abre el CRUD de usuarios
        });

        btnCategoria.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Módulo de Categorías aún no implementado",
                    "En desarrollo",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private JButton createMenuButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(0, 60));

        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuView().setVisible(true);
        });
    }
}