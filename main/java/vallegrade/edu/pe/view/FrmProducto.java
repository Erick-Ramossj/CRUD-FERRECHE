package vallegrade.edu.pe.view;

import vallegrade.edu.pe.model.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmProducto extends JFrame {

    public JTable tablaProductos;
    public JButton botonActualizar;
    private DefaultTableModel modeloTabla;

    // --- Componentes para el formulario ---
    public JTextField txtNombre, txtPrecio, txtStock;
    public JButton botonAgregar, botonGuardarCambios, botonLimpiar;

    // --- NUEVOS COMPONENTES PARA LA BÚSQUEDA ---
    public JTextField txtBuscar;
    public JButton botonBuscar;

    public FrmProducto() {
        setTitle("Gestión de Productos - CRUD");
        setSize(800, 700); // Un poco más alto para la barra de búsqueda
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel del Formulario (Norte) ---
        // (Este panel no cambia, lo dejamos como estaba)
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombre = new JTextField(20);
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        gbc.gridx = 0; gbc.gridy = 0; panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; panelFormulario.add(txtNombre, gbc);
        gbc.weightx = 0;
        gbc.gridx = 0; gbc.gridy = 1; panelFormulario.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panelFormulario.add(txtPrecio, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panelFormulario.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panelFormulario.add(txtStock, gbc);
        JPanel panelBotonesForm = new JPanel();
        botonAgregar = new JButton("Agregar");
        botonGuardarCambios = new JButton("Guardar Cambios");
        botonLimpiar = new JButton("Limpiar");
        panelBotonesForm.add(botonAgregar);
        panelBotonesForm.add(botonGuardarCambios);
        panelBotonesForm.add(botonLimpiar);
        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(panelBotonesForm, gbc);
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        // --- Panel Central (Búsqueda y Tabla) ---
        JPanel panelCentral = new JPanel(new BorderLayout(0, 5));

        // --- Barra de Búsqueda (Arriba del panel central) ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        txtBuscar = new JTextField(25);
        botonBuscar = new JButton("Buscar");
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(botonBuscar);
        panelCentral.add(panelBusqueda, BorderLayout.NORTH);

        // --- Tabla de Productos (Abajo del panel central) ---
        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        panelCentral.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // --- Panel de Botones Inferior (Sur) ---
        JPanel panelSur = new JPanel();
        botonActualizar = new JButton("Mostrar Todos");
        panelSur.add(botonActualizar);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    public void actualizarTabla(List<Producto> productos) {
        modeloTabla.setRowCount(0);
        for (Producto producto : productos) {
            Object[] fila = {producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock()};
            modeloTabla.addRow(fila);
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        tablaProductos.clearSelection();
    }
}