package Negocio;

import Datos.Clientes.RepuestosDatos;
import Recursos.Repuestos;

import java.util.ArrayList;
import java.util.List;

public class NegocioRepuestos {

    public List<Repuestos> Leer(){
        List<Repuestos> listaRepuestos = new ArrayList<>();
        try {

            listaRepuestos = RepuestosDatos.LeerRepuestos();

        }catch (Exception e){
        }
        return listaRepuestos;
        //-----
    }

    public String Insertar(Repuestos repuestos){
        String respuesta = "Error";
        try{

            if (repuestos.getTipo().isEmpty()){
                throw new Exception("Error: El tipo no debe estar vacio");
            }
            if (repuestos.getMarca().isEmpty()){
                throw new Exception("Error: La marca no debe estar vacia");
            }
            if (repuestos.getCantidad( )<=0)
            {
                throw new Exception("Error : La cantidad no debe estar vacia" );
            }
            if(repuestos.getPrecio()<=0)
            {
                throw new Exception("Error : El precio no debe estar vacio");
            }


            respuesta = RepuestosDatos.InsertarRepuestos(repuestos);

            if (respuesta == null){
                respuesta = "Guardado exitosamente";
            }

        }catch (Exception e){
            respuesta = e.getMessage();
        }
//        finally {
        return respuesta;
//        }
        //-----
    }


    public void Eliminar(Repuestos repuestos){
        try {

            RepuestosDatos.EliminarRepuestos(repuestos);

        }catch (Exception e){
        }

    }


    public void Actualizar(Repuestos repuestos){
        try {

            RepuestosDatos.ActualizarRepuestos(repuestos);

        }catch (Exception e){
        }

    }
    public List<Repuestos> Buscar(Repuestos repuestos) throws Exception{
        List<Repuestos> listaRepuestos = new ArrayList<>();

        try {

            listaRepuestos = RepuestosDatos.BuscarRepuestos(repuestos);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaRepuestos;
    }
}
