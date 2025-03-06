package Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PublicationController{
    private List<String> comentarios;
    //Datos en lista para usarlos en el View
    List<String[]> Data = new ArrayList<>();
     // Metodo para escribir en el archivo de Data
    @SuppressWarnings("unused")
    public int writeToData(String imagePath, String textPublish){
        String filePath = "src/main/Datas/PublicationData.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(imagePath + "," + textPublish);
            writer.newLine();
            return 1;
        }catch(IOException e){
            return 2;
        }
    }

    public int loadData(){
        String filePath = "src/main/Datas/PublicationData.txt";
        int publicationNumber;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] valoresLinea = linea.split(",");
                Data.add(valoresLinea); // Agregar los valores a la Data para usar luego
            }
            publicationNumber = Data.size();
            return publicationNumber;
        }catch (IOException e){
            return -1;
        }
    }

    public List<String[]> getData(){
        return Data;
    }

    //Metodo para leer comentarios desde el archivo
    public List<String> loadComents(String nombrePublicacion){
        File archivo = new File("src/main/Datas/ComentsData.txt");

        if(archivo.exists()){
            comentarios = new ArrayList<>(); //Inicializacion de la lista
            try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
                String linea;
                while((linea = br.readLine()) != null){
                    // Verificar si el comentario pertenece a esta publicación
                    if(linea.startsWith(nombrePublicacion + ",")){
                        comentarios.add(linea); //Agregar el comentario a la lista
                        return comentarios;
                    }
                }
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"No se lograron cargar los comentarios.");
            }
        }
        return null;
    }

    //Metodo para agregar un comentario al archivo
    public void writeComents(String comentario){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/Datas/ComentsData.txt", true))){
            bw.write(comentario);
            bw.newLine(); //Agregar una nueva linea para organizar mejor
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error al escribir en la data.");
        }
    }
}
