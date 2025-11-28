package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // CONSULTAS SQL CORRECTAS
    private static final String SQL_SELECT =
            "SELECT id, name, price, discount_price, image, color, genero, rubro, description FROM productos";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, price, discount_price, image, color, genero, rubro, description FROM productos WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO productos (name, price, discount_price, image, color, genero, rubro, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE productos SET name=?, price=?, discount_price=?, image=?, color=?, genero=?, rubro=?, description=? WHERE id=?";

    private static final String SQL_DELETE =
            "DELETE FROM productos WHERE id = ?";


    // LISTAR TODOS
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("discount_price"),
                        rs.getString("image"),
                        rs.getString("color"),
                        rs.getString("genero"),
                        rs.getString("rubro"),
                        rs.getString("description")
                );

                lista.add(p);
            }

        } catch (Exception e) {
            System.err.println("ERROR en listar(): " + e.getMessage());
        }

        return lista;
    }

    // INSERTAR
    public boolean agregar(Producto p) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setDouble(3, p.getDiscount_price());
            ps.setString(4, p.getImage());
            ps.setString(5, p.getColor());
            ps.setString(6, p.getGenero());
            ps.setString(7, p.getRubro());
            ps.setString(8, p.getDescription());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("ERROR en agregar(): " + e.getMessage());
            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizar(Producto p) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setDouble(3, p.getDiscount_price());
            ps.setString(4, p.getImage());
            ps.setString(5, p.getColor());
            ps.setString(6, p.getGenero());
            ps.setString(7, p.getRubro());
            ps.setString(8, p.getDescription());
            ps.setInt(9, p.getId());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("ERROR en actualizar(): " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("ERROR en eliminar(): " + e.getMessage());
            return false;
        }
    }

    // BUSCAR POR ID
    public Producto buscarPorId(int id) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("discount_price"),
                        rs.getString("image"),
                        rs.getString("color"),
                        rs.getString("genero"),
                        rs.getString("rubro"),
                        rs.getString("description")
                );
            }

        } catch (Exception e) {
            System.err.println("ERROR en buscarPorId(): " + e.getMessage());
        }

        return null;
    }
}
