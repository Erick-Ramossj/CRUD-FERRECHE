package vallegrade.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {

    public MainMenuView() {
        setTitle("Menú Principal - CRUD Tienda");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titulo = new JLabel("Sistema de Gestión - Valle Grande", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(titulo);

        JButton btnCategoria = new JButton("CRUD Categorías");
        JButton btnProducto = new JButton("CRUD Productos");
        JButton btnUsuario = new JButton("CRUD Usuarios");

        add(btnCategoria);
        add(btnProducto);
        add(btnUsuario);

        // Acciones de los botones
        btnCategoria.addActionListener(e -> new FrmCategoria().setVisible(true));
        btnProducto.addActionListener(e -> new FrmProducto().setVisible(true));
        btnUsuario.addActionListener(e -> new FrmUsuario().setVisible(true));
    }
}
