package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class PublicationManagementView extends JFrame implements ActionListener{
    private JLabel imageLabel;
    private BufferedImage image;
    private JButton importButton, publishButton;
    private JFileChooser filechooser;
    private JTextArea textarea;
    private JScrollPane scrollpane;
    public String textPublish = "";
    private String imagePath;

    public PublicationManagementView(){
        setTitle("Gestion de publicacion");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Crear botones para importar la imagen y publicar
        importButton = new JButton("Importar Image");
        importButton.addActionListener(this);
        importButton.setBounds(70, 400, 150, 30);
        add(importButton);

        publishButton = new JButton("Publicar");
        publishButton.addActionListener(this);
        publishButton.setBounds(250, 400, 150, 30);
        add(publishButton);

        //Crear y mover el Label de la imagen
        imageLabel = new JLabel("Importa una imagen");
        imageLabel.setBounds(50, 50, 480, 320);
        add(imageLabel);
        
        //Crear textarea y Scroll
        textarea = new JTextArea();
        textarea.setFont(new Font("Andale Mono", 0, 9));
        textarea.setForeground(new Color(0,0,0));
        scrollpane = new JScrollPane(textarea);
        scrollpane.setBounds(550,50,200,400);
        add(scrollpane);

    }

    private void importImage(){
        //Crear el selector de archivos
        filechooser = new JFileChooser();
        filechooser.setDialogTitle("Seleccionar Imagen");
        int userSelection = filechooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = filechooser.getSelectedFile();
            try {
                // Cargar la imagen
                image = ImageIO.read(fileToOpen);
                // Guardar la ruta de la imagen
                imagePath = fileToOpen.getAbsolutePath();
                Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), 0);
                // Mostrar la imagen en el Label
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.revalidate();
                imageLabel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
            }
        }
    }

    public BufferedImage getImage(){
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        if (icon != null) {
            return (BufferedImage) icon.getImage();
        }
        return null;
    }

    //Funcion para controlar las acciones de los botones
    public void actionPerformed(ActionEvent ae){
        //Obtener el evento del boton de importar
        if(ae.getSource() == importButton){
            importImage();
        }
        //Obtener el evento del boton publicar
        if(ae.getSource() == publishButton){
            textPublish = textarea.getText();
            writeToData(imagePath, textPublish);
            this.setVisible(false);
        }
    }

    // Metodo para escribir en el archivo de Data
    private void writeToData(String imagePath, String textPublish){
        String filePath = "src/main/Datas/PublicationData.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(imagePath + "$" + textPublish);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Publicación guardada exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la publicación: " + e.getMessage());
        }
    }

    public static void main(String args[]){
        PublicationManagementView view = new PublicationManagementView();
        view.setVisible(true);
        view.setBounds(0,0,800,600);
        view.setLocationRelativeTo(null);
        view.setResizable(false);
        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
    }
}
