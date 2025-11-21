package vallegrade.edu.pe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://database-1.cm2ob0olohnz.us-east-1.rds.amazonaws.com/ferreche";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin_1234_";

    /**
     * Intenta conectar a la base de datos.
     * @return una Conexión (Connection) si tiene éxito.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     * @throws ClassNotFoundException si el driver JDBC no se encuentra.
     */
    public static Connection conectar() throws SQLException, ClassNotFoundException {
        // Ya no capturamos las excepciones aquí, dejamos que el método que llama (DAO) se encargue.

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        // Si la conexión fue exitosa, imprimimos esto.
        System.out.println("✅ Conexión exitosa a la base de datos");
        return conn;
    }
}
