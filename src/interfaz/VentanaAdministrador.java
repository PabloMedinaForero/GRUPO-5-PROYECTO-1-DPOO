package interfaz;

import javax.swing.*;

import conexion.ConexionDerby;
import interfaz.ventanasOpcionesAdministrador.VentanaAgregarAdministrador;
import interfaz.ventanasOpcionesAdministrador.VentanaAgregarLugarDeServicio;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesAtracciones;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesEmpleado;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesLugaresDeServicio;
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
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Opciones del Administrador");
		lbl.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnGestionAtracciones = new JButton("Opciones atracciones");
		btnGestionAtracciones.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnGestionAtracciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaOpcionesAtracciones();
			}
		});
		
		JButton btnGestionEmpleados = new JButton("Opciones empleados");
		btnGestionEmpleados.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnGestionEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaOpcionesEmpleado(VentanaAdministrador.this);
			}
		});

		JButton btnGestionAdmins = new JButton("Agregar administradores");
		btnGestionAdmins.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnGestionAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaAgregarAdministrador();
			}
		});
		
		JButton btnGestionLugaresDeServicio = new JButton("Opciones lugares de servicio");
		btnGestionLugaresDeServicio.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnGestionLugaresDeServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaOpcionesLugaresDeServicio();
			}
		});
		
		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionAtracciones);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionEmpleados);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionAdmins);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionLugaresDeServicio);

		add(panel);
		setVisible(true);
	}
}

