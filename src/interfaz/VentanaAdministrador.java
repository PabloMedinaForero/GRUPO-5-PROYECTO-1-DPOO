package interfaz;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class VentanaAdministrador extends JFrame {
	public VentanaAdministrador() {
		setTitle("Panel Administrador");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Opciones del Administrador");
		lbl.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnGestionUsuarios = new JButton("Gestionar Usuarios");
		btnGestionUsuarios.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnReportes = new JButton("Ver Reportes");
		btnReportes.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnGestionUsuarios);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnReportes);

		add(panel);
		setVisible(true);
	}
}
