package org.example.vistas;

import org.example.devolucionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class historialdevoluciones {
    private JPanel contentPane;
    private JButton volverAMenuPrincipalButton;
    private JTable table1;

    public historialdevoluciones() {
        contentPane = new JPanel(new BorderLayout());

        // Crear el modelo de tabla con columnas
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Libro", "Fecha de Devolución"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de las celdas
            }
        };

        // Obtener las devoluciones desde la lista estática en devolucionModel
        for (devolucionModel devolucion : devolucionModel.getDevoluciones()) {
            model.addRow(new Object[]{
                    devolucion.getId_libro(),  // ID del libro
                    devolucion.getTitulo(),    // Título del libro
                    devolucion.getFechaDevuelto()  // Fecha de devolución
            });
        }

        // Crear la tabla con el modelo
        table1 = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table1);

        // Panel para el botón de volver al menú
        JPanel panelBoton = new JPanel();
        volverAMenuPrincipalButton = new JButton("Volver al Menú Principal");
        panelBoton.add(volverAMenuPrincipalButton);

        // Agregar componentes al contentPane
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panelBoton, BorderLayout.SOUTH);

        // Configurar acción para el botón de volver
        volverAMenuPrincipalButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
            frame.dispose();  // Cierra la ventana actual
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
