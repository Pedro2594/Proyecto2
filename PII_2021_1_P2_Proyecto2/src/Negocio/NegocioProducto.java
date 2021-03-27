package Negocio;

import Datos.Clientes.ClientesDatos;
import Datos.Clientes.ProductoDatos;
import Recursos.Clientes;
import Recursos.Producto;

import java.util.ArrayList;
import java.util.List;

public class NegocioProducto {


    public List<Producto> Leer(){
        List<Producto> listaProductos = new ArrayList<>();
        try {
          listaProductos = ProductoDatos.LeerProductos();


        }catch (Exception e){
        }
        return listaProductos;

    }

    public String Insertar(Producto producto){
        String respuesta = "Error";
        try{
           if (producto.getCodigo()<=0)
           {
               throw new Exception("Error : El codigo no debe estar vacio");
           }
           if(producto.getNombre().isEmpty())
           {
               throw new Exception("Error: El nombre no debe estar vacio ");
           }
           if(producto.getMarca().isEmpty())
           {
               throw new Exception("Error : La marca no debe estar vacia");
           }
         respuesta = ProductoDatos.InsertarProducto(producto);

            if (respuesta == null){
                respuesta = "Guardado exitosamente";
            }

        }catch (Exception e){
            respuesta = e.getMessage();
        }

        return respuesta;

    }


    public void Eliminar(Producto producto){
        try {
           ProductoDatos.EliminarProducto(producto);

        }catch (Exception e){
        }

    }

    public void Actualizar(Producto producto){
        try {
          ProductoDatos.ActualizarProducto(producto);


        }catch (Exception e){
        }

    }
    public List<Producto> Buscar(Producto producto) throws Exception{
        List<Producto> listaProductos = new ArrayList<>();

        try {
             listaProductos = ProductoDatos.BuscarProducto(producto);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaProductos;
    }

}
