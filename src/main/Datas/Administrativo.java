package Datas;

public class Administrativo extends Persona {
    private String escuela;
    private String cargo;

    public Administrativo(int id, String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena,int numTlf, String escuela, String cargo) {
        super(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, numTlf);
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
