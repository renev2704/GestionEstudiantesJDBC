package sv.edu.utec;

import sv.edu.utec.datos.EstudianteDAO;
import sv.edu.utec.datos.ConexionDB;
import sv.edu.utec.modelo.Estudiante;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. Probar conexión
        probarConexion();

        // 2. Crear DAO
        EstudianteDAO dao = new EstudianteDAO();

        // 3. Crear tabla
        dao.crearTabla();

        // 4. Insertar estudiantes de ejemplo
        insertarEstudiantesEjemplo(dao);

        // 5. Listar todos los estudiantes
        listarEstudiantes(dao);

        // 6. Actualizar un estudiante
        actualizarEstudiante(dao);

        // 7. Listar nuevamente para ver cambios
        System.out.println("\n--- DESPUES DE ACTUALIZAR ---");
        listarEstudiantes(dao);

        // 8. Eliminar un estudiante
        dao.eliminar(2);

        // 9. Listar después de eliminar
        System.out.println("\n--- DESPUES DE ELIMINAR ---");
        listarEstudiantes(dao);

        // 10. Buscar por carnet (ejemplo)
        buscarPorCarnet(dao, "20240001");
    }

    // Metodo para probar conexión
    private static void probarConexion() {
        try (Connection cn = ConexionDB.obtenerConexion()) {
            if (cn != null && !cn.isClosed()) {
                System.out.println(" Conexión exitosa a: " + cn.getMetaData().getURL());
            }
        } catch (SQLException e) {
            System.out.println(" Error de conexión: " + e.getMessage());
        }
    }

    // Metodo para insertar estudiantes de ejemplo
    private static void insertarEstudiantesEjemplo(EstudianteDAO dao) {
        Estudiante e1 = new Estudiante(1, "María", "González Pérez", "Ingeniería Informática",
                "20240001", "maria.g@utec.edu.sv", "7777-1111");
        Estudiante e2 = new Estudiante(2, "Carlos", "Ramírez López", "Administración de Empresas",
                "20240002", "carlos.r@utec.edu.sv", "7777-2222");
        Estudiante e3 = new Estudiante(3, "Ana", "Martínez Flores", "Medicina",
                "20240003", "ana.m@utec.edu.sv", "7777-3333");
        Estudiante e4 = new Estudiante(4, "Jorge", "Hernández Castro", "Arquitectura",
                "20240004", "jorge.h@utec.edu.sv", "7777-4444");

        dao.insertar(e1);
        dao.insertar(e2);
        dao.insertar(e3);
        dao.insertar(e4);
    }

    // Metodo para listar estudiantes en formato tabla
    private static void listarEstudiantes(EstudianteDAO dao) {
        List<Estudiante> estudiantes = dao.listarTodos();

        if (estudiantes.isEmpty()) {
            System.out.println(" No hay estudiantes registrados.");
            return;
        }

        System.out.println("\n LISTA DE ESTUDIANTES");
        System.out.println("=".repeat(110));
        System.out.printf("%-5s %-22s %-25s %-12s %-30s %-15s%n",
                "ID", "Nombre", "Carrera", "Carnet", "Correo", "Teléfono");
        System.out.println("-".repeat(110));

        for (Estudiante e : estudiantes) {
            System.out.printf("%-5d %-22s %-25s %-12s %-30s %-15s%n",
                    e.getId(),
                    e.getNombre() + " " + e.getApellidos(),
                    e.getCarrera(),
                    e.getCarnet(),
                    e.getCorreo(),
                    e.getTelefono());
        }
        System.out.println("=".repeat(110));
        System.out.println("Total: " + estudiantes.size() + " estudiantes\n");
    }

    // Metodo para actualizar un estudiante
    private static void actualizarEstudiante(EstudianteDAO dao) {
        Estudiante e = new Estudiante();
        e.setId(1);
        e.setNombre("María");
        e.setApellidos("González Pérez de León");
        e.setCarrera("Ingeniería Informática");
        e.setCarnet("20240001");
        e.setCorreo("maria.glez@utec.edu.sv");
        e.setTelefono("7777-9999");

        dao.actualizar(e);
    }

    // Metodo para buscar por carnet
    private static void buscarPorCarnet(EstudianteDAO dao, String carnet) {
        System.out.println("\n Buscando estudiante con carnet: " + carnet);
        Estudiante e = dao.buscarPorCarnet(carnet);

        if (e != null) {
            System.out.println(" Encontrado: " + e.getNombre() + " " + e.getApellidos() +
                    " | " + e.getCarrera());
        } else {
            System.out.println(" No se encontró estudiante con carnet: " + carnet);
        }
    }
}