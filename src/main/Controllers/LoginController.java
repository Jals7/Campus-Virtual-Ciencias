package Controllers;

import Datas.Administrativo;
import Datas.Alumno;
import Datas.Persona;
import Datas.Profesor;
import Datas.UserSession;
import java.io.*;

public class LoginController {
    public static boolean validateLogin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String line;
             
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
               
                if (data.length > 1) {
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
                        String numTlf = data[9].trim();                       
                        String escuela = data[10].trim();

                        Persona usuario = null;

                        if (id == 1) {
                            int anoDeIngreso = Integer.parseInt(data[11].trim());
                            usuario = new Alumno(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password, numTlf, escuela, anoDeIngreso ) ;
                            //JOptionPane.showMessageDialog(null,"encontrado alumno");

                        } else if (id == 2 ) {

                            int anoDeIngreso = Integer.parseInt(data[11].trim());
                            String ultimaMateriaDada = data[12].trim();
                            //String cargo = data[13].trim();
                            //usuario = new Profesor(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password, numTlf, escuela, anoDeIngreso, ultimaMateriaDada, cargo);
                            usuario = new Profesor(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password, numTlf, escuela, anoDeIngreso, ultimaMateriaDada);
                            //JOptionPane.showMessageDialog(null,"encontrado profesor");
                        } else if (id == 3 ) {
                           
                            String cargo = data[11].trim();
                            usuario = new Administrativo(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, email, password,numTlf, escuela, cargo);
                            //JOptionPane.showMessageDialog(null,"encontrado admin");
                        }

                        if (usuario != null) {
                            //JOptionPane.showMessageDialog(null,"iniciado");
                            UserSession.getInstance().setPersona(usuario);
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //JOptionPane.showMessageDialog(null,"no encontrado");
        return false;
    }
}
