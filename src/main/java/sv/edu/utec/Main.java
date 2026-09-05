package sv.edu.utec;

import sv.edu.utec.datos.EstudianteDAO;
import sv.edu.utec.modelo.Estudiante;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EstudianteDAO dao = new EstudianteDAO();

        System.out.println(dao.probarConexion());

        dao.crearTabla();

        insertarEstudiantesEjemplo(dao);

        listarEstudiantes(dao);

        actualizarEstudiante(dao);

        System.out.println("\n--- Despues de actualizacion ---");
        listarEstudiantes(dao);

        dao.eliminar(2);

        System.out.println("\n--- Despues de eliminar registro ---");
        listarEstudiantes(dao);

        buscarPorCarnet(dao, "2526102024");
    }

    private static void insertarEstudiantesEjemplo(EstudianteDAO dao) {
        Estudiante e1 = new Estudiante(1, "María", "González Pérez", "Ingeniería Informática",
                "2526102024", "maria.g@mail.utec.edu.sv", "7777-1111");
        Estudiante e2 = new Estudiante(2, "Carlos", "Ramírez López", "Administración de Empresas",
                "2123122025", "carlos.r@mail.utec.edu.sv", "7777-2222");
        Estudiante e3 = new Estudiante(3, "Ana", "Martínez Flores", "Mercadeo",
                "2026102025", "ana.m@mail.utec.edu.sv", "7777-3333");
        Estudiante e4 = new Estudiante(4, "Jorge", "Hernández Castro", "Arquitectura",
                "2212352025", "jorge.h@mail.utec.edu.sv", "7777-4444");

        dao.insertar(e1);
        dao.insertar(e2);
        dao.insertar(e3);
        dao.insertar(e4);
    }

    private static void listarEstudiantes(EstudianteDAO dao) {
        List<Estudiante> estudiantes = dao.listarTodos();

        if (estudiantes.isEmpty()) {
            System.out.println(" No hay estudiantes registrados.");
            return;
        }

        System.out.println("\n Lista de Estudiantes");

        System.out.printf("%-5s %-22s %-25s %-12s %-30s %-15s%n",
                "ID", "Nombre", "Carrera", "Carnet", "Correo", "Teléfono");


        for (Estudiante e : estudiantes) {
            System.out.printf("%-5d %-22s %-25s %-12s %-30s %-15s%n",
                    e.getId(),
                    e.getNombre() + " " + e.getApellidos(),
                    e.getCarrera(),
                    e.getCarnet(),
                    e.getCorreo(),
                    e.getTelefono());
        }
        System.out.println("Total: " + estudiantes.size() + " estudiantes\n");
    }

    private static void actualizarEstudiante(EstudianteDAO dao) {
        Estudiante e = new Estudiante();
        e.setId(1);
        e.setNombre("María");
        e.setApellidos("González Pérez de León");
        e.setCarrera("Ingeniería Informática");
        e.setCarnet("2526102024");
        e.setCorreo("maria.glez@utec.edu.sv");
        e.setTelefono("7777-9999");

        dao.actualizar(e);
    }

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