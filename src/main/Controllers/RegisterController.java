package Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController{
    public static boolean Register(String id, String nombre, String apellido, String fechaDeNacimiento, String edad, String sexo, String CI, String correo,
    String contrasenia, String carrera, String anioDeIngreso, String escuela, String MateriaDada, String cargo){
        try{            
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Datas/userData.txt", true));
                writer.write(id + "," + correo + "," + contrasenia + "," + nombre + "," + apellido 
                + "," + fechaDeNacimiento + "," +edad + "," + sexo + "," + CI + "," + "000");
                        
                // Guardar datos específicos según el rol
                if(id.equals("1")){
                    writer.write("," + carrera + "," + anioDeIngreso);
                }
                if(id.equals("2")) {
                   writer.write("," + escuela + "," + anioDeIngreso + "," + MateriaDada);
                // writer.write("," + escuela + "," + anioDeIngreso + "," + MateriaDada + "," + cargo);
                }
                if(id.equals("3")){
                    writer.write("," + escuela + "," + cargo);
                }

                writer.newLine();
                writer.close();

        }catch(IOException e){
                return false;
        }
    return true;
    }
}
