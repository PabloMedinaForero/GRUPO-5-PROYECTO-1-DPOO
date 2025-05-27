package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;

import conexion.ConexionDerby;
import sistema.usuarios.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class VentanaCambiarHorario extends JFrame {

    public VentanaCambiarHorario(JFrame ventanaAnterior) {
        ConexionDerby conexion = new ConexionDerby();

        setTitle("Cambiar Horario de Empleado");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblIdEmpleado = new JLabel("ID del Empleado:");
        JTextField txtIdEmpleado = new JTextField();

        JLabel lblDiaSemana = new JLabel("Día de la Semana:");
        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        JComboBox<String> comboDias = new JComboBox<>(diasSemana);

        JLabel lblNuevaActividad = new JLabel("Nueva Actividad:");
        JTextField txtNuevaActividad = new JTextField();

        JButton btnCambiar = new JButton("Cambiar Horario");
        btnCambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEmpleado = txtIdEmpleado.getText().trim();
                String dia = (String) comboDias.getSelectedItem();
                String nuevaActividad = txtNuevaActividad.getText().trim();

                if (idEmpleado.isEmpty() || dia.isEmpty() || nuevaActividad.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	HashMap<String, Empleado> empleados = conexion.obtenerEmpleados();
                	if(empleados.get(idEmpleado) != null) {
                		conexion.cambiarHorario(idEmpleado, dia, nuevaActividad);
                		JOptionPane.showMessageDialog(null, "Horario actualizado correctamente.");
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "No se ha encontrado al usuario digitado");
                	}
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

        panel.add(lblIdEmpleado);
        panel.add(txtIdEmpleado);
        panel.add(lblDiaSemana);
        panel.add(comboDias);
        panel.add(lblNuevaActividad);
        panel.add(txtNuevaActividad);
        panel.add(btnCambiar);
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }
}
