package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Producto;
import vallegrade.edu.pe.model.ProductoDAO;

import java.util.List;

public class ProductoService {
    private final ProductoDAO dao = new ProductoDAO();

    // Listar productos
    public List<Producto> obtenerProductos() {
        return dao.listar();
    }

    // Agregar producto
    public boolean agregarProducto(Producto producto) {
        return dao.agregar(producto);
    }

    // Actualizar producto
    public boolean actualizarProducto(Producto producto) {
        return dao.actualizar(producto);
    }

    // Eliminar producto
    public boolean eliminarProducto(int id) {
        return dao.eliminar(id);
    }
}
