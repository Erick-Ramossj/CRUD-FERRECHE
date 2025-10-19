package vallegrade.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class FrmRegistro extends JFrame {

    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JTextField txtRol;
    public JButton btnRegistrar;
    public JButton btnVolverLogin;

    public FrmRegistro() {
        setTitle("Registro de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtRol = new JTextField("USER");
        btnRegistrar = new JButton("Registrar");
        btnVolverLogin = new JButton("Volver a Login");

        add(new JLabel("Usuario Deseado:"));
        add(txtUsername);

        add(new JLabel("Contraseña:"));
        add(txtPassword);

        add(new JLabel("Rol (Ej: USER):"));
        add(txtRol);

        add(btnVolverLogin);
        add(btnRegistrar);
    }
}