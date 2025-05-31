package interfaz;

import java.awt.Component;
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
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesAtracciones;
import interfaz.ventanasOpcionesCliente.VentanaIdCliente;
import interfaz.ventanasOpcionesCliente.VentanaOpccionVerTiquetes;
import interfaz.ventanasOpcionesCliente.VentanaOpcionesComprarTiqueteClientes;
import sistema.atraccion.Atraccion;
import sistema.tiquete.Tiquete;

class VentanaCliente extends JFrame {
	public VentanaCliente() {
		ConexionDerby conexion = new ConexionDerby();
		setTitle("Panel Cliente");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		VentanaLogin login = new VentanaLogin(this,"cliente");
		boolean accesoPermitido = login.mostrarDialogo();

		if (!accesoPermitido) {
			JOptionPane.showMessageDialog(this, "Acceso denegado", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Opciones del Cliente");
		lbl.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnComprarEntrada = new JButton("Comprar Entrada");
		btnComprarEntrada.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnComprarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaOpcionesComprarTiqueteClientes();
			}
		});
		
		JButton btnVerAtracciones = new JButton("Ver todas las atracciones");
        btnVerAtracciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerAtracciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Atraccion> mapaAtracciones = conexion.obtenerAtracciones();
                new VentanaVerAtracciones(mapaAtracciones);
            }
        });

		
		JButton btnMisTiquetes = new JButton("Mis Tiquetes");
		btnMisTiquetes.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnMisTiquetes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ver tiquetes");
                HashMap<String, Tiquete> mapaTiquetes = conexion.obtenerTiquetes();
                //new VentanaOpccionVerTiquetes(mapaTiquetes);
                new VentanaIdCliente(mapaTiquetes);
            }
        });
		


		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnComprarEntrada);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnVerAtracciones);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnMisTiquetes);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnMisTiquetes);

		add(panel);
		setVisible(true);
	}
}

