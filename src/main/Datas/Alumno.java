package Datas;

public class Alumno extends Persona {
   
    private int anoDeIngreso;

    public Alumno(int id, String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String numTlf, String escuela, int anoDeIngreso) {
        super(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, numTlf,escuela);
        
        this.anoDeIngreso = anoDeIngreso;
    }

    public int getAnoDeIngreso() {
        return anoDeIngreso;
    }

    public void setAnoDeIngreso(int anoDeIngreso) {
        this.anoDeIngreso = anoDeIngreso;
    }
}
