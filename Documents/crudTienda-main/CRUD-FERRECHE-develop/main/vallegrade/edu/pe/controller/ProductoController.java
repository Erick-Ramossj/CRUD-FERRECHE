package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Producto;
import vallegrade.edu.pe.service.ProductoService;

import java.util.List;

public class ProductoController {

    private final ProductoService service;

    // 🔥 Constructor SIN parámetros (necesario para FrmProducto)
    public ProductoController() {
        this.service = new ProductoService();
    }

    // 📌 LISTAR
    public List<Producto> obtenerProductos() {
        return service.obtenerProductos();
    }

    // 📌 AGREGAR
    public boolean agregarProducto(Producto producto) {
        return service.agregarProducto(producto);
    }

    // 📌 ACTUALIZAR
    public boolean actualizarProducto(Producto producto) {
        return service.actualizarProducto(producto);
    }

    // 📌 ELIMINAR
    public boolean eliminarProducto(int id) {
        return service.eliminarProducto(id);}

}