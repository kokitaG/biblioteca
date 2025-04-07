package org.example.vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class Verlibros {
    private JPanel panelPrincipal;
    private JButton regresarANenuEstudianteButton;
    private JTable table1;

    public Verlibros() {
        // Inicializar componentes
        panelPrincipal = new JPanel();
        regresarANenuEstudianteButton = new JButton("Regresar al Menú Estudiante");
        table1 = new JTable();

        // Llenar la tabla con los libros disponibles
        cargarLibrosDisponibles();

        // Acción del botón regresar
        regresarANenuEstudianteButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
            frame.dispose();  // Cierra la ventana actual
            ESTUDIANTE estudiante = new ESTUDIANTE();  // Regresar al panel principal
            JFrame estudianteFrame = new JFrame("Estudiante");
            estudianteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            estudianteFrame.setContentPane(estudiante.getPanelPrincipal());
            estudianteFrame.pack();
            estudianteFrame.setVisible(true);
        });

        // Agregar componentes al panel
        panelPrincipal.add(new JScrollPane(table1));
        panelPrincipal.add(regresarANenuEstudianteButton);
    }

    // Método para cargar los libros disponibles
    private void cargarLibrosDisponibles() {
        // Obtener los libros disponibles desde el inventario
        List<Libro> librosDisponibles = obtenerLibrosDisponibles();

        // Crear el modelo de tabla con las columnas
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Título", "Autor", "Categoría", "Disponible"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        // Agregar los libros a la tabla
        for (Libro libro : librosDisponibles) {
            model.addRow(new Object[]{
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCategoria(),
                    libro.isDisponible() ? "Sí" : "No"
            });
        }

        // Asignar el modelo a la tabla
        table1.setModel(model);

        // Ajustar el tamaño de las columnas automáticamente
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    // Método simulado para obtener los libros disponibles (simula un inventario)
    private List<Libro> obtenerLibrosDisponibles() {
        // Simulación de libros en el inventario
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1, "El Principito", "Antoine de Saint-Exupéry", "Ficción", true)); // Disponible
        libros.add(new Libro(2, "Cien años de soledad", "Gabriel García Márquez", "Realismo mágico", false)); // No disponible
        libros.add(new Libro(3, "1984", "George Orwell", "Distopía", true)); // Disponible

        // Filtrar los libros que están disponibles (no prestados)
        List<Libro> librosDisponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                librosDisponibles.add(libro);
            }
        }
        return librosDisponibles;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}

// Clase de modelo de Libro
class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private boolean disponible;

    // Constructor
    public Libro(int id, String titulo, String autor, String categoria, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    // Métodos de acceso
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }
}

