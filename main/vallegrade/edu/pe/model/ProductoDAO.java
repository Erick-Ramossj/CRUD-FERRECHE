package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String SQL_SELECT = "SELECT id, nombre, precio, stock FROM producto";
    private static final String SQL_SELECT_BY_ID = "SELECT id, nombre, precio, stock FROM producto WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO producto (nombre, precio, stock) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE id = ?";

    // 🔹 Listar todos los productos
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error en listar(): " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Agregar producto
    public boolean agregar(Producto p) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en agregar(): " + e.getMessage());
            return false;
        }
    }

    // 🔹 Actualizar producto
    public boolean actualizar(Producto p) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en actualizar(): " + e.getMessage());
            return false;
        }
    }

    // 🔹 Eliminar producto
    public boolean eliminar(int id) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en eliminar(): " + e.getMessage());
            return false;
        }
    }
}
