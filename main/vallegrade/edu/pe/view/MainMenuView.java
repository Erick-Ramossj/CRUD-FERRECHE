package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuView extends JFrame {

    // Paleta de colores profesional
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);
    private final Color COLOR_PRODUCTOS = new Color(39, 174, 96);
    private final Color COLOR_USUARIOS = new Color(142, 68, 173);
    private final Color COLOR_CATEGORIAS = new Color(230, 126, 34);
    private final Color COLOR_BACKGROUND = new Color(236, 240, 241);
    private final Color COLOR_WHITE = Color.WHITE;
    private final Color COLOR_TEXT_DARK = new Color(44, 62, 80);
    private final Color COLOR_TEXT_LIGHT = new Color(127, 140, 141);

    public MainMenuView() {
        setTitle("Sistema de Gestión - Valle Grande");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(COLOR_BACKGROUND);
        add(mainPanel);

        // =============== HEADER SUPERIOR ===============
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(COLOR_PRIMARY);
        headerPanel.setPreferredSize(new Dimension(0, 150));
        headerPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        // Icono/Logo simulado
        JLabel iconLabel = new JLabel("⚙");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        iconLabel.setForeground(COLOR_WHITE);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título principal
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(COLOR_WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("Valle Grande - Ferretería Industrial");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitulo.setForeground(new Color(189, 195, 199));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(iconLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(titulo);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subtitulo);
        headerPanel.add(Box.createVerticalGlue());

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // =============== PANEL CENTRAL CON CARDS ===============
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(COLOR_BACKGROUND);
        centerPanel.setBorder(new EmptyBorder(40, 50, 40, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Crear las tarjetas de menú
        JPanel cardProductos = crearCard(
                "📦",
                "GESTIÓN DE PRODUCTOS",
                "Administrar catálogo de productos",
                COLOR_PRODUCTOS
        );

        JPanel cardUsuarios = crearCard(
                "👥",
                "GESTIÓN DE USUARIOS",
                "Administrar clientes y usuarios",
                COLOR_USUARIOS
        );

        JPanel cardCategorias = crearCard(
                "📁",
                "GESTIÓN DE Direccion",
                "Organizar productos por categorías",
                COLOR_CATEGORIAS
        );

        centerPanel.add(cardProductos, gbc);
        centerPanel.add(cardUsuarios, gbc);
        centerPanel.add(cardCategorias, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // =============== FOOTER ===============
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(COLOR_WHITE);
        footerPanel.setPreferredSize(new Dimension(0, 60));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(189, 195, 199)));

        JLabel footerLabel = new JLabel("© 2025 Valle Grande - Todos los derechos reservados");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(COLOR_TEXT_LIGHT);

        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // =============== EVENTOS ===============
        cardProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirProductos();
            }
        });

        cardUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirUsuarios();
            }
        });

        cardCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirDireccion();
            }
        });
    }

    // =============== MÉTODO PARA CREAR CARDS ===============
    private JPanel crearCard(String icono, String titulo, String descripcion, Color colorAccent) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 15));
        card.setBackground(COLOR_WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(20, 25, 20, 25)
        ));
        card.setPreferredSize(new Dimension(550, 100));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Panel izquierdo con icono
        JPanel iconPanel = new JPanel(new GridBagLayout());
        iconPanel.setBackground(colorAccent);
        iconPanel.setPreferredSize(new Dimension(80, 80));
        iconPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 42));
        lblIcono.setForeground(COLOR_WHITE);
        iconPanel.add(lblIcono);

        // Panel derecho con texto
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(COLOR_WHITE);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COLOR_TEXT_DARK);

        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDescripcion.setForeground(COLOR_TEXT_LIGHT);

        textPanel.add(lblTitulo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(lblDescripcion);

        // Panel para la flecha
        JPanel arrowPanel = new JPanel(new GridBagLayout());
        arrowPanel.setBackground(COLOR_WHITE);
        arrowPanel.setPreferredSize(new Dimension(40, 80));

        JLabel lblArrow = new JLabel("→");
        lblArrow.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblArrow.setForeground(colorAccent);
        arrowPanel.add(lblArrow);

        card.add(iconPanel, BorderLayout.WEST);
        card.add(textPanel, BorderLayout.CENTER);
        card.add(arrowPanel, BorderLayout.EAST);

        // Efectos hover
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(250, 250, 250));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(colorAccent, 2),
                        new EmptyBorder(19, 24, 19, 24)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(COLOR_WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                        new EmptyBorder(20, 25, 20, 25)
                ));
            }
        });

        return card;
    }

    // =============== MÉTODOS DE NAVEGACIÓN ===============
    private void abrirProductos() {
        this.dispose();
        new FrmProducto().setVisible(true);
    }

    private void abrirUsuarios() {
        this.dispose();
        new FrmCliente().setVisible(true);
    }

    private void abrirDireccion() {
        this.dispose();
        // CORRECCIÓN: uso del constructor correcto
        new FrmDireccion(this, 0).setVisible(true);
    }

}
