package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controllers.RegisterController;


// Clase para la ventana de registro de usuarios
public class RegisterView extends JFrame implements ActionListener, ItemListener{
    // Campos de texto para los datos del usuario
    private JTextField txtNombre, txtApellido, txtFechaNacimiento, txtEdad, txtSexo, txtCI, txtCorreo, txtContrasena;
    private JTextField txtCarrera, txtAnoDeIngreso, txtEscuela, txtCargo, txtUltimaMateriaDada;
    private JButton registerButton;
    private JButton backButton;
    private JComboBox optionCombo;
    private JLabel carreraLabel, anioDeIngresoLabel, escuelaLabel, cargoLabel, MateriaDadaLabel;

    // Constructor para inicializar la ventana de registro
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public RegisterView(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setBackground(new Color(3, 34, 63));

        // Crear y posicionar el título
        JLabel title = new JLabel("Campus Virtual Ciencias");
        title.setBounds(500, 10, 350, 50);
        title.setFont(new Font("Roboto", Font.BOLD, 30));
        add(title);

        // Mensaje para saber qué rol está registrándose
        JLabel rolLabel = new JLabel("Registro de nuevo usuario");
        rolLabel.setBounds(500, 60, 300, 30);
        rolLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        add(rolLabel);

        //TextFields y labels de roles
        carreraLabel = new JLabel("Carrera: ");
        carreraLabel.setBounds(600, 100, 100, 30);
        add(carreraLabel);
        txtCarrera = new JTextField();
        txtCarrera.setBounds(750, 100, 200, 30);
        add(txtCarrera);
        
        cargoLabel = new JLabel("Cargo: ");
        cargoLabel.setBounds(600, 140, 100, 30);
        cargoLabel.setVisible(false);
        add(cargoLabel);
        txtCargo = new JTextField();
        txtCargo.setBounds(750, 140, 200, 30);
        txtCargo.setVisible(false);
        add(txtCargo);
        
        anioDeIngresoLabel = new JLabel("Año de ingreso: ");
        anioDeIngresoLabel.setBounds(600, 140, 100, 30);
        add(anioDeIngresoLabel);
        txtAnoDeIngreso = new JTextField();
        txtAnoDeIngreso.setBounds(750, 140, 200, 30);
        add(txtAnoDeIngreso);
        
        escuelaLabel = new JLabel("Escuela: ");
        escuelaLabel.setBounds(600, 100, 100, 30);
        escuelaLabel.setVisible(false);
        add(escuelaLabel);
        txtEscuela = new JTextField();
        txtEscuela.setBounds(750, 100, 200, 30);
        txtEscuela.setVisible(false);
        add(txtEscuela);
        
        MateriaDadaLabel = new JLabel("Ultima Materia Dada: ");
        MateriaDadaLabel.setBounds(600, 180, 150, 30);
        MateriaDadaLabel.setVisible(false);
        add(MateriaDadaLabel);
        txtUltimaMateriaDada = new JTextField();
        txtUltimaMateriaDada.setBounds(750, 180, 200, 30);
        txtUltimaMateriaDada.setVisible(false);
        add(txtUltimaMateriaDada);

        optionCombo = new JComboBox();
        optionCombo.setBounds(150, 460,150,20);
        add(optionCombo);

        optionCombo.addItem("Alumno");
        optionCombo.addItem("Profesor");
        optionCombo.addItem("Administrativo");
        optionCombo.addItemListener(this);
        

        // Crear y posicionar los campos comunes
        createLabelAndTextField("Nombre:", 100);
        createLabelAndTextField("Apellido:", 140);
        createLabelAndTextField("Fecha de Nacimiento:", 180);
        createLabelAndTextField("Edad:", 220);
        createLabelAndTextField("Sexo:", 260);
        createLabelAndTextField("Cédula:", 300);
        createLabelAndTextField("Correo:", 340);
        createLabelAndTextField("Contraseña:", 380);
       // createLabelAndTextField("Numero de Telefono", 420);

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
    }

