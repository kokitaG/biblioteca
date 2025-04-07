package org.example.vistas;

import org.example.prestamoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class inventario extends JDialog {
    private JPanel contentPane;
    private JButton volverAMenuPrincipalButton;
    private JTable table1;
    private JButton buttonOK;
    private JButton buttonCancel;

    public inventario() {
        // Configuración básica del diálogo
        setTitle("Inventario de Libros");
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Configurar la tabla con un modelo de datos
        table1 = new JTable();
        JScrollPane scrollPane = new JScrollPane(table1);

        // Configurar botones
        volverAMenuPrincipalButton = new JButton("Volver al Menú Principal");
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancelar");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        buttonPanel.add(volverAMenuPrincipalButton);

        // Agregar componentes al contentPane
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setModal(true);

        // Configurar listeners
        buttonOK.addActionListener(this::onOK);
        buttonCancel.addActionListener(this::onCancel);
        volverAMenuPrincipalButton.addActionListener(this::volverAlMenu);

        // Cargar datos en la tabla
        cargarDatosLibros();

        // Ajustar tamaño automáticamente
        pack();
        // Centrar la ventana
        setLocationRelativeTo(null);
    }

    private void cargarDatosLibros() {
        // Crear modelo de tabla con columnas
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Título", "Autor", "Categoría", "ID", "Disponible"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        // Obtener todos los préstamos para mostrar los libros
        for (prestamoModel prestamo : prestamoModel.getPrestamos()) {
            // Verificar si el libro está prestado o no (activo = true o false)
            String disponible = prestamo.isActivo() ? "No" : "Sí"; // Si está activo, no disponible

            // Agregar los datos a la tabla
            model.addRow(new Object[]{
                    prestamo.getTitulo(),
                    prestamo.getAutor(),
                    prestamo.getCategoria(),
                    prestamo.getId_libro(),
                    disponible
            });
        }

        table1.setModel(model);

        // Ajustar el ancho de las columnas
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
    }

    private void onOK(ActionEvent e) {
        // Acción para el botón OK (puedes añadir funcionalidad aquí)
        dispose();
    }

    private void onCancel(ActionEvent e) {
        // Acción para el botón Cancelar
        dispose();
    }

    private void volverAlMenu(ActionEvent e) {
        // Cerrar esta ventana
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            inventario dialog = new inventario();
            dialog.setVisible(true);
        });
    }
}

