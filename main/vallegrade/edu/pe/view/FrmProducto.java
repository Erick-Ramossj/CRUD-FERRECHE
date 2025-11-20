package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.ProductoController;
import vallegrade.edu.pe.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmProducto extends JFrame {

    // Controlador SIN parámetros (NO GENERA ERROR)
    private ProductoController controller = new ProductoController();

    private JTextField txtId, txtNombre, txtPrecio, txtDescuento, txtImagen;
    private JComboBox<String> cboGenero, cboColor, cboRubro;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FrmProducto() {
        setTitle("Gestión de Productos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        cargarCombos();
        cargarTabla();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // ---------------- PANEL SUPERIOR (FORMULARIO) ----------------
        JPanel form = new JPanel(new GridLayout(4, 4, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtDescuento = new JTextField();
        txtImagen = new JTextField();

        cboGenero = new JComboBox<>();
        cboColor = new JComboBox<>();
        cboRubro = new JComboBox<>();

        form.add(new JLabel("ID:")); form.add(txtId);
        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Precio:")); form.add(txtPrecio);
        form.add(new JLabel("Oferta:")); form.add(txtDescuento);
        form.add(new JLabel("Imagen (ruta):")); form.add(txtImagen);
        form.add(new JLabel("Género:")); form.add(cboGenero);
        form.add(new JLabel("Color:")); form.add(cboColor);
        form.add(new JLabel("Rubro:")); form.add(cboRubro);

        panel.add(form, BorderLayout.NORTH);

        // ---------------- PANEL CENTRAL (TABLA) ----------------
        modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Precio", "Oferta", "Imagen", "Género", "Color", "Rubro"}, 0
        );
        tabla = new JTable(modelo);

        tabla.getSelectionModel().addListSelectionListener(e -> cargarDatosSeleccionados());

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // ---------------- PANEL INFERIOR (BOTONES) ----------------
        JPanel botones = new JPanel();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");
        JButton btnMenu = new JButton("Volver al Menú");


        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnEditar);
        botones.add(btnMenu);


        panel.add(botones, BorderLayout.SOUTH);

        // EVENTOS
        btnAgregar.addActionListener(e -> agregarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnEditar.addActionListener(e -> habilitarEdicion());

        btnMenu.addActionListener(e -> {
            this.dispose(); // cierra FrmProducto
            new MainMenuView().setVisible(true); // vuelve al menú principal
        });

    }

    // ------------------- LOGICA ---------------------

    private void cargarCombos() {
        // Genero
        cboGenero.addItem("Unisex");
        cboGenero.addItem("Hombre");
        cboGenero.addItem("Mujer");

        // Color
        cboColor.addItem("Amarillo");
        cboColor.addItem("Anaranjado");
        cboColor.addItem("Blanco");
        cboColor.addItem("Negro");
        cboColor.addItem("Rojo");

        // Rubro
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
                    p.getRubro()
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
        return p;
    }

    private void habilitarEdicion() {
        txtNombre.setEditable(true);
        txtPrecio.setEditable(true);
        txtDescuento.setEditable(true);
        txtImagen.setEditable(true);

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

        cboGenero.setSelectedIndex(0);
        cboColor.setSelectedIndex(0);
        cboRubro.setSelectedIndex(0);}
}