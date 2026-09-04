package sv.edu.utec.datos;

import sv.edu.utec.modelo.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    // 1. CREAR TABLA
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS estudiante ("
                + "id INT PRIMARY KEY, "
                + "nombre VARCHAR(50) NOT NULL, "
                + "apellidos VARCHAR(50) NOT NULL, "
                + "carrera VARCHAR(50), "
                + "carnet VARCHAR(20) UNIQUE, "
                + "correo VARCHAR(100), "
                + "telefono VARCHAR(20)"
                + ")";

        try (Connection cn = ConexionDB.obtenerConexion();
             Statement st = cn.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla 'estudiante' creada/verificada.");
        } catch (SQLException e) {
            System.out.println("Error al crear tabla: " + e.getMessage());
        }
    }

    // 2. INSERTAR (CREATE) - ACTUALIZADA
    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (id, nombre, apellidos, carrera, carnet, correo, telefono) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, estudiante.getId());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellidos());
            ps.setString(4, estudiante.getCarrera());
            ps.setString(5, estudiante.getCarnet());
            ps.setString(6, estudiante.getCorreo());
            ps.setString(7, estudiante.getTelefono());

            ps.executeUpdate();
            System.out.println("Estudiante insertado: " + estudiante.getNombre() + " " + estudiante.getApellidos());

        } catch (SQLException e) {
            System.out.println("Error al insertar estudiante: " + e.getMessage());
        }
    }

    // 3. LISTAR (READ)
    public List<Estudiante> listarTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiante ORDER BY id";

        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidos(rs.getString("apellidos"));
                e.setCarrera(rs.getString("carrera"));
                e.setCarnet(rs.getString("carnet"));
                e.setCorreo(rs.getString("correo"));
                e.setTelefono(rs.getString("telefono"));
                estudiantes.add(e);
            }

        } catch (SQLException e) {
            System.out.println(" Error al listar estudiantes: " + e.getMessage());
        }

        return estudiantes;
    }

    // 4. ACTUALIZAR (UPDATE)
    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre=?, apellidos=?, carrera=?, carnet=?, "
                + "correo=?, telefono=? WHERE id=?";

        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getCarrera());
            ps.setString(4, estudiante.getCarnet());
            ps.setString(5, estudiante.getCorreo());
            ps.setString(6, estudiante.getTelefono());
            ps.setInt(7, estudiante.getId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Estudiante actualizado: " + estudiante.getNombre() + " " + estudiante.getApellidos());
            } else {
                System.out.println("No se encontró estudiante con ID: " + estudiante.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante: " + e.getMessage());
        }
    }

    // 5. ELIMINAR (DELETE)
    public void eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";

        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Estudiante eliminado con ID: " + id);
            } else {
                System.out.println("⚠️ No se encontró estudiante con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        }
    }

    // METODO EXTRA: Buscar por Carnet (opcional pero útil)
    public Estudiante buscarPorCarnet(String carnet) {
        String sql = "SELECT * FROM estudiante WHERE carnet = ?";

        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, carnet);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Estudiante e = new Estudiante();
                    e.setId(rs.getInt("id"));
                    e.setNombre(rs.getString("nombre"));
                    e.setApellidos(rs.getString("apellidos"));
                    e.setCarrera(rs.getString("carrera"));
                    e.setCarnet(rs.getString("carnet"));
                    e.setCorreo(rs.getString("correo"));
                    e.setTelefono(rs.getString("telefono"));
                    return e;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar por carnet: " + e.getMessage());
        }

        return null;
    }
}