package Views;

import javax.swing.*;

import Controllers.PublicationController;
import Datas.Persona;
import Datas.UserSession;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PublicationPanel extends JPanel{
    private JTextField comentarioField; //Campo de texto para el comentario
    private JLabel comentarioLabel; //Etiqueta para mostrar el comentario
    private List<String> comentarios; //Lista para almacenar los comentarios
    private ComentariosWindow comentariosWindow; //Variable para almacenar la instancia de la ventana de comentarios
    private String nombrePublicacion; //Nombre de la publicación actual
    private PublicationController controller;

    public PublicationPanel(String rutaImagen, String nombrePublicacion){
        this.nombrePublicacion = nombrePublicacion; //Guardar el nombre de la publicación
        comentarios = new ArrayList<>(); //Inicializar la lista de comentarios
        Persona currentUser = UserSession.getInstance().getCurrentUser();
        controller = new PublicationController();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(252,203,111));

        // Colocar la imagen
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);
        Image imagen = imagenIcon.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(imagenRedimensionada));

        // Nombre de la publicación
        JLabel nombreLabel = new JLabel(nombrePublicacion);
        nombreLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nombreLabel.setForeground(Color.BLACK);

        // Panel para el campo de comentario y el botón
        JPanel comentarioPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Campo de texto para el comentario
        comentarioField = new JTextField();
        comentarioField.setFont(new Font("Roboto", Font.PLAIN, 12));
        comentarioField.setPreferredSize(new Dimension(400, 30));

        JButton comentarButton = new JButton("Comentar");
        comentarButton.setFont(new Font("Roboto", Font.BOLD, 12));
        JButton comentariosButton = new JButton("Comentarios");
        comentariosButton.setFont(new Font("Roboto", Font.BOLD, 12));

        comentariosButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Abrir la ventana de comentarios
                if(comentariosWindow == null){
                    comentarios = controller.loadComents(nombrePublicacion);
                    comentariosWindow = new ComentariosWindow(comentarios);
                }
                comentariosWindow.update();

                //Mostrar la ventana si está oculta
                comentariosWindow.setVisible(true);

            }
        });
        // Acción del botón "Comentar"
        comentarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String comentarioTexto = comentarioField.getText().trim(); //Obtener el texto del campo de comentario

                if(comentarioTexto.isEmpty()){
                    comentarios.clear();
                }else{
                    //Si el campo no está vacío, agregar el comentario al archivo y a la lista
                    String name = currentUser.getNombre();
                    String comentarioCompleto = nombrePublicacion + "," + name + "," + comentarioTexto;
                    controller.writeComents(comentarioCompleto);
                    if(comentarios == null){
                        comentarios = new ArrayList<>(); // Inicializar si es null
                    }
                    comentarios.add(comentarioCompleto); // Agregar el comentario a la lista
                    comentarioField.setText(""); // Limpiar el campo de texto
                }
            }
        });
        comentarioPanel.add(comentarioField);
        comentarioPanel.add(comentarButton);
        comentarioPanel.add(comentariosButton);
        comentarioPanel.setBackground(new Color(3, 34, 63));

        comentarioLabel = new JLabel();
        comentarioLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        comentarioLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        comentarioLabel.setForeground(Color.WHITE);

        // Panel para contener el panel de comentario y la etiqueta de comentario
        JPanel comentarioContainer = new JPanel();
        comentarioContainer.setLayout(new BoxLayout(comentarioContainer, BoxLayout.Y_AXIS));
        comentarioContainer.add(comentarioLabel);
        comentarioContainer.add(comentarioPanel);

        // Agregar componentes al panel principal
        add(imagenLabel, BorderLayout.CENTER);
        add(nombreLabel, BorderLayout.NORTH);
        add(comentarioContainer, BorderLayout.SOUTH);
    }

    

    private class ComentariosWindow extends JFrame{
        private List<String> comentarios;
        private JTextPane textPane;

        public ComentariosWindow(List<String> comentarios){
            this.comentarios = comentarios != null ? comentarios : new ArrayList<>();

            setTitle("Comentarios");
            setSize(300, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            //Esto hace que se pueda formatear el texto con HTML
            textPane = new JTextPane();
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textPane);
            add(scrollPane, BorderLayout.CENTER);
            update();
        }

        //Metodo para actualizar lista de comentarios
        public void update(){
            StringBuilder htmlContent = new StringBuilder("<html>");
            for(String comentario : comentarios){
                //Formatear el comentario: nombrePublicacion,nombreUsuario,comentario
                String[] partes = comentario.split(",");
                if(partes.length == 3){
                    htmlContent.append("<b>").append(partes[1]).append(":</b> ").append(partes[2]).append("<br>");
                }
            }
            htmlContent.append("</html>");
            textPane.setText(htmlContent.toString());
        }
    }
}