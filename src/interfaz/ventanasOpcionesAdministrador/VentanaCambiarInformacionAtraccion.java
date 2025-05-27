package interfaz.ventanasOpcionesAdministrador;

import conexion.ConexionDerby;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarInformacionAtraccion extends JDialog {
    public VentanaCambiarInformacionAtraccion(JFrame parent) {
        super(parent, "Cambiar Información de Atracción", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        String[] campos = {
                "NOMBRE_ATRACCION", "UBICACION", "CLIMA", "NIVEL_EXCLUSIVIDAD", "TIPO_ATRACCION",
                "RESTRICCIONES", "NIVEL_RIESGO", "TIPO_EVENTO", "EMPLEADOS", "ESTADO_OPERACION", "CAPACIDAD"
        };

        JComboBox<String> comboCampos = new JComboBox<>(campos);
        JTextField txtNuevoValor = new JTextField(15);
        JTextField txtNombreAtraccion = new JTextField(15);
        JButton btnActualizar = new JButton("Actualizar");

        add(Box.createVerticalStrut(10));
        add(new JLabel("Seleccione el campo a modificar:"));
        add(comboCampos);
        add(Box.createVerticalStrut(10));
        add(new JLabel("Nuevo valor:"));
        add(txtNuevoValor);
        add(Box.createVerticalStrut(10));
        add(new JLabel("Nombre de la atracción a modificar:"));
        add(txtNombreAtraccion);
        add(Box.createVerticalStrut(10));
        add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String campo = (String) comboCampos.getSelectedItem();
                String nuevoValor = txtNuevoValor.getText();
                String nombreAtraccion = txtNombreAtraccion.getText();

                if (campo.equals("CAPACIDAD")) {
                    try {
                        int nuevoValorInt = Integer.parseInt(nuevoValor);
                        ConexionDerby.ejecutarUpdateAtraccionInt(campo, nuevoValorInt, nombreAtraccion);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido para CAPACIDAD.");
                    }
                } else {
                    ConexionDerby.ejecutarUpdateAtraccionTexto(campo, nuevoValor, nombreAtraccion);
                }

                dispose();
            }
        });

        setVisible(true);
    }
}
