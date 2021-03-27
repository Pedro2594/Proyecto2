package Recursos;

public class Mecanico {

    private long DNI; //DNI MECANICO
    private String Nombre;
    private String Direccion;
    private long   Telefono;
    private String email;
    public Mecanico(){}
    public Mecanico(long DNI, String Nombre, String Direccion,long Telefono,String email){
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.email = email;
    }
    public long getDNI() {
        return DNI;
    }

    public void setDNI(long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
       this.Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public long getTelefono() {
        return Telefono;
    }

    public void setTelefono(long telefono) {
        this.Telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
