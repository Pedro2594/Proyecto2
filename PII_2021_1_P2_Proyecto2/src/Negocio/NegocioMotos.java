package Negocio;

import Datos.Clientes.MotosDatos;
import Recursos.Motos;

import java.util.ArrayList;
import java.util.List;

public class NegocioMotos {

    public List<Motos> Leer(){
        List<Motos> listaMotos = new ArrayList<>();
        try {

            listaMotos = MotosDatos.LeerMotos();

        }catch (Exception e){
        }
        return listaMotos;
        //-----
    }

    public String Insertar(Motos motos){
        String respuesta = "Error";
        try{

            if (motos.getMarca().isEmpty()){
                throw new Exception("Error: La marca no debe estar vacia");
            }
            if (motos.getColor().isEmpty()){
                throw new Exception("Error: El color no puede quedar vacio");
            }
            if (motos.getPlaca().isEmpty()){
                throw new Exception("Error: La placa no puede quedar vacia");
            }
            if (motos.getReparacion().isEmpty()){
                throw new Exception("Error: La reparacion no puede quedar vacia");
            }


            respuesta = MotosDatos.InsertarMotos(motos);

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


    public void Eliminar(Motos motos){
        try {

            MotosDatos.EliminarMotos(motos);

        }catch (Exception e){
        }

    }


    public void Actualizar(Motos motos){
        try {

            MotosDatos.ActualizarMotos(motos);

        }catch (Exception e){
        }

    }
    public List<Motos> Buscar(Motos motos) throws Exception{
        List<Motos> listaMotos = new ArrayList<>();

        try {

            listaMotos = MotosDatos.BuscarMotos(motos);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaMotos;
    }

}