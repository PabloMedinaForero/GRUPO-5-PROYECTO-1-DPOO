package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;

import conexion.ConexionDerby;
import sistema.usuarios.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class VentanaCambiarRol extends JFrame {

    public VentanaCambiarRol(JFrame ventanaAnterior) {
        ConexionDerby conexion = new ConexionDerby();
        HashMap<String, Empleado> mapaEmpleados = conexion.obtenerEmpleados();
        setTitle("Cambiar Rol del Empleado");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblIdEmpleado = new JLabel("ID del Empleado:");
        JTextField txtIdEmpleado = new JTextField();

        JLabel lblRolActual = new JLabel("Rol actual:");
        JTextArea txtRolActual = new JTextArea(2, 30);
        txtRolActual.setEditable(false);
        txtRolActual.setLineWrap(true);
        txtRolActual.setWrapStyleWord(true);

        JButton btnVerRol = new JButton("Ver Rol Actual");
        btnVerRol.addActionListener(e -> {
            String idEmpleado = txtIdEmpleado.getText().trim();
            if (!mapaEmpleados.containsKey(idEmpleado)) {
                JOptionPane.showMessageDialog(null, "ID de empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                txtRolActual.setText("");
            } else {
                Empleado empleado = mapaEmpleados.get(idEmpleado);
                String rol = empleado.getRoles();
                txtRolActual.setText(rol != null ? rol : "No tiene rol registrado.");
            }
        });

        JLabel lblNuevoRol = new JLabel("Nuevo Rol:");
        JTextField txtNuevoRol = new JTextField();

        JButton btnActualizar = new JButton("Actualizar Rol");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEmpleado = txtIdEmpleado.getText().trim();
                String nuevoRol = txtNuevoRol.getText().trim();

                if (idEmpleado.isEmpty() || nuevoRol.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    conexion.cambiarRol(idEmpleado, nuevoRol);
                    JOptionPane.showMessageDialog(null, "Rol actualizado correctamente.");
                    dispose();
                    ventanaAnterior.setVisible(true);
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            dispose();
        });

        formPanel.add(lblIdEmpleado);
        formPanel.add(txtIdEmpleado);
        formPanel.add(lblRolActual);
        formPanel.add(new JScrollPane(txtRolActual));
        formPanel.add(btnVerRol, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new GridLayout(2, 2, 10, 10));
        acciones.add(lblNuevoRol);
        acciones.add(txtNuevoRol);
        acciones.add(btnActualizar);
        acciones.add(btnCancelar);

        panel.add(Box.createVerticalStrut(10));
        panel.add(formPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(acciones);

        add(panel);
        setVisible(true);
    }
}
