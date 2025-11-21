package vallegrade.edu.pe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vallegrade.edu.pe.model.Cliente;
import vallegrade.edu.pe.service.ClienteService;
import vallegrade.edu.pe.view.FrmCliente;

/**
 * Controlador para la vista FrmCliente.
 * Maneja la lógica de la aplicación y los eventos de la interfaz de usuario.
 */
public class ClienteController implements ActionListener {

    private FrmCliente vista;
    private ClienteService servicio;
    private DefaultTableModel modeloTabla;
    // Variable para almacenar el ID del cliente seleccionado
    private int idClienteSeleccionado = -1;

    public ClienteController(FrmCliente vista, ClienteService servicio) {
        this.vista = vista;
        this.servicio = servicio;

        // Inicializar el modelo de la tabla
        this.modeloTabla = (DefaultTableModel) vista.tblClientes.getModel();

        // Registrar listeners para los botones
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);




        // Registrar listener para la tabla (para seleccionar una fila)
        this.vista.tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila(e);
            }
        });

        // Cargar la lista inicial de clientes al iniciar
        System.out.println("DEBUG: Iniciando controlador, llamando a listarClientes()...");
        listarClientes();
    }

    public void iniciar() {
        vista.setTitle("Gestión de Clientes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregarCliente();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizarCliente();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarCliente();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }else if (e.getSource() == vista.btnListar) {
            listarClientes();
        }

    }

    private void listarClientes() {
        // Limpiar tabla antes de cargar
        modeloTabla.setRowCount(0);

        List<Cliente> lista = servicio.listarClientes();

        System.out.println("DEBUG: Listado Controller. Clientes recibidos del servicio: " + lista.size());

        for (Cliente c : lista) {
            Object[] fila = new Object[7]; // Ajusta el número de columnas que quieres mostrar
            fila[0] = c.getId();
            fila[1] = c.getTipo_cliente();
            // Determinar el nombre a mostrar (Nombre/Apellido o Razón Social)
            String nombreMostrar = "N/A";
            if ("persona".equals(c.getTipo_cliente())) {
                nombreMostrar = (c.getNombre() != null ? c.getNombre() : "") + " " + (c.getApellidos() != null ? c.getApellidos() : "");
            } else if ("empresa".equals(c.getTipo_cliente())) {
                nombreMostrar = c.getRazon_social();
            }
            fila[2] = nombreMostrar.trim();

            // Determinar el documento a mostrar (Documento o RUC)
            String docMostrar = "N/A";
            if ("persona".equals(c.getTipo_cliente())) {
                docMostrar = c.getNumero_documento();
            } else if ("empresa".equals(c.getTipo_cliente())) {
                docMostrar = c.getRuc();
            }
            fila[3] = docMostrar;

            fila[4] = c.getCorreo();
            fila[5] = c.getCelular();
            fila[6] = c.getDireccion();
            modeloTabla.addRow(fila);
        }
    }

    /**
     * === MÉTODO MODIFICADO ===
     * Se llama al hacer clic en una fila de la tabla.
     * Busca el cliente completo por su ID y rellena todos los campos del formulario.
     */
    private void seleccionarFila(MouseEvent e) {
        int fila = vista.tblClientes.getSelectedRow();
        if (fila >= 0) {
            // 1. Obtener el ID de la fila seleccionada (columna 0)
            this.idClienteSeleccionado = Integer.parseInt(vista.tblClientes.getValueAt(fila, 0).toString());

            // 2. Usar el servicio para buscar el cliente completo
            Cliente cliente = servicio.buscarClientePorId(this.idClienteSeleccionado);

            // 3. Rellenar todos los campos del formulario si se encontró el cliente
            if (cliente != null) {
                vista.txtId.setText(String.valueOf(cliente.getId()));
                vista.cmbTipoCliente.setSelectedItem(cliente.getTipo_cliente());
                vista.txtCorreo.setText(cliente.getCorreo());
                vista.txtCelular.setText(cliente.getCelular());
                vista.txtContrasena.setText(cliente.getContrasena());
                vista.txtNombre.setText(cliente.getNombre());
                vista.txtApellidos.setText(cliente.getApellidos());
                vista.txtDireccion.setText(cliente.getDireccion());
                vista.txtTipoDoc.setText(cliente.getTipo_documento());
                vista.txtNumDoc.setText(cliente.getNumero_documento());
                vista.txtProfesion.setText(cliente.getProfesion());
                vista.txtRazonSocial.setText(cliente.getRazon_social());
                vista.txtTipoEmpresa.setText(cliente.getTipo_empresa());
                vista.txtRuc.setText(cliente.getRuc());
            } else {
                JOptionPane.showMessageDialog(vista, "Error: No se pudo encontrar el cliente con ID " + this.idClienteSeleccionado);
                this.idClienteSeleccionado = -1; // Resetear si no se encontró
            }
        }
    }

    private void agregarCliente() {
        // Validar campos (ejemplo simple)
        if (vista.txtCorreo.getText().isEmpty() || new String(vista.txtContrasena.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Correo y Contraseña son obligatorios.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente c = new Cliente();
        c.setTipo_cliente(vista.cmbTipoCliente.getSelectedItem().toString());
        c.setCorreo(vista.txtCorreo.getText());
        c.setCelular(vista.txtCelular.getText());
        c.setContrasena(new String(vista.txtContrasena.getPassword()));
        c.setNombre(vista.txtNombre.getText());
        c.setApellidos(vista.txtApellidos.getText());
        c.setDireccion(vista.txtDireccion.getText());
        c.setTipo_documento(vista.txtTipoDoc.getText());
        c.setNumero_documento(vista.txtNumDoc.getText());
        c.setProfesion(vista.txtProfesion.getText());
        c.setRazon_social(vista.txtRazonSocial.getText());
        c.setTipo_empresa(vista.txtTipoEmpresa.getText());
        c.setRuc(vista.txtRuc.getText());

        if (servicio.agregarCliente(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente agregado exitosamente");
            limpiarCampos();
            listarClientes();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar cliente. Verifique la consola.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCliente() {
        // Usamos el ID de la variable de clase, que se setea al seleccionar
        if (this.idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un cliente de la tabla", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente c = new Cliente();
        c.setId(this.idClienteSeleccionado); // Usar el ID almacenado
        c.setTipo_cliente(vista.cmbTipoCliente.getSelectedItem().toString());
        c.setCorreo(vista.txtCorreo.getText());
        c.setCelular(vista.txtCelular.getText());
        c.setContrasena(new String(vista.txtContrasena.getPassword()));
        c.setNombre(vista.txtNombre.getText());
        c.setApellidos(vista.txtApellidos.getText());
        c.setDireccion(vista.txtDireccion.getText());
        c.setTipo_documento(vista.txtTipoDoc.getText());
        c.setNumero_documento(vista.txtNumDoc.getText());
        c.setProfesion(vista.txtProfesion.getText());
        c.setRazon_social(vista.txtRazonSocial.getText());
        c.setTipo_empresa(vista.txtTipoEmpresa.getText());
        c.setRuc(vista.txtRuc.getText());

        if (servicio.actualizarCliente(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente actualizado exitosamente");
            limpiarCampos();
            listarClientes();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar cliente. Verifique la consola.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        // Usamos el ID de la variable de clase
        if (this.idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un cliente de la tabla", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (servicio.eliminarCliente(this.idClienteSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Cliente eliminado exitosamente");
                limpiarCampos();
                listarClientes();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar cliente. Verifique la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.cmbTipoCliente.setSelectedIndex(0);
        vista.txtCorreo.setText("");
        vista.txtCelular.setText("");
        vista.txtContrasena.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtDireccion.setText("");
        vista.txtTipoDoc.setText("");
        vista.txtNumDoc.setText("");
        vista.txtProfesion.setText("");
        vista.txtRazonSocial.setText("");
        vista.txtTipoEmpresa.setText("");
        vista.txtRuc.setText("");
        vista.tblClientes.clearSelection();
        this.idClienteSeleccionado = -1; // Resetear el ID
    }
}
