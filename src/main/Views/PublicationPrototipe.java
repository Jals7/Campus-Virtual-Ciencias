package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class PublicationPrototipe extends JFrame implements ActionListener{
    private JLabel imagen, texto, titulo;
    private JButton botonCrearPublicacion;
    private JButton actualizar;
    String textPublication = "";
    BufferedImage publicationImagen;

    public PublicationPrototipe(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Crear Label titulo y darle formato
        titulo = new JLabel("Campus Virtual Ciencias");
        titulo.setBounds(240, 30, 350, 50);
        titulo.setFont(new Font("Andale Mono", 3, 30));
        add(titulo);

        //Crear Botones
        botonCrearPublicacion = new JButton("Crear Publicacion");
        botonCrearPublicacion.setBounds(300, 400, 150, 30);
        botonCrearPublicacion.addActionListener(this);
        add(botonCrearPublicacion);

        actualizar = new JButton("Actualizar");
        actualizar.setBounds(300, 450, 150, 30);
        actualizar.addActionListener(this);
        add(actualizar);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == botonCrearPublicacion){
            PublicationManagementView view = new PublicationManagementView();
            view.setVisible(true);
            view.setBounds(0,0,800,600);
            view.setLocationRelativeTo(null);
            view.setResizable(false);
        }
        if(ae.getSource() == actualizar){
            ReadData();
        }
    }

    public void actualizarVista(String textoPublication, BufferedImage nuevaImagen){
        textPublication = textoPublication;
        publicationImagen = nuevaImagen;
        if(publicationImagen != null){
            imagen = new JLabel(new ImageIcon(publicationImagen));
        }
        else{
            imagen = new JLabel("No hay imagen disponible");
        }
        imagen.setBounds(30,50,480,320);
        add(imagen);

        //Crear el resto de Labels para Titulo y Descripcion
        texto = new JLabel("<html>" + textPublication.replaceAll("\n", "<br>") + "</html>");
        texto.setBounds(600, 70, 480, 100);
        add(texto);
        revalidate();
        repaint();
    }

    //Metodo para leer archivo de Data
    public void ReadData(){
        String filePath = "src/main/Datas/PublicationData.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if(partes.length == 2){
                    String rutaImagen = partes[0].trim();
                    String texto = partes[1].trim();
                    try {
                        BufferedImage imagen = ImageIO.read(new File(rutaImagen));
                        actualizarVista(texto, imagen);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage());
                    }
                }
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage());
        }
    }

    /*public static void main(String args[]){
        PublicationPrototipe ventana = new PublicationPrototipe();
        ventana.setVisible(true);
        ventana.setBounds(0,0,800,600);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }*/
}
