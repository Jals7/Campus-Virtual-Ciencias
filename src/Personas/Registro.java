package Personas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registro {
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Guarda la información del usuario en un archivo
    public void guardarUsuarioEnArchivo(String nombreArchivo) {
        if (usuario != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
                if (usuario instanceof Alumno) {
                    Alumno alumno = (Alumno) usuario;
                    writer.write("$");
                    writer.write(alumno.getNombre() + "$" +
                                 alumno.getApellido() + "$" +
                                 alumno.getFechaDeNacimiento() + "$" +
                                 alumno.getEdad() + "$" +
                                 alumno.getSexo() + "$" +
                                 alumno.getCI() + "$" +
                                 alumno.getCorreo() + "$" +
                                 alumno.getContrasena() + "$" +
                                 alumno.getCarrera() + "$" +
                                 alumno.getAnoDeIngreso());
                } else if (usuario instanceof Profesor) {
                    Profesor profesor = (Profesor) usuario;
                    writer.write("&");
                    writer.write(profesor.getNombre() + "&" +
                                 profesor.getApellido() + "&" +
                                 profesor.getFechaDeNacimiento() + "&" +
                                 profesor.getEdad() + "&" +
                                 profesor.getSexo() + "&" +
                                 profesor.getCI() + "&" +
                                 profesor.getCorreo() + "&" +
                                 profesor.getContrasena() + "&" +
                                 profesor.getEscuela() + "&" +
                                 profesor.getAnoDeIngreso() + "&" +
                                 profesor.getUltimaMateriaDada() + "&" +
                                 profesor.getCargo());
                } else if (usuario instanceof Administrativo) {
                    Administrativo administrativo = (Administrativo) usuario;
                    writer.write("#");
                    writer.write(administrativo.getNombre() + "#" +
                                 administrativo.getApellido() + "#" +
                                 administrativo.getFechaDeNacimiento() + "#" +
                                 administrativo.getEdad() + "#" +
                                 administrativo.getSexo() + "#" +
                                 administrativo.getCI() + "#" +
                                 administrativo.getCorreo() + "#" +
                                 administrativo.getContrasena() + "#" +
                                 administrativo.getEscuela() + "#" +
                                 administrativo.getCargo());
                }
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error al guardar la información del usuario: " + e.getMessage());
            }
        } else {
            System.out.println("No hay usuario registrado para guardar.");
        }
    }

    // Realiza el login del usuario verificando correo y contraseña
    public Usuario login(String correo, String contrasena, String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                char tipoUsuario = linea.charAt(0);
                String[] atributos = linea.substring(1).split(String.valueOf(tipoUsuario));
                if (atributos[6].equals(correo) && atributos[7].equals(contrasena)) {
                    switch (tipoUsuario) {
                        case '$':
                            return new Alumno(atributos[0], atributos[1], atributos[2], Integer.parseInt(atributos[3]), atributos[4], atributos[5], atributos[6], atributos[7], atributos[8], Integer.parseInt(atributos[9]));
                        case '&':
                            return new Profesor(atributos[0], atributos[1], atributos[2], Integer.parseInt(atributos[3]), atributos[4], atributos[5], atributos[6], atributos[7], atributos[8], Integer.parseInt(atributos[9]), atributos[10], atributos[11]);
                        case '#':
                            return new Administrativo(atributos[0], atributos[1], atributos[2], Integer.parseInt(atributos[3]), atributos[4], atributos[5], atributos[6], atributos[7], atributos[8], atributos[9]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return null; // Si no se encuentra el usuario
    }
    // Registra un alumno con los datos proporcionados por el usuario
    public void registrarAlumno(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Fecha de Nacimiento: ");
        String fechaDeNacimiento = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Cédula de Identidad: ");
        String CI = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();
        System.out.print("Año de Ingreso: ");
        int anoDeIngreso = scanner.nextInt();
        scanner.nextLine();
        Alumno alumno = new Alumno(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, carrera, anoDeIngreso);
        setUsuario(alumno);
    }

    // Registra un profesor con los datos proporcionados por el usuario
    public void registrarProfesor(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Fecha de Nacimiento: ");
        String fechaDeNacimiento = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Cédula de Identidad: ");
        String CI = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Escuela: ");
        String escuela = scanner.nextLine();
        System.out.print("Año de Ingreso: ");
        int anoDeIngreso = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Última Materia Dada: ");
        String ultimaMateriaDada = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        Profesor profesor = new Profesor(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, escuela, anoDeIngreso, ultimaMateriaDada, cargo);
        setUsuario(profesor);
    }

    // Registra un personal administrativo con los datos proporcionados por el usuario
    public void registrarAdministrativo(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Fecha de Nacimiento: ");
        String fechaDeNacimiento = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Cédula de Identidad: ");
        String CI = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Escuela: ");
        String escuela = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        Administrativo administrativo = new Administrativo(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, escuela, cargo);
        setUsuario(administrativo);
    }

    public static void main(String[] args) {
        Registro registro = new Registro();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecciona una opción:");
        System.out.println("1. Login");
        System.out.println("2. Registrar");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            // Login
            System.out.print("Correo para login: ");
            String correoLogin = scanner.nextLine();
            System.out.print("Contraseña para login: ");
            String contrasenaLogin = scanner.nextLine();

            Usuario usuarioLogeado = registro.login(correoLogin, contrasenaLogin, "RegistroPrincipal.txt");

            if (usuarioLogeado != null) {
                System.out.println("Login exitoso. Bienvenido, " + usuarioLogeado.getNombre());
            } else {
                System.out.println("Correo o contraseña incorrectos.");
            }
        } else if (opcion == 2) {
            // Registrar
            System.out.println("Selecciona el tipo de usuario a registrar:");
            System.out.println("1. Alumno");
            System.out.println("2. Profesor");
            System.out.println("3. Personal Administrativo");

            int tipoUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (tipoUsuario) {
                case 1:
                    registro.registrarAlumno(scanner);
                    break;
                case 2:
                    registro.registrarProfesor(scanner);
                    break;
                case 3:
                    registro.registrarAdministrativo(scanner);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            registro.guardarUsuarioEnArchivo("RegistroPrincipal.txt");
            System.out.println("Información guardada en el archivo RegistroPrincipal.txt");
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}
