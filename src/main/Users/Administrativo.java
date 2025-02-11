package Users;

public class Administrativo extends Personas {
    private String escuela;
    private String cargo;

    public Administrativo(String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String roll, String escuela, String cargo) {
        super(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, roll);
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
