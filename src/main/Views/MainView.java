package Views;

import javax.swing.*;

import Controllers.EventController;
import Controllers.PublicationController;
import Datas.EventData;
import Datas.Persona;
import Datas.UserSession;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainView extends JFrame implements ActionListener{
    private JPanel barraTitulo, topPanel, panelFondo, panelCentro, panelBotones, leftPanel, rightPanel;
    private JScrollPane scrollPane;
    private Point puntoInicial;
    private JButton closeButton, maximizeButton, minimizeButton, newsButton, logoutButton, goToEvents, newPublicationButton,
    myPublicationsButton, editProfileButton, extensionGroupButton, studyGroupButton;
    private JLabel logo, events, calendar, opciones, userName, profileAvatarLabel, calendarImage;
    private EventController controller;
    private DefaultListModel<String> listModel;
    private JList<String> eventList;
    private List<String[]> publicaciones;
    private PublicationController pubController;

    public MainView(){
        
        Persona currentUser = UserSession.getInstance().getCurrentUser();
        pubController = new PublicationController();
        pubController.loadData();
        publicaciones = pubController.getData();

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
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(3, 34, 63));
        //panelCentro.setPreferredSize(new Dimension(550, 540)); //Para colocarle los tamanios al panel central
        scrollPane = new JScrollPane(panelCentro);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Siempre mostrar la barra de desplazamiento vertical
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //Crear ImageIcon y JLabel para el Icono de Ciencias
        ImageIcon icono = new ImageIcon("src/main/Datas/images/logo.jpg");
        Image imagen = icono.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(66, 51, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenRedimensionada);
        logo = new JLabel(icono);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        logo.setBounds(0,0, 90, 90);
        topPanel.add(logo);

        //Calendar Image
        ImageIcon calendarIcon = new ImageIcon("src/main/Datas/images/calendar.png");
        Image calendarImg = calendarIcon.getImage();
        Image calendarImageRedimensionada = calendarImg.getScaledInstance(230, 230, Image.SCALE_SMOOTH);
        calendarIcon = new ImageIcon(calendarImageRedimensionada);
        calendarImage = new JLabel(calendarIcon);
        calendarImage.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        calendarImage.setBounds(10,30,300,300);

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
        panelFondo.add(northContainer, BorderLayout.NORTH);

        //Paneles izquierdo y derecho
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(3, 34, 63));
        leftPanel.setPreferredSize(new Dimension(300, 540));

        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(3, 34, 63));
        rightPanel.setPreferredSize(new Dimension(300, 540));
        
        //Agregar Calendario
        rightPanel.add(calendarImage);

        //Crear un panel principal para organizar los paneles laterales y el JScrollPane
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.add(leftPanel, BorderLayout.WEST);
        mainContentPanel.add(scrollPane, BorderLayout.CENTER);
        mainContentPanel.add(rightPanel, BorderLayout.EAST);

        //Agregar el panel principal al panelFondo
        panelFondo.add(mainContentPanel, BorderLayout.CENTER);
        add(panelFondo);
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

        userName = new JLabel(currentUser.getNombre() + " " + currentUser.getApellido());
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

        Dimension buttonSize = new Dimension(180, 30);

        goToEvents = new JButton("Ver eventos");
        goToEvents.setFont(new Font("Roboto", 1, 14));
        goToEvents.setPreferredSize(buttonSize);
        goToEvents.setBounds(60, 430, 180, 30);
        goToEvents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goToEvents.addActionListener(this);
        leftPanel.add(goToEvents);

        editProfileButton = new JButton("Editar Perfil");
        editProfileButton.setFont(new Font("Roboto", 1, 14));
        editProfileButton.setPreferredSize(buttonSize);
        editProfileButton.setBounds(60, 80, 180, 30);
        editProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editProfileButton.addActionListener(this);
        leftPanel.add(editProfileButton);

        newPublicationButton = new JButton("Nueva Publicacion");
        newPublicationButton.setFont(new Font("Roboto", 1, 14));
        newPublicationButton.setPreferredSize(buttonSize);
        newPublicationButton.setBounds(60, 150, 180, 30);
        newPublicationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newPublicationButton.addActionListener(this);
        leftPanel.add(newPublicationButton);

        myPublicationsButton = new JButton("Mis publicaciones");
        myPublicationsButton.setFont(new Font("Roboto", 1, 14));
        myPublicationsButton.setPreferredSize(buttonSize);
        myPublicationsButton.setBounds(60, 220, 180, 30);
        myPublicationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myPublicationsButton.addActionListener(this);
        leftPanel.add(myPublicationsButton);

        extensionGroupButton = new JButton("Ver Grupos de Extension");
        extensionGroupButton.setFont(new Font("Roboto", 1, 14));
        extensionGroupButton.setPreferredSize(buttonSize);
        extensionGroupButton.setBounds(60, 290, 180, 30);
        extensionGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        extensionGroupButton.addActionListener(this);
        leftPanel.add(extensionGroupButton);

        studyGroupButton = new JButton("Ver Grupos de Estudio");
        studyGroupButton.setFont(new Font("Roboto", 1, 14));
        studyGroupButton.setPreferredSize(buttonSize);
        studyGroupButton.setBounds(60, 360, 180, 30);
        studyGroupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        studyGroupButton.addActionListener(this);
        leftPanel.add(studyGroupButton);

        //Agregar Lista para Visualizar los Eventos
        listModel = new DefaultListModel<>();
        updateList();
        eventList = new JList<>(listModel);
        eventList.setBackground(new Color(3, 34, 63));
        eventList.setForeground(Color.WHITE);
        JScrollPane listScrollPane = new JScrollPane(eventList);
        listScrollPane.setBounds(0,330,300,320);
        rightPanel.add(listScrollPane);

        //Cargar publicaciones
        loadPublications(publicaciones);
    }

    private void updateList(){
        listModel.clear();
        for (EventData event : controller.getEvents()) {
            listModel.addElement(event.getId() + " - " + event.getName());
        }
    }
    private void loadPublications(List<String[]> publicaciones){
        //Cargar las publicaciones del usuario
        for(String[] publicacionData : publicaciones){
            String rutaImagen = publicacionData[0]; //Primer valor: ruta de la imagen
            String nombrePublicacion = publicacionData[1]; //Segundo valor: nombre de la publicación

            //Crear un panel para la publicación
            PublicationPanel publicacionPanel = new PublicationPanel(rutaImagen, nombrePublicacion);

            publicacionPanel.setPreferredSize(new Dimension(600, 600)); // Ajusta el tamaño según sea necesario

            //Agregar el panel al panelCentral
            panelCentro.add(publicacionPanel);

            //Agregar un espacio entre publicaciones
            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Actualizar la interfaz
        panelCentro.revalidate();
        panelCentro.repaint();
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
        if(ae.getSource() == editProfileButton){
            EditProfileView editProfile = new EditProfileView();
            editProfile.setVisible(true);
            editProfile.setLocationRelativeTo(null);
            editProfile.setResizable(false);
            dispose();
        }
        if(ae.getSource() == extensionGroupButton){
            SwingUtilities.invokeLater(ExtensionGroupView::new);
        }
        if(ae.getSource() == studyGroupButton){
            SwingUtilities.invokeLater(StudyGroupView::new);    
        }
        if(ae.getSource() == logoutButton){
            LoginView login = new LoginView();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            login.setResizable(false);
            dispose();
        }
        if(ae.getSource() == goToEvents){
            CalendarView calendarView = new CalendarView();
            calendarView.setVisible(true);
            calendarView.setLocationRelativeTo(null);
            calendarView.setResizable(false);
            dispose();
        }
        if(ae.getSource() == newPublicationButton){
            PublicationManagementView pubview = new PublicationManagementView();
            pubview.setBounds(0,0,800,600);
            pubview.setVisible(true);
            pubview.setResizable(false);
            pubview.setLocationRelativeTo(null);
            this.dispose();
        }
    }
}
