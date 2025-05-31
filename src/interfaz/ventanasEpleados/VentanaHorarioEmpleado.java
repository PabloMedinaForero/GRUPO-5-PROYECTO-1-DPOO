package interfaz.ventanasEpleados;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistema.Horario;
import sistema.usuarios.Empleado;

public class VentanaHorarioEmpleado extends JFrame {
	
	public VentanaHorarioEmpleado(Empleado empleado, Horario horario) {
		setTitle("Horario Empleado");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

       
        panel.add(new JLabel("Su turno es: " + empleado.getTurno()));

        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Su horario es: "));

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
