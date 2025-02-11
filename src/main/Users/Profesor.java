package Users;

public class Profesor extends Personas {
    private String escuela;
    private int anoDeIngreso;
    private String ultimaMateriaDada;
    private String cargo;

    public Profesor(String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String roll, String escuela, int anoDeIngreso, String ultimaMateriaDada, String cargo) {
        super(nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, roll);
        this.escuela = escuela;
        this.anoDeIngreso = anoDeIngreso;
        this.ultimaMateriaDada = ultimaMateriaDada;
        this.cargo = cargo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
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
