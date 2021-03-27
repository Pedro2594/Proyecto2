package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDatos {

    public static List<Factura> LeerFactura() {
        List<Factura> listaDeFactura = new ArrayList<Factura>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT  Codigo, DNICliente, NombreProducto, PrecioProducto, Cantidad FROM Facturas";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Factura factura = new Factura();
                factura.setCodigo(rs.getInt(1));
                factura.setDNICliente(rs.getLong(2));
                factura.setNombreProducto(rs.getString(3));
                factura.setPrecioProducto(rs.getInt(4));
                factura.setCantidad(rs.getInt(5));
                listaDeFactura.add(factura);
            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeFactura;
    }


    public static String InsertarFacturas (Factura factura){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Facturas VALUES(?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(   1,   factura.getCodigo());
            ps.setLong(  2,   factura.getDNICliente());
            ps.setString(3,   factura.getNombreProducto());
            ps.setInt(    4,  factura.getPrecioProducto());
            ps.setInt(    5,  factura.getCantidad());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }


    public static String ActualizarFacturas (Factura factura){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Facturas SET Codigo = ?,  DNICliente = ?, NombreProducto = ?, PrecioProducto = ?, Cantidad = ?  WHERE Codigo = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(   1,   factura.getCodigo());
            ps.setLong(  2,   factura.getDNICliente());
            ps.setString(3,   factura.getNombreProducto());
            ps.setInt(    4,  factura.getPrecioProducto());
            ps.setInt(    5,  factura.getCantidad());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }


    public static String EliminarFacturas (Factura factura){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Facturas WHERE Codigo = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, factura.getCodigo());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;

    }


    public static List<Factura> BuscarFactura (Factura factura) throws SQLException {
        List<Factura> listaFacturas = new  ArrayList<Factura>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();

            String sql = "SELECT Codigo, DNICliente, NombreProducto, PrecioProducto, Cantidad FROM Facturas WHERE UPPER(Codigo) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, Long.parseLong(+factura.getDNICliente()+"%"));
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Factura facturaObjeto = new Factura();
                    facturaObjeto.setCodigo(rs.getInt(1));
                    facturaObjeto.setDNICliente(rs.getLong(2));
                    facturaObjeto.setNombreProducto(rs.getString(3));
                    facturaObjeto.setPrecioProducto(rs.getInt(4));
                    facturaObjeto.setCantidad(rs.getInt(5));

                    listaFacturas.add(facturaObjeto);
                }while (rs.next());
            }else{
                throw new SQLException("Error no se encuentra coincidencia");
            }

            cn.close();
            rs.close();
            ps.close();

        }catch (SQLException e){
            throw new SQLException(e.getCause());
        }

        return listaFacturas;
    }

}



