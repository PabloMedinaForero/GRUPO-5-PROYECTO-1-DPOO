package interfaz.ventanasOpcionesCliente;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import conexion.ConexionDerby;
import interfaz.VentanaVerAtracciones;
import interfaz.ventanasEpleados.VentanaHorarioEmpleado;
import interfaz.ventanasEpleados.VentanaId;
import sistema.Horario;
import sistema.atraccion.Atraccion;
import sistema.tiquete.Tiquete;
import sistema.usuarios.Cliente;
import sistema.usuarios.Empleado;

public class VentanaIdCliente extends JFrame{
	public VentanaIdCliente(HashMap<String, Tiquete> todosTiquetes) {
		ConexionDerby conexion = new ConexionDerby();
		HashMap<String, Cliente> mapaClientes = conexion.obtenerClientes();
		setTitle("Panel selecciona ID");
		setSize(500, 130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("Ingrese el ID del cliente el cual quiere cosultar sus tiquetes");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        
        JTextField txtId = new JTextField(20);
        
        JButton btnEjecutar = new JButton("Buscar");
        
        btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText().trim();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(VentanaIdCliente.this, "El campo 'Nombre de cliente' es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					HashMap<String, Tiquete> todosTiquetes = conexion.obtenerTiquetes();
                    HashMap<String, Tiquete> tiquetesFiltrados = new HashMap<>();

                    for (Map.Entry<String, Tiquete> entry : todosTiquetes.entrySet()) {
                        Tiquete tiquete = entry.getValue();
                        if (tiquete.getIdComprador().equals(id)) {
                            tiquetesFiltrados.put(entry.getKey(), tiquete);
                        }
                    }
					
					/*System.out.println("Ver tiquetes");
	                HashMap<String, Tiquete> mapaTiquetes = conexion.obtenerTiquetes();
	                new VentanaOpccionVerTiquetes(mapaTiquetes);*/
                    
                    new VentanaOpccionVerTiquetes(tiquetesFiltrados);
				}
			}
        
        
        });
        
        panel.add(txtId);
        panel.add(btnEjecutar);
        
        add(panel);
		setVisible(true);
	}

}