    // Manejar eventos de los botones
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton){
            // Procesar la información de los campos de texto
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String fechaNacimiento = txtFechaNacimiento.getText();
            String edad = txtEdad.getText();
            String sexo = txtSexo.getText();
            String CI = txtCI.getText();
            String correo = txtCorreo.getText();
            String contrasena = txtContrasena.getText();
            String carrera = txtCarrera.getText();
            String anioDeIngreso = txtAnoDeIngreso.getText();
            String escuela = txtEscuela.getText();
            String materiaDada = txtUltimaMateriaDada.getText();
            String cargo = txtCargo.getText();
            String selected = (String) optionCombo.getSelectedItem();
            

            String id = "0";
            if(selected.equals("Alumno")){
                id = "1";
            }
            else if(selected.equals("Profesor")){
                id = "2";
            }
            else if(selected.equals("Administrativo")){
                id = "3";
            }
            
            //Se evalua el retorno de la funcion del registro para saber si el registro fue exitoso
            if(RegisterController.Register(id, nombre, apellido,fechaNacimiento,edad, 
            sexo, CI, correo, contrasena, carrera, anioDeIngreso, escuela, materiaDada, cargo) == true){
                // Mostrar mensaje de registro exitoso
                JOptionPane.showMessageDialog(this, "Registro exitoso");
                // Redirigir a la pantalla de Login
               LoginView login = new LoginView();
               login.setSize(800, 700);
               login.setLocationRelativeTo(null);
               login.setVisible(true);
               this.dispose(); // Cerrar ventana de registro
            }
            else{
                JOptionPane.showMessageDialog(null, "Error al registrar, verifique que los campos estan llenos");
            }
               
               
        }

        if (ae.getSource() == backButton) {
            // Volver a la pantalla de selección de rol
            LoginView login = new LoginView();
            login.setSize(800, 700);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            this.dispose();
        }
    }

    //Manejar eventos del ComboBox
    public void itemStateChanged(ItemEvent ie){
        if(ie.getSource() == optionCombo){
            String selected = (String) optionCombo.getSelectedItem();
            if(selected.equals("Alumno")){
                carreraLabel.setVisible(true);
                txtCarrera.setVisible(true);
                cargoLabel.setVisible(false);
                txtCargo.setVisible(false);
                anioDeIngresoLabel.setVisible(true);
                txtAnoDeIngreso.setVisible(true);
                escuelaLabel.setVisible(false);
                txtEscuela.setVisible(false);
                MateriaDadaLabel.setVisible(false);
                txtUltimaMateriaDada.setVisible(false);
            }
            if(selected.equals("Profesor")){
                carreraLabel.setVisible(false);
                txtCarrera.setVisible(false);
                cargoLabel.setVisible(false);
                txtCargo.setVisible(false);
                anioDeIngresoLabel.setVisible(true);
                txtAnoDeIngreso.setVisible(true);
                escuelaLabel.setVisible(true);
                txtEscuela.setVisible(true);
                MateriaDadaLabel.setVisible(true);
                txtUltimaMateriaDada.setVisible(true);
            }
            if(selected.equals("Administrativo")){
                carreraLabel.setVisible(false);
                txtCarrera.setVisible(false);
                cargoLabel.setVisible(true);
                txtCargo.setVisible(true);
                anioDeIngresoLabel.setVisible(false);
                txtAnoDeIngreso.setVisible(false);
                escuelaLabel.setVisible(true);
                txtEscuela.setVisible(true);
                MateriaDadaLabel.setVisible(false);
                txtUltimaMateriaDada.setVisible(false);
            }
        }
    }

   //* 
    public static void main(String[] args){
        RegisterView registro = new RegisterView();
        registro.setVisible(true);
        registro.setLocationRelativeTo(null);
        registro.setResizable(false);
    }// */
}
