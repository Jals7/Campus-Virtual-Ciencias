package Personas;

public class Administrativo extends Usuario {
    private String escuela;
    private String cargo;

    public Administrativo(String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String escuela, String cargo) {
        super(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena);
        this.escuela = escuela;
        this.cargo = cargo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
