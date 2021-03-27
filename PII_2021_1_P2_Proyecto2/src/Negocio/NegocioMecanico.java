package Negocio;
import Datos.Clientes.MecanicoDatos;
import Recursos.Mecanico;

import java.util.ArrayList;
import java.util.List;

public class NegocioMecanico {
    public List<Mecanico> Leer(){
        List<Mecanico> listaMecanico = new ArrayList<>();
        try {
            listaMecanico = MecanicoDatos.LeerMecanicos();


        }catch (Exception e){
        }
        return listaMecanico;
        //-----
    }


    public String Insertar(Mecanico mecanico){
        String respuesta = "Error";
        try{

            if (mecanico.getNombre().isEmpty()){
                throw new Exception("Error: El nombre no debe estar vacio");
            }
            if (mecanico.getDNI()<=0){
                throw new Exception("Error: El DNI no debe ser menor a 0");
            }
            if (mecanico.getDireccion( ).isEmpty())
            {
                throw new Exception("Error : La direccion no debe estar vacia" );
            }
            if(mecanico.getTelefono()<=0)
            {
                throw new Exception("Error : El numero no debe estar vacio");
            }
            if(mecanico.getEmail().isEmpty())
            {
                throw new Exception("Error: El email no debe estar vacio");

            }
            respuesta = MecanicoDatos.InsertarMecanicos(mecanico);

            if (respuesta == null){
                respuesta = "Guardado exitosamente";
            }

        }catch (Exception e){
            respuesta = e.getMessage();
        }

        return respuesta;

    }


    public void Eliminar(Mecanico mecanico){
        try {
            MecanicoDatos.EliminarMecanico(mecanico);


        }catch (Exception e){
        }

    }
    public void Actualizar(Mecanico mecanico){
        try {
            MecanicoDatos.ActualizarMecanicos(mecanico);

        }catch (Exception e){
        }

    }

    public List<Mecanico> Buscar(Mecanico mecanico) throws Exception{
        List<Mecanico> listaMecanico = new ArrayList<>();

        try {
            listaMecanico = MecanicoDatos.BuscarMecanico(mecanico);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaMecanico;
    }



}
