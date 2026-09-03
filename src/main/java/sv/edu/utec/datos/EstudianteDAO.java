package sv.edu.utec.datos;


import sv.edu.utec.modelo.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EstudianteDAO {

    public void registroEstudiante() {

        String sql = """
                CREATE TABLE IF NOT EXISTS estudiante(
                id INT PRIMARY KEY,
                nombre VARCHAR(100),
                carrera VARCHAR(100),
                promedio DOUBLE
                )
                """;

        try (
                Connection cn = ConexionDB.obtenerConexion();
                Statement st = cn.createStatement()
        ) {

            st.execute(sql);

            System.out.println("Registro de estudiante exitoso.");

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        }
    }

    public void insertar(Estudiante estudiante) {

        String sql = """
            INSERT INTO estudiante
            (id, nombre, carrera, promedio)
            VALUES (?, ?, ?, ?)
            """;

        try (
                Connection cn = ConexionDB.obtenerConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
        ) {

            ps.setInt(1, estudiante.getId());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getCarrera());
            ps.setDouble(4, estudiante.getPromedio());

            ps.executeUpdate();

            System.out.println("Estudiante insertado.");

        } catch (SQLException e) {

            System.out.println(
                    "Error al insertar: " + e.getMessage()
            );
        }
    }

}
