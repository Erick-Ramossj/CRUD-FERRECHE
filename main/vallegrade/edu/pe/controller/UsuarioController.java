package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Usuario;
import vallegrade.edu.pe.model.UsuarioDAO;
import vallegrade.edu.pe.view.FrmUsuario;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController implements ActionListener {

    private FrmUsuario view;
    private UsuarioDAO dao;

    public UsuarioController(FrmUsuario view) {
        this.view = view;
        this.dao = new UsuarioDAO();

        // Asignamos listeners a los botones
        this.view.btnAgregar.addActionListener(this);
        this.view.btnActualizar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);

        // Cargamos los usuarios al iniciar
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        List<Usuario> lista = dao.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) view.tabla.getModel();
        modelo.setRowCount(0); // Limpiar tabla
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{u.getId(), u.getUsername(), u.getPassword(), u.getRol()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnAgregar) {
            String username = view.txtUsername.getText().trim();
            String password = view.txtPassword.getText().trim();
            String rol = view.txtRol.getText().trim();

            if (username.isEmpty() || password.isEmpty() || rol.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int result = dao.registrarUsuario(new Usuario(0, username, password, rol));
            if (result > 0) {
                JOptionPane.showMessageDialog(view, "Usuario agregado correctamente.");
                cargarUsuarios();
                view.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(view, "Error al agregar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == view.btnLimpiar) {
            view.limpiarCampos();
        }
        // Aquí puedes agregar actualizar y eliminar si deseas
    }
}

