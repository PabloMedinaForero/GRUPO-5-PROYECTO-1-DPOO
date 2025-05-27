package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;

import conexion.ConexionDerby;
import interfaz.VentanaVerAtracciones;
import sistema.atraccion.Atraccion;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.usuarios.Empleado;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class VentanaOpcionesAtracciones extends JFrame {

    public VentanaOpcionesAtracciones() {
    	ConexionDerby conexion = new ConexionDerby();
		HashMap<String, Empleado> mapaEmpleados = conexion.obtenerEmpleados();
		HashMap<String, LugarDeServicio> mapaLugarDeServicio = conexion.obtenerLugaresDeServicio();
        setTitle("Opciones de Atracciones");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Seleccione las acciones que desea realizar");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnRegistrar = new JButton("Registrar nueva atracción");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Registrar nueva atracción");
                new VentanaRegistrarAtraccion(VentanaOpcionesAtracciones.this, mapaEmpleados);
            }
        });

        JButton btnModificar = new JButton("Realizar algún cambio en un campo específico");
        btnModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modificar campo específico de atracción");
                new VentanaCambiarInformacionAtraccion(VentanaOpcionesAtracciones.this);
            }
        });
        
        JButton btnVerAtracciones = new JButton("Ver información de atracciones");
        btnVerAtracciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerAtracciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ver atracciones");
                HashMap<String, Atraccion> mapaAtracciones = conexion.obtenerAtracciones();
                new VentanaVerAtracciones(mapaAtracciones);
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });

        panel.add(Box.createVerticalStrut(20));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnRegistrar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVerAtracciones);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnModificar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);

        add(panel);
        setVisible(true);
    }
}

