package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import conexion.ConexionDerby;

public class VentanaModificarLugarDeServicio extends JFrame {

    public VentanaModificarLugarDeServicio() {
    	ConexionDerby conexion = new ConexionDerby();
        setTitle("Modificar Lugar de Servicio");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre del Lugar:");
        JTextField txtNombre = new JTextField(20);

        JLabel lblCampo = new JLabel("Campo a modificar:");
        String[] campos = {"Cajero", "Tipo_Lugar", "Empleado_Auxiliar"};
        JComboBox<String> comboCampos = new JComboBox<>(campos);

        JLabel lblNuevoValor = new JLabel("Nuevo Valor:");
        JTextField txtNuevoValor = new JTextField(20);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String campo = (String) comboCampos.getSelectedItem();
            String nuevoValor = txtNuevoValor.getText().trim();

            if (nombre.isEmpty() || campo == null || nuevoValor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                conexion.ejecutarUpdateLugares(campo, nuevoValor, nombre);
                JOptionPane.showMessageDialog(this, "Campo actualizado correctamente.");
                dispose();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblCampo);
        panel.add(comboCampos);
        panel.add(lblNuevoValor);
        panel.add(txtNuevoValor);
        panel.add(btnActualizar);
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }
}
