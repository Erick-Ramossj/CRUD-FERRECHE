package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Direccion;
import vallegrade.edu.pe.dao.DireccionDAO; // Asegúrate de que el paquete 'dao' sea correcto

import java.util.List;

/**
 * Capa de servicio para la gestión de Direcciones.
 * Contiene la lógica de negocio y actúa como intermediario entre el Controller y el DAO.
 */
public class DireccionService {

    // Instancia del DAO para interactuar con la base de datos
    private final DireccionDAO dao = new DireccionDAO();

    // 📌 LISTAR Direcciones por Cliente
    /**
     * Obtiene la lista de direcciones de un cliente específico.
     * @param idCliente ID del cliente cuyas direcciones se desean listar.
     * @return Lista de objetos Direccion.
     */
    public List<Direccion> listarDireccionesPorCliente(int idCliente) {
        // Llama al método del DAO que filtra por id_cliente
        return dao.listarPorCliente(idCliente);
    }

    // 📌 AGREGAR dirección
    public boolean agregarDireccion(Direccion direccion) {
        return dao.agregar(direccion);
    }

    // 📌 ACTUALIZAR dirección
    public boolean actualizarDireccion(Direccion direccion) {
        return dao.actualizar(direccion);
    }

    // 📌 ELIMINAR dirección
    public boolean eliminarDireccion(int id) {
        return dao.eliminar(id);
    }

    // 📌 BUSCAR dirección por ID (opcional, pero útil)
    public Direccion buscarDireccionPorId(int id) {
        return dao.buscarPorId(id);
    }
}