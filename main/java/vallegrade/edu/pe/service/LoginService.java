package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Usuario;
import vallegrade.edu.pe.model.UsuarioDAO;

public class LoginService {

    private UsuarioDAO dao;

    public LoginService() {
        this.dao = new UsuarioDAO();
    }

    // Método de Login (para referencia)
    public Usuario iniciarSesion(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }
        return dao.validarLogin(username, password);
    }

    // MÉTODO DE REGISTRO CORREGIDO
    public boolean registrarNuevoUsuario(String username, String password, String rol) {
        // Validación básica de campos
        if (username == null || password == null || rol == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            System.err.println("Service Error: Usuario o contraseña no pueden ser vacíos.");
            return false;
        }

        // Crea el objeto Usuario con ID 0 (que se autogenerará en la DB)
        Usuario nuevoUsuario = new Usuario(0, username.trim(), password, rol.trim().toUpperCase());

        // Llama al DAO. Debe retornar true si 1 fila fue afectada (inserción exitosa).
        return dao.registrarUsuario(nuevoUsuario) == 1;
    }

    // ... otros métodos (listar, etc.)
}