package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Clase para la selección del rol del usuario
public class SeleccionDeRol extends JFrame implements ActionListener {
    private JButton alumnoButton, profesorButton, administrativoButton;
    private JLabel title, subtitle;
    private JButton backButton;

    // Constructor para inicializar la ventana de selección de rol
    public SeleccionDeRol() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear y posicionar el título
        title = new JLabel("Campus Virtual Ciencias");
        title.setBounds(240, 30, 350, 50);
        title.setFont(new Font("Andale Mono", Font.BOLD, 30));
        add(title);

        // Crear y posicionar el subtítulo
        subtitle = new JLabel("Seleccione su rol");
        subtitle.setBounds(290, 100, 200, 30);
        subtitle.setFont(new Font("Andale Mono", Font.BOLD, 18));
        add(subtitle);

        // Crear y posicionar los botones
        alumnoButton = new JButton("Alumno");
        alumnoButton.setBounds(150, 200, 150, 30);
        alumnoButton.addActionListener(this);
        add(alumnoButton);

        profesorButton = new JButton("Profesor");
        profesorButton.setBounds(320, 200, 150, 30);
        profesorButton.addActionListener(this);
        add(profesorButton);

        administrativoButton = new JButton("Administrativo");
        administrativoButton.setBounds(490, 200, 150, 30);
        administrativoButton.addActionListener(this);
        add(administrativoButton);

        // Botón para volver atrás
        backButton = new JButton("Atrás");
        backButton.setBounds(150, 580, 100, 30);
        backButton.addActionListener(this);
        add(backButton);
    }

    // Manejar eventos de los botones
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == alumnoButton) {
            // Abrir la ventana de registro para Alumno
            Registro registro = new Registro("Alumno");
            registro.setVisible(true);
            registro.setBounds(0, 0, 800, 700); 
            registro.setLocationRelativeTo(null);
            registro.setResizable(false);
            this.dispose();
        } else if (ae.getSource() == profesorButton) {
            // Abrir la ventana de registro para Profesor
            Registro registro = new Registro("Profesor");
            registro.setVisible(true);
            registro.setBounds(0, 0, 800, 700); 
            registro.setLocationRelativeTo(null);
            registro.setResizable(false);
            this.dispose();
        } else if (ae.getSource() == administrativoButton) {
            // Abrir la ventana de registro para Administrativo
            Registro registro = new Registro("Administrativo");
            registro.setVisible(true);
            registro.setBounds(0, 0, 800, 700); 
            registro.setLocationRelativeTo(null);
            registro.setResizable(false);
            this.dispose();
        } else if (ae.getSource() == backButton) {
            // Volver a la pantalla de inicio de sesión
            Login login = new Login();
            login.setSize(800, 700);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            this.dispose();
        }
    }
}
