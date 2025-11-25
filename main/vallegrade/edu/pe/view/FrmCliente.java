package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.*;
import java.awt.*;
import vallegrade.edu.pe.model.Cliente;
import vallegrade.edu.pe.service.ClienteService;
import java.util.List;

public class FrmCliente extends JFrame {

    // === Componentes principales ===
    public JTable tblClientes;
    public JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnListar;
    public JComboBox<String> cmbTipoCliente;

    // === Campos comunes ===
    public JTextField txtId, txtCorreo, txtCelular;
    public JPasswordField txtContrasena;

    // === Campos persona ===
    public JTextField txtNombre, txtApellidos, txtDireccion, txtTipoDoc, txtNumDoc, txtProfesion;

    // === Campos empresa ===
    public JTextField txtRazonSocial, txtTipoEmpresa, txtRuc;

    // Panel dinámico
    private JPanel panelDinamico;
    private CardLayout cardLayout;

    private ClienteService clienteService = new ClienteService();

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
    private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 12);
    private final Font FUENTE_CAMPO = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 12);
    private final Font FUENTE_TABLA = new Font("Segoe UI", Font.PLAIN, 12);

    public FrmCliente() {
        setTitle("Gestión de Clientes");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(COLOR_FONDO);

        initComponents();
        configurarEventos();
        cargarTabla(); // cargar al inicio
    }

    private void initComponents() {

        // ======== PANEL SUPERIOR (FORMULARIO) ========
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(COLOR_PANEL);
        panelForm.setBorder(crearBordeTitulado("Datos del Cliente"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBackground(new Color(245, 245, 245));

        cmbTipoCliente = new JComboBox<>(new String[]{"persona", "empresa"});
        txtCorreo = new JTextField();
        txtCelular = new JTextField();
        txtContrasena = new JPasswordField();

        // Aplicar estilo a campos comunes
        estilizarCampo(txtId);
        estilizarComboBox(cmbTipoCliente);
        estilizarCampo(txtCorreo);
        estilizarCampo(txtCelular);
        estilizarCampo(txtContrasena);

        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.insets = new Insets(5, 8, 5, 8);
        gbcForm.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID y Tipo Cliente
        gbcForm.gridx = 0; gbcForm.gridy = 0; gbcForm.weightx = 0;
        panelForm.add(crearLabel("ID:"), gbcForm);
        gbcForm.gridx = 1; gbcForm.weightx = 0.5;
        panelForm.add(txtId, gbcForm);

        gbcForm.gridx = 2; gbcForm.weightx = 0;
        panelForm.add(crearLabel("Tipo Cliente:"), gbcForm);
        gbcForm.gridx = 3; gbcForm.weightx = 0.5;
        panelForm.add(cmbTipoCliente, gbcForm);

        // Fila 2: Correo y Celular
        gbcForm.gridx = 0; gbcForm.gridy = 1; gbcForm.weightx = 0;
        panelForm.add(crearLabel("Correo:"), gbcForm);
        gbcForm.gridx = 1; gbcForm.weightx = 0.5;
        panelForm.add(txtCorreo, gbcForm);

        gbcForm.gridx = 2; gbcForm.weightx = 0;
        panelForm.add(crearLabel("Celular:"), gbcForm);
        gbcForm.gridx = 3; gbcForm.weightx = 0.5;
        panelForm.add(txtCelular, gbcForm);

        // Fila 3: Contraseña
        gbcForm.gridx = 0; gbcForm.gridy = 2; gbcForm.weightx = 0;
        panelForm.add(crearLabel("Contraseña:"), gbcForm);
        gbcForm.gridx = 1; gbcForm.weightx = 0.5;
        panelForm.add(txtContrasena, gbcForm);

        // ======== PANEL PERSONA ========
        JPanel panelPersona = new JPanel(new GridBagLayout());
        panelPersona.setBackground(COLOR_PANEL);
        panelPersona.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        txtNombre = new JTextField();
        txtApellidos = new JTextField();
        txtDireccion = new JTextField();
        txtTipoDoc = new JTextField();
        txtNumDoc = new JTextField();
        txtProfesion = new JTextField();

        // Aplicar estilo a campos persona
        estilizarCampo(txtNombre);
        estilizarCampo(txtApellidos);
        estilizarCampo(txtDireccion);
        estilizarCampo(txtTipoDoc);
        estilizarCampo(txtNumDoc);
        estilizarCampo(txtProfesion);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Nombre y Apellidos
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelPersona.add(crearLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panelPersona.add(txtNombre, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        panelPersona.add(crearLabel("Apellidos:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        panelPersona.add(txtApellidos, gbc);

        // Fila 2: Tipo Doc y N° Doc
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelPersona.add(crearLabel("Tipo Doc.:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.6;
        panelPersona.add(txtTipoDoc, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        panelPersona.add(crearLabel("N° Doc.:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        panelPersona.add(txtNumDoc, gbc);

        // Fila 3: Profesión y Dirección
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelPersona.add(crearLabel("Profesión:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panelPersona.add(txtProfesion, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        panelPersona.add(crearLabel("Dirección:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        panelPersona.add(txtDireccion, gbc);

        // ======== PANEL EMPRESA ========
        JPanel panelEmpresa = new JPanel(new GridBagLayout());
        panelEmpresa.setBackground(COLOR_PANEL);
        panelEmpresa.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        txtRazonSocial = new JTextField();
        txtTipoEmpresa = new JTextField();
        txtRuc = new JTextField();

        // Aplicar estilo a campos empresa
        estilizarCampo(txtRazonSocial);
        estilizarCampo(txtTipoEmpresa);
        estilizarCampo(txtRuc);

        GridBagConstraints gbcEmp = new GridBagConstraints();
        gbcEmp.insets = new Insets(6, 8, 6, 8);
        gbcEmp.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Razón Social y Tipo Empresa
        gbcEmp.gridx = 0; gbcEmp.gridy = 0; gbcEmp.weightx = 0;
        panelEmpresa.add(crearLabel("Razón Social:"), gbcEmp);
        gbcEmp.gridx = 1; gbcEmp.weightx = 1;
        panelEmpresa.add(txtRazonSocial, gbcEmp);

        gbcEmp.gridx = 2; gbcEmp.weightx = 0;
        panelEmpresa.add(crearLabel("Tipo Empresa:"), gbcEmp);
        gbcEmp.gridx = 3; gbcEmp.weightx = 1;
        panelEmpresa.add(txtTipoEmpresa, gbcEmp);

        // Fila 2: RUC
        gbcEmp.gridx = 0; gbcEmp.gridy = 1; gbcEmp.weightx = 0;
        panelEmpresa.add(crearLabel("RUC:"), gbcEmp);
        gbcEmp.gridx = 1; gbcEmp.weightx = 1;
        panelEmpresa.add(txtRuc, gbcEmp);

        // ======== CARDLAYOUT (dinámico) ========
        panelDinamico = new JPanel();
        cardLayout = new CardLayout();
        panelDinamico.setLayout(cardLayout);
        panelDinamico.setBackground(COLOR_PANEL);
        panelDinamico.setBorder(crearBordeTitulado("Información Específica"));
        panelDinamico.setPreferredSize(new Dimension(0, 150));
        panelDinamico.add(panelPersona, "persona");
        panelDinamico.add(panelEmpresa, "empresa");

        JPanel panelCentro = new JPanel(new BorderLayout(0, 10));
        panelCentro.setBackground(COLOR_FONDO);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        panelCentro.add(panelForm, BorderLayout.NORTH);
        panelCentro.add(panelDinamico, BorderLayout.CENTER);

        // Panel contenedor superior con tamaño fijo
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(COLOR_FONDO);
        panelSuperior.setPreferredSize(new Dimension(0, 330));
        panelSuperior.add(panelCentro, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // ======== TABLA (LISTADO) ========
        tblClientes = new JTable();
        tblClientes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID", "Tipo", "Nombre / Razón Social", "Documento / RUC", "Correo", "Celular", "Dirección"
                }
        ));
        estilizarTabla(tblClientes);

        JScrollPane scroll = new JScrollPane(tblClientes);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(COLOR_PANEL);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(COLOR_FONDO);
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 15, 10, 15),
                crearBordeTitulado("Listado de Clientes")
        ));
        panelTabla.add(scroll, BorderLayout.CENTER);

        add(panelTabla, BorderLayout.CENTER);

        // ======== BOTONES ========
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        panelBotones.setBackground(COLOR_SECUNDARIO);

        btnAgregar = crearBoton("Agregar", COLOR_ACENTO);
        btnActualizar = crearBoton("Actualizar", COLOR_PRIMARIO);
        btnEliminar = crearBoton("Eliminar", COLOR_PELIGRO);
        btnLimpiar = crearBoton("Limpiar", COLOR_ADVERTENCIA);
        btnListar = crearBoton("Listar", new Color(149, 165, 166));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnListar);

        add(panelBotones, BorderLayout.SOUTH);
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

    private void estilizarComboBox(JComboBox<String> combo) {
        combo.setFont(FUENTE_CAMPO);
        combo.setForeground(COLOR_TEXTO);
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(189, 195, 199), 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private Border crearBordeTitulado(String titulo) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARIO, 1, true),
                titulo
        );
        border.setTitleFont(FUENTE_TITULO);
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
        boton.setPreferredSize(new Dimension(100, 32));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
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
        tabla.setFont(FUENTE_TABLA);
        tabla.setForeground(COLOR_TEXTO);
        tabla.setBackground(COLOR_PANEL);
        tabla.setSelectionBackground(COLOR_PRIMARIO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setRowHeight(28);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setShowGrid(true);
        tabla.setIntercellSpacing(new Dimension(1, 1));

        // Estilo del encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(COLOR_SECUNDARIO);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 32));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRIMARIO));
    }

    // === Mostrar el panel correcto según el tipo seleccionado ===
    private void configurarEventos() {
        // Cambiar panel dinámico
        cmbTipoCliente.addActionListener(e -> {
            String tipo = (String) cmbTipoCliente.getSelectedItem();
            cardLayout.show(panelDinamico, tipo.equals("persona") ? "persona" : "empresa");
        });

        // === BOTÓN LISTAR ===
        btnListar.addActionListener(e -> cargarTabla());

        // === BOTÓN LIMPIAR ===
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // === BOTÓN AGREGAR ===
        btnAgregar.addActionListener(e -> {
            Cliente c = obtenerClienteDelFormulario(false);
            if (c != null) {
                try {
                    clienteService.agregarCliente(c);
                    cargarTabla();
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Cliente agregado correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
                }
            }
        });

        // === BOTÓN ACTUALIZAR ===
        btnActualizar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecciona un registro primero.");
                return;
            }
            Cliente c = obtenerClienteDelFormulario(true);
            if (c != null) {
                try {
                    clienteService.actualizarCliente(c);
                    cargarTabla();
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
                }
            }
        });

        // === BOTÓN ELIMINAR ===
        btnEliminar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecciona un registro para eliminar.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas eliminar este cliente?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    clienteService.eliminarCliente(Integer.parseInt(txtId.getText()));
                    cargarTabla();
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Cliente eliminado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
                }
            }
        });

        // === CLICK EN LA TABLA: subir datos al formulario ===
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tblClientes.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(tblClientes.getValueAt(fila, 0).toString());
                    cmbTipoCliente.setSelectedItem(tblClientes.getValueAt(fila, 1).toString());
                    String tipo = tblClientes.getValueAt(fila, 1).toString();
                    String campo2 = tblClientes.getValueAt(fila, 2) != null ? tblClientes.getValueAt(fila, 2).toString() : "";
                    String campo3 = tblClientes.getValueAt(fila, 3) != null ? tblClientes.getValueAt(fila, 3).toString() : "";

                    if ("persona".equalsIgnoreCase(tipo)) {
                        // Separar nombre y apellidos
                        String[] nombreCompleto = campo2.split(" ", 2);
                        txtNombre.setText(nombreCompleto.length > 0 ? nombreCompleto[0] : "");
                        txtApellidos.setText(nombreCompleto.length > 1 ? nombreCompleto[1] : "");
                        txtNumDoc.setText(campo3);
                        cardLayout.show(panelDinamico, "persona");
                    } else {
                        txtRazonSocial.setText(campo2);
                        txtRuc.setText(campo3);
                        cardLayout.show(panelDinamico, "empresa");
                    }

                    txtCorreo.setText(tblClientes.getValueAt(fila, 4) != null ? tblClientes.getValueAt(fila, 4).toString() : "");
                    txtCelular.setText(tblClientes.getValueAt(fila, 5) != null ? tblClientes.getValueAt(fila, 5).toString() : "");
                    txtDireccion.setText(tblClientes.getValueAt(fila, 6) != null ? tblClientes.getValueAt(fila, 6).toString() : "");
                }
            }
        });
    }

    private Cliente obtenerClienteDelFormulario(boolean incluirId) {
        try {
            Cliente c = new Cliente();
            if (incluirId && !txtId.getText().isEmpty()) c.setId(Integer.parseInt(txtId.getText()));

            c.setTipo_cliente((String) cmbTipoCliente.getSelectedItem());
            c.setCorreo(txtCorreo.getText());
            c.setCelular(txtCelular.getText());
            c.setContrasena(new String(txtContrasena.getPassword()));
            c.setDireccion(txtDireccion.getText());

            if ("persona".equalsIgnoreCase(c.getTipo_cliente())) {
                c.setNombre(txtNombre.getText());
                c.setApellidos(txtApellidos.getText());
                c.setTipo_documento(txtTipoDoc.getText());
                c.setNumero_documento(txtNumDoc.getText());
                c.setProfesion(txtProfesion.getText());
            } else {
                c.setRazon_social(txtRazonSocial.getText());
                c.setTipo_empresa(txtTipoEmpresa.getText());
                c.setRuc(txtRuc.getText());
            }

            return c;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al leer los campos: " + ex.getMessage());
            return null;
        }
    }

    private void cargarTabla() {
        List<Cliente> lista = clienteService.listarClientes();

        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setRowCount(0);

        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                    c.getId(),
                    c.getTipo_cliente(),
                    c.getTipo_cliente().equalsIgnoreCase("persona") ?
                            (c.getNombre() != null ? c.getNombre() : "") + (c.getApellidos() != null ? " " + c.getApellidos() : "") :
                            (c.getRazon_social() != null ? c.getRazon_social() : ""),
                    c.getTipo_cliente().equalsIgnoreCase("persona") ?
                            (c.getNumero_documento() != null ? c.getNumero_documento() : "") :
                            (c.getRuc() != null ? c.getRuc() : ""),
                    c.getCorreo(),
                    c.getCelular(),
                    c.getDireccion()
            });
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtCorreo.setText("");
        txtCelular.setText("");
        txtContrasena.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtTipoDoc.setText("");
        txtNumDoc.setText("");
        txtProfesion.setText("");
        txtRazonSocial.setText("");
        txtTipoEmpresa.setText("");
        txtRuc.setText("");
    }

    // Para abrir con el cardLayout correcto al iniciar
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        String tipo = (String) cmbTipoCliente.getSelectedItem();
        cardLayout.show(panelDinamico, tipo != null && tipo.equals("empresa") ? "empresa" : "persona");
    }
}
