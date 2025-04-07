package org.example.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class usuarios extends JDialog {
    private JPanel panel1;
    private JRadioButton administradorRadioButton;
    private JRadioButton estudianteRadioButton;
    private JButton ingresarButton;
    private ButtonGroup grupoRadioButtons;

    // Credenciales de administrador
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    public usuarios() {
        setContentPane(panel1);
        setModal(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Selección de Usuario");

        // Agrupar los RadioButtons para que solo uno pueda estar seleccionado
        grupoRadioButtons = new ButtonGroup();
        grupoRadioButtons.add(administradorRadioButton);
        grupoRadioButtons.add(estudianteRadioButton);

        // Acción del botón Ingresar
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (administradorRadioButton.isSelected()) {
                    mostrarLoginAdministrador();
                } else if (estudianteRadioButton.isSelected()) {
                    abrirPanelEstudiante();
                } else {
                    JOptionPane.showMessageDialog(usuarios.this,
                            "Seleccione un tipo de usuario",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void mostrarLoginAdministrador() {
        // Crear el diálogo de login
        JDialog loginDialog = new JDialog(this, "Login de Administrador", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLocationRelativeTo(this);
        loginDialog.setLayout(new GridLayout(3, 2, 10, 10));

        // Componentes del login
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnLogin);
        panelBotones.add(btnCancelar);

        // Agregar componentes al diálogo
        loginDialog.add(lblUsuario);
        loginDialog.add(txtUsuario);
        loginDialog.add(lblPassword);
        loginDialog.add(txtPassword);
        loginDialog.add(new JLabel()); // Espacio vacío
        loginDialog.add(panelBotones);

        // Acción del botón Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());

                if (usuario.equals(ADMIN_USER) && password.equals(ADMIN_PASSWORD)) {
                    loginDialog.dispose();
                    abrirPanelAdministrador();
                } else {
                    JOptionPane.showMessageDialog(loginDialog,
                            "Usuario o contraseña incorrectos",
                            "Error de autenticación",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDialog.dispose();
            }
        });

        loginDialog.setVisible(true);
    }

    private void abrirPanelAdministrador() {
        // Cierra la ventana actual
        dispose();

        // Abre el panel ADMINISTRADOR
        ADMINISTRADOR adminPanel = new ADMINISTRADOR();
        JFrame frame = new JFrame("Panel de Administrador");
        frame.setContentPane(adminPanel.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirPanelEstudiante() {
        // Cierra la ventana actual
        dispose();

        // Abre el panel ESTUDIANTE
        ESTUDIANTE estudiantePanel = new ESTUDIANTE();
        JFrame frame = new JFrame("Panel de Estudiante");
        frame.setContentPane(estudiantePanel.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                usuarios dialog = new usuarios();
                dialog.setVisible(true);
            }
        });
    }
}