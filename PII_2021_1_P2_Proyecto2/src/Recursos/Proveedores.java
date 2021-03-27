package Recursos;

public class Proveedores {

    private String nombre;
    private String producto;
    private String garantia;
    private int    precio;


    public Proveedores(){
    }

    public Proveedores(String nombre, String producto, String garantia, int precio){
        this.nombre   = nombre;
        this.producto = producto;
        this.garantia = garantia;
        this.precio   = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}