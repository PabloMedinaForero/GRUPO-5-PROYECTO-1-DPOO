package interfaz;

import javax.swing.*;

import conexion.ConexionDerby;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesAtracciones;
import sistema.atraccion.Atraccion;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.usuarios.Empleado;

import java.awt.event.*;
import java.util.HashMap;

public class VentanaAdministrador extends JFrame {
	public VentanaAdministrador() {
		setTitle("Panel Administrador");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		VentanaLogin login = new VentanaLogin(this, "administrador");
		boolean accesoPermitido = login.mostrarDialogo();

		if (!accesoPermitido) {
			JOptionPane.showMessageDialog(this, "Acceso denegado", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}
		ConexionDerby conexion = new ConexionDerby();
		HashMap<String, Empleado> mapaEmpleados = conexion.obtenerEmpleados();
		HashMap<String, Atraccion> mapaAtracciones = conexion.obtenerAtracciones();
		HashMap<String, LugarDeServicio> mapaLugarDeServicio = conexion.obtenerLugaresDeServicio();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Opciones del Administrador");
		lbl.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnGestionAtracciones = new JButton("Opciones atracciones");
		btnGestionAtracciones.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnGestionAtracciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaOpcionesAtracciones(mapaEmpleados, mapaAtracciones, mapaLugarDeServicio);
			}
		});
		
		JButton btnGestionEmpleados = new JButton("Opciones empleados");
		btnGestionEmpleados.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnGestionLugaresDeServicio = new JButton("Opciones lugares de servicio");
		btnGestionLugaresDeServicio.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionAtracciones);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionEmpleados);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionLugaresDeServicio);

		add(panel);
		setVisible(true);
	}
}

