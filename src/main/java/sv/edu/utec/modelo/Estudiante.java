package sv.edu.utec.modelo;

public class Estudiante {
    // Atributos privados
    private int id;
    private String nombre;
    private String apellidos;
    private String carrera;
    private String carnet;
    private String correo;
    private String telefono;

    // Constructor vacio
    public Estudiante() {}

    // Constructor con todos los atributos
    public Estudiante(int id, String nombre, String apellidos, String carrera,
                      String carnet, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.carrera = carrera;
        this.carnet = carnet;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // metodo toString para mostrar informacion
    @Override
    public String toString() {
        return String.format("ID: %d | %s %s | Carnet: %s | %s | Tel: %s",
                id, nombre, apellidos, carnet, carrera, telefono);
    }
}