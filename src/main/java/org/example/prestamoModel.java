package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class prestamoModel extends usuarioModel {
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean activo; // Propiedad activo
    private static List<prestamoModel> prestamos = new ArrayList<>();

    // Constructor
    public prestamoModel(String titulo, String autor, String categoria, int id_libro, boolean disponible, String nombre, String apellido, String tipo, int id_usuario, Date fechaPrestamo, Date fechaDevolucion, boolean activo) {
        super(titulo, autor, categoria, id_libro, disponible, nombre, apellido, tipo, id_usuario);
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.activo = activo;
    }

    public prestamoModel() {}

    // Métodos estáticos
    public static List<prestamoModel> getPrestamos() {
        return prestamos;
    }

    // Método para agregar datos quemados
    public static void agregarDatosQuemados() {
        // Crear libros con datos estáticos (quemados)
        Date fechaPrestamo = new Date();
        Date fechaDevolucion = new Date(fechaPrestamo.getTime() + 86400000); // 1 día después

        prestamos.add(new prestamoModel("Libro 1", "Autor 1", "Categoría 1", 1, true, "Usuario 1", "Apellido 1", "Estudiante", 1, fechaPrestamo, fechaDevolucion, true));
        prestamos.add(new prestamoModel("Libro 2", "Autor 2", "Categoría 2", 2, true, "Usuario 2", "Apellido 2", "Profesor", 2, fechaPrestamo, fechaDevolucion, false));  // Este libro no está activo
        prestamos.add(new prestamoModel("Libro 3", "Autor 3", "Categoría 3", 3, true, "Usuario 3", "Apellido 3", "Estudiante", 3, fechaPrestamo, fechaDevolucion, true));
    }

    // Métodos de acceso
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    // Método para modificar el estado "activo" del préstamo
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public static void setPrestamos(List<prestamoModel> prestamos) {
        prestamoModel.prestamos = prestamos;
    }

    protected void setFechaDevolucion(Date date) {
    }
}
