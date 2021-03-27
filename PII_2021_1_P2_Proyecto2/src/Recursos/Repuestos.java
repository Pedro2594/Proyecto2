package Recursos;

public class Repuestos {

    private String tipo;
    private String marca;
    private int    cantidad;
    private int    precio;


    public Repuestos() {
    }

    public Repuestos(String tipo, String marca, int cantidad, int precio){
        this.tipo     = tipo;
        this.marca    = marca;
        this.cantidad = cantidad;
        this.precio   = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}