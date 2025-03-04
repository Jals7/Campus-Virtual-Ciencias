package test;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.RegisterController;
import java.io.*;

public class RegisterTest {

    // Limpiar el archivo de datos antes de cada prueba
   // @Before
    public void limpiarArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Datas/userData.txt", false))) {
            // Sobrescribir el archivo con contenido vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegistroAlumno() {
        // Arrange
        boolean result;

        // Act: Registro exitoso de un Alumno
        result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "M", "25498765",
                "juan.perez@gmail.com", "password123", "Ingeniería de Software", "2020", "Ingeniería", "", "");

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testRegistroProfesor() {
        // Arrange
        boolean result;

        // Act: Registro exitoso de un Profesor
        result = RegisterController.Register("2", "Ana", "García", "05/12/1985", "38", "F", "20458795",
                "ana.garcia@school.com", "clave456", "", "2015", "Matemáticas", "Álgebra Avanzada", "Docente Senior");

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testRegistroAdministrador() {
        // Arrange
        boolean result;

        // Act: Registro exitoso de un Administrador
        result = RegisterController.Register("3", "Carlos", "López", "22/08/1978", "47", "M", "18123456",
                "carlos.lopez@admin.com", "adminPass789", "", "2008", "Recursos Humanos", "", "Director General");

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testPersistenciaDeDatos() {
        // Arrange
        RegisterController.Register("1", "Luis", "Martínez", "15/07/2001", "22", "M", "26489375",
                "luis.martinez@gmail.com", "securePass123", "Arquitectura", "2019", "Diseño", "", "");

        // Act: Verificar que los datos se guardaron en el archivo
        boolean registroEncontrado = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("luis.martinez@gmail.com")) {
                    registroEncontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assert
        assertTrue("El registro no fue persistido correctamente en el archivo", registroEncontrado);
    }

    @Test
    public void testRegistroDatosEspecificosAlumno() {
        // Arrange
        RegisterController.Register("1", "María", "Rodríguez", "03/11/2003", "20", "F", "28594761",
                "maria.rodriguez@gmail.com", "claveAlumno", "Biología", "2021", "Ciencias", "", "");

        // Act: Verificar que se guardaron los datos específicos de Alumno
        boolean datosCorrectos = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Biología,2021")) { // Validar datos específicos de Alumno
                    datosCorrectos = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assert
        assertTrue("Los datos específicos del Alumno no se guardaron correctamente", datosCorrectos);
    }

    @Test
    public void testRegistroDatosEspecificosProfesor() {
        // Arrange
        RegisterController.Register("2", "Sofía", "Hernández", "18/06/1980", "43", "F", "19475829",
                "sofia.hernandez@school.com", "claveProfesor", "", "2010", "Física", "Mecánica Clásica", "Docente Investigador");

        // Act: Verificar que se guardaron los datos específicos del Profesor
        boolean datosCorrectos = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Física,2010,Mecánica Clásica")) {
                    datosCorrectos = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assert
        assertTrue("Los datos específicos del Profesor no se guardaron correctamente", datosCorrectos);
    }

    @Test
    public void testRegistroDatosEspecificosAdministrador() {
        // Arrange
        RegisterController.Register("3", "Ricardo", "Ramírez", "30/09/1965", "58", "M", "15594837",
                "ricardo.ramirez@admin.com", "claveAdmin", "", "2005", "Administración", "", "Coordinador General");

        // Act: Verificar que se guardaron los datos específicos del Administrador
        boolean datosCorrectos = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Datas/userData.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Administración,Coordinador General")) {
                    datosCorrectos = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assert
        assertTrue("Los datos específicos del Administrador no se guardaron correctamente", datosCorrectos);
    }
}
