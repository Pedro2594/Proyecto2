package Recursos;

import java.util.Date;
import java.util.List;

public class Clientes {


    private long   DNI;
    private String Nombre;
    private String Direccion;
    private long   Telefono;

    public Clientes (){}
    public Clientes ( long  DNI, String nombre, String Direccion, long Telefono){
        this.DNI                = DNI;
        this.Nombre             = Nombre;
        this.Direccion          = Direccion;
        this.Telefono           = Telefono;
    }



    public long getDNI(){
        return DNI;
    }
    public void setDNI(long dni){
        this.DNI = dni;
    }


    public String getNombre(){
        return Nombre;
    }
    public void setNombre(String nombre){
        this.Nombre = nombre;
    }

    public String getDireccion()
    {
        return Direccion;
    }
    public void setDireccion(String Direccion)
    {
        this.Direccion = Direccion;
    }

    public long getTelefono ()
    {
        return Telefono;
    }
    public void  setTelefono(long Telefono)
    {
        this.Telefono = Telefono;
    }


}
