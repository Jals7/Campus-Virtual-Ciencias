package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Clase para la ventana de inicio de sesión
public class Login2cesar extends JFrame implements ActionListener {
    private JButton loginButton, signUpButton;
    private JLabel title, labelUsername, labelPassword;
    private JTextField txtUsername, txtPassword;
    // Constructor para inicializar la ventana de inicio de sesión
    public Login2cesar() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear y posicionar los botones
        loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(270, 370, 120, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        signUpButton = new JButton("Registrarse");
        signUpButton.setBounds(420, 370, 110, 30);
        signUpButton.addActionListener(this);
        add(signUpButton);

        // Crear y posicionar el título
        title = new JLabel("Campus Virtual Ciencias");
        title.setBounds(240, 30, 350, 50);
        title.setFont(new Font("Andale Mono", Font.BOLD, 30));
        add(title);

        // Crear y posicionar las etiquetas de usuario y contraseña
        labelUsername = new JLabel("Ingrese su nombre de usuario o cédula: ");
        labelUsername.setBounds(290, 200, 260, 30);
        labelUsername.setFont(new Font("Andale Mono", Font.BOLD, 13));
        add(labelUsername);

        labelPassword = new JLabel("Ingrese su contraseña: ");
        labelPassword.setBounds(290, 280, 230, 30);
        labelPassword.setFont(new Font("Andale Mono", Font.BOLD, 13));
        add(labelPassword);

        // Crear y posicionar los campos de texto
        txtUsername = new JTextField();
        txtUsername.setBounds(290, 240, 200, 30);
        add(txtUsername);

        txtPassword = new JTextField();
        txtPassword.setBounds(290, 320, 200, 30);
        add(txtPassword);
    }

    // Capturar eventos al presionar botones
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            boolean loginSuccess = validarCredenciales(username, password);

            if (loginSuccess) {
                // Mostrar mensaje de inicio de sesión exitoso
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");

            } else {
                // Mostrar mensaje de error
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (ae.getSource() == signUpButton) {
            // Abrir la ventana de selección de rol para registrarse
            SeleccionDeRol seleccionDeRol = new SeleccionDeRol();
            seleccionDeRol.setSize(800, 700);
            seleccionDeRol.setLocationRelativeTo(null);
            seleccionDeRol.setVisible(true);
            this.dispose();
        }
    }

    // Método para validar las credenciales
    private boolean validarCredenciales(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\$");
                if (parts.length > 2) {
                String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método principal para ejecutar la aplicación
    public static void main(String args[]) {
        Login2cesar login = new Login2cesar();
        login.setVisible(true);
        login.setBounds(0, 0, 800, 700);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
    }
}
