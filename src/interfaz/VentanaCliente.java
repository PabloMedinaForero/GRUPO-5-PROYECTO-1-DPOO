package interfaz;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class VentanaCliente extends JFrame {
	public VentanaCliente() {
		setTitle("Panel Cliente");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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

		JButton btnVerAtracciones = new JButton("Ver Atracciones");
		btnVerAtracciones.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnComprarEntrada);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnVerAtracciones);

		add(panel);
		setVisible(true);
	}
}

