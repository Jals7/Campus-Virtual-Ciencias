package Controllers;

import Datas.Persona;
import Datas.Alumno;
import Datas.Profesor;
import Datas.Administrativo;
import Datas.UserSession;

import java.io.*;

import javax.swing.JOptionPane;

public class LoginController {
    public static boolean validateLogin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String line;
             
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
               
                if (data.length > 0) {
                    int id = Integer.parseInt(data[0].trim());
                    String dataEmail = data[1].trim();
                    String dataPassword = data[2].trim();
                           
                    if (dataEmail.equals(email) && dataPassword.equals(password)) {
                        
                        String nombre = data[3].trim();
                        String apellido = data[4].trim();
                        String fechaDeNacimiento = data[5].trim();
                        int edad = Integer.parseInt(data[6].trim());
                        String sexo = data[7].trim();
                        String CI = data[8].trim();                        
                        int numTlf = 000;                        
                       
                        Persona usuario = null;

                        if (id == 1) {                            
                            String carrera = data[10].trim();
                            int anoDeIngreso = Integer.parseInt(data[11].trim());
                            usuario = new Alumno(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password, numTlf, carrera, anoDeIngreso);
                        
                        } else if (id == 2 ) {
                            String escuela = data[10].trim();
                            int anoDeIngreso = Integer.parseInt(data[11].trim());
                            String ultimaMateriaDada = data[12].trim();
                            String cargo = data[13].trim();
                            usuario = new Profesor(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password, numTlf, escuela, anoDeIngreso, ultimaMateriaDada, cargo);
                        } else if (id == 3 ) {
                            String escuela = data[10].trim();
                            String cargo = data[11].trim();
                            usuario = new Administrativo(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password,numTlf, escuela, cargo);
                        }

                        if (usuario != null) {
                            UserSession.getInstance().setPersona(usuario);
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
