package Views;

import javax.swing.*;

import Controllers.EventController;
import Datas.EventData;

import java.awt.*;
import java.awt.event.*;

public class CalendarView extends JFrame implements ActionListener{
    private JPanel barraTitulo, topPanel, panelFondo, panelCentro, panelBotones, leftPanel, rightPanel;
    private Point puntoInicial;
    private JButton closeButton, maximizeButton, minimizeButton, newsButton, logoutButton, newEventButton,
    calendarMarkButton, goToMainButton, supportButton;
    private JLabel logo, eventsLabel, calendarLabel, opciones, userName, profileAvatarLabel;
    private EventController controller;
    private DefaultListModel<String> listModel;
    private JList<String> eventList;

    public CalendarView(){
        setTitle("Campus Virtual Ciencias");
        setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true); //Para quitar la barra de titulo y los bordes
        controller = new EventController();

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
        ImageIcon icono = new ImageIcon("src/main/Datas/images/logo.jpg");
        Image imagen = icono.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(66, 51, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenRedimensionada);
        logo = new JLabel(icono);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        logo.setBounds(0,0, 90, 90);
        topPanel.add(logo);
        //Agregar avatar
        ImageIcon avatar = new ImageIcon("src/main/Datas/images/avatarProfileDefault.png");
        Image avatarSet = avatar.getImage();
        Image avatarRedimension = avatarSet.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar = new ImageIcon(avatarRedimension);
        profileAvatarLabel = new JLabel(avatar);
        profileAvatarLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));
        profileAvatarLabel.setBounds(580,0, 90, 90);
        topPanel.add(profileAvatarLabel);

        northContainer.add(barraTitulo);
        northContainer.add(topPanel);
        
        //Panel extra que sirve para centrar el rectangulo central
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
        eventsLabel = new JLabel("Eventos");
        eventsLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        eventsLabel.setForeground(Color.WHITE);
        eventsLabel.setBounds(125, 40, 200, 20);
        rightPanel.add(eventsLabel);

        calendarLabel = new JLabel("Calendario de eventos");
        calendarLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        calendarLabel.setForeground(Color.WHITE);
        calendarLabel.setBounds(200, 30, 200, 20);
        panelCentro.add(calendarLabel);

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

        newEventButton = new JButton("Gestionar eventos");
        newEventButton.setFont(new Font("Roboto", 1, 14));
        newEventButton.setBounds(71, 110, 160, 30);
        newEventButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newEventButton.addActionListener(this);
        leftPanel.add(newEventButton);
        
        calendarMarkButton = new JButton("Marcar evento en calendario");
        calendarMarkButton.setFont(new Font("Roboto", 1, 12));
        calendarMarkButton.setBounds(58, 210, 185, 30);
        calendarMarkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        calendarMarkButton.addActionListener(this);
        leftPanel.add(calendarMarkButton);

        goToMainButton = new JButton("Volver a inicio");
        goToMainButton.setFont(new Font("Roboto", 1, 14));
        goToMainButton.setBounds(71, 310, 155, 30);
        goToMainButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goToMainButton.addActionListener(this);
        leftPanel.add(goToMainButton);

        supportButton = new JButton("Contacta al soporte");
        supportButton.setFont(new Font("Roboto", 1, 14));
        supportButton.setBounds(51, 410, 195, 30);
        supportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        supportButton.addActionListener(this);
        leftPanel.add(supportButton);

        //Agregar Lista para Visualizar los Eventos
        listModel = new DefaultListModel<>();
        updateList();
        eventList = new JList<>(listModel);
        eventList.setBackground(new Color(3, 34, 63));
        eventList.setForeground(Color.WHITE);
        JScrollPane listScrollPane = new JScrollPane(eventList);
        listScrollPane.setBounds(0,80,300,460);
        rightPanel.add(listScrollPane);
    }

    private void updateList() {
        listModel.clear();
        for (EventData event : controller.getEvents()) {
            listModel.addElement(event.getId() + " - " + event.getName());
        }
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
        if(ae.getSource() == goToMainButton){
            MainView ventana = new MainView();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);
        }
        if(ae.getSource() == logoutButton){
            LoginView login = new LoginView();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            login.setResizable(false);
        }
        if(ae.getSource() == supportButton){
            JOptionPane.showMessageDialog(null,"Si desea contactar con Soporte \n mande un un mensaje a correro@correo.com");
        }
    }
    /*public static void main(String args[]){
        CalendarView calendarView = new CalendarView();
        calendarView.setVisible(true);
        calendarView.setLocationRelativeTo(null);
        calendarView.setResizable(false);
    }*/
}
