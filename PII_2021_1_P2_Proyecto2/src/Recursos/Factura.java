package Recursos;

import java.util.Date;

public class Factura {
    private int     Codigo;

    private long    DNICliente;
    private String  NombreProducto;
    private int     PrecioProducto;
    private int     Cantidad;


    public Factura (){

    }
 public Factura (int Codigo, long DNICliente, String NombreProducto, int PrecioProducto, int Cantidad)
 {
     this.Codigo         = Codigo;
     this.DNICliente     = DNICliente;
     this.PrecioProducto = PrecioProducto;
     this.NombreProducto = NombreProducto;
     this.Cantidad       = Cantidad;
 }
    public int getCodigo ()
    {
        return Codigo;
    }
    public void setCodigo (int Codigo){
        this.Codigo = Codigo;

    }


    public long getDNICliente ()
    {
        return DNICliente;
    }
    public void setDNICliente(long DNICliente)
    {
        this.DNICliente = DNICliente;
    }

    public String getNombreProducto ()
    {
        return NombreProducto;
    }
    public void setNombreProducto(String NombreProducto)
    {
        this.NombreProducto = NombreProducto;
    }

    public int getPrecioProducto ()
    {
        return PrecioProducto;
    }
    public void setPrecioProducto (int PrecioProducto)
    {
        this.PrecioProducto = PrecioProducto;
    }

    public int getCantidad ()
    {
        return Cantidad;
    }
    public void setCantidad (int Cantidad)
    {
        this.Cantidad = Cantidad;
    }


}
