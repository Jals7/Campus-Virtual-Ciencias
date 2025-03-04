package Datas;

public class Profesor extends Persona {
   
    private int anoDeIngreso;
    private String ultimaMateriaDada;
    private String cargo;

    public Profesor(int id, String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String numTlf, String escuela, int anoDeIngreso, String ultimaMateriaDada){//}, String cargo) {
        super(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, numTlf,escuela);
        
        this.anoDeIngreso = anoDeIngreso;
        this.ultimaMateriaDada = ultimaMateriaDada;
        //this.cargo = cargo;
    }

    public int getAnoDeIngreso() {
        return anoDeIngreso;
    }

    public void setAnoDeIngreso(int anoDeIngreso) {
        this.anoDeIngreso = anoDeIngreso;
    }

    public String getUltimaMateriaDada() {
        return ultimaMateriaDada;
    }

    public void setUltimaMateriaDada(String ultimaMateriaDada) {
        this.ultimaMateriaDada = ultimaMateriaDada;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
