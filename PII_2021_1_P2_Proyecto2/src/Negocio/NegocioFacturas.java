package Negocio;

import Datos.Clientes.FacturaDatos;
import Recursos.Factura;

import java.util.ArrayList;
import java.util.List;

public class NegocioFacturas {

    public List<Factura> Leer(){
        List<Factura> listafacturas = new ArrayList<>();
        try {

            listafacturas = FacturaDatos.LeerFactura();

        }catch (Exception e){
        }
        return listafacturas;
    }


    public String Insertar(Factura factura){
        String respuesta = "Error";
        try{
            if (factura.getCodigo()<=0){
                throw new Exception("Error: el codigo no debe ser menor a 0");
            }

            if (factura.getDNICliente()<=0){
                throw new Exception("Error: El DNI no debe ser menor a 0");
            }
            if (factura.getNombreProducto().isEmpty()){
                throw new Exception("Error: El nombre no debe estar vacio");
            }
           if(factura.getPrecioProducto()<=0)
           {
               throw new Exception("Error : El Precio no debe estar vacio");
           }
           if(factura.getCantidad()<=0)
           {
               throw new Exception("Error : La cantidad no debe estar vacia" );
           }

            respuesta = FacturaDatos. InsertarFacturas(factura);

            if (respuesta == null){
                respuesta = "Guardado exitosamente";
            }

        }catch (Exception e){
            respuesta = e.getMessage();
        }

        return respuesta;

    }

    public void Actualizar (Factura factura){
        try {

            FacturaDatos.ActualizarFacturas(factura);

        }catch (Exception e){
        }
    }

    public void Eliminar(Factura factura){
        try {

            FacturaDatos.EliminarFacturas(factura);

        }catch (Exception e){
        }
    }

    public List<Factura> Buscar(Factura factura) throws Exception{
        List<Factura> listaFacturas = new ArrayList<>();

        try {

            listaFacturas = FacturaDatos.BuscarFactura(factura);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return listaFacturas;
    }
}
