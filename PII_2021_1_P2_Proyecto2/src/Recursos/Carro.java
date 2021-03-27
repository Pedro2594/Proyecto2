package Recursos;

public class Carro {
    private String Marca;
    private String Modelo;
    private int Placa;
    private String Color;
    private String Reparacion;
    public Carro (){}
    public Carro ( String Marca, String Modelo, int Placa, String Color, String Reparacion)
    {
        this.Marca  = Marca;
        this.Modelo = Modelo;
        this.Placa  = Placa;
        this.Color  = Reparacion;
        this.Color  = Color;
    }

    public String getMarca ()
    {
        return Marca;
    }
    public void setMarca (String Modelo)
    {
        this.Marca = Marca;
    }

    public String getModelo ()
    {
        return Modelo;
    }
    public void setModelo (String Modelo)
    {
        this.Modelo = Modelo;
    }

    public int getPlaca ()
    {
        return Placa;
    }
    public void setPlaca (int Placa)
    {
        this.Placa = Placa;
    }

    public String getColor ()
    {
       return Color;
    }
    public void setColor (String Color)
    {
        this.Color = Color;
    }

    public String getReparacion ()
    {
        return Reparacion;
    }
    public void setReparacion (String Reparacion)
    {
        this.Reparacion = Reparacion;
    }

}
