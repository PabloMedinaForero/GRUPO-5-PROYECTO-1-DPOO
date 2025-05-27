package interfaz.ventanasOpcionesAdministrador;

import javax.swing.*;
import java.awt.*;
import conexion.ConexionDerby;

public class VentanaOpcionesLugaresDeServicio extends JFrame {

    public VentanaOpcionesLugaresDeServicio() {
        setTitle("Opciones - Lugares de Servicio");
        setSize(350, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Seleccione una opción", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JButton btnInsertar = new JButton("Insertar nuevo lugar de servicio");
        btnInsertar.addActionListener(e -> new VentanaAgregarLugarDeServicio());

        JButton btnModificar = new JButton("Modificar lugar de servicio");
        btnModificar.addActionListener(e -> new VentanaModificarLugarDeServicio());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.add(btnInsertar);
        panelBotones.add(btnModificar);

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }
}
