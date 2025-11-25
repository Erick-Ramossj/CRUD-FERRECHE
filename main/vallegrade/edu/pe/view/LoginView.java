package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnSalir;

    // === COLORES (Mismo tema que tu FrmCliente) ===
    private final Color COLOR_PRIMARIO = new Color(41, 128, 185);
    private final Color COLOR_FONDO = new Color(236, 240, 241);
    private final Color COLOR_TEXTO = new Color(44, 62, 80);

    public LoginView() {
        setTitle("Inicio de Sesión");
        setSize(400, 350); // Tamaño compacto para Login
        setLocationRelativeTo(null); // Centrado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true); // Opcional: Quita los bordes de Windows para un look moderno

        initComponents();
    }

    private void initComponents() {
        // 1. PANEL CABECERA (Azul)
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        panelHeader.setPreferredSize(new Dimension(0, 60));
        JLabel lblTitulo = new JLabel("ACCESO AL SISTEMA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        // 2. PANEL CENTRAL (Formulario)
        JPanel panelCentro = new JPanel(new GridLayout(4, 1, 10, 10));
        panelCentro.setBackground(COLOR_FONDO);
        panelCentro.setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setForeground(COLOR_TEXTO);

        txtUsuario = new JTextField();
        estilizarCampo(txtUsuario);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPass.setForeground(COLOR_TEXTO);

        txtPassword = new JPasswordField();
        estilizarCampo(txtPassword);

        panelCentro.add(lblUser);
        panelCentro.add(txtUsuario);
        panelCentro.add(lblPass);
        panelCentro.add(txtPassword);

        // 3. PANEL BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setBackground(COLOR_FONDO);

        btnIngresar = crearBoton("Ingresar", COLOR_PRIMARIO);
        btnSalir = crearBoton("Salir", new Color(231, 76, 60));

        panelBotones.add(btnIngresar);
        panelBotones.add(btnSalir);

        // Agregamos paneles a la ventana
        add(panelHeader, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // === EVENTOS ===

        // Botón Salir
        btnSalir.addActionListener(e -> System.exit(0));

        // Botón Ingresar
        btnIngresar.addActionListener(e -> validarCredenciales());

        // Permitir entrar presionando ENTER en el campo de contraseña
        txtPassword.addActionListener(e -> validarCredenciales());
    }

    private void validarCredenciales() {
        // Obtenemos los datos y usamos trim() para borrar espacios accidentales al inicio o final
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        // 1. Definimos la contraseña común
        String passwordCorrecta = "1234";

        // 2. Verificamos si el usuario es uno de los permitidos (ignorando mayúsculas/minúsculas)
        boolean usuarioEsValido = usuario.equalsIgnoreCase("lucia") ||
                usuario.equalsIgnoreCase("erick") ||
                usuario.equalsIgnoreCase("fabrizio");

        // 3. Validamos que AMBAS cosas sean ciertas: usuario válido Y contraseña correcta
        if (usuarioEsValido && password.equals(passwordCorrecta)) {

            JOptionPane.showMessageDialog(this, "¡Bienvenido/a " + usuario + "!");

            // Abrir el Menú Principal
            MainMenuView menu = new MainMenuView();
            menu.setVisible(true);

            // Cerrar el Login
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Credenciales incorrectas.\nVerifica tu usuario y contraseña.",
                    "Acceso Denegado",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.setText(""); // Limpiar campo de contraseña
            txtUsuario.requestFocus(); // Volver el foco al usuario
        }
    }

    // === Métodos auxiliares de diseño (similares a tu FrmCliente) ===
    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
