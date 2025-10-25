package vallegrade.edu.pe.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import vallegrade.edu.pe.controller.ClienteController;
import vallegrade.edu.pe.service.ClienteService;

/**
 * Vista principal (JFrame) para la gestión de Clientes.
 * Contiene el formulario y la tabla.
 */
public class FrmCliente extends JFrame {

    // --- Componentes del Formulario ---
    // Hacemos públicos los componentes para que el Controlador pueda acceder a ellos
    public JTextField txtId = new JTextField(5);
    public JComboBox<String> cmbTipoCliente = new JComboBox<>(new String[]{"persona", "empresa"});
    public JTextField txtCorreo = new JTextField(20);
    public JTextField txtCelular = new JTextField(15);
    public JPasswordField txtContrasena = new JPasswordField(20);

    // Campos Persona
    public JTextField txtNombre = new JTextField(20);
    public JTextField txtApellidos = new JTextField(20);
    public JTextField txtDireccion = new JTextField(20);
    public JTextField txtTipoDoc = new JTextField(10);
    public JTextField txtNumDoc = new JTextField(15);
    public JTextField txtProfesion = new JTextField(20);

    // Campos Empresa
    public JTextField txtRazonSocial = new JTextField(20);
    public JTextField txtTipoEmpresa = new JTextField(20);
    public JTextField txtRuc = new JTextField(15);

    // --- Botones ---
    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");

    // --- Tabla ---
    public JTable tblClientes = new JTable();
    public DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;

    public FrmCliente() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout(10, 10));

        // --- Panel del Formulario (Norte) ---
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: ID y Tipo Cliente
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId.setEditable(false); // El ID no debe ser editable
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Tipo Cliente:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(cmbTipoCliente, gbc);

        // Fila 1: Correo y Celular
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtCorreo, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Celular:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtCelular, gbc);

        // Fila 2: Contraseña y Dirección
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtContrasena, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtDireccion, gbc);

        // --- Separador Persona ---
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        panelFormulario.add(new JLabel("--- Datos Persona ---"), gbc);
        gbc.gridwidth = 1; // Reset

        // Fila 4: Nombre y Apellidos
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 2; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtApellidos, gbc);

        // Fila 5: Tipo Doc y Num Doc
        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Tipo Doc.:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtTipoDoc, gbc);

        gbc.gridx = 2; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Num. Doc.:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtNumDoc, gbc);

        // Fila 6: Profesión
        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(new JLabel("Profesión:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtProfesion, gbc);

        // --- Separador Empresa ---
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 4;
        panelFormulario.add(new JLabel("--- Datos Empresa ---"), gbc);
        gbc.gridwidth = 1; // Reset

        // Fila 8: Razón Social y Tipo Empresa
        gbc.gridx = 0; gbc.gridy = 8;
        panelFormulario.add(new JLabel("Razón Social:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtRazonSocial, gbc);

        gbc.gridx = 2; gbc.gridy = 8;
        panelFormulario.add(new JLabel("Tipo Empresa:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtTipoEmpresa, gbc);

        // Fila 9: RUC
        gbc.gridx = 0; gbc.gridy = 9;
        panelFormulario.add(new JLabel("RUC:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtRuc, gbc);

        // --- Panel de Botones (Sur) ---
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // --- Panel de la Tabla (Centro) ---
        // Definir columnas (Ajusta esto a lo que quieras mostrar)
        String[] columnas = {"ID", "Tipo", "Nombre/Razón Social", "Doc/RUC", "Correo", "Celular", "Dirección"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };
        tblClientes.setModel(modeloTabla);
        scrollTabla = new JScrollPane(tblClientes);

        // Añadir paneles al JFrame
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Método principal para ejecutar la aplicación.
     * Crea la Vista, el Servicio y el Controlador, y los enlaza.
     */
    public static void main(String[] args) {
        // Se recomienda ejecutar la GUI en el Event Dispatch Thread (EDT)
        java.awt.EventQueue.invokeLater(() -> {
            FrmCliente vista = new FrmCliente();
            ClienteService servicio = new ClienteService();
            ClienteController controlador = new ClienteController(vista, servicio);
            controlador.iniciar(); // Inicia el controlador y muestra la vista
        });
    }
}

