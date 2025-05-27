package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;

import conexion.ConexionDerby;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarEmpleado extends JFrame {

    public VentanaAgregarEmpleado(JFrame ventanaAnterior) {
        ConexionDerby conexion = new ConexionDerby();
        setTitle("Agregar Nuevo Empleado");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel lblId = new JLabel("ID del Empleado:");
        JTextField txtId = new JTextField();

        JLabel lblContrasenia = new JLabel("Contraseña:");
        JTextField txtContrasenia = new JTextField();

        JLabel lblRoles = new JLabel("Rol:");
        JTextField txtRoles = new JTextField();

        JLabel lblCapacitacion = new JLabel("Capacitación Mecánicas:");
        JTextField txtCapacitacion = new JTextField();

        JLabel lblTurno = new JLabel("Turno:");
        JTextField txtTurno = new JTextField();

        panelFormulario.add(lblId);
        panelFormulario.add(txtId);
        panelFormulario.add(lblContrasenia);
        panelFormulario.add(txtContrasenia);
        panelFormulario.add(lblRoles);
        panelFormulario.add(txtRoles);
        panelFormulario.add(lblCapacitacion);
        panelFormulario.add(txtCapacitacion);
        panelFormulario.add(lblTurno);
        panelFormulario.add(txtTurno);

        JPanel panelDias = new JPanel(new GridLayout(7, 2, 5, 5));
        panelDias.setBorder(BorderFactory.createTitledBorder("Horarios por Día"));

        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
        JTextField[] camposDias = new JTextField[dias.length];

        for (int i = 0; i < dias.length; i++) {
            JLabel lblDia = new JLabel(dias[i] + ":");
            camposDias[i] = new JTextField();
            panelDias.add(lblDia);
            panelDias.add(camposDias[i]);
        }

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAgregar = new JButton("Agregar Empleado");
        JButton btnCancelar = new JButton("Cancelar");

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                String contrasenia = txtContrasenia.getText().trim();
                String rol = txtRoles.getText().trim();
                String capacitacion = txtCapacitacion.getText().trim();
                String turno = txtTurno.getText().trim();

                if (id.isEmpty() || contrasenia.isEmpty() || rol.isEmpty() || capacitacion.isEmpty() || turno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    conexion.insertarEmpleado(id, contrasenia, rol, capacitacion, turno);
                    conexion.insertarHorario(id,camposDias[0].getText().trim(), camposDias[1].getText().trim(), camposDias[2].getText().trim(), camposDias[3].getText().trim(), camposDias[4].getText().trim(), camposDias[5].getText().trim(), camposDias[6].getText().trim());
                    JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.");
                    dispose();
                    ventanaAnterior.setVisible(true);                    
                }
            }
        });

        btnCancelar.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            dispose();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnCancelar);

        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(panelDias);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(panelBotones);

        add(panelPrincipal);
        setVisible(true);
    }
}
