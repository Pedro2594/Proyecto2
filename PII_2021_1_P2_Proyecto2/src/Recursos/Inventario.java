package Recursos;

public class Inventario {
    private long Codigo;
    private String Referencia;
    private int Cantidad;

    public Inventario(){}

    public Inventario(long Codigo, String Referencia,int Cantidad){
        this.Codigo = Codigo;
        this.Referencia = Referencia;
        this.Cantidad = Cantidad;
    }

    public long getCodigo() {
        return Codigo;
    }

    public void setCodigo(long codigo) {
        Codigo = codigo;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }


}
