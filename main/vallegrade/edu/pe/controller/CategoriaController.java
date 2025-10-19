package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Categoria;
import vallegrade.edu.pe.service.CategoriaService;
import java.util.List;

public class CategoriaController {
    private CategoriaService service = new CategoriaService();

    public List<Categoria> obtenerCategorias() {
        return service.listarCategorias();
    }

    public boolean guardarCategoria(Categoria c) {
        return service.agregarCategoria(c);
    }

    public boolean actualizarCategoria(Categoria c) {
        return service.actualizarCategoria(c);
    }

    public boolean eliminarCategoria(int id) {
        return service.eliminarCategoria(id);
    }
}
