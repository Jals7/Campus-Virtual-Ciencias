package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Clase para la ventana de perfil del usuario
public class Perfil extends JFrame implements ActionListener {
    // Campos de texto para los datos del usuario
    private JTextField txtNombre, txtApellido, txtEscuela, txtTelefono;
    private JButton changePasswordButton, changeEmailButton, changePhoneButton, saveButton, cancelButton;
    private String roll;

    // Constructor para inicializar la ventana de perfil
    public Perfil(String roll) {
        this.roll = roll;
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear y posicionar el título
        JLabel title = new JLabel("Editar Perfil");
        title.setBounds(240, 30, 350, 50);
        title.setFont(new Font("Andale Mono", Font.BOLD, 30));
        add(title);

        // Crear y posicionar los campos comunes
        createLabelAndTextField("Nombre:", 100);
        createLabelAndTextField("Apellido:", 140);
        createLabelAndTextField("Escuela:", 180);
        createLabelAndTextField("Teléfono:", 220);

        // Crear y posicionar los botones para cambiar información
        changePasswordButton = new JButton("Cambiar Contraseña");
        changePasswordButton.setBounds(150, 260, 200, 30);
        changePasswordButton.addActionListener(this);
        add(changePasswordButton);

        changeEmailButton = new JButton("Cambiar Correo");
        changeEmailButton.setBounds(150, 300, 200, 30);
        changeEmailButton.addActionListener(this);
        add(changeEmailButton);

        changePhoneButton = new JButton("Cambiar Número de Teléfono");
        changePhoneButton.setBounds(150, 340, 200, 30);
        changePhoneButton.addActionListener(this);
        add(changePhoneButton);

        // Botones para guardar y cancelar
        saveButton = new JButton("Guardar");
        saveButton.setBounds(150, 380, 100, 30);
        saveButton.addActionListener(this);
        add(saveButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(270, 380, 100, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);
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
        else if (labelText.contains("Escuela")) txtEscuela = textField;
        else if (labelText.contains("Teléfono")) txtTelefono = textField;
    }

    // Manejar eventos de los botones
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == changePasswordButton) {
            // Lógica para cambiar la contraseña
            JOptionPane.showMessageDialog(this, "Cambiar Contraseña");
        }

        if (ae.getSource() == changeEmailButton) {
            // Lógica para cambiar el correo
            JOptionPane.showMessageDialog(this, "Cambiar Correo");
        }

        if (ae.getSource() == changePhoneButton) {
            // Lógica para cambiar el número de teléfono
            JOptionPane.showMessageDialog(this, "Cambiar Número de Teléfono");
        }

        if (ae.getSource() == saveButton) {
            // Lógica para guardar los cambios
            JOptionPane.showMessageDialog(this, "Cambios Guardados");
        }

        if (ae.getSource() == cancelButton) {
            // Lógica para cancelar los cambios
            JOptionPane.showMessageDialog(this, "Cambios Cancelados");
        }
    }

    public static void main(String[] args) {
        Perfil perfil = new Perfil("Alumno");
        perfil.setSize(800, 700);
        perfil.setLocationRelativeTo(null);
        perfil.setVisible(true);
    }
}
