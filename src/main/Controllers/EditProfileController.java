package Controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Datas.Persona;
import Datas.Alumno;
import Datas.Profesor;
import Datas.Administrativo;

public class EditProfileController {
    public static boolean updateProfile(Persona currentUser) {
        String filePath = "src/main/Datas/userData.txt";
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try {
            // Leer todas las líneas del archivo
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(","); // Dividir la línea en partes
                if (datos[0].equals(String.valueOf(currentUser.getId()))) { // Comparar con el ID del usuario
                    // Reconstruir la línea actualizada con datos comunes y específicos
                    String nuevaLinea = construirLineaActualizada(currentUser);
                    lineas.add(nuevaLinea);
                    encontrado = true;
                } else {
                    // Guardar la línea sin modificar
                    lineas.add(linea);
                }
            }
            reader.close();

            // Sobrescribir el archivo con los datos actualizados
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String lineaActualizada : lineas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return encontrado; // Retorna true si el usuario fue encontrado y actualizado
    }

    // Método para construir una línea actualizada dependiendo del tipo de usuario
    private static String construirLineaActualizada(Persona currentUser) {
        StringBuilder nuevaLinea = new StringBuilder();

        // Datos comunes a todos los usuarios
        nuevaLinea.append(currentUser.getId()).append(",")
                  .append(currentUser.getCorreo()).append(",")
                  .append(currentUser.getContrasenia()).append(",")
                  .append(currentUser.getNombre()).append(",")
                  .append(currentUser.getApellido()).append(",")
                  .append(currentUser.getFechaDeNacimiento()).append(",")
                  .append(currentUser.getEdad()).append(",")
                  .append(currentUser.getSexo()).append(",")
                  .append(currentUser.getCI()).append(",")
                  .append(currentUser.getNumTlf()).append(",");

        // Datos específicos según el tipo de usuario
        if (currentUser instanceof Alumno) {
            Alumno alumno = (Alumno) currentUser;
            nuevaLinea.append(alumno.getEscuela()).append(",")
                      .append(alumno.getAnoDeIngreso());
        } else if (currentUser instanceof Profesor) {
            Profesor profesor = (Profesor) currentUser;
            nuevaLinea.append(profesor.getEscuela()).append(",")
                      .append(profesor.getAnoDeIngreso()).append(",")
                      .append(profesor.getUltimaMateriaDada());
        } else if (currentUser instanceof Administrativo) {
            Administrativo admin = (Administrativo) currentUser;
            nuevaLinea.append(admin.getEscuela()).append(",")
                      .append(admin.getCargo());
        }

        return nuevaLinea.toString();
    }
}
