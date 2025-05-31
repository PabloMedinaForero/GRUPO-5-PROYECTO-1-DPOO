package interfaz.ventanasEpleados;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import conexion.ConexionDerby;
import interfaz.ventanasOpcionesCliente.VentanaOpcionesComprarTiqueteClientes;
import sistema.Horario;
import sistema.usuarios.Empleado;

public class VentanaId extends JFrame  {
	public VentanaId() {
		ConexionDerby conexion = new ConexionDerby();
		HashMap<String, Empleado> mapaEmpleados = conexion.obtenerEmpleados();
		setTitle("Panel selecciona ID");
		setSize(500, 130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("Ingrese el ID del empleado el cual quiere cosultar su horario");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        
        JTextField txtId = new JTextField(20);
        
        JButton btnEjecutar = new JButton("Buscar");
        
        btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(VentanaId.this, "El campo 'Nombre de empleado' es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					Empleado empleado = mapaEmpleados.get(id);
	                Horario horario = conexion.obtenerHorarios().get(empleado.getUsuario());
	                new VentanaHorarioEmpleado(empleado, horario);
				}
			}
        
        
        });
        
        panel.add(txtId);
        panel.add(btnEjecutar);
        
        add(panel);
		setVisible(true);
	}

}
