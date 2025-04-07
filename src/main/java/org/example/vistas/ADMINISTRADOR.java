package org.example.vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ADMINISTRADOR {
    private JPanel panelPrincipal;
    private JTextField menuAdministradorTextField;
    private JButton agregarLibroButton;
    private JButton verInventarioButton;
    private JButton verPrestamosActivosButton;
    private JButton verHistorialDeDevolucionesButton;
    private JButton volverAlMenuPrincipalButton;

    public ADMINISTRADOR() {
        // Crear e inicializar el panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Inicializar componentes
        menuAdministradorTextField = new JTextField("Menú Administrador");
        agregarLibroButton = new JButton("Agregar Libro");
        verInventarioButton = new JButton("Ver Inventario");
        verPrestamosActivosButton = new JButton("Ver Préstamos Activos");
        verHistorialDeDevolucionesButton = new JButton("Ver Historial de Devoluciones");
        volverAlMenuPrincipalButton = new JButton("Volver al Menú Principal");

        // Configurar acción para el botón de volver
        volverAlMenuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
                if (frame != null) {
                    frame.dispose();
                }

                // Abrir la ventana de selección de usuario
                usuarios dialog = new usuarios();
                dialog.setVisible(true);
            }
        });
        // Configurar acción para el botón de Agregar Libro
        agregarLibroButton.addActionListener(e -> {
            JFrame frame = new JFrame("Agregar Libro");
            agregarlibro agregarLibroPanel = new agregarlibro();
            frame.setContentPane(agregarLibroPanel.getContentPane());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });


        // Configurar acción para el botón de Ver Inventario
        verInventarioButton.addActionListener(e -> {
            inventario dialog = new inventario();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        // Configurar acción para el botón de Ver Préstamos Activos
        verPrestamosActivosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Préstamos Activos");
                frame.setContentPane(new prestamosactivos().getContentPane());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Para Ver Historial de Devoluciones
        verHistorialDeDevolucionesButton.addActionListener(e -> {
            JFrame frame = new JFrame("Historial de Devoluciones");
            frame.setContentPane(new historialdevoluciones().getContentPane());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        // Agregar componentes al panel
        panelPrincipal.add(menuAdministradorTextField);
        panelPrincipal.add(agregarLibroButton);
        panelPrincipal.add(verInventarioButton);
        panelPrincipal.add(verPrestamosActivosButton);
        panelPrincipal.add(verHistorialDeDevolucionesButton);
        panelPrincipal.add(volverAlMenuPrincipalButton);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}