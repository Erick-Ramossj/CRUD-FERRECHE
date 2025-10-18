package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        Connection conn = ConexionBD.conectar();

        if (conn == null) {
            // Si no hay conexión, devolveremos datos simulados (modo offline)
            System.out.println("📴 Modo sin conexión: mostrando datos simulados");
            lista.add(new Categoria(1, "Ferretería", "Productos de ferretería"));
            lista.add(new Categoria(2, "Electricidad", "Materiales eléctricos"));
            lista.add(new Categoria(3, "Pinturas", "Pinturas y accesorios"));
            return lista;
        }

        String sql = "SELECT * FROM categoria";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    // Los métodos agregar, actualizar y eliminar también deben manejar la falta de conexión:

    public boolean agregar(Categoria c) {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("⚠️ No hay conexión. No se puede agregar categoría.");
            return false;
        }
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Categoria c) {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("⚠️ No hay conexión. No se puede actualizar categoría.");
            return false;
        }
        String sql = "UPDATE categoria SET nombre=?, descripcion=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("⚠️ No hay conexión. No se puede eliminar categoría.");
            return false;
        }
        String sql = "DELETE FROM categoria WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
}
