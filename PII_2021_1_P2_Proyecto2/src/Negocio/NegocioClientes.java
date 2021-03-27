package Negocio;

import Datos.Clientes.ClientesDatos;
import Recursos.Clientes;

import java.util.ArrayList;
import java.util.List;

public class NegocioClientes {
    public List<Clientes> Leer(){
        List<Clientes> listaClientes = new ArrayList<>();
        try {

           listaClientes = ClientesDatos.LeerClientes();

        }catch (Exception e){
        }
        return listaClientes;
        //-----
    }

    public String Insertar(Clientes clientes){
        String respuesta = "Error";
        try{

            if (clientes.getNombre().isEmpty()){
                throw new Exception("Error: El nombre no debe estar vacio");
            }
            if (clientes.getDNI()<=0){
                throw new Exception("Error: El DNI no debe ser menor a 0");
            }
          if (clientes.getDireccion( ).isEmpty())
          {
              throw new Exception("Error : La direccion no debe estar vacia" );
          }
          if(clientes.getTelefono()<=0)
          {
              throw new Exception("Error : El numero no debe estar vacio");
          }


            respuesta = ClientesDatos.InsertarClientes(clientes);

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


    public void Eliminar(Clientes cliente){
        try {

            ClientesDatos.EliminarClientes(cliente);

        }catch (Exception e){
        }

    }


    public void Actualizar(Clientes cliente){
        try {

            ClientesDatos.ActualizarClientes(cliente);

        }catch (Exception e){
        }

    }
    public List<Clientes> Buscar(Clientes clientes) throws Exception{
        List<Clientes> listaClientes = new ArrayList<>();

        try {

            listaClientes = ClientesDatos.BuscarClientes(clientes);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaClientes;
    }

}
