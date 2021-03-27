package Negocio;
import  Datos.Clientes.InventarioDatos;
import  Recursos.Inventario;


import java.util.ArrayList;
import java.util.List;

public class NegocioInventario {
    public List<Inventario> Leer(){
        List<Inventario> listaInventario = new ArrayList<>();
        try {
            listaInventario = InventarioDatos.LeerInventario();


        }catch (Exception e){
        }
        return listaInventario;
        //-----
    }


    public String Insertar(Inventario inventario){
        String respuesta = "Error";
        try{

            if (inventario.getCodigo()<=0){
                throw new Exception("Error: El codigo no debe estar vacio");
            }
            if (inventario.getReferencia().isEmpty())
            {
                throw new Exception("Error: La referencia no debe estar vacia");
            }
            if (inventario.getCantidad( )<=0)
            {
                throw new Exception("Error : La Cantidad no debe ser menor a 0." );
            }

            respuesta = InventarioDatos.InsertarInventario(inventario);

            if (respuesta == null){
                respuesta = "Guardado exitosamente";
            }

        }catch (Exception e){
            respuesta = e.getMessage();
        }

        return respuesta;

    }


    public void Eliminar(Inventario inventario){
        try {
            InventarioDatos.EliminarInventario(inventario);


        }catch (Exception e){
        }

    }
    public void Actualizar(Inventario inventario){
        try {
            InventarioDatos.ActualizarInventario(inventario);

        }catch (Exception e){
        }

    }

    public List<Inventario> Buscar(Inventario inventario) throws Exception{
        List<Inventario> listaInventario = new ArrayList<>();

        try {
            listaInventario = InventarioDatos.BuscarInventario(inventario);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaInventario;
    }



}