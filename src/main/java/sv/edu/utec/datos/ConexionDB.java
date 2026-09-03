package sv.edu.utec.datos;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConexionDB {
    private static final String URL = "jdbc:h2:./estudiante";
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
