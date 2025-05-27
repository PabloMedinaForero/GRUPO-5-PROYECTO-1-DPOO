package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import conexion.ConexionDerby;

public class VentanaAgregarAdministrador extends JFrame {

    public VentanaAgregarAdministrador() {
    	ConexionDerby conexion = new ConexionDerby();
        setTitle("Agregar Administrador");
        setSize(350, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID del Administrador:");
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
                conexion.insertarAdministrador(id, contrasenia);
                JOptionPane.showMessageDialog(this, "Administrador agregado correctamente.");
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
