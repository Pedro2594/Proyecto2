package Negocio;

import Datos.Clientes.CarroDatos;
import Datos.Clientes.ClientesDatos;
import Recursos.Carro;
import Recursos.Clientes;

import java.util.ArrayList;
import java.util.List;

public class NegocioCarro {

    public List<Carro> Leer(){
        List<Carro> listaCarros = new ArrayList<>();
        try {

            listaCarros = CarroDatos.LeerCarro();

        }catch (Exception e){
        }
        return listaCarros;

    }


    public String Insertar(Carro carros){
        String respuesta = "Error";
        try{
            if (carros.getModelo().isEmpty()) {

                throw new Exception("Error: El modelo no debe estar vacio");
            }

            if ( carros.getMarca().isEmpty()) {
                throw new Exception("Error : La marca no debe estar vacia");
            }

            if (carros.getColor().isEmpty())
            {
                throw new Exception("Error : El color no debe estar vacio");
            }

            if(carros.getPlaca()<=0) {

                throw new Exception("La placa no debe estar vacia");
            }

            if(carros.getReparacion().isEmpty()) {
                throw new Exception("Error : La descripcion de la reparacion no debe estar vacia");
            }
            respuesta = CarroDatos.InsertarCarros(carros);

            if (respuesta == null){
                respuesta = "Guardado exitosamente";
            }

        }catch (Exception e){
            respuesta = e.getMessage();
        }

        return respuesta;
       }

    public void Eliminar(Carro carros){
        try {

            CarroDatos.EliminarCarros(carros);

        }catch (Exception e){
        }

    }

    public void Actualizar(Carro carros ){
        try {
              CarroDatos.ActualizarCarros(carros);


        }catch (Exception e){
        }

    }

    public List<Carro> Buscar(Carro carros) throws Exception{
        List<Carro> listaCarros = new ArrayList<>();

        try {
            listaCarros = CarroDatos.BuscarCarro(carros);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaCarros;
    }

}


