package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class devolucionModel extends prestamoModel {
    private Date fechaDevuelto;
    private static List<devolucionModel> devoluciones = new ArrayList<>();

    //metodo para registra la devolucion

    public static boolean devolverLibro(int id_libro) {
        for (prestamoModel prestamo : devoluciones) {
            if (prestamo.getId_libro() == id_libro && prestamo.isActivo()) {
                prestamo.setActivo(false);
                prestamo.setDisponible(true);
                prestamo.setFechaDevolucion(new Date());
                System.out.println("Libro devuelto exitosamente");
                return true;
            }
        }
        System.out.println("No se encontró un préstamo activo para este libro");
        return false;
    }

    //contructores

    public devolucionModel(String titulo, String autor, String categoria, int id_libro, boolean disponible, String nombre, String apellido, String tipo, int id_usuario, Date fechaPrestamo, Date fechaDevolucion, boolean activo, Date fechaDevolucion1) {
        super(titulo, autor, categoria, id_libro, disponible, nombre, apellido, tipo, id_usuario, fechaPrestamo, fechaDevolucion, activo);
        this.fechaDevuelto = fechaDevuelto;
    }

    public devolucionModel() {
    }

    //Accesores


    public Date getFechaDevuelto() {
        return fechaDevuelto;
    }

    public static List<devolucionModel> getDevoluciones() {
        return devoluciones;
    }

    //Modificadores


    public void setFechaDevuelto(Date fechaDevuelto) {
        this.fechaDevuelto = fechaDevuelto;
    }

    public static void setDevoluciones(List<devolucionModel> devoluciones) {
        devolucionModel.devoluciones = devoluciones;
    }
}



