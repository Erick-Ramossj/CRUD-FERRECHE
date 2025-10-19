package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrmUsuario extends JFrame {

    public JTable tabla;
    public JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    public JTextField txtId, txtUsername, txtPassword, txtRol;
    private DefaultTableModel modelo;

    public FrmUsuario() {
        setTitle("Gestión de Usuarios");
        setSize(750, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        txtId = new JTextField(); txtId.setEditable(false);
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtRol = new JTextField();

        panelCampos.add(new JLabel("ID:")); panelCampos.add(txtId);
        panelCampos.add(new JLabel("Usuario:")); panelCampos.add(txtUsername);
        panelCampos.add(new JLabel("Contraseña:")); panelCampos.add(txtPassword);
        panelCampos.add(new JLabel("Rol:")); panelCampos.add(txtRol);

        add(panelCampos, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID", "Usuario", "Contraseña", "Rol"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);

        // Iniciar controlador
        new vallegrade.edu.pe.controller.UsuarioController(this);
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRol.setText("");
        tabla.clearSelection();
    }
}
