package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditProfileView extends JFrame implements ActionListener{
    private JPanel barraTitulo, topPanel, panelFondo, panelCentro, panelBotones, leftPanel;
    private Point puntoInicial;
    private JButton closeButton, maximizeButton, minimizeButton, newsButton, logoutButton, cancelButton, supportButton, saveButton,
    deleteProfileButton, changeProfileImgButton, backToMainButton;
    private JLabel logo, nameLabel, schoolLabel, profileTypeLabel, phoneLabel, editViewLabel, userName, opcionesLabel, informacionLabel,
    profileAvatar;
    private JTextField emailField, passwordField, phoneField;

    public EditProfileView(){
        setTitle("Campus Virtual Ciencias");
        setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true); //Para quitar la barra de titulo y los bordes

        panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(255,255,255));

        barraTitulo = new JPanel(new BorderLayout());
        barraTitulo.setBackground(new Color(3, 34, 63));
        barraTitulo.setPreferredSize(new Dimension(1280, 25)); //Para colocarle los tamanios a la barra de titulo
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(3, 34, 63));
        topPanel.setPreferredSize(new Dimension(1280, 80));

        //Agregar un MouseListener y MouseMotionListener a la barra de título
        barraTitulo.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                //Se guarda la posicion inicial del mouse para usarse luego
                puntoInicial = e.getPoint();
            }
        });

        barraTitulo.addMouseMotionListener(new MouseAdapter(){
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
        panelCentro.setPreferredSize(new Dimension(870, 540)); //Para colocarle los tamanios al panel central

        //Crear ImageIcon y JLabel para el Icono de Ciencias
        ImageIcon icono = new ImageIcon("src/main/Datas/images/logo.jpg");
        Image imagen = icono.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(66, 51, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenRedimensionada);
        logo = new JLabel(icono);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        logo.setBounds(0,0, 90, 90);
        topPanel.add(logo);

        northContainer.add(barraTitulo);
        northContainer.add(topPanel);
        
        //Panel extra que sirve para centrar el rectangulo central donde van los JTEXTFIELD
        JPanel PanelExtra = new JPanel(new GridBagLayout());
        PanelExtra.setBackground(new Color(252,203,111));
        //Configurar GridBagConstraints para panelCentro, esto sirve para alinear en el grid los paneles
        GridBagConstraints gbcCentro = new GridBagConstraints();
        gbcCentro.gridx = 1; //Columna 1 derecha de leftPanel
        gbcCentro.gridy = 0;
        gbcCentro.fill = GridBagConstraints.BOTH; //Rellenar en ambas direcciones
        PanelExtra.add(panelCentro, gbcCentro);

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

        //Panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(3, 34, 63));
        leftPanel.setPreferredSize(new Dimension(300, 540));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0; // Columna 0
        gbcLeft.gridy = 0; // Fila 0
        gbcLeft.anchor = GridBagConstraints.WEST; // Anclar a la izquierda
        gbcLeft.insets = new Insets(0, 0, 0, 50);
        PanelExtra.add(leftPanel, gbcLeft);

        //JLABELS y JTEXTFIELDS
        editViewLabel = new JLabel("Editar Perfil");
        editViewLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        editViewLabel.setForeground(Color.WHITE);
        editViewLabel.setBounds(300, 30, 200, 20);
        panelCentro.add(editViewLabel);

        userName = new JLabel("Nombre de Usuario");
        userName.setFont(new Font("Roboto", Font.BOLD, 16));
        userName.setForeground(Color.WHITE);
        userName.setBounds(720, 22, 200, 20);
        topPanel.add(userName);

        informacionLabel = new JLabel("Informacion");
        informacionLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        informacionLabel.setForeground(Color.WHITE);
        informacionLabel.setBounds(640, 60, 200, 20);
        panelCentro.add(informacionLabel);

        nameLabel = new JLabel("Nombre y Apellido: ");
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(600, 130, 200, 20);
        panelCentro.add(nameLabel);

        emailField = new JTextField("Ingrese el nuevo correo");
        emailField.setFont(new Font("Roboto", 1, 12));
        emailField.setForeground(Color.GRAY);
        emailField.setBounds(260, 130, 200, 30);
        panelCentro.add(emailField);

        schoolLabel = new JLabel("Escuela: ");
        schoolLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        schoolLabel.setForeground(Color.WHITE);
        schoolLabel.setBounds(600, 200, 200, 20);
        panelCentro.add(schoolLabel);

        passwordField = new JTextField("Ingrese la nueva contraseña");
        passwordField.setFont(new Font("Roboto", 1, 12));
        passwordField.setForeground(Color.GRAY);
        passwordField.setBounds(260, 200, 200, 30);
        panelCentro.add(passwordField);

        profileTypeLabel = new JLabel("Tipo de perfil: ");
        profileTypeLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        profileTypeLabel.setForeground(Color.WHITE);
        profileTypeLabel.setBounds(600, 270, 200, 20);
        panelCentro.add(profileTypeLabel);

        phoneField = new JTextField("Ingrese el nuevo numero de telefono");
        phoneField.setFont(new Font("Roboto", 1, 12));
        phoneField.setForeground(Color.GRAY);
        phoneField.setBounds(260, 270, 200, 30);
        panelCentro.add(phoneField);

        phoneLabel = new JLabel("Telefono: ");
        phoneLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBounds(600, 340, 200, 20);
        panelCentro.add(phoneLabel);

        opcionesLabel = new JLabel("Opciones");
        opcionesLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        opcionesLabel.setForeground(Color.WHITE);
        opcionesLabel.setBounds(110, 90, 195, 30);
        leftPanel.add(opcionesLabel);


        ImageIcon avatar = new ImageIcon("src/main/Datas/images/avatarProfileDefault.png");
        Image avat = avatar.getImage();
        Image avatRedimensionado = avat.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        avatar = new ImageIcon(avatRedimensionado);
        profileAvatar = new JLabel(avatar);
        profileAvatar.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        profileAvatar.setBounds(50,10, 160, 160);
        panelCentro.add(profileAvatar);

        //Buttons
        newsButton = new JButton("Notificaciones");
        newsButton.setFont(new Font("Roboto", 1, 14));
        newsButton.setBounds(925, 20,135, 30);
        newsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newsButton.addActionListener(this);
        topPanel.add(newsButton);

        logoutButton = new JButton("Cerrar Sesion");
        logoutButton.setFont(new Font("Roboto", 1, 14));
        logoutButton.setBounds(1125, 20, 135, 30);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(this);
        topPanel.add(logoutButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Roboto", 1, 14));
        cancelButton.setBounds(200, 480, 155, 30);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(this);
        panelCentro.add(cancelButton);

        saveButton = new JButton("Guardar");
        saveButton.setFont(new Font("Roboto", 1, 14));
        saveButton.setBounds(400, 480, 155, 30);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(this);
        panelCentro.add(saveButton);

        changeProfileImgButton = new JButton("Cambiar foto de perfil");
        changeProfileImgButton.setFont(new Font("Roboto", 1, 10));
        changeProfileImgButton.setBounds(50, 150, 155, 20);
        changeProfileImgButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeProfileImgButton.addActionListener(this);
        panelCentro.add(changeProfileImgButton);

        backToMainButton = new JButton("Volver a inicio");
        backToMainButton.setFont(new Font("Roboto", 1, 14));
        backToMainButton.setBounds(51, 260, 195, 30);
        backToMainButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backToMainButton.addActionListener(this);
        leftPanel.add(backToMainButton);
        
        deleteProfileButton = new JButton("Borrar tu perfil de usuario");
        deleteProfileButton.setFont(new Font("Roboto", 1, 12));
        deleteProfileButton.setBounds(51, 190, 195, 30);
        deleteProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteProfileButton.addActionListener(this);
        leftPanel.add(deleteProfileButton);

        supportButton = new JButton("Contacta a nuestro soporte");
        supportButton.setFont(new Font("Roboto", 1, 12));
        supportButton.setBounds(51, 330, 195, 30);
        supportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        supportButton.addActionListener(this);
        leftPanel.add(supportButton);
    }

    public void actionPerformed(ActionEvent ae){
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
        if(ae.getSource() == backToMainButton){
            MainView ventana = new MainView();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);
            dispose();
        }
        if(ae.getSource() == logoutButton){
            LoginView login = new LoginView();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            login.setResizable(false);
        }
        if(ae.getSource() == supportButton){
            JOptionPane.showMessageDialog(null,"Si desea contactar con Soporte \n mande un un mensaje a correo@correo.com");
        } 
    }
    /*public static void main(String args[]){
        EditProfileView profileView = new EditProfileView();
        profileView.setVisible(true);
        profileView.setLocationRelativeTo(null);
        profileView.setResizable(false);
    }*/
}