package vallegrade.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class FrmLogin extends JFrame {

    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnIngresar;
    public JButton btnIrRegistro;

    public FrmLogin() {
        setTitle("Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridLayout(4, 2, 10, 10));

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        btnIngresar = new JButton("Ingresar");
        btnIrRegistro = new JButton("Registrarme");

        panelPrincipal.add(new JLabel("Usuario:"));
        panelPrincipal.add(txtUsername);

        panelPrincipal.add(new JLabel("Contraseña:"));
        panelPrincipal.add(txtPassword);

        panelPrincipal.add(new JLabel(""));
        panelPrincipal.add(btnIngresar);

        panelPrincipal.add(new JLabel("¿No tienes cuenta?"));
        panelPrincipal.add(btnIrRegistro);

        add(panelPrincipal, BorderLayout.CENTER);

        pack();
        setResizable(false);
    }
}