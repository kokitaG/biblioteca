package org.example.vistas;

import org.example.prestamoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class devolverlibro {
    private JPanel panelPrincipal;
    private JFormattedTextField formattedTextField1;  // Campo para ingresar el ID del libro a devolver
    private JButton devolverButton;  // Botón para devolver el libro
    private JButton regresarAMenuEstudianteButton;  // Botón para regresar al menú del estudiante
    private JTable table1;  // Tabla para mostrar los libros prestados

    public devolverlibro() {
        // Inicializar el panel y los componentes
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        formattedTextField1 = new JFormattedTextField();
        devolverButton = new JButton("Devolver Libro");
        regresarAMenuEstudianteButton = new JButton("Regresar al Menú Estudiante");
        table1 = new JTable();

        // Cargar los libros prestados por el estudiante
        cargarTablaLibrosPrestados();

        // Agregar componentes al panel
        panelPrincipal.add(new JLabel("Ingrese el ID del libro a devolver:"));
        panelPrincipal.add(formattedTextField1);
        panelPrincipal.add(devolverButton);
        panelPrincipal.add(new JScrollPane(table1));  // Añadir la tabla con los libros prestados
        panelPrincipal.add(regresarAMenuEstudianteButton);

        // Acción del botón "Devolver Libro"
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que el campo de texto no esté vacío
                if (!formattedTextField1.getText().isEmpty()) {
                    try {
                        int idLibro = Integer.parseInt(formattedTextField1.getText()); // Obtener el ID del libro
                        if (devolverLibro(idLibro)) {
                            JOptionPane.showMessageDialog(panelPrincipal, "¡Libro devuelto con éxito!");
                            cargarTablaLibrosPrestados();  // Actualizar la tabla después de devolver el libro
                        } else {
                            JOptionPane.showMessageDialog(panelPrincipal, "El libro no está prestado o no existe.");
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
        regresarAMenuEstudianteButton.addActionListener(new ActionListener() {
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

    // Método para cargar los libros prestados en la tabla
    private void cargarTablaLibrosPrestados() {
        // Obtener los préstamos activos del estudiante desde la lista de prestamos
        List<prestamoModel> prestamos = prestamoModel.getPrestamos();
        Object[][] data = new Object[prestamos.size()][4];
        int rowIndex = 0;
        for (prestamoModel prestamo : prestamos) {
            // Filtrar solo los préstamos activos y los que corresponden al estudiante
            if (prestamo.isActivo()) {
                data[rowIndex][0] = prestamo.getId_libro();  // ID del libro
                data[rowIndex][1] = prestamo.getTitulo();  // Título
                data[rowIndex][2] = prestamo.getAutor();  // Autor
                data[rowIndex][3] = "Sí";  // Estado (prestado)
                rowIndex++;
            }
        }

        String[] columnNames = {"ID", "Título", "Autor", "Prestado"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);

        // Ajustar el tamaño de las columnas
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setPreferredWidth(150);  // Ajustar el ancho de las columnas
        }
    }

    // Método para devolver un libro (cambiar el estado de activo a inactivo)
    private boolean devolverLibro(int idLibro) {
        List<prestamoModel> prestamos = prestamoModel.getPrestamos();
        for (prestamoModel prestamo : prestamos) {
            if (prestamo.getId_libro() == idLibro && prestamo.isActivo()) {
                prestamo.setActivo(false);  // Cambiar el estado del préstamo a inactivo
                return true;  // Libro devuelto con éxito
            }
        }
        return false;  // Si no se encuentra el libro prestado
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}

