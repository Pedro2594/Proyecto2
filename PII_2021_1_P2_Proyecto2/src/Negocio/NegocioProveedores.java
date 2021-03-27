package Negocio;

import Datos.Clientes.ProveedoresDatos;
import Recursos.Proveedores;

import java.util.ArrayList;
import java.util.List;

public class NegocioProveedores {

    public List<Proveedores> Leer(){
        List<Proveedores> listaProveedores = new ArrayList<>();
        try {

            listaProveedores = ProveedoresDatos.LeerProveedores();

        }catch (Exception e){
        }
        return listaProveedores;
        //-----
    }

    public String Insertar(Proveedores proveedores){
        String respuesta = "Error";
        try{

            if (proveedores.getNombre().isEmpty()){
                throw new Exception("Error: El nombre no debe estar vacio");
            }
            if (proveedores.getProducto().isEmpty()){
                throw new Exception("Error: El producto no puede quedar vacio");
            }
            if (proveedores.getGarantia().isEmpty()){
                throw new Exception("Error: La garantia no puede quedar vacia");
            }
            if (proveedores.getPrecio()<=0){
                throw new Exception("Error: El precio no debe estar vacio");
            }
            respuesta = ProveedoresDatos.InsertarProveedores(proveedores);

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


    public void Eliminar(Proveedores proveedores){
        try {

            ProveedoresDatos.EliminarProveedores(proveedores);

        }catch (Exception e){
        }

    }


    public void Actualizar(Proveedores proveedores){
        try {

            ProveedoresDatos.ActualizarProveedores(proveedores);

        }catch (Exception e){
        }

    }
    public List<Proveedores> Buscar(Proveedores proveedores) throws Exception{
        List<Proveedores> listaProveedores = new ArrayList<>();

        try {

            listaProveedores = ProveedoresDatos.BuscarProveedores(proveedores);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaProveedores;
    }

}
