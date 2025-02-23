package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView extends JFrame implements ActionListener{
    private JPanel barraTitulo, topPanel, panelFondo, panelCentro, panelBotones, leftPanel, rightPanel;
    private Point puntoInicial;
    private JButton closeButton, maximizeButton, minimizeButton, newsButton, logoutButton, goToEvents, newPublicationButton,
    myPublicationsButton, editProfileButton, extensionGroupButton, studyGroupButton;
    private JLabel logo, events, calendar, opciones, userName;

    public MainView(){
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
        panelCentro.setPreferredSize(new Dimension(550, 540)); //Para colocarle los tamanios al panel central

        //Crear ImageIcon y JLabel para el Icono de Ciencias
        ImageIcon icono = new ImageIcon("src/main/Datas/logo.jpg");
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

        //Paneles izquierdo y derecho
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

        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(3, 34, 63));
        rightPanel.setPreferredSize(new Dimension(300, 540));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 2;
        gbcRight.gridy = 0;
        gbcRight.anchor = GridBagConstraints.EAST;
        gbcRight.insets = new Insets(0, 50, 0, 0);
        PanelExtra.add(rightPanel, gbcRight);

        //JLABELS de paneles
        events = new JLabel("Eventos");
        events.setFont(new Font("Roboto", Font.BOLD, 16));
        events.setForeground(Color.WHITE);
        events.setBounds(115, 300, 200, 20);
        rightPanel.add(events);

        calendar = new JLabel("Calendario");
        calendar.setFont(new Font("Roboto", Font.BOLD, 16));
        calendar.setForeground(Color.WHITE);
        calendar.setBounds(115, 30, 200, 20);
        rightPanel.add(calendar);

        opciones = new JLabel("Opciones");
        opciones.setFont(new Font("Roboto", Font.BOLD, 16));
        opciones.setForeground(Color.WHITE);
        opciones.setBounds(115, 30, 200, 20);
        leftPanel.add(opciones);

        userName = new JLabel("Nombre de Usuario");
        userName.setFont(new Font("Roboto", Font.BOLD, 16));
        userName.setForeground(Color.WHITE);
        userName.setBounds(720, 22, 200, 20);
        topPanel.add(userName);

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

        goToEvents = new JButton("Ver eventos");
        goToEvents.setFont(new Font("Roboto", 1, 14));
        goToEvents.setBounds(80, 430, 140, 30);
        goToEvents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goToEvents.addActionListener(this);
        leftPanel.add(goToEvents);

        editProfileButton = new JButton("Editar Perfil");
        editProfileButton.setFont(new Font("Roboto", 1, 14));
        editProfileButton.setBounds(71, 80, 155, 30);
        editProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editProfileButton.addActionListener(this);
        leftPanel.add(editProfileButton);

        newPublicationButton = new JButton("Nueva Publicacion");
        newPublicationButton.setFont(new Font("Roboto", 1, 14));
        newPublicationButton.setBounds(71, 150, 155, 30);
        newPublicationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newPublicationButton.addActionListener(this);
        leftPanel.add(newPublicationButton);

        myPublicationsButton = new JButton("Mis publicaciones");
        myPublicationsButton.setFont(new Font("Roboto", 1, 14));
        myPublicationsButton.setBounds(71, 220, 155, 30);
        myPublicationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myPublicationsButton.addActionListener(this);
        leftPanel.add(myPublicationsButton);

        extensionGroupButton = new JButton("Ver Grupos de Extension");
        extensionGroupButton.setFont(new Font("Roboto", 1, 14));
        extensionGroupButton.setBounds(51, 290, 195, 30);
        extensionGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        extensionGroupButton.addActionListener(this);
        leftPanel.add(extensionGroupButton);

        studyGroupButton = new JButton("Ver Grupos de Estudio");
        studyGroupButton.setFont(new Font("Roboto", 1, 14));
        studyGroupButton.setBounds(51, 360, 195, 30);
        studyGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        studyGroupButton.addActionListener(this);
        leftPanel.add(studyGroupButton);
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
    }
    public static void main(String args[]){
        MainView ventana = new MainView();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
    }
}
