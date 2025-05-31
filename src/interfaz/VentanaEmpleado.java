package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conexion.ConexionDerby;
import interfaz.ventanasEpleados.VentanaId;
import interfaz.ventanasEpleados.VentanaOpcionesComprarTiquetesEmpleados;
import interfaz.ventanasOpcionesCliente.VentanaOpcionesComprarTiqueteClientes;
import sistema.atraccion.Atraccion;

class VentanaEmpleado extends JFrame {
	public VentanaEmpleado() {
		ConexionDerby conexion = new ConexionDerby();
		setTitle("Panel Empleado");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		VentanaLogin login = new VentanaLogin(this,"empleado");
		boolean accesoPermitido = login.mostrarDialogo();

		if (!accesoPermitido) {
			JOptionPane.showMessageDialog(this, "Acceso denegado", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Opciones del Empleado");
		lbl.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnConsultarHorario = new JButton("Consultar Horario");
		btnConsultarHorario.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnConsultarHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaId();
			}
		});
		
		JButton btnComprarTiquete = new JButton("Comprar tiquete para empleado");
		btnComprarTiquete.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnComprarTiquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaOpcionesComprarTiquetesEmpleados();
			}
		});
		
		JButton btnVerAtracciones = new JButton("Ver todas las atracciones");
		btnVerAtracciones.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnVerAtracciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ver atracciones");
                HashMap<String, Atraccion> mapaAtracciones = conexion.obtenerAtracciones();
                new VentanaVerAtracciones(mapaAtracciones);
            }
        });

		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnConsultarHorario);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnComprarTiquete);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnVerAtracciones);

		add(panel);
		setVisible(true);
	}
}
