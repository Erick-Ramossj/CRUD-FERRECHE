package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import vallegrade.edu.pe.controller.ClienteController;
import vallegrade.edu.pe.service.ClienteService;

/**
 * Vista principal mejorada para la gestión de Clientes.
 * Diseño profesional con temática de ferretería.
 */
public class FrmCliente extends JFrame {

    // Paleta de colores profesional para ferretería
    private static final Color COLOR_PRINCIPAL = new Color(255, 87, 34); // Naranja ferretería
    private static final Color COLOR_SECUNDARIO = new Color(66, 66, 66); // Gris oscuro
    private static final Color COLOR_ACENTO = new Color(255, 152, 0); // Naranja claro
    private static final Color COLOR_EXITO = new Color(76, 175, 80); // Verde
    private static final Color COLOR_PELIGRO = new Color(244, 67, 54); // Rojo
    private static final Color COLOR_INFO = new Color(33, 150, 243); // Azul
    private static final Color COLOR_FONDO = new Color(245, 245, 245); // Gris muy claro
    private static final Color COLOR_BLANCO = Color.WHITE;

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
    public JButton btnAgregar = new JButton("🔨 Agregar");
    public JButton btnActualizar = new JButton("🔧 Actualizar");
    public JButton btnEliminar = new JButton("🗑️ Eliminar");
    public JButton btnLimpiar = new JButton("🧹 Limpiar");

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
        setTitle("🛠️ Gestión de Clientes - Ferretería ValleGrade");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Color de fondo
        getContentPane().setBackground(COLOR_FONDO);

        // --- Panel Principal del Formulario ---
        JPanel panelFormularioPrincipal = new JPanel(new BorderLayout(10, 10));
        panelFormularioPrincipal.setBackground(COLOR_FONDO);
        panelFormularioPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título del formulario
        JLabel lblTituloFormulario = new JLabel("🔨 FORMULARIO DE REGISTRO DE CLIENTES", SwingConstants.CENTER);
        lblTituloFormulario.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTituloFormulario.setForeground(COLOR_PRINCIPAL);
        lblTituloFormulario.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        panelFormularioPrincipal.add(lblTituloFormulario, BorderLayout.NORTH);

        // Panel contenedor para los formularios
        JPanel panelContenedorFormularios = new JPanel(new BorderLayout(10, 10));
        panelContenedorFormularios.setBackground(COLOR_FONDO);

        // --- Panel de Datos Generales ---
        JPanel panelDatosGenerales = crearPanelDatosGenerales();

        // --- Panel Dinámico (Persona/Empresa) ---
        cardLayout = new CardLayout();
        panelDinamico = new JPanel(cardLayout);
        panelDinamico.setBackground(COLOR_FONDO);

        panelPersona = crearPanelPersona();
        panelEmpresa = crearPanelEmpresa();

        panelDinamico.add(panelPersona, "persona");
        panelDinamico.add(panelEmpresa, "empresa");

        // Agregar paneles al contenedor de formularios
        panelContenedorFormularios.add(panelDatosGenerales, BorderLayout.NORTH);
        panelContenedorFormularios.add(panelDinamico, BorderLayout.CENTER);

        // Agregar contenedor al panel principal
        panelFormularioPrincipal.add(panelContenedorFormularios, BorderLayout.CENTER);

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
        panel.setBackground(COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(COLOR_PRINCIPAL, 2),
                        "📋 Datos Generales",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        COLOR_PRINCIPAL
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: ID y Tipo Cliente
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(crearLabel("🔢 ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        txtId.setEditable(false);
        txtId.setBackground(new Color(230, 230, 230));
        estilizarTextField(txtId);
        panel.add(txtId, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("👤 Tipo Cliente:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        cmbTipoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbTipoCliente.setBackground(COLOR_BLANCO);
        panel.add(cmbTipoCliente, gbc);

        // Fila 1: Correo y Celular
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("📧 Correo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtCorreo);
        panel.add(txtCorreo, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("📱 Celular:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtCelular);
        panel.add(txtCelular, gbc);

        // Fila 2: Contraseña
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("🔐 Contraseña:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtContrasena);
        panel.add(txtContrasena, gbc);

        return panel;
    }

    private JPanel crearPanelPersona() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(COLOR_EXITO, 2),
                        "👷 Datos de Persona",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        COLOR_EXITO
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Nombre y Apellidos
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(crearLabel("✏️ Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtNombre);
        panel.add(txtNombre, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("✏️ Apellidos:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtApellidos);
        panel.add(txtApellidos, gbc);

        // Fila 1: Tipo Doc y Num Doc
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("📄 Tipo Doc:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtTipoDoc);
        panel.add(txtTipoDoc, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("🆔 Núm. Doc:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtNumDoc);
        panel.add(txtNumDoc, gbc);

        // Fila 2: Profesión y Dirección
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("💼 Profesión:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtProfesion);
        panel.add(txtProfesion, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("🏠 Dirección:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtDireccion);
        panel.add(txtDireccion, gbc);

        return panel;
    }

    private JPanel crearPanelEmpresa() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(COLOR_INFO, 2),
                        "🏢 Datos de Empresa",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        COLOR_INFO
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Razón Social y Tipo Empresa
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(crearLabel("🏭 Razón Social:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtRazonSocial);
        panel.add(txtRazonSocial, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("🏪 Tipo Empresa:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtTipoEmpresa);
        panel.add(txtTipoEmpresa, gbc);

        // Fila 1: RUC y Dirección
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("📝 RUC:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtRuc);
        panel.add(txtRuc, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("🏠 Dirección:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtDireccion);
        panel.add(txtDireccion, gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, COLOR_SECUNDARIO),
                BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));

        // Estilizar botones
        estilizarBoton(btnAgregar, COLOR_EXITO);
        estilizarBoton(btnActualizar, COLOR_INFO);
        estilizarBoton(btnEliminar, COLOR_PELIGRO);
        estilizarBoton(btnLimpiar, COLOR_SECUNDARIO);

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Título principal
        JLabel lblTitulo = new JLabel("🛠️ CRUD GESTIONADOR DE CLIENTES - FERRETERÍA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_PRINCIPAL);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Panel contenedor de la tabla
        JPanel panelTablaContenedor = new JPanel(new BorderLayout());
        panelTablaContenedor.setBackground(COLOR_FONDO);
        panelTablaContenedor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(COLOR_PRINCIPAL, 2),
                        "📊 Lista de Clientes Registrados",
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        COLOR_PRINCIPAL
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
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
        tblClientes.setRowHeight(28);
        tblClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblClientes.getTableHeader().setBackground(COLOR_PRINCIPAL);
        tblClientes.getTableHeader().setForeground(COLOR_BLANCO);
        tblClientes.setSelectionBackground(new Color(255, 224, 178));
        tblClientes.setSelectionForeground(COLOR_SECUNDARIO);
        tblClientes.setGridColor(new Color(220, 220, 220));
        tblClientes.setShowGrid(true);
        tblClientes.setIntercellSpacing(new Dimension(1, 1));

        JScrollPane scrollTabla = new JScrollPane(tblClientes);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollTabla.getViewport().setBackground(COLOR_BLANCO);
        panelTablaContenedor.add(scrollTabla, BorderLayout.CENTER);

        panel.add(panelTablaContenedor, BorderLayout.CENTER);

        return panel;
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(COLOR_SECUNDARIO);
        return label;
    }

    private void estilizarTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }

    private void estilizarBoton(JButton boton, Color color) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(COLOR_BLANCO);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
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