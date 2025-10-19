package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Usuario;
import vallegrade.edu.pe.service.LoginService;
import vallegrade.edu.pe.view.FrmLogin;
import vallegrade.edu.pe.view.FrmRegistro;
import vallegrade.edu.pe.view.FrmUsuario; // Ventana principal

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginController implements ActionListener {

    private FrmLogin loginView;
    private FrmRegistro registroView;
    private LoginService loginService;

    // Constructor 1: Login
    public LoginController(FrmLogin loginView) {
        this.loginView = loginView;
        this.loginService = new LoginService();
        this.loginView.btnIngresar.addActionListener(this);
        this.loginView.btnIrRegistro.addActionListener(this);
    }

    // Constructor 2: Registro
    public LoginController(FrmRegistro registroView) {
        this.registroView = registroView;
        this.loginService = new LoginService();
        this.registroView.btnRegistrar.addActionListener(this);
        this.registroView.btnVolverLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (loginView != null && e.getSource() == loginView.btnIngresar) {
            iniciarSesion();
        } else if (loginView != null && e.getSource() == loginView.btnIrRegistro) {
            abrirRegistro();
        } else if (registroView != null && e.getSource() == registroView.btnRegistrar) {
            registrarUsuario();
        } else if (registroView != null && e.getSource() == registroView.btnVolverLogin) {
            volverLogin();
        }
    }

    private void iniciarSesion() {
        String user = loginView.txtUsername.getText();
        String pass = new String(loginView.txtPassword.getPassword());
        Usuario usuario = loginService.iniciarSesion(user, pass);

        if (usuario != null) {
            JOptionPane.showMessageDialog(loginView, "¡Bienvenido, " + usuario.getUsername() + "!");
            loginView.dispose();
            // Abre la ventana de gestión de usuarios (CRUD)
            new FrmUsuario().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos.", "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarUsuario() {
        String user = registroView.txtUsername.getText();
        String pass = new String(registroView.txtPassword.getPassword());
        String rol = registroView.txtRol.getText().trim().toUpperCase();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(registroView, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (loginService.registrarNuevoUsuario(user, pass, rol)) {
            JOptionPane.showMessageDialog(registroView, "Registro exitoso. ¡Inicia sesión!");
            volverLogin();
        } else {
            JOptionPane.showMessageDialog(registroView, "Error al registrar. El nombre de usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        loginView.setVisible(false);
        FrmRegistro registro = new FrmRegistro();
        new LoginController(registro); // Nuevo controlador para el registro
        registro.setVisible(true);
    }

    private void volverLogin() {
        registroView.dispose();
        if (loginView != null) {
            loginView.setVisible(true);
        }
    }
}