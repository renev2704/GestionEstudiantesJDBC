package sv.edu.utec;


import sv.edu.utec.modelo.Estudiante;

import sv.edu.utec.datos.EstudianteDAO;

public class Main {

    public static void main(String[] args) {

        EstudianteDAO dao = new EstudianteDAO();

        dao.registroEstudiante();

        dao.insertar(
                new Estudiante(
                        1,
                        "Alejandra Carolina Guillen Campos",
                        "Ingenieria en Sistemas",
                        8.5
                )
        );

        dao.insertar(
                new Estudiante(
                        2,
                        "Juan Martinez",
                        "Ingenieria Industrial",
                        7.8
                )
        );

        dao.insertar(
                new Estudiante(
                        3,
                        "Maria Lopez",
                        "Licenciatura en Informatica",
                        9.2
                )
        );

    }
}