package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Producto;
import vallegrade.edu.pe.service.ProductoService;
import vallegrade.edu.pe.view.FrmProducto;

import javax.swing.*;
import java.util.List;

public class ProductoController {
    private FrmProducto vista;
    private ProductoService servicio;

    public ProductoController(FrmProducto vista, ProductoService servicio) {
        this.vista = vista;
        this.servicio = servicio;

        // --- Listeners para los botones ---
        this.vista.botonActualizar.addActionListener(e -> cargarDatosEnLaTabla());
        this.vista.botonAgregar.addActionListener(e -> agregarProducto());
        this.vista.botonGuardarCambios.addActionListener(e -> actualizarProducto());
        this.vista.botonLimpiar.addActionListener(e -> vista.limpiarCampos());
        this.vista.botonBuscar.addActionListener(e -> buscarProductos()); // Listener para el nuevo botón de búsqueda

        // --- Listener para la selección en la tabla ---
        this.vista.tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && vista.tablaProductos.getSelectedRow() != -1) {
                cargarProductoSeleccionadoEnCampos();
            }
        });
    }

    public void iniciar() {
        cargarDatosEnLaTabla();
        vista.setVisible(true);
    }

    private void cargarDatosEnLaTabla() {
        List<Producto> listaProductos = servicio.obtenerTodosLosProductos();
        vista.actualizarTabla(listaProductos);
        vista.txtBuscar.setText(""); // Limpiar la barra de búsqueda
    }

    // --- NUEVO MÉTODO PARA BUSCAR ---
    private void buscarProductos() {
        String termino = vista.txtBuscar.getText();
        if (termino.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese un término para buscar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<Producto> productosEncontrados = servicio.buscarProductosPorNombre(termino);
        vista.actualizarTabla(productosEncontrados);
    }

    // --- MÉTODOS EXISTENTES (sin cambios) ---
    private void cargarProductoSeleccionadoEnCampos() {
        int fila = vista.tablaProductos.getSelectedRow();
        vista.txtNombre.setText(vista.tablaProductos.getValueAt(fila, 1).toString());
        vista.txtPrecio.setText(vista.tablaProductos.getValueAt(fila, 2).toString());
        vista.txtStock.setText(vista.tablaProductos.getValueAt(fila, 3).toString());
    }

    private void agregarProducto() {
        try {
            String nombre = vista.txtNombre.getText();
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double precio = Double.parseDouble(vista.txtPrecio.getText());
            int stock = Integer.parseInt(vista.txtStock.getText());

            Producto p = new Producto();
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setStock(stock);

            if (servicio.agregarNuevoProducto(p)) {
                JOptionPane.showMessageDialog(vista, "Producto agregado con éxito.");
                vista.limpiarCampos();
                cargarDatosEnLaTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio y Stock deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarProducto() {
        int fila = vista.tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = (int) vista.tablaProductos.getValueAt(fila, 0);
            String nombre = vista.txtNombre.getText();
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double precio = Double.parseDouble(vista.txtPrecio.getText());
            int stock = Integer.parseInt(vista.txtStock.getText());

            Producto p = new Producto();
            p.setId(id);
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setStock(stock);

            if (servicio.actualizarProductoExistente(p)) {
                JOptionPane.showMessageDialog(vista, "Producto modificado con éxito.");
                vista.limpiarCampos();
                cargarDatosEnLaTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al modificar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio y Stock deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}