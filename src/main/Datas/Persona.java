package Datas;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String fechaDeNacimiento;
    private int edad;
    private String sexo;
    private String CI;
    private String correo;
    private String Contrasenia;
    private String  numTlf;
    private String escuela;

    public Persona(int id, String nombre, String apellido, String fechaDeNacimiento, int edad, String sexo, String CI, String correo, String Contrasenia, String numTlf, String escuela) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.CI = CI;
        this.correo = correo;
        this.Contrasenia = Contrasenia;
        this.numTlf = numTlf;
        this.escuela = escuela;
        
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }    

    public String getNumTlf() {
        return numTlf;
    }   

    public void setNumTlf(String numTlf) {
        this.numTlf = numTlf;
    }
    
    public String getEscuela() {
        return escuela;
    }
    
    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
}
