package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Direccion;
import vallegrade.edu.pe.service.DireccionService;
import vallegrade.edu.pe.view.FrmDireccion; // ⭐️ Necesario para referenciar la Vista ⭐️

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; // Necesario para manipular la tabla

public class DireccionController implements ActionListener { // Implementa ActionListener para eventos

    private final FrmDireccion vista; // ⭐️ Referencia a la Vista ⭐️
    private final DireccionService service;
    private final int idCliente; // ⭐️ ID del cliente que estamos gestionando ⭐️
    private int idDireccionSeleccionada = -1; // ID de la fila seleccionada

    // ⭐️ CONSTRUCTOR CORREGIDO: Acepta la Vista y el ID del Cliente ⭐️
    public DireccionController(FrmDireccion vista, int idCliente) {
        this.vista = vista;
        this.idCliente = idCliente;
        this.service = new DireccionService();

        // 1. Configurar Listeners (Botones y Tabla)
        configurarEventos();

        // 2. Cargar datos iniciales
        listarDirecciones();
    }

    // Método de inicio (llamado desde FrmDireccion)
    public void iniciar() {
        // En este caso, el FrmDireccion ya está visible al ser creado
    }
// ----------------------------------------------------------------------------------------------------

    private void configurarEventos() {
        // Asignar ActionListener al controlador para manejar los eventos de los botones
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        // Registrar listener para la tabla (para seleccionar una fila)
        this.vista.tblDirecciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregarDireccion();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizarDireccion();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarDireccion();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    // 📌 LÓGICA DE LISTADO Y SELECCIÓN

    private void listarDirecciones() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblDirecciones.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        List<Direccion> lista = service.listarDireccionesPorCliente(this.idCliente);

        for (Direccion d : lista) {
            Object[] fila = new Object[5];
            fila[0] = d.getId();
            fila[1] = d.getCalle() + " " + d.getNumero();
            fila[2] = d.getDistrito();
            fila[3] = d.getReferencia();
            fila[4] = d.isEsPrincipal() ? "Sí" : "No";
            modelo.addRow(fila);
        }
    }

    private void seleccionarFila() {
        int fila = vista.tblDirecciones.getSelectedRow();
        if (fila >= 0) {
            // 1. Obtener el ID de la dirección (columna 0)
            this.idDireccionSeleccionada = Integer.parseInt(vista.tblDirecciones.getValueAt(fila, 0).toString());

            // 2. Buscar la dirección completa para llenar los campos (buena práctica)
            Direccion direccion = service.buscarDireccionPorId(this.idDireccionSeleccionada);

            if (direccion != null) {
                vista.txtIdDireccion.setText(String.valueOf(direccion.getId()));
                vista.txtCalle.setText(direccion.getCalle());
                vista.txtNumero.setText(direccion.getNumero());
                vista.txtDistrito.setText(direccion.getDistrito());
                vista.txtReferencia.setText(direccion.getReferencia());
                vista.chkEsPrincipal.setSelected(direccion.isEsPrincipal());
            }
        }
    }

    // 📌 LÓGICA CRUD (Implementación simple)

    private void agregarDireccion() {
        Direccion d = obtenerDatosDireccion();
        if (d == null) return; // Validación fallida

        d.setIdCliente(this.idCliente); // Asignar el ID del cliente actual

        if (service.agregarDireccion(d)) {
            JOptionPane.showMessageDialog(vista, "Dirección agregada.");
            limpiarCampos();
            listarDirecciones();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarDireccion() {
        if (this.idDireccionSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una dirección para actualizar.");
            return;
        }

        Direccion d = obtenerDatosDireccion();
        if (d == null) return;

        d.setId(this.idDireccionSeleccionada);
        d.setIdCliente(this.idCliente);

        if (service.actualizarDireccion(d)) {
            JOptionPane.showMessageDialog(vista, "Dirección actualizada.");
            limpiarCampos();
            listarDirecciones();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarDireccion() {
        if (this.idDireccionSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una dirección para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar esta dirección?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (service.eliminarDireccion(this.idDireccionSeleccionada)) {
                JOptionPane.showMessageDialog(vista, "Dirección eliminada.");
                limpiarCampos();
                listarDirecciones();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Direccion obtenerDatosDireccion() {
        if (vista.txtCalle.getText().trim().isEmpty() || vista.txtDistrito.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Calle y Distrito son obligatorios.");
            return null;
        }

        Direccion d = new Direccion();
        d.setCalle(vista.txtCalle.getText());
        d.setNumero(vista.txtNumero.getText());
        d.setDistrito(vista.txtDistrito.getText());
        // Provincias y Departamentos no están en la vista simple, se omiten o se añaden a la vista
        d.setReferencia(vista.txtReferencia.getText());
        d.setEsPrincipal(vista.chkEsPrincipal.isSelected());

        return d;
    }

    private void limpiarCampos() {
        vista.txtIdDireccion.setText("");
        vista.txtCalle.setText("");
        vista.txtNumero.setText("");
        vista.txtDistrito.setText("");
        vista.txtReferencia.setText("");
        vista.chkEsPrincipal.setSelected(false);
        vista.tblDirecciones.clearSelection();
        this.idDireccionSeleccionada = -1;
    }
}
