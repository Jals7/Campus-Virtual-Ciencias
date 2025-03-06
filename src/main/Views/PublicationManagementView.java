package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.image.BufferedImage;
import Controllers.PublicationController;

public class PublicationManagementView extends JFrame implements ActionListener{
    private JLabel imageLabel;
    private BufferedImage image;
    private JButton importButton, publishButton, cancelButton;
    private JFileChooser filechooser;
    private JTextArea textarea;
    private JScrollPane scrollpane;
    public String textPublish = "";
    private String imagePath;
    private JPanel mainPanel;
    PublicationController publicationControl = new PublicationController();

    public PublicationManagementView(){
        setTitle("Gestion de publicacion");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Crear panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(3, 34, 63));
        mainPanel.setBounds(0, 0, 1280, 720);
        add(mainPanel);
        
        //Crear botones para importar la imagen y publicar
        importButton = new JButton("Importar Image");
        importButton.addActionListener(this);
        importButton.setBounds(500, 360, 150, 30);
        mainPanel.add(importButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(500, 500, 150, 30);
        mainPanel.add(cancelButton);

        publishButton = new JButton("Publicar");
        publishButton.addActionListener(this);
        publishButton.setBounds(500, 430, 150, 30);
        mainPanel.add(publishButton);

        //Crear y mover el Label de la imagen
        imageLabel = new JLabel("Importa una imagen");
        imageLabel.setBounds(50, 20, 480, 320);
        mainPanel.add(imageLabel);
        
        //Crear textarea y Scroll
        textarea = new JTextArea();
        textarea.setFont(new Font("Roboto", 0, 9));
        textarea.setForeground(new Color(0,0,0));
        scrollpane = new JScrollPane(textarea);
        scrollpane.setBounds(50,350,400,200);
        mainPanel.add(scrollpane);

    }

    private void importImage(){
        //Crear el selector de archivos
        filechooser = new JFileChooser();
        filechooser.setDialogTitle("Seleccionar Imagen");
        int userSelection = filechooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = filechooser.getSelectedFile();
            try{
                File destination = new File("src/main/Datas/images/" + fileToOpen.getName());
                Files.copy(fileToOpen.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Cargar la imagen
                image = ImageIO.read(fileToOpen);
                //Guardar ruta
                imagePath = "src/main/Datas/images/" + fileToOpen.getName();
                Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), 0);
                // Mostrar la imagen en el Label
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.revalidate();
                imageLabel.repaint();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
            }
        }
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
            int isOk = 0;
            isOk = publicationControl.writeToData(imagePath, textPublish);
            if(isOk == 1){
                JOptionPane.showMessageDialog(this, "Publicación exitosa");
            }
            else if(isOk == 2){
                JOptionPane.showMessageDialog(this, "Error al publicar");
            }
            MainView ventanaPrincipal = new MainView();
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.setLocationRelativeTo(null);
            ventanaPrincipal.setResizable(false);
            this.dispose();
        }
        if(ae.getSource() == cancelButton){
            MainView ventanaPrincipal = new MainView();
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.setLocationRelativeTo(null);
            ventanaPrincipal.setResizable(false);
            this.dispose();
        }
    }
}

