package vallegrade.edu.pe.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import vallegrade.edu.pe.controller.DireccionController;

public class FrmDireccion extends JDialog {

    public JTable tblDirecciones;
    public DefaultTableModel modeloTabla;

    public JTextField txtIdDireccion;
    public JTextField txtCalle;
    public JTextField txtNumero;
    public JTextField txtDistrito;
    public JTextField txtProvincia;
    public JTextField txtDepartamento;
    public JTextField txtReferencia;
    public JCheckBox chkEsPrincipal;

    public JButton btnAgregar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnLimpiar;
    public JButton btnVolver;

    // === COLORES DEL TEMA ===
    private final Color COLOR_PRIMARIO = new Color(41, 128, 185);
    private final Color COLOR_SECUNDARIO = new Color(52, 73, 94);
    private final Color COLOR_FONDO = new Color(236, 240, 241);
    private final Color COLOR_PANEL = new Color(255, 255, 255);
    private final Color COLOR_TEXTO = new Color(44, 62, 80);
    private final Color COLOR_ACENTO = new Color(26, 188, 156);
    private final Color COLOR_PELIGRO = new Color(231, 76, 60);
    private final Color COLOR_ADVERTENCIA = new Color(241, 196, 15);

    // === FUENTES ===
    private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 16);
    private final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 12);
    private final Font FUENTE_CAMPO = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 12);

    public FrmDireccion(JFrame parent) {
        super(parent, true); // true indica que es MODAL (bloquea la ventana de atrás)

        setTitle("Gestión de Direcciones");
        setSize(1000, 700);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COLOR_FONDO);

        inicializarComponentes();

        // Inicializar controlador
        new DireccionController(this).iniciar();
    }

    private void inicializarComponentes() {
        // Panel superior: encabezado + formulario
        JPanel panelSuperior = new JPanel(new BorderLayout(0, 5));
        panelSuperior.setBackground(COLOR_FONDO);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelEncabezado = crearPanelEncabezado();
        JPanel panelForm = crearPanelFormulario();

        panelSuperior.add(panelEncabezado, BorderLayout.NORTH);
        panelSuperior.add(panelForm, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel central: tabla
        JPanel panelTabla = crearPanelTabla();
        add(panelTabla, BorderLayout.CENTER);

        // Panel inferior: botones
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearPanelEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PRIMARIO);
        panel.setPreferredSize(new Dimension(0, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JLabel lblTitulo = new JLabel("Gestión de Direcciones");
        lblTitulo.setFont(FUENTE_TITULO);
        lblTitulo.setForeground(Color.WHITE);

        panel.add(lblTitulo, BorderLayout.WEST);
        return panel;
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                crearBordeTitulado("Datos de la Dirección"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtIdDireccion = new JTextField(5);
        txtIdDireccion.setEditable(false);
        txtIdDireccion.setBackground(new Color(245, 245, 245));
        txtCalle = new JTextField(20);
        txtNumero = new JTextField(10);
        txtDistrito = new JTextField(20);
        txtProvincia = new JTextField(20);
        txtDepartamento = new JTextField(20);
        txtReferencia = new JTextField(30);
        chkEsPrincipal = new JCheckBox("Principal");

        estilizarCampo(txtIdDireccion);
        estilizarCampo(txtCalle);
        estilizarCampo(txtNumero);
        estilizarCampo(txtDistrito);
        estilizarCampo(txtProvincia);
        estilizarCampo(txtDepartamento);
        estilizarCampo(txtReferencia);

        // Fila 1: ID, Calle, Número
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panel.add(crearLabel("ID Dir:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        panel.add(txtIdDireccion, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        panel.add(crearLabel("Calle:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        panel.add(txtCalle, gbc);
        gbc.gridx = 4; gbc.weightx = 0;
        panel.add(crearLabel("Número:"), gbc);
        gbc.gridx = 5; gbc.weightx = 0.4;
        panel.add(txtNumero, gbc);

        // Fila 2: Distrito, Provincia, Departamento
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panel.add(crearLabel("Distrito:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        panel.add(txtDistrito, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        panel.add(crearLabel("Provincia:"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.8;
        panel.add(txtProvincia, gbc);
        gbc.gridx = 4; gbc.weightx = 0;
        panel.add(crearLabel("Depto.:"), gbc);
        gbc.gridx = 5; gbc.weightx = 0.6;
        panel.add(txtDepartamento, gbc);

        // Fila 3: Referencia y Principal
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panel.add(crearLabel("Referencia:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.weightx = 1;
        panel.add(txtReferencia, gbc);
        gbc.gridx = 5; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(chkEsPrincipal, gbc);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 15, 10, 15),
                crearBordeTitulado("Listado de Direcciones")
        ));

        String[] columnas = {"ID", "Calle", "Número", "Distrito", "Provincia", "Departamento", "Principal"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblDirecciones = new JTable(modeloTabla);
        estilizarTabla(tblDirecciones);

        JScrollPane scrollPane = new JScrollPane(tblDirecciones);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(COLOR_PANEL);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(COLOR_SECUNDARIO);

        btnAgregar = crearBoton("Agregar", COLOR_ACENTO);
        btnActualizar = crearBoton("Actualizar", COLOR_PRIMARIO);
        btnEliminar = crearBoton("Eliminar", COLOR_PELIGRO);
        btnLimpiar = crearBoton("Limpiar", COLOR_ADVERTENCIA);
        btnVolver = crearBoton("Volver", new Color(149, 165, 166));

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        panel.add(btnVolver);

        btnLimpiar.addActionListener(e -> limpiarCampos());

        // --- AQUÍ ESTÁ EL CAMBIO ---
        // Al dar clic en Volver, simplemente cerramos la ventana (dispose).
        // Como es un JDialog modal, la ventana padre (MainMenu) que estaba "congelada" detrás
        // volverá a estar activa automáticamente. No creamos un "new MainMenuView()".
        btnVolver.addActionListener(e -> {
            dispose();
        });

        return panel;
    }

    // === MÉTODOS DE ESTILIZACIÓN ===

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_LABEL);
        label.setForeground(COLOR_TEXTO);
        return label;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(FUENTE_CAMPO);
        campo.setForeground(COLOR_TEXTO);
        campo.setPreferredSize(new Dimension(0, 28));
        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(189, 195, 199), 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        campo.setCaretColor(COLOR_PRIMARIO);
    }

    private Border crearBordeTitulado(String titulo) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARIO, 1, true),
                titulo
        );
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        border.setTitleColor(COLOR_PRIMARIO);
        return BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(5, 10, 8, 10)
        );
    }

    private JButton crearBoton(String texto, Color colorBase) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorBase);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(110, 32));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase);
            }
        });

        return boton;
    }

    private void estilizarTabla(JTable tabla) {
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setForeground(COLOR_TEXTO);
        tabla.setBackground(COLOR_PANEL);
        tabla.setSelectionBackground(COLOR_PRIMARIO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setRowHeight(28);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setShowGrid(true);
        tabla.setIntercellSpacing(new Dimension(1, 1));

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(COLOR_SECUNDARIO);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 32));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRIMARIO));
    }

    public void limpiarCampos() {
        txtIdDireccion.setText("");
        txtCalle.setText("");
        txtNumero.setText("");
        txtDistrito.setText("");
        txtProvincia.setText("");
        txtDepartamento.setText("");
        txtReferencia.setText("");
        chkEsPrincipal.setSelected(false);
    }
}
