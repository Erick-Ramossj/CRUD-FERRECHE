package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import vallegrade.edu.pe.controller.ClienteController;
import vallegrade.edu.pe.service.ClienteService;

/**
 * Vista principal mejorada para la gestión de Clientes.
 * Diseño más limpio y organizado con paneles dinámicos.
 */
public class FrmCliente extends JFrame {

    // --- Componentes del Formulario ---
    public JTextField txtId = new JTextField(10);
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

    // Paneles dinámicos
    private JPanel panelPersona;
    private JPanel panelEmpresa;
    private CardLayout cardLayout;
    private JPanel panelDinamico;

    public FrmCliente() {
        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestión de Clientes - ValleGrade");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Color de fondo
        getContentPane().setBackground(new Color(240, 240, 245));

        // --- Panel Principal del Formulario ---
        JPanel panelFormularioPrincipal = new JPanel(new BorderLayout(10, 10));
        panelFormularioPrincipal.setBackground(new Color(240, 240, 245));
        panelFormularioPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel de Datos Generales ---
        JPanel panelDatosGenerales = crearPanelDatosGenerales();

        // --- Panel Dinámico (Persona/Empresa) ---
        cardLayout = new CardLayout();
        panelDinamico = new JPanel(cardLayout);
        panelDinamico.setBackground(new Color(240, 240, 245));

        panelPersona = crearPanelPersona();
        panelEmpresa = crearPanelEmpresa();

        panelDinamico.add(panelPersona, "persona");
        panelDinamico.add(panelEmpresa, "empresa");

        // Agregar paneles al formulario principal
        panelFormularioPrincipal.add(panelDatosGenerales, BorderLayout.NORTH);
        panelFormularioPrincipal.add(panelDinamico, BorderLayout.CENTER);

        // --- Panel de Botones ---
        JPanel panelBotones = crearPanelBotones();

        // --- Panel de la Tabla ---
        JPanel panelTabla = crearPanelTabla();

        // --- Agregar al JFrame ---
        add(panelFormularioPrincipal, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearPanelDatosGenerales() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                        "Datos Generales",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(70, 130, 180)
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: ID y Tipo Cliente
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(crearLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        txtId.setEditable(false);
        txtId.setBackground(new Color(230, 230, 230));
        panel.add(txtId, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Tipo Cliente:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        cmbTipoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(cmbTipoCliente, gbc);

        // Fila 1: Correo y Celular
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("Correo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtCorreo, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Celular:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        panel.add(txtCelular, gbc);

        // Fila 2: Contraseña
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtContrasena, gbc);

        return panel;
    }

    private JPanel crearPanelPersona() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
                        "Datos de Persona",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(34, 139, 34)
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Nombre y Apellidos
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(crearLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtNombre, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Apellidos:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        panel.add(txtApellidos, gbc);

        // Fila 1: Tipo Doc y Num Doc
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("Tipo Doc:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtTipoDoc, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Núm. Doc:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        panel.add(txtNumDoc, gbc);

        // Fila 2: Profesión y Dirección
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Profesión:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtProfesion, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Dirección:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        panel.add(txtDireccion, gbc);

        return panel;
    }

    private JPanel crearPanelEmpresa() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(220, 20, 60), 2),
                        "Datos de Empresa",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(220, 20, 60)
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Razón Social y Tipo Empresa
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(crearLabel("Razón Social:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtRazonSocial, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Tipo Empresa:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        panel.add(txtTipoEmpresa, gbc);

        // Fila 1: RUC y Dirección
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("RUC:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtRuc, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Dirección:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        panel.add(txtDireccion, gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(240, 240, 245));

        // Estilizar botones
        estilizarBoton(btnAgregar, new Color(34, 139, 34));
        estilizarBoton(btnActualizar, new Color(70, 130, 180));
        estilizarBoton(btnEliminar, new Color(220, 20, 60));
        estilizarBoton(btnLimpiar, new Color(128, 128, 128));

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(240, 240, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Título principal
        JLabel lblTitulo = new JLabel("CRUD GESTIONADOR DE CLIENTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(70, 130, 180));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Panel contenedor de la tabla
        JPanel panelTablaContenedor = new JPanel(new BorderLayout());
        panelTablaContenedor.setBackground(new Color(240, 240, 245));
        panelTablaContenedor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                        "Lista de Clientes",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(70, 130, 180)
                ),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        String[] columnas = {"ID", "Tipo", "Nombre/Razón Social", "Doc/RUC", "Correo", "Celular", "Dirección"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblClientes.setModel(modeloTabla);
        tblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblClientes.setRowHeight(25);
        tblClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblClientes.getTableHeader().setBackground(new Color(70, 130, 180));
        tblClientes.getTableHeader().setForeground(Color.WHITE);
        tblClientes.setSelectionBackground(new Color(173, 216, 230));
        tblClientes.setSelectionForeground(Color.BLACK);
        tblClientes.setGridColor(new Color(200, 200, 200));

        JScrollPane scrollTabla = new JScrollPane(tblClientes);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panelTablaContenedor.add(scrollTabla, BorderLayout.CENTER);

        panel.add(panelTablaContenedor, BorderLayout.CENTER);

        return panel;
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(50, 50, 50));
        return label;
    }

    private void estilizarBoton(JButton boton, Color color) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 1),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
    }

    private void configurarEventos() {
        // Cambiar panel según tipo de cliente seleccionado
        cmbTipoCliente.addActionListener(e -> {
            String tipo = (String) cmbTipoCliente.getSelectedItem();
            cardLayout.show(panelDinamico, tipo);
        });
    }

    public static void main(String[] args) {
        // Configurar Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            FrmCliente vista = new FrmCliente();
            ClienteService servicio = new ClienteService();
            ClienteController controlador = new ClienteController(vista, servicio);
            controlador.iniciar();
        });
    }
}