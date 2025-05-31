package interfaz.ventanasOpcionesCrearCuenta;

import javax.swing.*;

import conexion.ConexionDerby;
import interfaz.ventanasOpcionesAdministrador.VentanaAgregarAdministrador;
import interfaz.ventanasOpcionesAdministrador.VentanaAgregarLugarDeServicio;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesAtracciones;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesEmpleado;
import interfaz.ventanasOpcionesAdministrador.VentanaOpcionesLugaresDeServicio;
import sistema.atraccion.Atraccion;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.usuarios.Empleado;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.HashMap;


public class VentanaCrearCuenta extends JFrame{
	public VentanaCrearCuenta() {
    	ConexionDerby conexion = new ConexionDerby();
        setTitle("Agregar Cliente");
        setSize(350, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("Ingresa tu ID");
        JTextField txtId = new JTextField(20);

        JLabel lblContrasenia = new JLabel("Contraseña:");
        JPasswordField txtContrasenia = new JPasswordField(20);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String contrasenia = new String(txtContrasenia.getPassword()).trim();

            if (id.isEmpty() || contrasenia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                conexion.insertarCliente(id, contrasenia);
                JOptionPane.showMessageDialog(this, "Tu cuenta como cliente se creo correctamente");
                dispose();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblContrasenia);
        panel.add(txtContrasenia);
        panel.add(btnAgregar);
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }
}
