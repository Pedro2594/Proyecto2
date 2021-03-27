package Datos.Clientes;
import Conexion.Conexion;
import Recursos.Inventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDatos {
    public static List<Inventario> LeerInventario (){
        List<Inventario> listaDeInventario = new ArrayList<Inventario>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT Codigo,Referencia,Cantidad  FROM Inventario";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Inventario Inventarios = new Inventario();

                Inventarios.setCodigo(rs.getLong(1));
                Inventarios.setReferencia(rs.getString(2));
                Inventarios.setCantidad(rs.getInt(3));

            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeInventario;
    }

    public static String InsertarInventario (Inventario Inventarios){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Inventario VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1,Inventarios.getCodigo());
            ps.setString(2,Inventarios.getReferencia());
            ps.setString(3, String.valueOf(Inventarios.getCantidad()));
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }


    public static String ActualizarInventario (Inventario Inventarios){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Inventario SET Codigo = ?, Referencia = ?, Cantidad = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1,Inventarios.getCodigo());
            ps.setString(2, Inventarios.getReferencia());
            ps.setString(3, String.valueOf(Inventarios.getCantidad()));
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }

    public static String EliminarInventario (Inventario Invetarios){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Inventario WHERE Codigo = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, Invetarios.getCodigo());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
        //-----
    }

    public static List<Inventario> BuscarInventario (Inventario Inventarios) throws SQLException {
        List<Inventario> listaInventario= new  ArrayList<Inventario>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT Codigo,Referencia,Cantidad FROM Inventario WHERE UPPER(Referencia) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+Inventarios.getReferencia().toUpperCase()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Inventario InventarioObjeto = new Inventario();
                    InventarioObjeto.setCodigo(rs.getLong(1));
                    InventarioObjeto.setReferencia(rs.getString(2));
                    InventarioObjeto.setCantidad(rs.getInt(3));

                    listaInventario.add(InventarioObjeto);
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

        return listaInventario;
    }
}
