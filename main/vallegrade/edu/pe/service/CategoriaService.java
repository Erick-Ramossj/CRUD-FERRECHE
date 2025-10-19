package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Categoria;
import vallegrade.edu.pe.model.CategoriaDAO;
import java.util.List;

public class CategoriaService {
    private CategoriaDAO dao = new CategoriaDAO();

    public List<Categoria> listarCategorias() {
        return dao.listar();
    }

    public boolean agregarCategoria(Categoria c) {
        return dao.agregar(c);
    }

    public boolean actualizarCategoria(Categoria c) {
        return dao.actualizar(c);
    }

    public boolean eliminarCategoria(int id) {
        return dao.eliminar(id);
    }
}
