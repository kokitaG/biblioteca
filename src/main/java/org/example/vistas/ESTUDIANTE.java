package org.example.vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ESTUDIANTE {
    private JPanel panelPrincipal;
    private JButton verLibrosDisponiblesButton;
    private JButton solicitarPrestamoButton;
    private JButton devolverLibroButton;
    private JButton prestamosActivosButton;
    private JButton volverAlMenuPrincipalButton;

    public ESTUDIANTE() {
        // Inicializar componentes
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        verLibrosDisponiblesButton = new JButton("Ver Libros Disponibles");
        solicitarPrestamoButton = new JButton("Solicitar Préstamo");
        devolverLibroButton = new JButton("Devolver Libro");
        prestamosActivosButton = new JButton("Préstamos Activos");
        volverAlMenuPrincipalButton = new JButton("Volver al Menú Principal");

        // Acción para el botón "Ver Libros Disponibles"
        verLibrosDisponiblesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana para ver los libros disponibles
                Verlibros verLibros = new Verlibros();
                JFrame frame = new JFrame("Ver Libros Disponibles");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(verLibros.getPanelPrincipal());
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Acción para el botón "Solicitar Préstamo"
        solicitarPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana para solicitar un préstamo
                prestarlibro prestarLibro = new prestarlibro();
                JFrame frame = new JFrame("Solicitar Préstamo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(prestarLibro.getPanelPrincipal());
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Acción para el botón "Devolver Libro"
        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana para devolver un libro
                devolverlibro devolverLibro = new devolverlibro();
                JFrame frame = new JFrame("Devolver Libro");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(devolverLibro.getPanelPrincipal());
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Acción para el botón "Préstamos Activos"
        prestamosActivosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana para los préstamos activos
                prestamosactivos prestamosActivos = new prestamosactivos();
                JFrame frame = new JFrame("Préstamos Activos");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(prestamosActivos.getContentPane());
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Acción para el botón "Volver al Menú Principal"
        volverAlMenuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver al menú principal (ventana anterior)
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
                if (frame != null) {
                    frame.dispose(); // Cierra la ventana actual
                }
                usuarios dialog = new usuarios(); // Asumiendo que usuarios es el nombre de la clase de tu menú principal
                dialog.setVisible(true);
            }
        });

        // Agregar botones al panel
        panelPrincipal.add(verLibrosDisponiblesButton);
        panelPrincipal.add(solicitarPrestamoButton);
        panelPrincipal.add(devolverLibroButton);
        panelPrincipal.add(prestamosActivosButton);
        panelPrincipal.add(volverAlMenuPrincipalButton);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
