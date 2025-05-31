package interfaz.ventanasOpcionesCliente;

import javax.swing.*;


import conexion.ConexionDerby;
import interfaz.VentanaVerAtracciones;
import interfaz.ventanasOpcionesAdministrador.VentanaRegistrarAtraccion;
import main.Main;
import sistema.atraccion.Atraccion;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.usuarios.Empleado;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;


public class VentanaOpcionesComprarTiqueteClientes extends JFrame {
	
	
	public VentanaOpcionesComprarTiqueteClientes() {
		
		ConexionDerby conexion = null;
		
		setTitle("Compra de tiquete");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        String[] camposTemporada = {
                "verano", "invierno", "ninguno"
        };
        
        String[] camposTiquete = {
                "individual", "basico", "diamante", "oro", "familiar"
        };
        
        String[] camposFastPass = {
                "Si", "No"
        };
        
        JComboBox<String> opcionesTemporadas = new JComboBox<>(camposTemporada);
        JComboBox<String> opcionesTiquetes = new JComboBox<>(camposTiquete);
        JComboBox<String> opcionesFastPass = new JComboBox<>(camposFastPass);
        JTextField txtNombre = new JTextField(20);
		JTextField txtAtracciones = new JTextField(20);	
		
		JButton btnComprar = new JButton("Comprar tiquete");

		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText().trim();
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(VentanaOpcionesComprarTiqueteClientes.this, "El campo 'Nombre de cliente' es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					HashMap<String,Atraccion> atracciones = Main.getAtracciones();
					Collection<Atraccion> listaAtracciones = atracciones.values();
					
					String idComprador = txtNombre.getText().trim();
					String tipoTiquete = (String) opcionesTiquetes.getSelectedItem();
					String StringDescuento = "false";
					String eleccionFastPass = (String) opcionesFastPass.getSelectedItem();
					String temporada = (String) opcionesTemporadas.getSelectedItem();
					String atraccionesIndividual = txtAtracciones.getText().trim();
					String atraccionesValidas = atraccionesIndividual;
					String esFastPass = "false";
					String fechaInicial = "";
					String fechaFinal = "";
					
					for(Atraccion atraccion : listaAtracciones) {
						if(tipoTiquete.contains("diamante")) {
							atraccionesValidas += atraccion.getNombreAtraccion() + "-";
						}
						else if(tipoTiquete.contains("oro")) {
							if(atraccion.getNivelExclusividad().contains("familiar") || atraccion.getNivelExclusividad().contains("oro")) {
								atraccionesValidas += atraccion.getNombreAtraccion() + "-";
							}
						}
						else if(tipoTiquete.contains("familiar")) {
							if(atraccion.getNivelExclusividad().contains("familiar")) {
								atraccionesValidas += atraccion.getNombreAtraccion() + "-";
							}
						}
					}
					
					if(tipoTiquete.contains("individual")) {
						atraccionesValidas = atraccionesIndividual;
					}
					else if(tipoTiquete.contains("basico")) {
						atraccionesValidas = "";
					}
					
					
					if(eleccionFastPass.contains("Si")) {
						esFastPass = "true";
					}
					
					//---------------------------------
			        
		        	if(temporada.contains("verano")) {
		        		fechaInicial = "01/06/2025";
		        		fechaFinal = "01/09/2025";
		        	}
		        	else if(temporada.contains("invierno")){
		        		fechaInicial = "01/09/2025";
		        		fechaFinal = "31/12/2025";
		        	}
		        	else {
		        		LocalDate hoy = LocalDate.now(); 
		                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		                fechaInicial = hoy.format(formato);
		                fechaFinal = fechaInicial;
		        	}
					//------------------------------------
		        	

					
					conexion.insertarTiquete(idComprador, fechaInicial, fechaFinal, temporada, tipoTiquete, StringDescuento, atraccionesValidas, esFastPass);
					
					JOptionPane.showMessageDialog(VentanaOpcionesComprarTiqueteClientes.this, "Tu compra se realizo con exito");
	                dispose();
				}
			}
		});
		
		panel.add(new JLabel("Nombre de cliente:")); panel.add(txtNombre);
		panel.add(new JLabel("Ingrese la temporada de su tiquete:")); panel.add(opcionesTemporadas);
		panel.add(new JLabel("Seleccione el tipo de tiquete")); panel.add(opcionesTiquetes);
		panel.add(new JLabel("Especifique las atracciones si su tiquete es individual")); panel.add(new JLabel("(Separe cada atraccion con un \"-\")"));panel.add(txtAtracciones);
		panel.add(new JLabel("Quiere que sea fastpass?")); panel.add(opcionesFastPass);
		panel.add(Box.createVerticalStrut(10));
		panel.add(btnComprar);

		add(panel);
		
		
		
		setVisible(true);
	}
	
}

		
		

