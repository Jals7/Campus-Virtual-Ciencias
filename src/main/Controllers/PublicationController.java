package Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PublicationController{

    //Datos en lista para usarlos en el View
    List<String[]> Data = new ArrayList<>();
     // Metodo para escribir en el archivo de Data
    @SuppressWarnings("unused")
    public int writeToData(String imagePath, String textPublish){
        String filePath = "src/main/Datas/PublicationData.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(imagePath + "," + textPublish);
            writer.newLine();
            return 1;
        } catch (IOException e) {
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
}
