package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;

import sistema.atraccion.Atraccion;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.usuarios.Empleado;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class VentanaOpcionesAtracciones extends JFrame {

    public VentanaOpcionesAtracciones(HashMap<String, Empleado> mapaEmpleados, HashMap<String, Atraccion> mapaAtracciones, HashMap<String, LugarDeServicio> mapaLugarDeServicio) {
        setTitle("Opciones de Atracciones");
        setSize(400, 300);
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
                new VentanaRegistrarAtraccion(VentanaOpcionesAtracciones.this, mapaEmpleados, mapaAtracciones);
            }
        });

        JButton btnModificar = new JButton("Realizar algún cambio en un campo específico");
        btnModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí va la lógica para modificar una atracción
                System.out.println("Modificar campo específico de atracción");
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
        panel.add(btnModificar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);

        add(panel);
        setVisible(true);
    }
}

