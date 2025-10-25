package vallegrade.edu.pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import vallegrade.edu.pe.database.ConexionBD;

/**
 * Clase DAO (Data Access Object) para la entidad Cliente.
 * Contiene los métodos CRUD para interactuar con la tabla 'clientes'.
 * Versión corregida con manejo completo de excepciones.
 */
public class ClienteDAO {

    // ... (Aquí van tus métodos agregar, listar, actualizar, eliminar que ya te envié) ...
    // ... (No los repito aquí para ser breve, pero asegúrate de que estén) ...

    public boolean agregar(Cliente cliente) {
        String sql = "INSERT INTO clientes (tipo_cliente, correo, celular, contrasena, "
                + "nombre, apellidos, direccion, tipo_documento, numero_documento, profesion, "
                + "razon_social, tipo_empresa, ruc) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConexionBD.conectar(); // Conectar DENTRO del try
            if (conn == null) return false;

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, cliente.getTipo_cliente());
            pstmt.setString(2, cliente.getCorreo());
            pstmt.setString(3, cliente.getCelular());
            pstmt.setString(4, cliente.getContrasena()); // Idealmente, encriptar antes de guardar
            pstmt.setString(5, cliente.getNombre());
            pstmt.setString(6, cliente.getApellidos());
            pstmt.setString(7, cliente.getDireccion());
            pstmt.setString(8, cliente.getTipo_documento());
            pstmt.setString(9, cliente.getNumero_documento());
            pstmt.setString(10, cliente.getProfesion());
            pstmt.setString(11, cliente.getRazon_social());
            pstmt.setString(12, cliente.getTipo_empresa());
            pstmt.setString(13, cliente.getRuc());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (ClassNotFoundException e) {
            System.out.println("Error FATAL: Driver JDBC no encontrado.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public List<Cliente> listar() {
        System.out.println("DEBUG: Iniciando listado de clientes (DAO)..."); // Mensaje de depuración
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.conectar(); // Conectar DENTRO del try
            if (conn == null) return clientes; // Devuelve lista vacía si no hay conexión

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // Paso 2: Ejecutar consulta

            System.out.println("DEBUG: Consulta ejecutada. Procesando resultados...");

            while (rs.next()) { // Paso 3: Recorrer resultados
                System.out.println("DEBUG: Cliente encontrado. ID: " + rs.getInt("id")); // Depuración
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTipo_cliente(rs.getString("tipo_cliente"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTipo_documento(rs.getString("tipo_documento"));
                cliente.setNumero_documento(rs.getString("numero_documento"));
                cliente.setProfesion(rs.getString("profesion"));
                cliente.setRazon_social(rs.getString("razon_social"));
                cliente.setTipo_empresa(rs.getString("tipo_empresa"));
                cliente.setRuc(rs.getString("ruc"));
                cliente.setFecha_registro(rs.getTimestamp("fecha_registro"));

                clientes.add(cliente);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error FATAL: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de SQL al listar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos en orden inverso
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        System.out.println("DEBUG: Listado DAO finalizado. Clientes encontrados: " + clientes.size());
        return clientes;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET tipo_cliente = ?, correo = ?, celular = ?, contrasena = ?, "
                + "nombre = ?, apellidos = ?, direccion = ?, tipo_documento = ?, numero_documento = ?, profesion = ?, "
                + "razon_social = ?, tipo_empresa = ?, ruc = ? "
                + "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConexionBD.conectar(); // Conectar DENTRO del try
            if (conn == null) return false;

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, cliente.getTipo_cliente());
            pstmt.setString(2, cliente.getCorreo());
            pstmt.setString(3, cliente.getCelular());
            pstmt.setString(4, cliente.getContrasena());
            pstmt.setString(5, cliente.getNombre());
            pstmt.setString(6, cliente.getApellidos());
            pstmt.setString(7, cliente.getDireccion());
            pstmt.setString(8, cliente.getTipo_documento());
            pstmt.setString(9, cliente.getNumero_documento());
            pstmt.setString(10, cliente.getProfesion());
            pstmt.setString(11, cliente.getRazon_social());
            pstmt.setString(12, cliente.getTipo_empresa());
            pstmt.setString(13, cliente.getRuc());
            pstmt.setInt(14, cliente.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (ClassNotFoundException e) {
            System.out.println("Error FATAL: Driver JDBC no encontrado.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConexionBD.conectar(); // Conectar DENTRO del try
            if (conn == null) return false;

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (ClassNotFoundException e) {
            System.out.println("Error FATAL: Driver JDBC no encontrado.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    /**
     * === NUEVO MÉTODO ===
     * Método para buscar un cliente por su ID.
     * @param id El ID del cliente a buscar.
     * @return Un objeto Cliente si se encuentra, o null si no se encuentra.
     */
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.conectar();
            if (conn == null) return null;

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTipo_cliente(rs.getString("tipo_cliente"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTipo_documento(rs.getString("tipo_documento"));
                cliente.setNumero_documento(rs.getString("numero_documento"));
                cliente.setProfesion(rs.getString("profesion"));
                cliente.setRazon_social(rs.getString("razon_social"));
                cliente.setTipo_empresa(rs.getString("tipo_empresa"));
                cliente.setRuc(rs.getString("ruc"));
                cliente.setFecha_registro(rs.getTimestamp("fecha_registro"));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error FATAL: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de SQL al buscar cliente por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return cliente;
    }
}
