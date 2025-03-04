package Datas;

public class Administrativo extends Persona {
   
    private String cargo;

    public Administrativo(int id, String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String contrasena, String numTlf, String escuela, String cargo) {
        super(id, nombre, apellido, fechaDeNacimiento, edad, sexo, CI, correo, contrasena, numTlf,escuela);
        this.cargo = cargo;
    } 

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
