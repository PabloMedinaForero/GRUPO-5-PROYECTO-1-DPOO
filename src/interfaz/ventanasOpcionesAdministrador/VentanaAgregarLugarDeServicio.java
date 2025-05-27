package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import conexion.ConexionDerby;

public class VentanaAgregarLugarDeServicio extends JFrame {

    public VentanaAgregarLugarDeServicio() {
    	ConexionDerby conexion = new ConexionDerby();
        setTitle("Agregar Lugar de Servicio");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre del Lugar:");
        JTextField txtNombre = new JTextField(20);

        JLabel lblCajero = new JLabel("ID Cajero:");
        JTextField txtCajero = new JTextField(20);

        JLabel lblTipo = new JLabel("Tipo de Lugar:");
        JTextField txtTipo = new JTextField(20);

        JLabel lblEmpleadoAuxiliar = new JLabel("ID Empleado Auxiliar:");
        JTextField txtEmpleadoAuxiliar = new JTextField(20);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String cajero = txtCajero.getText().trim();
            String tipo = txtTipo.getText().trim();
            String auxiliar = txtEmpleadoAuxiliar.getText().trim();

            if (nombre.isEmpty() || cajero.isEmpty() || tipo.isEmpty() || auxiliar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                conexion.insertarLugarDeServicio(nombre, cajero, tipo, auxiliar);
                JOptionPane.showMessageDialog(this, "Lugar de servicio insertado correctamente.");
                dispose();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblCajero);
        panel.add(txtCajero);
        panel.add(lblTipo);
        panel.add(txtTipo);
        panel.add(lblEmpleadoAuxiliar);
        panel.add(txtEmpleadoAuxiliar);
        panel.add(btnAgregar);
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }
}
