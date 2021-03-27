package Recursos;

public class Motos {
    private String Marca;
    private String Color;
    private String Placa;
    private String Reparacion;


    public Motos(){
    }
    public Motos(String Marca, String Color, String Placa, String Reparacion){
        this.Marca     = Marca;
        this.Color     = Color;
        this.Placa = Placa;
        this.Reparacion = Reparacion;

    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getReparacion() {
        return Reparacion;
    }

    public void setReparacion(String Reparacion) {
        this.Reparacion = Reparacion;
    }
}