package vallegrade.edu.pe.database; // Corregido

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Reemplaza con la dirección de tu servidor (Endpoint)
    private static final String HOST = "database-1.crg8ic00cg2q.us-east-1.rds.amazonaws.com";
    private static final String PORT = "3306";
    private static final String DB_NAME = "tiendadb";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

    // Reemplaza con tus credenciales
    private static final String USER = "admin";
    private static final String PASSWORD = "tienda_db";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión a la BD exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
}