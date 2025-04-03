package org.example;

import java.util.ArrayList;
import java.util.List;

public class libroModel {
    private String titulo;
    private String autor;
    private String categoria;
    private int id_libro;
    private boolean disponible;
    private static List<libroModel> inventario = new ArrayList();

    //constructores

    public libroModel(String titulo, String autor, String categoria, int id_libro, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.id_libro = id_libro;
        this.disponible = disponible;
    }


    public libroModel() {
    }

    public static void inicializarDatos() {
        if (inventario.isEmpty()) {
            libroModel libro1 = new libroModel();
            libro1.setTitulo("Cien años de soledad");
            libro1.setAutor("Gabriel García Márquez");
            libro1.setCategoria("Novela");
            libro1.setId_libro(1);
            libro1.setDisponible(true);
            libroModel libro2 = new libroModel();
            libro2.setTitulo("1984");
            libro2.setAutor("George Orwell");
            libro2.setCategoria("Ciencia ficción");
            libro2.setId_libro(2);
            libro2.setDisponible(true);
            inventario.add(libro1);
            inventario.add(libro2);
        }
    }

    // Métodos con control de acceso
    public static void agregarLibro(usuarioModel usuario, libroModel libro) {
        if (usuario == null || !usuario.puedeAgregarLibros()) {
            throw new SecurityException("Acceso denegado: Solo administradores pueden agregar libros");
        }
        inventario.add(libro);
    }

    public static List<libroModel> obtenerLibros(usuarioModel usuario) {
        if (usuario == null) {
            throw new SecurityException("Debe especificar un usuario");
        }
        return new ArrayList<>(inventario);
    }
//accesores
    public static List<libroModel> getInventario() {
        return inventario;
    }

    public static void setInventario(List<libroModel> nuevosLibros) {
        inventario = nuevosLibros;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public int getId_libro() {
        return this.id_libro;
    }

    public boolean isDisponible() {
        return this.disponible;
    }

    //modificadores
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setId_libro(int id) {
        this.id_libro = id;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public static libroModel buscarPorId(int id) {
        for(libroModel libro : inventario) {
            if (libro.getId_libro() == id) {
                return libro;
            }
        }

        return null;
    }
}