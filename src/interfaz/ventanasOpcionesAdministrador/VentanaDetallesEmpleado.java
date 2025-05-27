package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import conexion.ConexionDerby;
import sistema.Horario;
import sistema.usuarios.Empleado;

import java.util.HashMap;

class VentanaDetallesEmpleado extends JFrame {

    public VentanaDetallesEmpleado(Empleado empleado, Horario horario) {
        setTitle("Detalles del Empleado");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID: " + empleado.getUsuario()));
        panel.add(new JLabel("Contraseña: " + empleado.getContrasenia()));
        panel.add(new JLabel("Roles: " + empleado.getRoles()));
        panel.add(new JLabel("Capacitaciones: " + empleado.getAtraccionesCapacitado()));
        panel.add(new JLabel("Turno: " + empleado.getTurno()));

        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("=== Horarios ==="));

        if (horario != null) {
            panel.add(new JLabel("Lunes: " + horario.getLunes()));
            panel.add(new JLabel("Martes: " + horario.getMartes()));
            panel.add(new JLabel("Miércoles: " + horario.getMiercoles()));
            panel.add(new JLabel("Jueves: " + horario.getJueves()));
            panel.add(new JLabel("Viernes: " + horario.getViernes()));
            panel.add(new JLabel("Sábado: " + horario.getSabado()));
            panel.add(new JLabel("Domingo: " + horario.getDomingo()));
        } else {
            panel.add(new JLabel("No se pudieron cargar los horarios."));
        }

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.addActionListener(e -> dispose());

        panel.add(Box.createVerticalStrut(15));
        panel.add(btnCerrar);

        add(panel);
        setVisible(true);
    }
}
