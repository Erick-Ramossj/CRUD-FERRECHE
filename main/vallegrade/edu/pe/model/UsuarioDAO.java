package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.ConexionBD;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UsuarioDAO {

    public Usuario validarLogin(String username, String password) {
        String sql = "SELECT id, username, password, rol FROM usuarios WHERE username = ? AND password = ?";
        Usuario usuario = null;
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en validarLogin (DAO): " + e.getMessage());
        }
        return usuario;
    }

    public int registrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (username, password, rol) VALUES (?, ?, ?)";
        int filasAfectadas = 0;
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Error de duplicidad
                System.err.println("Error: El usuario '" + u.getUsername() + "' ya existe.");
            } else {
                System.err.println("Error al registrar usuario (DAO): " + e.getMessage());
            }
        }
        return filasAfectadas;
    }

    // Método para listar (necesario para FrmUsuario/CRUD)
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, username, password, rol FROM usuarios";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rol")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios (DAO): " + e.getMessage());
        }
        return lista;
    }
}