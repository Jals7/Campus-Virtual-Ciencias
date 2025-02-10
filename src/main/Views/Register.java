package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener{
    private JButton loginButton, signUpButton;
    private JLabel title, labelUsername, labelPassword;
    private JTextField txtUsername, txtPassword;

    public Register(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Se Crean y posicionan los botones
        loginButton = new JButton("Iniciar sesion");
        loginButton.setBounds(270,370,120,30);
        loginButton.addActionListener(this);
        add(loginButton);

        signUpButton = new JButton("Registrarse");
        signUpButton.setBounds(420,370,110,30);
        signUpButton.addActionListener(this);
        add(signUpButton);

        //Se crean y posicionan los Labels
        title = new JLabel("Campus Virtual Ciencias");
        title.setBounds(240,30,350,50);
        title.setFont(new Font("Andale Mono", 3, 30));
        add(title);

        labelUsername = new JLabel("Ingrese su nombre de usuario o cedula: ");
        labelUsername.setBounds(290,200,260,30);
        labelUsername.setFont(new Font("Andale Mono", 1, 13));
        add(labelUsername);

        labelPassword = new JLabel("Ingrese su contraseña: ");
        labelPassword.setBounds(290,280,230,30);
        labelPassword.setFont(new Font("Andale Mono", 1, 13));
        add(labelPassword);

        //Se crean y posicionan los campos de texto
        txtUsername = new JTextField();
        txtUsername.setBounds(290,240,200,30);
        add(txtUsername);

        txtPassword = new JTextField();
        txtPassword.setBounds(290,320,200,30);
        add(txtPassword);
    }

    //Capturar eventos al presionar botones.
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == loginButton){
            //Llamar Ventana principal
        }
        if(ae.getSource() == signUpButton){
            //Llamar ventana de registro
        }
    }

    public static void main(String args[]){
        Register register = new Register();
        register.setVisible(true);
        register.setBounds(0,0,800,600);
        register.setLocationRelativeTo(null);
        register.setResizable(false);
    }
}