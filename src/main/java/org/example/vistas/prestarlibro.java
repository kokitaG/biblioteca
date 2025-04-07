package org.example.vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class prestarlibro {
    private JPanel panelPrincipal;
    private JTextField textField1;  // Campo de texto para ingresar el ID del libro
    private JTextPane textPane1;    // Lo dejamos por ahora, pero no lo estamos usando en esta funcionalidad
    private JTable table1;          // Tabla para mostrar los libros disponibles
    private JButton prestarButton;  // Botón para prestar el libro
    private JButton regresarLaMenuButton;  // Botón para regresar al menú

    // Lista ficticia de libros disponibles, reemplázalo con tu lógica real
    private List<Libro> librosDisponibles = new ArrayList<>();

    public prestarlibro() {
        // Inicializar el panel y los componentes
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        textField1 = new JTextField(20);  // Campo de texto para ingresar el ID del libro
        prestarButton = new JButton("Prestar Libro");
        regresarLaMenuButton = new JButton("Regresar al Menú Estudiante");
        table1 = new JTable();  // Tabla para mostrar los libros disponibles

        // Llenar la lista de libros disponibles (esto sería parte de tu lógica real)
        cargarLibrosDisponibles();

        // Crear el modelo de tabla y cargar los libros disponibles
        cargarTablaLibrosDisponibles();

        // Agregar componentes al panel
        panelPrincipal.add(new JLabel("Ingrese el ID del libro a prestar:"));
        panelPrincipal.add(textField1);
        panelPrincipal.add(prestarButton);
        panelPrincipal.add(new JScrollPane(table1));  // Añadir la tabla con los libros disponibles
        panelPrincipal.add(regresarLaMenuButton);

        // Acción del botón "Prestar Libro"
        prestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que el campo de texto no esté vacío
                if (!textField1.getText().isEmpty()) {
                    try {
                        int idLibro = Integer.parseInt(textField1.getText()); // Obtener el ID del libro
                        if (prestarLibro(idLibro)) {
                            JOptionPane.showMessageDialog(panelPrincipal, "¡Libro prestado con éxito!");
                            cargarTablaLibrosDisponibles();  // Actualizar la tabla después de prestar el libro
                        } else {
                            JOptionPane.showMessageDialog(panelPrincipal, "El libro no está disponible o no existe.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panelPrincipal, "Por favor ingrese un ID de libro válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panelPrincipal, "Por favor, ingrese un ID de libro.");
                }
            }
        });

        // Acción del botón "Regresar al Menú Estudiante"
        regresarLaMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
                frame.dispose();  // Cierra la ventana actual
                ESTUDIANTE estudiante = new ESTUDIANTE();  // Regresar al panel principal
                JFrame estudianteFrame = new JFrame("Estudiante");
                estudianteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                estudianteFrame.setContentPane(estudiante.getPanelPrincipal());
                estudianteFrame.pack();
                estudianteFrame.setVisible(true);
            }
        });
    }

    // Método para cargar los libros disponibles en la tabla
    private void cargarTablaLibrosDisponibles() {
        // Suponiendo que tienes una lista de libros y un método que te permite obtener los libros disponibles
        Object[][] data = new Object[librosDisponibles.size()][4];
        for (int i = 0; i < librosDisponibles.size(); i++) {
            Libro libro = librosDisponibles.get(i);
            data[i][0] = libro.getId();  // ID del libro
            data[i][1] = libro.getTitulo();  // Título
            data[i][2] = libro.getAutor();  // Autor
            data[i][3] = libro.isDisponible() ? "Sí" : "No";  // Estado (disponible o no)
        }

        String[] columnNames = {"ID", "Título", "Autor", "Disponible"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);

        // Ajustar el tamaño de las columnas
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setPreferredWidth(150);  // Ajustar el ancho de las columnas
        }
    }

    // Método para llenar la lista de libros disponibles (simulada)
    private void cargarLibrosDisponibles() {
        // Simulación de libros disponibles
        librosDisponibles.add(new Libro(1, "El Principito", "Antoine de Saint-Exupéry", true));
        librosDisponibles.add(new Libro(2, "1984", "George Orwell", true));
        librosDisponibles.add(new Libro(3, "Cien Años de Soledad", "Gabriel García Márquez", true));
    }

    // Método para prestar un libro
    private boolean prestarLibro(int idLibro) {
        for (Libro libro : librosDisponibles) {
            if (libro.getId() == idLibro && libro.isDisponible()) {
                libro.setDisponible(false);  // Marcar como no disponible
                return true;
            }
        }
        return false;  // Si no se encuentra el libro o no está disponible
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    // Clase interna que simula el libro (debes reemplazarla con tu implementación real)
    public static class Libro {
        private int id;
        private String titulo;
        private String autor;
        private boolean disponible;

        public Libro(int id, String titulo, String autor, boolean disponible) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.disponible = disponible;
        }

        public int getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public boolean isDisponible() {
            return disponible;
        }

        public void setDisponible(boolean disponible) {
            this.disponible = disponible;
        }
    }
}

