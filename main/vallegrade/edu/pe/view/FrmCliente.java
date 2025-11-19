package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import vallegrade.edu.pe.controller.ClienteController;
import vallegrade.edu.pe.service.ClienteService;

/**
 * Vista principal mejorada para la gestión de Clientes.
 * Diseño profesional con temática de ferretería ValleGrade.
 */
public class FrmCliente extends JFrame {

    // Paleta de colores profesional inspirada en ferreterías
    private static final Color COLOR_PRINCIPAL = new Color(230, 81, 0); // Naranja vibrante
    private static final Color COLOR_SECUNDARIO = new Color(52, 58, 64); // Gris oscuro profesional
    private static final Color COLOR_ACENTO = new Color(255, 167, 38); // Amarillo herramientas
    private static final Color COLOR_EXITO = new Color(40, 167, 69); // Verde
    private static final Color COLOR_PELIGRO = new Color(220, 53, 69); // Rojo
    private static final Color COLOR_INFO = new Color(23, 162, 184); // Azul cyan
    private static final Color COLOR_FONDO = new Color(248, 249, 250); // Gris muy claro
    private static final Color COLOR_BLANCO = Color.WHITE;
    private static final Color COLOR_TABLA_HEADER = new Color(33, 37, 41); // Negro-gris

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

    // --- Botones con iconos mejorados ---
    public JButton btnAgregar = new JButton("✚ AGREGAR");
    public JButton btnActualizar = new JButton("✎ ACTUALIZAR");
    public JButton btnEliminar = new JButton("✖ ELIMINAR");
    public JButton btnLimpiar = new JButton("⟲ LIMPIAR");

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
        setTitle("Ferretería ValleGrade - Sistema de Gestión de Clientes");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(COLOR_FONDO);

        // --- Panel Superior con Header ---
        JPanel panelHeader = crearPanelHeader();
        add(panelHeader, BorderLayout.NORTH);

        // --- Panel Central con Formulario ---
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBackground(COLOR_FONDO);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        // Panel de formularios
        JPanel panelFormularios = new JPanel(new BorderLayout(10, 10));
        panelFormularios.setBackground(COLOR_FONDO);

        // Datos Generales
        JPanel panelDatosGenerales = crearPanelDatosGenerales();

        // Panel Dinámico (Persona/Empresa)
        cardLayout = new CardLayout();
        panelDinamico = new JPanel(cardLayout);
        panelDinamico.setBackground(COLOR_FONDO);

        panelPersona = crearPanelPersona();
        panelEmpresa = crearPanelEmpresa();

        panelDinamico.add(panelPersona, "persona");
        panelDinamico.add(panelEmpresa, "empresa");

        panelFormularios.add(panelDatosGenerales, BorderLayout.NORTH);
        panelFormularios.add(panelDinamico, BorderLayout.CENTER);

        panelCentral.add(panelFormularios, BorderLayout.NORTH);

