package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Producto;
import vallegrade.edu.pe.service.ProductoService;
import vallegrade.edu.pe.view.FrmProducto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController implements ActionListener {
    private ProductoService service;
    private FrmProducto vista;

    public ProductoController(FrmProducto vista) {
        this.vista = vista;
        this.service = new ProductoService();

        // Registrar eventos
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnListar().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);

        // Cargar lista inicial
        cargarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregar()) {
            agregarProducto();
        } else if (e.getSource() == vista.getBtnEditar()) {
            editarProducto();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminarProducto();
        } else if (e.getSource() == vista.getBtnListar()) {
            cargarProductos();
        } else if (e.getSource() == vista.getBtnLimpiar()) {
            vista.limpiarCampos();
        }
    }

    private void agregarProducto() {
        try {
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());
            int stock = Integer.parseInt(vista.getTxtStock().getText());

            Producto p = new Producto();
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setStock(stock);

            boolean exito = service.agregarProducto(p);
            if (exito) {
                mostrarMensaje("✅ Producto agregado correctamente.");
                cargarProductos();
                vista.limpiarCampos();
            } else {
                mostrarMensaje("❌ No se pudo agregar el producto.");
            }
        } catch (Exception ex) {
            mostrarMensaje("⚠️ Error al agregar: " + ex.getMessage());
        }
    }

    private void editarProducto() {
        try {
            if (vista.getTxtId().getText().isEmpty()) {
                mostrarMensaje("Selecciona un producto de la tabla para editar.");
                return;
            }

            int id = Integer.parseInt(vista.getTxtId().getText());
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());
            int stock = Integer.parseInt(vista.getTxtStock().getText());

            Producto p = new Producto(id, nombre, precio, stock);

            boolean exito = service.actualizarProducto(p);
            if (exito) {
                mostrarMensaje("✅ Producto actualizado correctamente.");
                cargarProductos();
                vista.limpiarCampos();
            } else {
                mostrarMensaje("❌ No se pudo actualizar el producto.");
            }
        } catch (Exception ex) {
            mostrarMensaje("⚠️ Error al editar: " + ex.getMessage());
        }
    }

    private void eliminarProducto() {
        try {
            if (vista.getTxtId().getText().isEmpty()) {
                mostrarMensaje("Selecciona un producto de la tabla para eliminar.");
                return;
            }

            int id = Integer.parseInt(vista.getTxtId().getText());

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Seguro que deseas eliminar este producto?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean exito = service.eliminarProducto(id);
                if (exito) {
                    mostrarMensaje("✅ Producto eliminado correctamente.");
                    cargarProductos();
                    vista.limpiarCampos();
                } else {
                    mostrarMensaje("❌ No se pudo eliminar el producto.");
                }
            }
        } catch (Exception ex) {
            mostrarMensaje("⚠️ Error al eliminar: " + ex.getMessage());
        }
    }

    private void cargarProductos() {
        List<Producto> lista = service.obtenerProductos();
        vista.mostrarProductos(lista);
    }

    private void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}

