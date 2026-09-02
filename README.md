# 🎓 GestionEstudiantesJDBC

Sistema de gestión de estudiantes para la UTEC - Proyecto JDBC con H2 Database

---

## Integrantes

| Nombre | Carnet |
|--------|--------|
| [Nombre Estudiante 1] | [Carnet] |
| [Nombre Estudiante 2] | [Carnet] |
| [Nombre Estudiante 3] | [Carnet] |

---

## Entidad Elegida

**Estudiante** - Sistema de gestión académica universitaria

### Atributos de la entidad

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | INT (PK) | Identificador único del estudiante |
| `nombre` | VARCHAR(50) | Nombre del estudiante |
| `apellidos` | VARCHAR(50) | Apellidos del estudiante |
| `carrera` | VARCHAR(50) | Carrera que cursa |
| `carnet` | VARCHAR(20) UNIQUE | Número de carnet universitario |
| `correo` | VARCHAR(100) | Correo electrónico institucional |
| `telefono` | VARCHAR(20) | Número de teléfono de contacto |

---

## Operaciones del CRUD

| Operación | Método | Descripción |
|-----------|--------|-------------|
| **Create** | `insertar(Estudiante e)` | Registra un nuevo estudiante en la BD |
| **Read** | `listarTodos()` | Obtiene todos los estudiantes registrados |
| **Update** | `actualizar(Estudiante e)` | Modifica los datos de un estudiante |
| **Delete** | `eliminar(int id)` | Elimina un estudiante por su ID |

---

## Tecnologías Utilizadas

- **Java 22** - Lenguaje de programación
- **Maven** - Gestor de dependencias
- **H2 Database** - Base de datos embebida
- **JDBC** - Java Database Connectivity
- **IntelliJ IDEA** - IDE de desarrollo

---

## Estructura del Proyecto

GestionEstudiantesJDBC/
├── pom.xml
├── .gitignore
├── README.md
└── src/
└── main/
└── java/
└── sv/
└── edu/
└── utec/
├── Main.java
├── modelo/
│ └── Estudiante.java
└── datos/
├── ConexionDB.java
└── EstudianteDAO.java

## Instalación y Ejecución

### Requisitos previos
- JDK 17 o superior
- IntelliJ IDEA (o cualquier IDE con soporte Maven)
- Conexión a Internet (para descargar dependencias)

### Pasos para ejecutar

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/GestionEstudiantesJDBC.git
Abrir el proyecto en IntelliJ IDEA

2. File → Open → Seleccionar la carpeta del proyecto

Recargar dependencias Maven

3. Abrir el panel Maven (derecha)

Click en Refresh

4. Ejecutar la aplicación

Abrir src/main/java/sv/edu/utec/Main.java

Click derecho → Run 'Main.main()'


##
   
Uso de Inteligencia Artificial
Declaración obligatoria según guía de la práctica

Herramientas utilizadas
ChatGPT (OpenAI) - Asistente de programación.

Finalidad del uso de IA
Generar código base para acelerar el desarrollo

Explicar conceptos de JDBC y PreparedStatement

Corregir errores de conexión y configuración

Ayudar con la estructura del proyecto y buenas prácticas

Compromiso académico
El uso de IA ha sido una herramienta de apoyo para el aprendizaje, no un sustituto del trabajo propio. Todo el código ha sido revisado, entendido y adaptado por los integrantes del grupo.

