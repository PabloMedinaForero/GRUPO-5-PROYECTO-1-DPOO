package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesEmpleado extends JFrame {

    public VentanaOpcionesEmpleado(JFrame ventanaAnterior) {
        setTitle("Opciones de Empleado");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Seleccione las acciones que desea realizar");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCrear = new JButton("Crear nuevo empleado");
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaAgregarEmpleado(ventanaAnterior);
            }
        });

        JButton btnCambiarHorario = new JButton("Cambiar horario");
        btnCambiarHorario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCambiarHorario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaCambiarHorario(ventanaAnterior);
            }
        });

        JButton btnCambiarCapacitaciones = new JButton("Cambiar capacitaciones");
        btnCambiarCapacitaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCambiarCapacitaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaCambiarCapacitaciones(ventanaAnterior);
            }
        });

        JButton btnCambiarRoles = new JButton("Cambiar roles");
        btnCambiarRoles.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCambiarRoles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaCambiarRol(ventanaAnterior);
            }
        });
        
        
        JButton btnVerEmpleados = new JButton("Ver empleados");
        btnVerEmpleados.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerEmpleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaVerEmpleados();
            }
        });
        

        JButton btnVolver = new JButton("Volver");
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                dispose();
            }
        });

        panel.add(Box.createVerticalStrut(20));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnCrear);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnCambiarHorario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnCambiarCapacitaciones);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnCambiarRoles);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVerEmpleados);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);

        add(panel);
        setVisible(true);
    }
}
