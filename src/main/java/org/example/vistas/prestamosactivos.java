package org.example.vistas;

import org.example.prestamoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class prestamosactivos {
    private JPanel contentPane;
    private JButton volverAMenuPrincipalButton;
    private JTable table1;

    public prestamosactivos() {
        // Configuración del layout y el panel principal
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));

        // Inicializar componentes
        volverAMenuPrincipalButton = new JButton("Volver al Menú Principal");
        table1 = new JTable();

        // Agregar componentes al contentPane
        contentPane.add(new JScrollPane(table1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(volverAMenuPrincipalButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar acción para el botón volver
        volverAMenuPrincipalButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
            frame.dispose(); // Cierra la ventana actual
        });

        // Llenar la tabla con los préstamos activos
        cargarDatosPrestamosActivos();
    }

    private void cargarDatosPrestamosActivos() {
        // Suponemos que tenemos el ID del estudiante autenticado
        int estudianteId = 123; // Reemplázalo con el ID del estudiante actual

        // Crear el modelo de tabla con columnas
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Título", "Autor", "Categoría", "ID", "Fecha de Préstamo", "Fecha de Devolución", "Activo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        // Obtener todos los préstamos activos desde la lista estática en la clase prestamoModel
        for (prestamoModel prestamo : prestamoModel.getPrestamos()) {
            // Filtrar préstamos solo del estudiante con ID específico
            if (prestamo.getId_usuario() == estudianteId && prestamo.isActivo()) { // Validación por ID de estudiante y activo
                model.addRow(new Object[]{
                        prestamo.getTitulo(),
                        prestamo.getAutor(),
                        prestamo.getCategoria(),
                        prestamo.getId_libro(),
                        prestamo.getFechaPrestamo(),
                        prestamo.getFechaDevolucion(),
                        prestamo.isActivo() ? "Sí" : "No"
                });
            }
        }

        // Asignar el modelo a la tabla
        table1.setModel(model);

        // Ajustar el tamaño de las columnas automáticamente
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setPreferredWidth(150); // Ajustar el ancho de las columnas
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
