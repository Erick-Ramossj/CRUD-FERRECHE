package vallegrade.edu.pe.dao;

import vallegrade.edu.pe.database.ConexionBD;
import vallegrade.edu.pe.model.Direccion; // Cambiado el paquete a 'dao' si no lo tenías
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionDAO {

    // CONSULTAS SQL
    // Se incluye id_cliente en todas las consultas
    private static final String SQL_SELECT_BY_CLIENTE =
            "SELECT id, id_cliente, calle, numero, distrito, provincia, departamento, referencia, es_principal FROM direcciones WHERE id_cliente = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, id_cliente, calle, numero, distrito, provincia, departamento, referencia, es_principal FROM direcciones WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO direcciones (id_cliente, calle, numero, distrito, provincia, departamento, referencia, es_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE direcciones SET id_cliente = ?, calle = ?, numero = ?, distrito = ?, provincia = ?, departamento = ?, referencia = ?, es_principal = ? WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM direcciones WHERE id = ?";

    // -----------------------------------------------------------------------------------
    // ▶ LISTAR DIRECCIONES POR CLIENTE (FUNCIÓN CLAVE)
    // -----------------------------------------------------------------------------------
    public List<Direccion> listarPorCliente(int idCliente) {
        List<Direccion> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_CLIENTE)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(crearDireccionDesdeResultSet(rs));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ERROR en listarPorCliente(" + idCliente + "): " + e.getMessage());
        }

        return lista;
    }

    // -----------------------------------------------------------------------------------
    // ▶ BUSCAR POR ID DE DIRECCIÓN
    // -----------------------------------------------------------------------------------
    public Direccion buscarPorId(int id) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearDireccionDesdeResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ERROR en buscarPorId(): " + e.getMessage());
        }
        return null;
    }

    // -----------------------------------------------------------------------------------
    // ▶ INSERTAR
    // -----------------------------------------------------------------------------------
    public boolean agregar(Direccion d) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, d.getIdCliente());
            ps.setString(2, d.getCalle());
            ps.setString(3, d.getNumero());
            ps.setString(4, d.getDistrito());
            ps.setString(5, d.getProvincia());
            ps.setString(6, d.getDepartamento());
            ps.setString(7, d.getReferencia());
            ps.setBoolean(8, d.isEsPrincipal());

            ps.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ERROR en agregar(): " + e.getMessage());
            return false;
        }
    }

    // -----------------------------------------------------------------------------------
    // ▶ ACTUALIZAR
    // -----------------------------------------------------------------------------------
    public boolean actualizar(Direccion d) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, d.getIdCliente());
            ps.setString(2, d.getCalle());
            ps.setString(3, d.getNumero());
            ps.setString(4, d.getDistrito());
            ps.setString(5, d.getProvincia());
            ps.setString(6, d.getDepartamento());
            ps.setString(7, d.getReferencia());
            ps.setBoolean(8, d.isEsPrincipal());
            ps.setInt(9, d.getId()); // ID al final del UPDATE

            ps.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ERROR en actualizar(): " + e.getMessage());
            return false;
        }
    }

    // -----------------------------------------------------------------------------------
    // ▶ ELIMINAR
    // -----------------------------------------------------------------------------------
    public boolean eliminar(int id) {
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ERROR en eliminar(): " + e.getMessage());
            return false;
        }
    }

    // -----------------------------------------------------------------------------------
    // MÉTODO AUXILIAR para mapear ResultSet a Objeto
    // -----------------------------------------------------------------------------------
    private Direccion crearDireccionDesdeResultSet(ResultSet rs) throws SQLException {
        Direccion d = new Direccion();
        d.setId(rs.getInt("id"));
        d.setIdCliente(rs.getInt("id_cliente"));
        d.setCalle(rs.getString("calle"));
        d.setNumero(rs.getString("numero"));
        d.setDistrito(rs.getString("distrito"));
        d.setProvincia(rs.getString("provincia"));
        d.setDepartamento(rs.getString("departamento"));
        d.setReferencia(rs.getString("referencia"));
        d.setEsPrincipal(rs.getBoolean("es_principal"));
        return d;
    }
}
