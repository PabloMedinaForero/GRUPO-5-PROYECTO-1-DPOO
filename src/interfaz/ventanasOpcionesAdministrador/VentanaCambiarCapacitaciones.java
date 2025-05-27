package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;

import conexion.ConexionDerby;
import sistema.usuarios.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class VentanaCambiarCapacitaciones extends JFrame {

    public VentanaCambiarCapacitaciones(JFrame ventanaAnterior) {
        ConexionDerby conexion = new ConexionDerby();
        HashMap<String, Empleado> mapaEmpleados = conexion.obtenerEmpleados();
        setTitle("Cambiar Capacitación del Empleado");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblIdEmpleado = new JLabel("ID del Empleado:");
        JTextField txtIdEmpleado = new JTextField();

        JLabel lblCapActual = new JLabel("Capacitaciones actuales:");
        JTextArea txtCapacitacionesActuales = new JTextArea(2, 30);
        txtCapacitacionesActuales.setEditable(false);
        txtCapacitacionesActuales.setLineWrap(true);
        txtCapacitacionesActuales.setWrapStyleWord(true);

        JButton btnVerActuales = new JButton("Ver Capacitaciones");
        btnVerActuales.addActionListener(e -> {
            String idEmpleado = txtIdEmpleado.getText().trim();
            if (!mapaEmpleados.containsKey(idEmpleado)) {
                JOptionPane.showMessageDialog(null, "ID de empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                txtCapacitacionesActuales.setText("");
            } else {
                Empleado empleado = mapaEmpleados.get(idEmpleado);
                String capacitaciones = empleado.getAtraccionesCapacitado();
                txtCapacitacionesActuales.setText(capacitaciones != null ? capacitaciones : "No tiene capacitaciones registradas.");
            }
        });

        JLabel lblNuevaCapacitacion = new JLabel("Nueva Capacitación:");
        JTextField txtNuevaCapacitacion = new JTextField();

        JButton btnActualizar = new JButton("Actualizar Capacitación");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEmpleado = txtIdEmpleado.getText().trim();
                String nuevaCapacitacion = txtNuevaCapacitacion.getText().trim();

                if (idEmpleado.isEmpty() || nuevaCapacitacion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    conexion.cambiarCapacitacion(idEmpleado, nuevaCapacitacion);
                    JOptionPane.showMessageDialog(null, "Capacitación actualizada correctamente.");
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
        formPanel.add(lblCapActual);
        formPanel.add(new JScrollPane(txtCapacitacionesActuales));
        formPanel.add(btnVerActuales, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new GridLayout(2, 2, 10, 10));
        acciones.add(lblNuevaCapacitacion);
        acciones.add(txtNuevaCapacitacion);
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
