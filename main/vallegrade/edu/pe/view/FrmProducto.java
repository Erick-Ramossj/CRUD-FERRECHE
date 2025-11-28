package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.ProductoController;
import vallegrade.edu.pe.model.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class FrmProducto extends JFrame {

    private ProductoController controller = new ProductoController();

    private JTextField txtId, txtNombre, txtPrecio, txtDescuento, txtImagen;
    private JComboBox<String> cboGenero, cboColor, cboRubro;
    private JTextArea txtDescripcion;
    private JTable tabla;
    private DefaultTableModel modelo;

    // Paleta de colores profesional
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);
    private final Color COLOR_SUCCESS = new Color(39, 174, 96);
    private final Color COLOR_DANGER = new Color(231, 76, 60);
    private final Color COLOR_WARNING = new Color(243, 156, 18);
    private final Color COLOR_SECONDARY = new Color(52, 73, 94);
    private final Color COLOR_BACKGROUND = new Color(236, 240, 241);
    private final Color COLOR_WHITE = Color.WHITE;

    public FrmProducto() {
        setTitle("Sistema de Gestión de Productos");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(COLOR_BACKGROUND);

        initComponents();
        cargarCombos();
        cargarTabla();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // ------------------- TÍTULO -------------------
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(COLOR_PRIMARY);
        headerPanel.setPreferredSize(new Dimension(0, 70));
        JLabel lblTitulo = new JLabel("GESTIÓN DE PRODUCTOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(COLOR_WHITE);
        headerPanel.add(lblTitulo);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ------------------- FORMULARIO -------------------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(COLOR_WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        txtId = crearTextField(false);
        txtNombre = crearTextField(true);
        txtPrecio = crearTextField(true);
        txtDescuento = crearTextField(true);
        txtImagen = crearTextField(true);

        cboGenero = crearComboBox();
        cboColor = crearComboBox();
        cboRubro = crearComboBox();

        // Campo descripción
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setPreferredSize(new Dimension(200, 70));

        // FILA 1
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        formPanel.add(crearLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        formPanel.add(txtId, gbc);

        gbc.gridx = 2; gbc.weightx = 0.2;
        formPanel.add(crearLabel("Nombre:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.8;
        formPanel.add(txtNombre, gbc);

        // FILA 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(crearLabel("Precio:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtPrecio, gbc);

        gbc.gridx = 2;
        formPanel.add(crearLabel("Oferta:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtDescuento, gbc);

        // FILA 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(crearLabel("Género:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cboGenero, gbc);

        gbc.gridx = 2;
        formPanel.add(crearLabel("Color:"), gbc);
        gbc.gridx = 3;
        formPanel.add(cboColor, gbc);

        // FILA 4
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(crearLabel("Rubro:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cboRubro, gbc);

        gbc.gridx = 2;
        formPanel.add(crearLabel("Imagen:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtImagen, gbc);

        // FILA 5 - Descripción
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(crearLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        formPanel.add(scrollDesc, gbc);
        gbc.gridwidth = 1;

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // ------------------- TABLA -------------------
        modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Precio", "Oferta", "Imagen", "Género", "Color", "Rubro", "Descripción"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(30);
        tabla.setSelectionBackground(new Color(52, 152, 219));
        tabla.setSelectionForeground(COLOR_WHITE);
        tabla.setGridColor(new Color(189, 195, 199));
        tabla.setShowGrid(true);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(COLOR_BACKGROUND);
        header.setForeground(COLOR_PRIMARY);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tabla.getSelectionModel().addListSelectionListener(e -> cargarDatosSeleccionados());

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        scrollPane.getViewport().setBackground(COLOR_WHITE);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ------------------- BOTONES -------------------
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        botonesPanel.setBackground(COLOR_BACKGROUND);

        JButton btnAgregar = crearBoton("Agregar", COLOR_SUCCESS);
        JButton btnEditar = crearBoton("Editar", COLOR_WARNING);
        JButton btnActualizar = crearBoton("Actualizar", COLOR_PRIMARY);
        JButton btnEliminar = crearBoton("Eliminar", COLOR_DANGER);
        JButton btnMenu = crearBoton("Volver al Menú", COLOR_SECONDARY);

        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnActualizar);
        botonesPanel.add(btnEliminar);
        botonesPanel.add(btnMenu);

        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        // EVENTOS
        btnAgregar.addActionListener(e -> agregarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnEditar.addActionListener(e -> habilitarEdicion());
        btnMenu.addActionListener(e -> {
            this.dispose();
            new MainMenuView().setVisible(true);
        });
    }

    // ------------------- COMPONENTES DE DISEÑO -------------------

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(COLOR_SECONDARY);
        return label;
    }

    private JTextField crearTextField(boolean editable) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 35));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        textField.setEditable(editable);
        if (!editable) {
            textField.setBackground(new Color(236, 240, 241));
        }
        return textField;
    }

    private JComboBox<String> crearComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(200, 35));
        comboBox.setBackground(COLOR_WHITE);
        return comboBox;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(COLOR_WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(150, 40));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    // ------------------- LÓGICA -------------------

    private void cargarCombos() {
        cboGenero.addItem("Unisex");
        cboGenero.addItem("Hombre");
        cboGenero.addItem("Mujer");

        cboColor.addItem("Amarillo");
        cboColor.addItem("Anaranjado");
        cboColor.addItem("Blanco");
        cboColor.addItem("Negro");
        cboColor.addItem("Rojo");

        cboRubro.addItem("Construcción");
        cboRubro.addItem("Seguridad Industrial");
        cboRubro.addItem("Salud");
        cboRubro.addItem("Minería");
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Producto> lista = controller.obtenerProductos();

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getPrice(),
                    p.getDiscount_price(),
                    p.getImage(),
                    p.getGenero(),
                    p.getColor(),
                    p.getRubro(),
                    p.getDescription()
            });
        }
    }

    private void cargarDatosSeleccionados() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            txtId.setText(tabla.getValueAt(fila, 0).toString());
            txtNombre.setText(tabla.getValueAt(fila, 1).toString());
            txtPrecio.setText(tabla.getValueAt(fila, 2).toString());
            txtDescuento.setText(tabla.getValueAt(fila, 3).toString());
            txtImagen.setText(tabla.getValueAt(fila, 4).toString());

            cboGenero.setSelectedItem(tabla.getValueAt(fila, 5).toString());
            cboColor.setSelectedItem(tabla.getValueAt(fila, 6).toString());
            cboRubro.setSelectedItem(tabla.getValueAt(fila, 7).toString());

            txtDescripcion.setText(tabla.getValueAt(fila, 8).toString());
        }
    }

    private void agregarProducto() {
        Producto p = obtenerProductoDesdeFormulario();
        if (controller.agregarProducto(p)) {
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
            cargarTabla();
            limpiarFormulario();
        }
    }

    private void actualizarProducto() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        Producto p = obtenerProductoDesdeFormulario();
        p.setId(Integer.parseInt(txtId.getText()));

        if (controller.actualizarProducto(p)) {
            JOptionPane.showMessageDialog(this, "Producto actualizado.");
            cargarTabla();
        }
    }

    private void eliminarProducto() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());

        if (controller.eliminarProducto(id)) {
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
            cargarTabla();
            limpiarFormulario();
        }
    }

    private Producto obtenerProductoDesdeFormulario() {
        Producto p = new Producto();
        p.setName(txtNombre.getText());
        p.setPrice(Double.parseDouble(txtPrecio.getText()));
        p.setDiscount_price(Double.parseDouble(txtDescuento.getText()));
        p.setImage(txtImagen.getText());
        p.setGenero(cboGenero.getSelectedItem().toString());
        p.setColor(cboColor.getSelectedItem().toString());
        p.setRubro(cboRubro.getSelectedItem().toString());
        p.setDescription(txtDescripcion.getText());
        return p;
    }

    private void habilitarEdicion() {
        txtNombre.setEditable(true);
        txtPrecio.setEditable(true);
        txtDescuento.setEditable(true);
        txtImagen.setEditable(true);
        txtDescripcion.setEditable(true);

        cboGenero.setEnabled(true);
        cboColor.setEnabled(true);
        cboRubro.setEnabled(true);

        JOptionPane.showMessageDialog(this, "Campos habilitados para edición.");
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescuento.setText("");
        txtImagen.setText("");
        txtDescripcion.setText("");

        cboGenero.setSelectedIndex(0);
        cboColor.setSelectedIndex(0);
        cboRubro.setSelectedIndex(0);
    }
}