        // Tabla
        JPanel panelTabla = crearPanelTabla();
        panelCentral.add(panelTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // --- Panel de Botones ---
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearPanelHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PRINCIPAL);
        panel.setPreferredSize(new Dimension(0, 80));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, COLOR_ACENTO),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Panel izquierdo con título
        JPanel panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelIzq.setOpaque(false);

        JLabel lblIcono = new JLabel("🔨");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        panelIzq.add(lblIcono);

        JPanel panelTextos = new JPanel(new GridLayout(2, 1, 0, 2));
        panelTextos.setOpaque(false);

        JLabel lblTitulo = new JLabel("FERRETERÍA VALLEGRADE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COLOR_BLANCO);

        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Clientes");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(255, 255, 255, 200));

        panelTextos.add(lblTitulo);
        panelTextos.add(lblSubtitulo);
        panelIzq.add(panelTextos);

        panel.add(panelIzq, BorderLayout.WEST);

        return panel;
    }

    private JPanel crearPanelDatosGenerales() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_PRINCIPAL, 2, true),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 15, 10, 15),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                )
        ));

        // Título del panel
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.gridwidth = 4;
        gbcTitulo.insets = new Insets(5, 0, 10, 0);
        gbcTitulo.anchor = GridBagConstraints.WEST;

        JLabel lblTituloPanel = new JLabel("📋 DATOS GENERALES DEL CLIENTE");
        lblTituloPanel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTituloPanel.setForeground(COLOR_PRINCIPAL);
        panel.add(lblTituloPanel, gbcTitulo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID y Tipo Cliente
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("ID Cliente:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        txtId.setEditable(false);
        txtId.setBackground(new Color(233, 236, 239));
        estilizarTextField(txtId);
        panel.add(txtId, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Tipo de Cliente:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarComboBox(cmbTipoCliente);
        panel.add(cmbTipoCliente, gbc);

        // Fila 2: Correo y Celular
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("📧 Correo Electrónico:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtCorreo);
        panel.add(txtCorreo, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("📱 Celular:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtCelular);
        panel.add(txtCelular, gbc);

        // Fila 3: Contraseña
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
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
                BorderFactory.createLineBorder(COLOR_EXITO, 2, true),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 15, 10, 15),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                )
        ));

        // Título
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.gridwidth = 4;
        gbcTitulo.insets = new Insets(5, 0, 10, 0);
        gbcTitulo.anchor = GridBagConstraints.WEST;

        JLabel lblTituloPanel = new JLabel("👤 INFORMACIÓN PERSONAL");
        lblTituloPanel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTituloPanel.setForeground(COLOR_EXITO);
        panel.add(lblTituloPanel, gbcTitulo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Nombre y Apellidos
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("Nombres:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtNombre);
        panel.add(txtNombre, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Apellidos:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtApellidos);
        panel.add(txtApellidos, gbc);

        // Fila 2: Tipo Doc y Num Doc
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Tipo Documento:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtTipoDoc);
        panel.add(txtTipoDoc, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Nro. Documento:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtNumDoc);
        panel.add(txtNumDoc, gbc);

        // Fila 3: Profesión y Dirección
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
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
                BorderFactory.createLineBorder(COLOR_INFO, 2, true),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 15, 10, 15),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                )
        ));

        // Título
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.gridwidth = 4;
        gbcTitulo.insets = new Insets(5, 0, 10, 0);
        gbcTitulo.anchor = GridBagConstraints.WEST;

        JLabel lblTituloPanel = new JLabel("🏢 INFORMACIÓN EMPRESARIAL");
        lblTituloPanel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTituloPanel.setForeground(COLOR_INFO);
        panel.add(lblTituloPanel, gbcTitulo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Razón Social y Tipo Empresa
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(crearLabel("Razón Social:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        estilizarTextField(txtRazonSocial);
        panel.add(txtRazonSocial, gbc);

        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(crearLabel("Tipo de Empresa:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.3;
        estilizarTextField(txtTipoEmpresa);
        panel.add(txtTipoEmpresa, gbc);

        // Fila 2: RUC y Dirección
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
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

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(COLOR_FONDO);

        // Título de la sección
        JLabel lblTitulo = new JLabel("📊 REGISTRO DE CLIENTES", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COLOR_SECUNDARIO);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Configurar tabla
        String[] columnas = {"ID", "Tipo", "Nombre/Razón Social", "Doc/RUC", "Correo", "Celular", "Dirección"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblClientes.setModel(modeloTabla);
        tblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblClientes.setRowHeight(32);
        tblClientes.setShowGrid(true);
        tblClientes.setGridColor(new Color(222, 226, 230));
        tblClientes.setIntercellSpacing(new Dimension(1, 1));
        tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Estilizar header
        JTableHeader header = tblClientes.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(COLOR_TABLA_HEADER);
        header.setForeground(COLOR_BLANCO);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRINCIPAL));

        // Centrar contenido de celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblClientes.getColumnCount(); i++) {
            tblClientes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Colores alternados en filas
        tblClientes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(new Color(255, 193, 7, 100));
                    c.setForeground(COLOR_SECUNDARIO);
                } else {
                    c.setBackground(row % 2 == 0 ? COLOR_BLANCO : new Color(248, 249, 250));
                    c.setForeground(COLOR_SECUNDARIO);
                }
                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tblClientes);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218), 1));
        scrollPane.getViewport().setBackground(COLOR_BLANCO);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(COLOR_BLANCO);
        panel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(222, 226, 230)));

        // Estilizar botones
        estilizarBoton(btnAgregar, COLOR_EXITO, new Color(33, 136, 56));
        estilizarBoton(btnActualizar, COLOR_INFO, new Color(19, 132, 150));
        estilizarBoton(btnEliminar, COLOR_PELIGRO, new Color(200, 35, 51));
        estilizarBoton(btnLimpiar, COLOR_SECUNDARIO, new Color(40, 45, 50));

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);

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
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        textField.setBackground(COLOR_BLANCO);

        // Efecto focus
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(COLOR_PRINCIPAL, 2),
                        BorderFactory.createEmptyBorder(5, 9, 5, 9)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                ));
            }
        });
    }

    private void estilizarComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setBackground(COLOR_BLANCO);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void estilizarBoton(JButton boton, Color color, Color colorHover) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(color);
        boton.setForeground(COLOR_BLANCO);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
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
            limpiarCamposEspecificos();
        });
    }

    private void limpiarCamposEspecificos() {
        // Limpiar campos específicos al cambiar tipo de cliente
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTipoDoc.setText("");
        txtNumDoc.setText("");
        txtProfesion.setText("");
        txtRazonSocial.setText("");
        txtTipoEmpresa.setText("");
        txtRuc.setText("");
        txtDireccion.setText("");
    }

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            FrmCliente vista = new FrmCliente();
            ClienteService servicio = new ClienteService();
            ClienteController controlador = new ClienteController(vista, servicio);
            controlador.iniciar();
        });
    }
}