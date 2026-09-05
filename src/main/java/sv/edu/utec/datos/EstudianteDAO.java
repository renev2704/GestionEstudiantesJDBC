package sv.edu.utec.datos;

import sv.edu.utec.modelo.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public String probarConexion() {
        try (Connection cn = ConexionDB.obtenerConexion()) {
            if (cn != null && !cn.isClosed()) {
                return " Conexion exitosa a: " + cn.getMetaData().getURL();
            }
        } catch (SQLException e) {
            return " Error de conexion: " + e.getMessage();
        }
        return " No se pudo verificar la conexion.";
    }

    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS estudiante (" +
                "id INT PRIMARY KEY, " +
                "nombre VARCHAR(50), " +
                "apellidos VARCHAR(50), " +
                "carrera VARCHAR(50), " +
                "carnet VARCHAR(20) UNIQUE, " +
                "correo VARCHAR(60), " +
                "telefono VARCHAR(15))";
        try (Connection cn = ConexionDB.obtenerConexion();
             Statement st = cn.createStatement()) {
            st.execute(sql);
            System.out.println(" Tabla estudiante lista.");
        } catch (SQLException e) {
            System.out.println(" Error al crear la tabla: " + e.getMessage());
        }
    }

    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (id, nombre, apellidos, carrera, carnet, correo, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            System.out.println(" Estudiante insertado.");

        } catch (SQLException e) {
            System.out.println(" Error al insertar: " + e.getMessage());
        }
    }

    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellidos, carrera, carnet, correo, telefono FROM estudiante";

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
                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println(" Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // Informa si el registro existía o no (retorna boolean)
    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, apellidos = ?, carrera = ?, " +
                "carnet = ?, correo = ?, telefono = ? WHERE id = ?";
        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getCarrera());
            ps.setString(4, estudiante.getCarnet());
            ps.setString(5, estudiante.getCorreo());
            ps.setString(6, estudiante.getTelefono());
            ps.setInt(7, estudiante.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println(" Estudiante actualizado.");
                return true;
            } else {
                System.out.println(" No existe un estudiante con ese id.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(" Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // Informa si la eliminación tuvo efecto (retorna boolean)
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";
        try (Connection cn = ConexionDB.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(" Estudiante eliminado.");
                return true;
            } else {
                System.out.println(" No existe un estudiante con ese id.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(" Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public Estudiante buscarPorCarnet(String carnet) {
        String sql = "SELECT id, nombre, apellidos, carrera, carnet, correo, telefono " +
                "FROM estudiante WHERE carnet = ?";

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
            System.out.println(" Error al buscar por carnet: " + e.getMessage());
        }
        return null;
    }
}