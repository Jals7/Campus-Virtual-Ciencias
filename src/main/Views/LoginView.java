package Views;

import Controllers.LoginController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView extends JFrame implements ActionListener{
    private JPanel panelFondo, topPanel, panelCentro, barraTitulo, panelBotones;
    private JButton closeButton, maximizeButton, minimizeButton, botonIniciar, botonRegistrarse, olvidoDeClave, soporte;
    private JLabel logo, nombreDelSoftware, contrasenia, userCorreo;
    private JTextField txtUserCorreo;
    private JPasswordField txtPasswordField;
    private Point puntoInicial;

    public LoginView(){
        setTitle("Campus Virtual Ciencias");
        setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true); //Para quitar la barra de titulo y los bordes

        panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(255,255,255));

        barraTitulo = new JPanel(new BorderLayout());
        barraTitulo.setBackground(new Color(3, 34, 63));
        barraTitulo.setPreferredSize(new Dimension(1280, 25)); //Para colocarle los tamanios a la barra de titulo
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(3, 34, 63));
        topPanel.setPreferredSize(new Dimension(1280, 130));

        //Agregar un MouseListener y MouseMotionListener a la barra de título
        barraTitulo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                //Se guarda la posicion inicial del mouse para usarse luego
                puntoInicial = e.getPoint();
            }
        });

        barraTitulo.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                //Se calcula aqui la nueva posicion de la ventana para hacer el movimiento
                int deltaX = e.getXOnScreen() - puntoInicial.x;
                int deltaY = e.getYOnScreen() - puntoInicial.y;
                setLocation(deltaX, deltaY);
            }
        });

        JPanel northContainer = new JPanel();
        northContainer.setLayout(new BoxLayout(northContainer, BoxLayout.Y_AXIS)); //Para organizar verticalmente los paneles.
        
        panelCentro = new JPanel();
        panelCentro.setLayout(null);
        panelCentro.setBackground(new Color(3, 34, 63));
        panelCentro.setPreferredSize(new Dimension(640, 360)); //Para colocarle los tamanios al panel central

        //Crear JLABEL del Software
        nombreDelSoftware = new JLabel("Campus Virtual Ciencias");
        nombreDelSoftware.setFont(new Font("Roboto", 3, 36));
        nombreDelSoftware.setForeground(new Color(255,255,255));
        nombreDelSoftware.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        topPanel.add(nombreDelSoftware, BorderLayout.EAST);

        //Crear ImageIcon y JLabel para el Icono de Ciencias
        ImageIcon icono = new ImageIcon("src/main/Datas/images/logo.jpg");
        Image imagen = icono.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(106, 91, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenRedimensionada);
        logo = new JLabel(icono);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        topPanel.add(logo, BorderLayout.WEST);

        northContainer.add(barraTitulo);
        northContainer.add(topPanel);
        
        //Panel extra que sirve para centrar el rectangulo central donde van los JTEXTFIELD
        JPanel PanelExtra = new JPanel(new GridBagLayout());
        PanelExtra.setBackground(new Color(252,203,111));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        PanelExtra.add(panelCentro, gbc);

        //Boton de cerrar
        closeButton = new JButton("X");
        closeButton.setBackground(new Color(3, 34, 63));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(this);

        //Boton de minimizar
        minimizeButton = new JButton("-");
        minimizeButton.setBackground(new Color(3, 34, 63));
        minimizeButton.setForeground(Color.WHITE);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        minimizeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        minimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        minimizeButton.addActionListener(this);

        //Boton de maximizar
        maximizeButton = new JButton("□");
        maximizeButton.setBackground(new Color(3, 34, 63));
        maximizeButton.setForeground(Color.WHITE);
        maximizeButton.setFocusPainted(false);
        maximizeButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        maximizeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        maximizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maximizeButton.addActionListener(this);

        //Panel para contener los botones
        panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panelBotones.setBackground(new Color(3, 34, 63)); // Mismo color que la barra de título
        panelBotones.add(minimizeButton);
        panelBotones.add(maximizeButton);
        panelBotones.add(closeButton);
        barraTitulo.add(panelBotones);

        panelFondo.add(PanelExtra, BorderLayout.CENTER);
        panelFondo.add(northContainer, BorderLayout.NORTH);
        add(panelFondo);

        //JLABELS y JTEXTFIELDS
        userCorreo = new JLabel("Ingrese su direccion de correo:");
        userCorreo.setFont(new Font("Roboto", Font.BOLD, 12));
        userCorreo.setForeground(Color.WHITE);
        userCorreo.setBounds(150, 30, 200, 20);
        panelCentro.add(userCorreo);

        txtUserCorreo = new JTextField();
        txtUserCorreo.setFont(new Font("Roboto", Font.BOLD, 12));
        txtUserCorreo.setBounds(150, 70, 290, 25);
        panelCentro.add(txtUserCorreo);

        contrasenia = new JLabel("Ingrese su contraseña:");
        contrasenia.setFont(new Font("Roboto", Font.BOLD, 12));
        contrasenia.setForeground(Color.WHITE);
        contrasenia.setBounds(150, 110, 200, 20);
        panelCentro.add(contrasenia);

        txtPasswordField = new JPasswordField();
        txtPasswordField.setFont(new Font("Roboto", Font.BOLD, 12));
        txtPasswordField.setBounds(150, 150, 290, 25);
        panelCentro.add(txtPasswordField);

        //Creando botones de panel central
        botonIniciar = new JButton("Iniciar Sesion");
        botonIniciar.setFont(new Font("Roboto", 1, 14));
        botonIniciar.setBounds(140, 210,125, 30);
        botonIniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonIniciar.addActionListener(this);

        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setFont(new Font("Roboto", 1, 14));
        botonRegistrarse.setBounds(370, 210, 125, 30);
        botonRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonRegistrarse.addActionListener(this);

        olvidoDeClave = new JButton("¿Olvidaste tu contraseña?");
        olvidoDeClave.setFont(new Font("Roboto", 1, 14));
        olvidoDeClave.setBounds(140, 260,250, 30);
        olvidoDeClave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        olvidoDeClave.addActionListener(this);

        soporte = new JButton("!!"); //👨‍🔧 este emoji lo puedes poner para el boton de soporte
        soporte.setFont(new Font("Roboto", 2, 18));
        soporte.setBounds(440, 260,50, 30);
        soporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        soporte.addActionListener(this);

        panelCentro.add(botonRegistrarse);
        panelCentro.add(botonIniciar);
        panelCentro.add(olvidoDeClave);
        panelCentro.add(soporte);

    }

    public void actionPerformed(ActionEvent ae){
        //Para las acciones de los botones del panel superior
        if(ae.getSource() == closeButton){
            System.exit(0);
        }
        if(ae.getSource() == maximizeButton){
            if(getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.NORMAL); // Restaurar tamaño normal
            }else{
                setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
            }
        }
        if(ae.getSource() == minimizeButton){
            setState(JFrame.ICONIFIED);
        }
        //Para el Boton de iniciar Sesion
        if(ae.getSource() == botonIniciar){
            String email = txtUserCorreo.getText();
            char[] passwordChars = txtPasswordField.getPassword();
            String password = new String(passwordChars);
            
            if((email.trim().isEmpty()) || (password.trim().isEmpty())){
                JOptionPane.showMessageDialog(null,"Debe introducir Usuario y Contraseña.");
            }else if(LoginController.validateLogin(email, password) == true){
                this.dispose();
                MainView ventanaPrincipal = new MainView();
                ventanaPrincipal.setVisible(true);
                ventanaPrincipal.setLocationRelativeTo(null);
                ventanaPrincipal.setResizable(false);
            }
            else{
                JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrectos.");
            };
        }
        //Para la accion del boton registrar
        if(ae.getSource() == botonRegistrarse){
            this.dispose();
            SignUpView registro = new SignUpView();
            registro.setVisible(true);
            registro.setLocationRelativeTo(null);
            registro.setResizable(false);
        }
         //Para la accion del boton !!
        if(ae.getSource() == soporte){
            JOptionPane.showMessageDialog(null,"Si desea contactar con Soporte \n mande un un mensaje a correo@correo.com");
        }
        
        // Para la accion del boton de cambio de contraseña
        if(ae.getSource() == olvidoDeClave){
            JOptionPane.showMessageDialog(null,"Si olvidaste tu contraseña \n mande un un mensaje a contrasena@correo.com ");
        }    
    }
    public static void main(String args[]){
        LoginView ventanaLogin = new LoginView();
        ventanaLogin.setVisible(true);
        ventanaLogin.setLocationRelativeTo(null);
        ventanaLogin.setResizable(false);
    }
}
