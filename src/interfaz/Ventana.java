package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import crearFrames.Ventana;

public class Ventana extends JFrame {

	private static JTabbedPane tabbedPane;
	private static JPanel panel1;
	private static JPanel panel2;

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		ventana.setSize(500, 500);
		ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tabbedPane = new JTabbedPane();
		panel1 = new JPanel();
		
		JLabel lbl = new JLabel("Bienvenidos a Senec Park");
		panel1.add(lbl);
				
		String[] lista = {"Administrador", "Empleado", "Cliente"};
		JList list = new JList(lista);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
		            System.out.println(e);
		            JDialog dialog = new JDialog();
		            JPanel innerPane = new JPanel();
		            int index = e.getLastIndex();
		            String opcion = "";
		            if(index == 0) {
		            	opcion = "Administrador";
		            }
		            else if (index == 1) {
		            	opcion = "Empleado";
		            }
		            else if (index == 2) {
		            	opcion = "Cliente";
		            }
		            
		            JLabel lbl = new JLabel(opcion);
		            innerPane.add(lbl);
		            
		            dialog.add(innerPane);
		            
		            dialog.setTitle(opcion);
		            dialog.setSize(500, 500);
		            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            dialog.setVisible(true);
		        }
			}
		});
		JScrollPane scroll = new JScrollPane(list);
		panel1.add(scroll);
		tabbedPane.add("http://SenecPark.com", panel1);
		ventana.add(tabbedPane);
		ventana.setVisible(true);
	}

	public void menuAdministrador() {
		
		Ventana ventana = new Ventana();
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
