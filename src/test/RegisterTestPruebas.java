package test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import Controllers.RegisterController;
import java.io.*;

public class RegisterTestPruebas {

    // Limpiar el archivo solo una vez antes de ejecutar las pruebas
    @BeforeClass
    public static void limpiarArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Datas/userData.txt", false))) {
            // Limpieza inicial del archivo
            System.out.println("Archivo limpiado antes de las pruebas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegistroAlumno() {
        // Arrange
        boolean result;

        // Act: Registro de un Alumno con datos de prueba (números, usuario, y contraseña de un solo dígito)
        result = RegisterController.Register("1", "1", "1", "1/1/1111", "1", "1", "1",
                "1", "1", "1", "1", "1", "", "");

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testRegistroProfesor() {
        // Arrange
        boolean result;

        // Act: Registro de un Profesor con datos de prueba (números, usuario, y contraseña de un solo dígito)
        result = RegisterController.Register("2", "2", "2", "2/2/2222", "2", "2", "2",
                "2", "2", "", "2", "2", "2", "2");

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testRegistroAdministrador() {
        // Arrange
        boolean result;

        // Act: Registro de un Administrador con datos de prueba (números, usuario, y contraseña de un solo dígito)
        result = RegisterController.Register("3", "3", "3", "3/3/3333", "3", "3", "3",
                "3", "3", "", "3", "3", "", "3");

        // Assert
        assertEquals(true, result);
    }
}
