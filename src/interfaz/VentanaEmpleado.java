package interfaz;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class VentanaEmpleado extends JFrame {
	public VentanaEmpleado() {
		setTitle("Panel Empleado");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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

		JButton btnRegistrarEntrada = new JButton("Registrar Entrada");
		btnRegistrarEntrada.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnConsultarHorario = new JButton("Consultar Horario");
		btnConsultarHorario.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalStrut(20));
		panel.add(lbl);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnRegistrarEntrada);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnConsultarHorario);

		add(panel);
		setVisible(true);
	}
}
