package Controllers;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class RegisterController {
    // Método principal de registro que acepta varios parámetros de usuario
    public static boolean Register(String id, String nombre, String apellido, String fechaDeNacimiento, String edad, String sexo,
                                   String CI, String correo, String contrasenia, String carrera, String anioDeIngreso, String escuela,
                                   String MateriaDada, String cargo) {
        // Llamada a la función verificarDatosRegistro antes de guardar los datos
        if (!verificarDatosRegistro(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo,
                contrasenia, carrera, anioDeIngreso, escuela, MateriaDada, cargo)) {
            return false; // Si la verificación falla, se detiene el registro
        }

        // Normalizar el formato del sexo para guardar (todo en mayúsculas)
        sexo = sexo.toUpperCase();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Datas/userData.txt", true));
            // Escribe los datos generales del usuario en el archivo
            writer.write(id + "," + correo + "," + new String(contrasenia) + "," + nombre + "," + apellido
                    + "," + fechaDeNacimiento + "," + edad + "," + sexo + "," + CI + "," + "000");

            // Guardar datos específicos según el rol del usuario
            if (id.equals("1")) { // Alumno
                writer.write("," + carrera + "," + anioDeIngreso);
            }
            if (id.equals("2")) { // Profesor
                writer.write("," + escuela + "," + anioDeIngreso + "," + MateriaDada);
            }
            if (id.equals("3")) { // Personal Administrativo
                writer.write("," + escuela + "," + cargo);
            }

            writer.newLine(); // Agregar una nueva línea al final del registro
            writer.close(); // Cerrar el archivo

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de datos: " + e.getMessage());
            return false; // Manejo de errores al escribir en el archivo
        }
        return true; // Registro exitoso
    }

    // Función de verificación de datos
    private static boolean verificarDatosRegistro(String id, String nombre, String apellido, String fechaDeNacimiento, String edad,
                                                  String sexo, String CI, String correo, String contrasenia, String carrera,
                                                  String anioDeIngreso, String escuela, String MateriaDada, String cargo) {
        // Validar campos obligatorios según el rol del usuario
        if (nombre.isEmpty() || apellido.isEmpty() || fechaDeNacimiento.isEmpty() || edad.isEmpty() ||
            sexo.isEmpty() || CI.isEmpty() || correo.isEmpty() || contrasenia.isEmpty() ||
            (id.equals("1") && (carrera.isEmpty() || anioDeIngreso.isEmpty())) ||
            (id.equals("2") && (escuela.isEmpty() || anioDeIngreso.isEmpty() || MateriaDada.isEmpty())) ||
            (id.equals("3") && (escuela.isEmpty() || cargo.isEmpty()))) {
            JOptionPane.showMessageDialog(null, "Error: Todos los campos son obligatorios .");
            return false;
        }

        // Validación del formato del correo electrónico
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern patternEmail = Pattern.compile(emailRegex);
        if (!patternEmail.matcher(correo).matches()) {
            JOptionPane.showMessageDialog(null, "Error: El correo electrónico '" + correo +
                    "' no es válido. Formato requerido: usuario@dominio.com");
            return false;
        }

        // Validación de la longitud de la contraseña
        if (contrasenia.length() < 8) {
            JOptionPane.showMessageDialog(null, "Error: La contraseña debe tener al menos 8 caracteres.");
            return false;
        }

        //Validación de la seguridad de la contraseña (incluye el asterisco en el conjunto de caracteres especiales)
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=*]).{8,}$";
        Pattern patternPassword = Pattern.compile(passwordRegex);
        if (!patternPassword.matcher(contrasenia).matches()) {
            JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener al menos un número y uno de los siguientes caracteres especiales: @, #, $, %, ^, &, +, =, *.");
            return false;
        }

        // Validación de la existencia (leer el archivo para ver si el correo o la cédula ya fueron registrados)
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    String existingCorreo = data[1];
                    String existingCI = data[8];
                    if (existingCorreo.equals(correo)) {
                        JOptionPane.showMessageDialog(null, "Error: El correo electrónico '" + correo + "' ya está registrado.");
                        return false;
                    }
                    if (existingCI.equals(CI)) {
                        JOptionPane.showMessageDialog(null, "Error: La cédula '" + CI + "' ya está registrada.");
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de datos: " + e.getMessage());
            return false;
        }

        // Validación del formato del nombre y apellido. Se permite acentos y caracteres Unicode (letras) y espacios.
        String nameRegex = "^[\\p{L}\\s]+$";
        if (!nombre.matches(nameRegex)) {
            JOptionPane.showMessageDialog(null, "Error: El nombre '" + nombre + "' solo debe contener letras (incluyendo acentos) y espacios.");
            return false;
        }
        if (!apellido.matches(nameRegex)) {
            JOptionPane.showMessageDialog(null, "Error: El apellido '" + apellido + "' solo debe contener letras (incluyendo acentos) y espacios.");
            return false;
        }

        // Validación de la fecha de nacimiento (formato dd/MM/yyyy y rango entre 01/01/1900 y hoy)
        String fechaRegex = "^\\d{2}/\\d{2}/\\d{4}$";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaMin = LocalDate.of(1900, 1, 1);
        LocalDate fechaMax = LocalDate.now();
        try {
            LocalDate fecha = LocalDate.parse(fechaDeNacimiento, dateFormatter);
            if (!fechaDeNacimiento.matches(fechaRegex) || fecha.isBefore(fechaMin) || fecha.isAfter(fechaMax)) {
                JOptionPane.showMessageDialog(null, "Error: La fecha de nacimiento '" + fechaDeNacimiento +
                        "' no es válida. Debe tener el formato dd/MM/yyyy y estar entre el 01/01/1900 y hoy.");
                return false;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Error: La fecha de nacimiento debe tener el formato dd/MM/yyyy y estar entre el 01/01/1900 y hoy.");
            return false;
        }

        // Validación de que la edad sea un número
        try {
            Integer.parseInt(edad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La edad '" + edad + "' debe ser un número válido.");
            return false;
        }

        // Validación del formato del sexo (acepta 'MASCULINO', 'FEMENINO' u 'OTRO'; sin importar mayúsculas/minúsculas)
        sexo = sexo.toUpperCase();
        if (!sexo.equals("MASCULINO") && !sexo.equals("FEMENINO") && !sexo.equals("OTRO")) {
            JOptionPane.showMessageDialog(null, "Error: El sexo ingresado debe ser 'MASCULINO', 'FEMENINO' o 'OTRO'.");
            return false;
        }

        // Validación de que la cédula sea numérica
        try {
            Integer.parseInt(CI);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La cédula '" + CI + "' debe contener únicamente números.");
            return false;
        }

        // Validación del año de ingreso para profesores: debe ser un número entre 1900 y el año actual
        if (id.equals("2")) { // Para el rol Profesor
            try {
                int anio = Integer.parseInt(anioDeIngreso);
                int anioActual = LocalDate.now().getYear();
                if (anio < 1900 || anio > anioActual) {
                    JOptionPane.showMessageDialog(null, "Error: El año de ingreso '" + anioDeIngreso +
                            "' debe estar entre 1900 y " + anioActual + ".");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El año de ingreso debe ser un número válido.");
                return false;
            }
        }

        return true; // Si todas las verificaciones pasan, se retorna true
    }
}
