package org.example.vistas;

import org.example.prestamoModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class agregarlibro {
    private JTextField textField1;  // Título
    private JTextField textField2;  // Autor
    private JTextField textField3;  // Categoría
    private JTextField textField4;  // ID
    private JButton volverAMenuPrincipalButton;
    private JButton agregarLibroButton;
    private JPanel contentPane;

    public agregarlibro() {
        // Inicializar el contentPane si es null
        if (contentPane == null) {
            contentPane = new JPanel();
            contentPane.setLayout(new BorderLayout(10, 10));
            contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // Panel para los campos del formulario
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(4, 2, 10, 10));

            // Inicializar campos de texto
            textField1 = new JTextField(20);
            textField2 = new JTextField(20);
            textField3 = new JTextField(20);
            textField4 = new JTextField(20);

            // Agregar etiquetas y campos al formulario
            formPanel.add(new JLabel("Título del libro:"));
            formPanel.add(textField1);
            formPanel.add(new JLabel("Autor:"));
            formPanel.add(textField2);
            formPanel.add(new JLabel("Categoría:"));
            formPanel.add(textField3);
            formPanel.add(new JLabel("ID del libro:"));
            formPanel.add(textField4);

            // Panel para los botones
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            volverAMenuPrincipalButton = new JButton("Volver");
            agregarLibroButton = new JButton("Agregar Libro");

            // Configurar acciones de los botones
            volverAMenuPrincipalButton.addActionListener(this::volverAlMenu);
            agregarLibroButton.addActionListener(this::agregarLibro);

            buttonPanel.add(volverAMenuPrincipalButton);
            buttonPanel.add(agregarLibroButton);

            // Agregar componentes al contentPane
            contentPane.add(formPanel, BorderLayout.CENTER);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    private void agregarLibro(ActionEvent e) {
        try {
            // Validar campos obligatorios
            if (textField1.getText().trim().isEmpty() || textField4.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Título y ID son campos obligatorios");
            }

            // Convertir ID a número
            int idLibro = Integer.parseInt(textField4.getText());

            // Crear nuevo libro en prestamoModel (disponible por defecto)
            prestamoModel nuevoLibro = new prestamoModel(
                    textField1.getText(),  // título
                    textField2.getText(),  // autor
                    textField3.getText(),  // categoría
                    idLibro,               // id_libro
                    true,                  // disponible (true por defecto)
                    "", "", "", 0,         // datos de usuario vacíos
                    null,                  // fechaPrestamo (null porque está disponible)
                    null,                  // fechaDevolucion
                    false                  // activo (false porque es nuevo)
            );

            // Agregar a la lista de préstamos
            prestamoModel.getPrestamos().add(nuevoLibro);

            // Mostrar mensaje de confirmación
            JOptionPane.showMessageDialog(contentPane,
                    "Libro agregado exitosamente!\n" +
                            "Título: " + textField1.getText() + "\n" +
                            "ID: " + idLibro,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos después de agregar
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(contentPane,
                    "El ID debe ser un número válido",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(contentPane,
                    "Error: " + ex.getMessage(),
                    "Error al agregar libro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAlMenu(ActionEvent e) {
        // Cerrar la ventana actual
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
        if (frame != null) {
            frame.dispose();
        }
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}