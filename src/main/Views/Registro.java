package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Clase para la ventana de registro de usuarios
public class Registro extends JFrame implements ActionListener {
    // Campos de texto para los datos del usuario
    private JTextField txtNombre, txtApellido, txtFechaNacimiento, txtEdad, txtSexo, txtCI, txtCorreo, txtContrasena;
    private JTextField txtCarrera, txtAnoDeIngreso, txtEscuela, txtCargo, txtUltimaMateriaDada;
    private JButton registerButton;
    private String roll;
    private JButton backButton;

    // Constructor para inicializar la ventana de registro
    public Registro(String roll) {
        this.roll = roll;
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear y posicionar el título
        JLabel title = new JLabel("Campus Virtual Ciencias");
        title.setBounds(240, 30, 350, 50);
        title.setFont(new Font("Andale Mono", Font.BOLD, 30));
        add(title);

        // Mensaje para saber qué rol está registrándose
        JLabel rolLabel = new JLabel("Registrando como: " + roll);
        rolLabel.setBounds(240, 70, 300, 30);
        rolLabel.setFont(new Font("Andale Mono", Font.BOLD, 18));
        add(rolLabel);

        // Crear y posicionar los campos comunes
        createLabelAndTextField("Nombre:", 100);
        createLabelAndTextField("Apellido:", 140);
        createLabelAndTextField("Fecha de Nacimiento:", 180);
        createLabelAndTextField("Edad:", 220);
        createLabelAndTextField("Sexo:", 260);
        createLabelAndTextField("Cédula:", 300);
        createLabelAndTextField("Correo:", 340);
        createLabelAndTextField("Contraseña:", 380);

        // Crear y posicionar los campos específicos según el rol
        if ("Alumno".equals(roll)) {
            createLabelAndTextField("Carrera:", 420);
            createLabelAndTextField("Año de Ingreso:", 460);
        } else if ("Profesor".equals(roll)) {
            createLabelAndTextField("Escuela:", 420);
            createLabelAndTextField("Año de Ingreso:", 460);
            createLabelAndTextField("Última Materia Dada:", 500);
            createLabelAndTextField("Cargo:", 540);
        } else if ("Administrativo".equals(roll)) {
            createLabelAndTextField("Escuela:", 420);
            createLabelAndTextField("Cargo:", 460);
        }

        // Botón para registrar
        registerButton = new JButton("Registrar");
        registerButton.setBounds(300, 580, 200, 30);
        registerButton.addActionListener(this);
        add(registerButton);

        // Botón para volver atrás
        backButton = new JButton("Atrás");
        backButton.setBounds(150, 580, 100, 30);
        backButton.addActionListener(this);
        add(backButton);
    }

    // Método para crear etiquetas y campos de texto
    private void createLabelAndTextField(String labelText, int yPosition) {
        JLabel label = new JLabel(labelText);
        label.setBounds(150, yPosition, 150, 30);
        add(label);

        JTextField textField = new JTextField();
        textField.setBounds(300, yPosition, 200, 30);
        add(textField);

        // Asignar el campo de texto a la variable correspondiente
        if (labelText.contains("Nombre")) txtNombre = textField;
        else if (labelText.contains("Apellido")) txtApellido = textField;
        else if (labelText.contains("Fecha de Nacimiento")) txtFechaNacimiento = textField;
        else if (labelText.contains("Edad")) txtEdad = textField;
        else if (labelText.contains("Sexo")) txtSexo = textField;
        else if (labelText.contains("Cédula")) txtCI = textField;
        else if (labelText.contains("Correo")) txtCorreo = textField;
        else if (labelText.contains("Contraseña")) txtContrasena = textField;
        else if (labelText.contains("Carrera")) txtCarrera = textField;
        else if (labelText.contains("Año de Ingreso")) txtAnoDeIngreso = textField;
        else if (labelText.contains("Escuela")) txtEscuela = textField;
        else if (labelText.contains("Cargo")) txtCargo = textField;
        else if (labelText.contains("Última Materia Dada")) txtUltimaMateriaDada = textField;
    }

    // Manejar eventos de los botones
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton) {
            try {
                // Validar y guardar datos
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String fechaDeNacimiento = txtFechaNacimiento.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                String sexo = txtSexo.getText();
                String CI = txtCI.getText();
                String correo = txtCorreo.getText();
                String contrasena = txtContrasena.getText();

                BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true));
                writer.write(CI + "$" + contrasena + "$" + roll + "$" + nombre + "$" + apellido + "$" + fechaDeNacimiento + "$" +
                        edad + "$" + sexo + "$" + correo);

                // Guardar datos específicos según el rol
                if ("Alumno".equals(roll)) {
                    String carrera = txtCarrera.getText();
                    int anoDeIngreso = Integer.parseInt(txtAnoDeIngreso.getText());
                    writer.write("$" + carrera + "$" + anoDeIngreso);
                } else if ("Profesor".equals(roll)) {
                    String escuela = txtEscuela.getText();
                    int anoDeIngreso = Integer.parseInt(txtAnoDeIngreso.getText());
                    String ultimaMateriaDada = txtUltimaMateriaDada.getText();
                    String cargo = txtCargo.getText();
                    writer.write("$" + escuela + "$" + anoDeIngreso + "$" + ultimaMateriaDada + "$" + cargo);
                } else if ("Administrativo".equals(roll)) {
                    String escuela = txtEscuela.getText();
                    String cargo = txtCargo.getText();
                    writer.write("$" + escuela + "$" + cargo);
                }

                writer.newLine();
                writer.close();

                // Mostrar mensaje de registro exitoso
                JOptionPane.showMessageDialog(this, "Registro exitoso");

                // Redirigir a la pantalla de Login
                Login2cesar login = new Login2cesar();
                login.setSize(800, 700);
                login.setLocationRelativeTo(null);
                login.setVisible(true);
                this.dispose(); // Cerrar ventana de registro
            } catch (IOException e) {
                // Mostrar mensaje de error
                JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (ae.getSource() == backButton) {
            // Volver a la pantalla de selección de rol
            SeleccionDeRol seleccionDeRol = new SeleccionDeRol();
            seleccionDeRol.setSize(800, 700);
            seleccionDeRol.setLocationRelativeTo(null);
            seleccionDeRol.setVisible(true);
            this.dispose();
        }
    }
}
