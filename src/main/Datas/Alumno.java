package Datas;

public class Alumno extends Persona {
    private String carrera;
    private int anoDeIngreso;

    public Alumno(int id, String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena,int numTlf, String carrera, int anoDeIngreso) {
        super(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, numTlf);
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
