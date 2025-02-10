package Personas;

public class Alumno extends Usuario {
    private String carrera;
    private int anoDeIngreso;

    public Alumno(String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String carrera, int anoDeIngreso) {
        super(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena);
        this.carrera = carrera;
        this.anoDeIngreso = anoDeIngreso;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getAnoDeIngreso() {
        return anoDeIngreso;
    }

    public void setAnoDeIngreso(int anoDeIngreso) {
        this.anoDeIngreso = anoDeIngreso;
    }
}
