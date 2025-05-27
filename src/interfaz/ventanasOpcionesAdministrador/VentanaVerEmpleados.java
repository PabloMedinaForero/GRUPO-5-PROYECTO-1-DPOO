package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import conexion.ConexionDerby;
import sistema.Horario;
import sistema.usuarios.Empleado;


public class VentanaVerEmpleados extends JFrame {

    public VentanaVerEmpleados() {
    	ConexionDerby conexion = new ConexionDerby();
    	HashMap<String, Empleado> mapaEmpleados = conexion.obtenerEmpleados();
        setTitle("Lista de Empleados");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (String id : mapaEmpleados.keySet()) {
            modeloLista.addElement(id);
        }

        JList<String> lista = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(lista);

        JButton btnVerDetalles = new JButton("Ver detalles");
        btnVerDetalles.addActionListener(e -> {
            String idSeleccionado = lista.getSelectedValue();
            if (idSeleccionado != null) {
                Empleado empleado = mapaEmpleados.get(idSeleccionado);
                Horario horario = conexion.obtenerHorarios().get(empleado.getUsuario());
                new VentanaDetallesEmpleado(empleado, horario);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVerDetalles);
        panelBotones.add(btnCerrar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
	
}
