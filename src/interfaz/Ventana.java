package interfaz;

import java.awt.event.*;
import javax.swing.*;

import interfaz.ventanasOpcionesAdministrador.VentanaAgregarAdministrador;
import interfaz.ventanasOpcionesCrearCuenta.VentanaCrearCuenta;

public class Ventana extends JFrame {

	private static JTabbedPane tabbedPane;
	private static JPanel panel1;

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		ventana.setSize(500, 500);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		
		tabbedPane = new JTabbedPane();
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Bienvenidos a Senec Park");
		lbl.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panel1.add(Box.createVerticalStrut(20));
		panel1.add(lbl);

		// Botón Administrador
		JButton btnAdmin = new JButton("Administrador");
		btnAdmin.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaAdministrador();
			}
		});

		// Botón Empleado
		JButton btnEmpleado = new JButton("Empleado");
		btnEmpleado.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaEmpleado();
			}
		});

		// Botón Cliente
		JButton btnCliente = new JButton("Cliente");
		btnCliente.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaCliente();
			}
		});
		
		// Botón Nueva Cuenta
		JButton btnNuevaCuenta = new JButton("Nueva cuenta");
		btnNuevaCuenta.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnNuevaCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCuenta();
			}
		});
				

		panel1.add(Box.createVerticalStrut(20));
		panel1.add(btnAdmin);
		panel1.add(Box.createVerticalStrut(10));
		panel1.add(btnEmpleado);
		panel1.add(Box.createVerticalStrut(10));
		panel1.add(btnCliente);
		panel1.add(Box.createVerticalStrut(30));

		JLabel otroElemento = new JLabel("¿No tienes cuenta? Crea tu cuenta a continuación:");
		otroElemento.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panel1.add(otroElemento);
		
		
		panel1.add(btnNuevaCuenta);
		panel1.add(Box.createVerticalStrut(30));

		tabbedPane.add("http://SenecPark.com", panel1);
		ventana.add(tabbedPane);
		ventana.setVisible(true);
	}
}
