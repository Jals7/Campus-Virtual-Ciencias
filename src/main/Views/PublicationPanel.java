package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PublicationPanel extends JPanel{
    private JTextField comentarioField; //Campo de texto para el comentario
    private JLabel comentarioLabel; //Etiqueta para mostrar el comentario

    public PublicationPanel(String rutaImagen, String nombrePublicacion){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Espacio alrededor del panel

        //Colocar la imagen
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);
        Image imagen = imagenIcon.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(400, 400, Image.SCALE_SMOOTH); //Redimensionar la imagen
        JLabel imagenLabel = new JLabel(new ImageIcon(imagenRedimensionada));

        //Nombre de la publicación
        JLabel nombreLabel = new JLabel(nombrePublicacion);
        nombreLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Panel para el campo de comentario y el botón
        JPanel comentarioPanel = new JPanel(new BorderLayout());

        //Campo de texto para el comentario
        comentarioField = new JTextField();
        comentarioField.setFont(new Font("Roboto", Font.PLAIN, 12));

        //Botón "Comentar"
        JButton comentarButton = new JButton("Comentar");
        comentarButton.setFont(new Font("Roboto", Font.BOLD, 12));
        comentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Obtener el texto del campo de comentario
                String comentario = comentarioField.getText();

                //Mostrar el comentario en el JLabel
                comentarioLabel.setText(comentario);

                //Borrar el texto del campo de comentario
                comentarioField.setText("");
            }
        });

        //Agregar el campo de texto y el botón al panel de comentario
        comentarioPanel.add(comentarioField, BorderLayout.CENTER);
        comentarioPanel.add(comentarButton, BorderLayout.EAST);

        //Etiqueta para mostrar el comentario
        comentarioLabel = new JLabel();
        comentarioLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        comentarioLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); //Espacio arriba del JLabel

        //Panel para contener el panel de comentario y la etiqueta de comentario
        JPanel comentarioContainer = new JPanel();
        comentarioContainer.setLayout(new BoxLayout(comentarioContainer, BoxLayout.Y_AXIS));
        comentarioContainer.add(comentarioLabel);
        comentarioContainer.add(comentarioPanel);

        //Agregar componentes al panel principal
        add(imagenLabel, BorderLayout.CENTER);
        add(nombreLabel, BorderLayout.NORTH);
        add(comentarioContainer, BorderLayout.SOUTH);
    }
}