package Recursos;

public class Recursos {

    private int Id;
    private String Nombre;
    private String Direccion;
    private long Telefono;

    public Recursos(){}

    public  Recursos(int id, String nombre, String Direccion, int Telefono){
        this.Id = id;
        this.Nombre = nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }
    public int getId(){
        return Id;
    }
    public void setId(int id){
        this.Id = id;
    }

    public String getNombre(){
        return Nombre;
    }
    public void setNombre(String nombre){
        this.Nombre = nombre;
    }

    public String getDireccion (){return Direccion;}
    public void setDireccion (String Direccion){this.Direccion = Direccion;}

    public long getTelefono()
    {
        return Telefono;
    }
     public void setTelefono (long Telefono)
     {
         this.Telefono = Telefono;
     }
    @Override
    public String toString(){
        return Nombre;
    }

}
