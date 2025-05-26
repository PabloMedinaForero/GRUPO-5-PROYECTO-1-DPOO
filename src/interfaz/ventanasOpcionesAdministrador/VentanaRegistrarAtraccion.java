package interfaz.ventanasOpcionesAdministrador;



import javax.swing.*;

import conexion.ConexionDerby;
import sistema.Clima;
import sistema.Ubicacion;
import sistema.atraccion.Atraccion;
import sistema.usuarios.Empleado;

import java.awt.event.*;
import java.util.HashMap;

public class VentanaRegistrarAtraccion extends JDialog {
	public VentanaRegistrarAtraccion(JFrame parent, HashMap<String, Empleado> mapaEmpleados, HashMap<String, Atraccion> atracciones) {
		super(parent, "Registrar Atracción", true);
		setSize(400, 500);
		setLocationRelativeTo(parent);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JTextField txtNombre = new JTextField(20);
		JTextField txtUbicacion = new JTextField(20);
		JTextField txtClima = new JTextField(20);
		JTextField txtExclusividad = new JTextField(20);
		JTextField txtTipo = new JTextField(20);
		JTextField txtRestricciones = new JTextField(20);
		JTextField txtRiesgo = new JTextField(20);
		JTextField txtEvento = new JTextField(20);
		JTextField txtEmpleados = new JTextField(20);
		JTextField txtEstado = new JTextField(20);
		JTextField txtCapacidad = new JTextField(20);

		JButton btnAgregar = new JButton("Agregar Atracción");

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText().trim();
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(VentanaRegistrarAtraccion.this, "El campo 'Nombre de atracción' es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					int ubicacion = Integer.parseInt(txtUbicacion.getText().trim());
					String clima = txtClima.getText().trim();
					String exclusividad = txtExclusividad.getText().trim();
					String tipo = txtTipo.getText().trim();
					String restricciones = txtRestricciones.getText().trim();
					String riesgo = txtRiesgo.getText().trim();
					String evento = txtEvento.getText().trim();
					String empleados = txtEmpleados.getText().trim();
					String estado = txtEstado.getText().trim();
					int capacidad = Integer.parseInt(txtCapacidad.getText().trim());

					registrarNuevaAtraccion(mapaEmpleados, atracciones, nombre, ubicacion, clima, exclusividad, tipo,
							restricciones, riesgo, evento, empleados, estado, capacidad);

					JOptionPane.showMessageDialog(VentanaRegistrarAtraccion.this, "Atracción registrada con éxito.");
					dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(VentanaRegistrarAtraccion.this, "Verifique que los campos numéricos son válidos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(new JLabel("Nombre de atracción:")); add(txtNombre);
		add(new JLabel("Número de ubicación:")); add(txtUbicacion);
		add(new JLabel("Clima:")); add(txtClima);
		add(new JLabel("Nivel de exclusividad:")); add(txtExclusividad);
		add(new JLabel("Tipo de atracción:")); add(txtTipo);
		add(new JLabel("Restricciones:")); add(txtRestricciones);
		add(new JLabel("Nivel de riesgo:")); add(txtRiesgo);
		add(new JLabel("Tipo de evento:")); add(txtEvento);
		add(new JLabel("Empleados (códigos separados por -):")); add(txtEmpleados);
		add(new JLabel("Estado de operación (true/false):")); add(txtEstado);
		add(new JLabel("Capacidad:")); add(txtCapacidad);
		add(Box.createVerticalStrut(10));
		add(btnAgregar);

		setVisible(true);
	}

	private void registrarNuevaAtraccion(HashMap<String, Empleado> mapaEmpleados, HashMap<String, Atraccion> atracciones,
										 String nombreAtraccion, int NumeroUbicacion, String StringClima, String nivelExclusividad,
										 String tipoAtraccion, String restricciones, String nivelRiesgo, String tipoEvento,
										 String empleados, String estadoOperacion, int capacidad) {
		ConexionDerby conexion = new ConexionDerby();
		Ubicacion ubicacion = new Ubicacion(NumeroUbicacion);
		Clima clima = new Clima(StringClima);
		boolean operacion = estadoOperacion.toLowerCase().equals("true");

		String[] arrayEmpleados = empleados.split("-");
		String stringEmpleado = "";
		for (String e : arrayEmpleados) {
			Empleado empleado = mapaEmpleados.get(e);
			if (empleado != null) {
				if (!empleado.getAtraccionesCapacitado().contains(nombreAtraccion)) {
					System.out.println("El empleado: " + e + " no está capacitado para esta atracción...");
				} else {
					stringEmpleado += e;
				}
			} else {
				System.out.println("El empleado no existe...");
			}
		}

		if (tipoAtraccion.equalsIgnoreCase("mecanica") || tipoAtraccion.equalsIgnoreCase("cultural")) {
			conexion.insertarAtraccion(nombreAtraccion, NumeroUbicacion, StringClima, nivelExclusividad,
					tipoAtraccion, restricciones, nivelRiesgo, tipoEvento, stringEmpleado, estadoOperacion, capacidad);
		} else {
			System.out.println("No existe ese tipo de atracción.");
		}
	}
}
