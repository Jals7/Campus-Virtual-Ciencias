package Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PublicationController{
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
}
