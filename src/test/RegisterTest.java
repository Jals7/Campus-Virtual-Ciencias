package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controllers.RegisterController;
import java.io.*;

public class RegisterTest {

    // Limpia el archivo de datos antes de cada prueba
    @Before
    public void limpiarArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Datas/userData.txt", false))) {
            // Sobrescribe el archivo con contenido vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ========================
    // PRUEBAS PARA ALUMNOS
    // ========================

    @Test
    public void testRegistroAlumnoValido() {
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(true, result);
    }

    @Test
    public void testRegistroAlumnoCorreoInvalido() {
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.perez@@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }

    @Test
    public void testRegistroAlumnoPasswordInvalida() {
        // Falta carácter especial (o número) en la contraseña
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }

    @Test
    public void testRegistroAlumnoNombreInvalido() {
        // Nombre con dígitos (no permitido)
        boolean result = RegisterController.Register("1", "Juan3", "Pérez", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }

    @Test
    public void testRegistroAlumnoApellidoInvalido() {
        // Apellido con dígitos (no permitido)
        boolean result = RegisterController.Register("1", "Juan", "Pérez1", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }

    @Test
    public void testRegistroAlumnoFechaFormatoInvalido() {
        // Formato de fecha incorrecto (se espera dd/MM/yyyy)
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "2002/03/10", "21", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroAlumnoFechaFueraRango() {
        // Fecha fuera de rango (anterior a 01/01/1900)
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/1800", "21", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroAlumnoEdadNoNumerica() {
        // Edad que no es numérica
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "veintiuno", "MASCULINO", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroAlumnoSexoInvalido() {
        // Sexo inválido (no es MASCULINO, FEMENINO u OTRO)
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "HOMBRE", "25498765",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroAlumnoCedulaNoNumerica() {
        // Cédula que contiene caracteres no numéricos
        boolean result = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "MASCULINO", "ABC254987",
                "juan.perez@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroAlumnoDuplicadoCorreo() {
        // Primer registro (válido)
        boolean firstResult = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.dup@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        // Segundo registro con el mismo correo
        boolean secondResult = RegisterController.Register("1", "Carlos", "Ramírez", "12/04/2000", "23", "MASCULINO", "31457892",
                "juan.dup@gmail.com", "password123*", "Ingeniería", "2020", "Ingeniería", "", "");
        assertEquals(true, firstResult);
        assertEquals(false, secondResult);
    }
    
    @Test
    public void testRegistroAlumnoDuplicadoCedula() {
        // Primer registro (válido)
        boolean firstResult = RegisterController.Register("1", "Juan", "Pérez", "10/03/2002", "21", "MASCULINO", "25498765",
                "juan.uniq@gmail.com", "password123*", "Ingeniería de Software", "2020", "Ingeniería", "", "");
        // Segundo registro con la misma cédula
        boolean secondResult = RegisterController.Register("1", "Carlos", "Ramírez", "12/04/2000", "23", "MASCULINO", "25498765",
                "carlos.uniq@gmail.com", "password123*", "Ingeniería", "2020", "Ingeniería", "", "");
        assertEquals(true, firstResult);
        assertEquals(false, secondResult);
    }
    
    // ========================
    // PRUEBAS PARA PROFESORES
    // ========================

    @Test
    public void testRegistroProfesorValido() {
        boolean result = RegisterController.Register("2", "Ana", "García", "05/12/1985", "38", "FEMENINO", "20458795",
                "ana.garcia@school.com", "clave456*", "", "2015", "Matemáticas", "Álgebra Avanzada", "");
        assertEquals(true, result);
    }
    
    @Test
    public void testRegistroProfesorAnioIngresoNoNumerico() {
        // Año de ingreso no numérico
        boolean result = RegisterController.Register("2", "Ana", "García", "05/12/1985", "38", "FEMENINO", "20458795",
                "ana.garcia@school.com", "clave456*", "", "20A5", "Matemáticas", "Álgebra Avanzada", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroProfesorAnioIngresoFueraRango() {
        // Año de ingreso fuera del rango (menor a 1900)
        boolean result = RegisterController.Register("2", "Ana", "García", "05/12/1985", "38", "FEMENINO", "20458795",
                "ana.garcia@school.com", "clave456*", "", "1890", "Matemáticas", "Álgebra Avanzada", "");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroProfesorCamposObligatoriosFaltantes() {
        // Falta el campo 'escuela' para profesor
        boolean result = RegisterController.Register("2", "Ana", "García", "05/12/1985", "38", "FEMENINO", "20458795",
                "ana.garcia@school.com", "clave456*", "", "2015", "", "Álgebra Avanzada", "");
        assertEquals(false, result);
    }
    
    // ========================
    // PRUEBAS PARA PERSONAL ADMINISTRATIVO
    // ========================

    @Test
    public void testRegistroPersonalAdministrativoValido() {
        boolean result = RegisterController.Register("3", "Carlos", "López", "22/08/1978", "47", "MASCULINO", "18123456",
                "carlos.lopez@admin.com", "adminPass789*", "", "2008", "Recursos Humanos", "", "Director General");
        assertEquals(true, result);
    }
    
    @Test
    public void testRegistroPersonalAdministrativoCedulaNoNumerica() {
        // Cédula con letra
        boolean result = RegisterController.Register("3", "Carlos", "López", "22/08/1978", "47", "MASCULINO", "18A23456",
                "carlos.lopez@admin.com", "adminPass789*", "", "2008", "Recursos Humanos", "", "Director General");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegistroPersonalAdministrativoCamposObligatoriosFaltantes() {
        // Falta el campo 'cargo'
        boolean result = RegisterController.Register("3", "Carlos", "López", "22/08/1978", "47", "MASCULINO", "18123456",
                "carlos.lopez@admin.com", "adminPass789*", "", "2008", "Recursos Humanos", "", "");
        assertEquals(false, result);
    }
    
    // ========================
    // PRUEBA DE PERSISTENCIA DE DATOS
    // ========================

    @Test
    public void testPersistenciaDeDatos() {
        RegisterController.Register("1", "Luis", "Martínez", "15/07/2001", "22", "MASCULINO", "26489375",
                "luis.martinez@gmail.com", "securePass123*", "Arquitectura", "2019", "Diseño", "", "");
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
        assertTrue("El registro no fue persistido correctamente en el archivo", registroEncontrado);
    }
}
