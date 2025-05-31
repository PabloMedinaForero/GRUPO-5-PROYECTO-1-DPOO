package interfaz.ventanasOpcionesCliente;

import sistema.atraccion.*;


import sistema.usuarios.Empleado;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaz.VentanaVerAtracciones;
import sistema.atraccion.Atraccion;
import sistema.atraccion.AtraccionCultural;
import sistema.atraccion.AtraccionMecanica;
import sistema.tiquete.Tiquete;
import sistema.usuarios.Empleado;


public class VentanaOpccionVerTiquetes extends JFrame{
	
	private HashMap<String, Tiquete> mapaTiquetes;
	
	public VentanaOpccionVerTiquetes(HashMap<String, Tiquete> mapaTiquetes) {
		this.mapaTiquetes = mapaTiquetes;
		setTitle("Lista de tiquetes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (String nombre : mapaTiquetes.keySet()) {
            modeloLista.addElement(nombre);
        }

        JList<String> listaTiquetes = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaTiquetes);
        add(scroll, BorderLayout.CENTER);

        JButton btnVerDetalles = new JButton("Ver Detalles de Tiquete");
        add(btnVerDetalles, BorderLayout.SOUTH);
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccion = listaTiquetes.getSelectedValue();
                if (seleccion != null) {
                    mostrarInformacion(seleccion);
                } else {
                    JOptionPane.showMessageDialog(VentanaOpccionVerTiquetes.this, "Seleccione un tiquete para ver los detalles.");
                }
            }
        });

        setVisible(true);
    }

    private void mostrarInformacion(String idTiquete) {
        Tiquete tiquete = mapaTiquetes.get(idTiquete);
        if (tiquete == null) return;

        StringBuilder info = new StringBuilder();
        info.append("Nombre del comprador: ").append(tiquete.getIdComprador()).append("\n");
        info.append("Numero de tiquete: ").append(tiquete.getNumeroTiquete()).append("\n");
        info.append("Fecha inicial: ").append(tiquete.getFechaInicial()).append("\n");
        info.append("Fecha final: ").append(tiquete.getFechaFinal()).append("\n");
        info.append("Es Fast Pass?: ").append(tiquete.isEsFastPass()).append("\n");
        info.append("Es de temporada?: ").append(tiquete.isEsDeTemporada()).append("\n");
        info.append("La temporada es: ").append(tiquete. getTemporada()).append("\n");
        info.append("Tiene tipo: ").append(tiquete. getTipo()).append("\n");

        
        JOptionPane.showMessageDialog(this, info.toString(), "Detalles del tiquete", JOptionPane.INFORMATION_MESSAGE);
    }
} 

