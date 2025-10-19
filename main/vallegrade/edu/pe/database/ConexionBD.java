package vallegrade.edu.pe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://crud-poo.crg8ic00cg2q.us-east-1.rds.amazonaws.com/bd_ferreteria";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin_1234_";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("⚠️ Driver JDBC no encontrado, continuando sin base de datos...");
        } catch (SQLException e) {
            System.out.println("⚠️ No se pudo conectar a la base de datos, continuando sin conexión...");
        }
        return null; // devolvemos null, pero lo manejaremos más adelante
    }
}
