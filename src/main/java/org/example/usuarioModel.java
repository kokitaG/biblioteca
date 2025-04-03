package org.example;
import java.util.List;
import java.util.ArrayList;

public class usuarioModel extends libroModel {
    private String nombre;
    private String Apellido;
    private String tipo;
    private int id_usuario;
    private static List<usuarioModel> usuarios = new ArrayList();

    //constructores


    public usuarioModel(String titulo, String autor, String categoria, int id_libro, boolean disponible, String nombre, String apellido, String tipo, int id_usuario) {
        super(titulo, autor, categoria, id_libro, disponible);
        this.nombre = nombre;
        Apellido = apellido;
        this.tipo = tipo;
        this.id_usuario = id_usuario;
    }

    public usuarioModel(String nombre, String apellido, String tipo, int id_usuario) {
        this.nombre = nombre;
        Apellido = apellido;
        this.tipo = tipo;
        this.id_usuario = id_usuario;
    }

    public usuarioModel() {
    }

    public static void inicializarDatosQuemados() {
        if (usuarios.isEmpty()) {
            // Administradores
            usuarios.add(new usuarioModel("Admin", "Principal", "administrador", 1));
            usuarios.add(new usuarioModel("Maria", "Gonzalez", "administrador", 2));

            // Estudiantes
            usuarios.add(new usuarioModel("Juan", "Perez", "estudiante", 101));
            usuarios.add(new usuarioModel("Ana", "Lopez", "estudiante", 102));
            usuarios.add(new usuarioModel("Carlos", "Martinez", "estudiante", 103));
            usuarios.add(new usuarioModel("Luisa", "Ramirez", "estudiante", 104));
        }
    }

    // Métodos de verificación de permisos
    public boolean puedeAgregarLibros() {
        return "administrador".equalsIgnoreCase(this.tipo);
    }

    public boolean puedeVerLibros() {
        return true; // Todos pueden ver libros
    }
    //accesores

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public static List<usuarioModel> getUsuarios() {
        return usuarios;
    }

    //modificadores


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public static void setUsuarios(List<usuarioModel> usuario) {
        usuarioModel.usuarios = usuario;
    }
    public static usuarioModel getAdministrador() {
        return usuarios.stream()
                .filter(u -> "administrador".equalsIgnoreCase(u.tipo))
                .findFirst()
                .orElse(null);
    }

    public static usuarioModel getEstudiante() {
        return usuarios.stream()
                .filter(u -> "estudiante".equalsIgnoreCase(u.tipo))
                .findFirst()
                .orElse(null);
    }
}
