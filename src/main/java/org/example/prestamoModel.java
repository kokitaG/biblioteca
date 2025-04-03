package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class prestamoModel extends usuarioModel{
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean activo;
    private static List<prestamoModel> prestamos = new ArrayList<>();

    // disponibilidad

    public static boolean verificarDisponibilidad(int id_libro) {
        for (prestamoModel prestamo : prestamos) {
            if (prestamo.getId_libro() == id_libro && prestamo.isActivo()) {
                return false; // No disponible
            }
        }
        return true; // Disponible
    }

//constructores


    public prestamoModel(String titulo, String autor, String categoria, int id_libro, boolean disponible, String nombre, String apellido, String tipo, int id_usuario, Date fechaPrestamo, Date fechaDevolucion, boolean activo) {
        super(titulo, autor, categoria, id_libro, disponible, nombre, apellido, tipo, id_usuario);
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.activo = activo;
    }

    public prestamoModel() {
    }

    //Accesores


    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

   public boolean isActivo() {
        return activo;
    }

    public static List<prestamoModel> getPrestamos() {
        return prestamos;
    }

    //Modificadores


    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

   public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public static void setPrestamos(List<prestamoModel> prestamos) {
        prestamoModel.prestamos = prestamos;
    }
}
