package interfaz;

import sistema.atraccion.*;
import sistema.usuarios.Empleado;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VentanaVerAtracciones extends JFrame {

    private HashMap<String, Atraccion> mapaAtracciones;

    public VentanaVerAtracciones(HashMap<String, Atraccion> mapaAtracciones) {
        this.mapaAtracciones = mapaAtracciones;
        setTitle("Lista de Atracciones");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (String nombre : mapaAtracciones.keySet()) {
            modeloLista.addElement(nombre);
        }

        JList<String> listaAtracciones = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaAtracciones);
        add(scroll, BorderLayout.CENTER);

        JButton btnVerDetalles = new JButton("Ver Detalles de Atracción");
        add(btnVerDetalles, BorderLayout.SOUTH);
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccion = listaAtracciones.getSelectedValue();
                if (seleccion != null) {
                    mostrarInformacion(seleccion);
                } else {
                    JOptionPane.showMessageDialog(VentanaVerAtracciones.this, "Seleccione una atracción para ver los detalles.");
                }
            }
        });

        setVisible(true);
    }

    private void mostrarInformacion(String nombreAtraccion) {
        Atraccion atraccion = mapaAtracciones.get(nombreAtraccion);
        if (atraccion == null) return;

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(atraccion.getNombreAtraccion()).append("\n");
        info.append("Ubicacion: ").append(atraccion.getUbicacion().getNumeroPlaza()).append("\n");
        info.append("Capacidad: ").append(atraccion.getCapacidad()).append("\n");
        info.append("Restricciones: ").append(atraccion.getRestricciones()).append("\n");
        info.append("Clima: ").append(atraccion.getClima().getTipoClima()).append("\n");
        info.append("Estado Operacion: ").append(atraccion.isEstadoOperacion()).append("\n");
        info.append("Nivel Exclusividad: ").append(atraccion.getNivelExclusividad()).append("\n");
        info.append("Empleados: \n");
        System.out.println(atraccion.getEmpleados().size());
        if(atraccion.getEmpleados().size() != 0) {
	        for (Empleado emp : atraccion.getEmpleados()) {
	        	if(emp != null) {
	        		info.append("  - ").append(emp.getUsuario()).append("\n");
	        	}
	        }
        }
        if (atraccion instanceof AtraccionCultural) {
            AtraccionCultural cultural = (AtraccionCultural) atraccion;
            info.append("Tipo Evento: ").append(cultural.getTipoEvento()).append("\n");
        } else if (atraccion instanceof AtraccionMecanica) {
            AtraccionMecanica mecanica = (AtraccionMecanica) atraccion;
            info.append("Tipo Construcción: ").append(mecanica.getTipoConstruccion()).append("\n");
            info.append("Nivel Riesgo: ").append(mecanica.getNivelRiesgo()).append("\n");
        }

        JOptionPane.showMessageDialog(this, info.toString(), "Detalles de la Atracción", JOptionPane.INFORMATION_MESSAGE);
    }
} 
