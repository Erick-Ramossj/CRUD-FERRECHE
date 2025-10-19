package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.CategoriaController;
import vallegrade.edu.pe.model.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FrmCategoria extends JFrame {
    private CategoriaController controller = new CategoriaController();
    private JTable tabla;
    private JTextField txtId, txtNombre, txtDescripcion;
    private JButton btnAgregar, btnEditar, btnGuardar, btnEliminar, btnListar;

    private boolean modoEdicion = false;

    public FrmCategoria() {
        setTitle("Gestión de Categorías");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTitulo = new JLabel("CRUD DE CATEGORÍAS");
        lblTitulo.setBounds(230, 10, 200, 25);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 50, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 50, 150, 25);
        txtId.setEnabled(false);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 90, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 90, 150, 25);
        add(txtNombre);

        JLabel lblDesc = new JLabel("Descripción:");
        lblDesc.setBounds(30, 130, 100, 25);
        add(lblDesc);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(130, 130, 150, 25);
        add(txtDescripcion);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(320, 50, 120, 30);
        btnAgregar.addActionListener(e -> agregar());
        add(btnAgregar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(450, 50, 120, 30);
        btnEditar.addActionListener(e -> editar());
        add(btnEditar);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(320, 90, 120, 30);
        btnGuardar.addActionListener(e -> guardar());
        btnGuardar.setEnabled(false);
        add(btnGuardar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(450, 90, 120, 30);
        btnEliminar.addActionListener(e -> eliminar());
        add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(385, 130, 120, 30);
        btnListar.addActionListener(e -> listar());
        add(btnListar);

        // Tabla
        tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 180, 580, 200);
        add(scroll);

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                    txtDescripcion.setText(tabla.getValueAt(fila, 2).toString());
                }
            }
        });

        listar();
    }

    // === Métodos CRUD ===

    private void listar() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción"}, 0);
        List<Categoria> lista = controller.obtenerCategorias();

        if (lista == null || lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ No hay conexión o no existen registros.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            for (Categoria c : lista) {
                model.addRow(new Object[]{c.getId(), c.getNombre(), c.getDescripcion()});
            }
        }

        tabla.setModel(model);
    }

    private void agregar() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ Debe ingresar un nombre.");
            return;
        }

        Categoria c = new Categoria();
        c.setNombre(txtNombre.getText());
        c.setDescripcion(txtDescripcion.getText());

        if (controller.guardarCategoria(c)) {
            JOptionPane.showMessageDialog(this, "✅ Categoría agregada correctamente.");
            listar();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ No se pudo agregar (sin conexión o error).");
        }
    }

    private void editar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ Seleccione una fila para editar.");
            return;
        }

        txtNombre.requestFocus();
        modoEdicion = true;
        btnGuardar.setEnabled(true);
        btnAgregar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnListar.setEnabled(false);
    }

    private void guardar() {
        if (!modoEdicion) return;

        Categoria c = new Categoria();
        c.setId(Integer.parseInt(txtId.getText()));
        c.setNombre(txtNombre.getText());
        c.setDescripcion(txtDescripcion.getText());

        if (controller.actualizarCategoria(c)) {
            JOptionPane.showMessageDialog(this, "✅ Cambios guardados correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ No se pudo guardar (sin conexión o error).");
        }

        modoEdicion = false;
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnListar.setEnabled(true);
        listar();
        limpiar();
    }

    private void eliminar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ Seleccione una categoría para eliminar.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar esta categoría?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            if (controller.eliminarCategoria(id)) {
                JOptionPane.showMessageDialog(this, "✅ Categoría eliminada.");
                listar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ No se pudo eliminar (sin conexión o error).");
            }
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }
}

