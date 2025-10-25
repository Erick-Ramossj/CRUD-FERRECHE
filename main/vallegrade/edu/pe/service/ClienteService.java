package vallegrade.edu.pe.service;

import java.util.List;
import vallegrade.edu.pe.model.Cliente;
import vallegrade.edu.pe.model.ClienteDAO;

/**
 * Capa de servicio para Clientes.
 * Contiene la lógica de negocio y actúa como intermediario.
 */
public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public boolean agregarCliente(Cliente cliente) {
        // Aquí podrías añadir validaciones (ej. que el correo no esté vacío)
        return clienteDAO.agregar(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        // Validaciones...
        return clienteDAO.actualizar(cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminar(id);
    }

    /**
     * === MÉTODO NUEVO ===
     * Busca un cliente por su ID.
     * @param id El ID del cliente a buscar.
     * @return El cliente encontrado, o null.
     */
    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarPorId(id);
    }
}

