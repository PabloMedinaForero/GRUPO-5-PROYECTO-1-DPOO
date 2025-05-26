package interfaz;

import javax.swing.*;

import conexion.ConexionDerby;
import sistema.usuarios.Administrador;
import sistema.usuarios.Cliente;
import sistema.usuarios.Empleado;
import sistema.usuarios.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class VentanaLogin extends JDialog {
	private boolean autenticado = false;

	public VentanaLogin(JFrame parent, String tipoInicio) {
		super(parent, "Autenticación", true);
		setSize(300, 200);
		setLocationRelativeTo(parent);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JTextField txtUsuario = new JTextField(15);
		JPasswordField txtContrasena = new JPasswordField(15);
		HashMap<String,Usuario> mapa = new HashMap<>();
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String contrasena = new String(txtContrasena.getPassword());
				ConexionDerby conexion = new ConexionDerby();
				if(tipoInicio.equals("administrador")) {
					HashMap<String, Administrador> datos = conexion.obtenerAdministradores();
				    mapa.putAll(datos);
				}
				else if(tipoInicio.equals("cliente")) {
					HashMap<String, Cliente> datos = conexion.obtenerClientes();
				    mapa.putAll(datos);
				}
				else{
					HashMap<String, Empleado> datos = conexion.obtenerEmpleados();
				    mapa.putAll(datos);
				}
				String id = txtUsuario.getText();
				Usuario user =  mapa.get(id);
				if (user != null) {
					String contrasenia = txtContrasena.getText();
					if(user.getContrasenia().equals(contrasenia)) {
						autenticado = true;
						dispose(); 
					}
					else {
						JOptionPane.showMessageDialog(VentanaLogin.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} 
				else {
					JOptionPane.showMessageDialog(VentanaLogin.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(Box.createVerticalStrut(15));
		add(new JLabel("Usuario:"));
		add(txtUsuario);
		add(Box.createVerticalStrut(10));
		add(new JLabel("Contraseña:"));
		add(txtContrasena);
		add(Box.createVerticalStrut(15));
		add(btnIngresar);
	}

	public boolean mostrarDialogo() {
		setVisible(true);
		return autenticado;
	}
}
