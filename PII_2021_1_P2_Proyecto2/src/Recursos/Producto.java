package Recursos;

public class Producto {
private int Codigo;
private String Nombre;
private String Marca;

public Producto(){}
public Producto (int Codigo, String Nombre, String Marca)
{
    this.Codigo = Codigo;
    this.Nombre = Nombre;
    this.Marca  = Marca;
}

public int getCodigo ()
{
    return Codigo;
}

public void setCodigo (int Codigo)
{
    this.Codigo = Codigo;
}

public String getNombre ()
{
    return Nombre;
}

public void setNombre (String Nombre)
{
    this.Nombre = Nombre;
}

public String getMarca ()
{
    return Marca;
}
public void setMarca (String Marca)
{
    this.Marca = Marca;
}
}
