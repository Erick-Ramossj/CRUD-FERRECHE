package vallegrade.edu.pe.service; // Corregido

import vallegrade.edu.pe.model.Producto; // Corregido
import vallegrade.edu.pe.model.ProductoDAO; // Corregido

import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listar();
    }
    // Nuevos componentes para agregar y modificar

    public boolean agregarNuevoProducto(Producto producto) {
        return productoDAO.agregarProducto(producto);
    }

    public boolean actualizarProductoExistente(Producto producto) {
        return productoDAO.actualizarProducto(producto);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoDAO.buscarProductos(nombre);
    }
}