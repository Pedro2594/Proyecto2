package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Repuestos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepuestosDatos {

    public static List<Repuestos> LeerRepuestos (){
        List<Repuestos> listaDeRepuestos = new ArrayList<Repuestos>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT tipo, marca, cantidad, precio  FROM Clientes";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Repuestos repuestos = new Repuestos();

                repuestos.setTipo(rs.getString(1));
                repuestos.setMarca(rs.getString(2));
                repuestos.setCantidad(rs.getInt(3));
                repuestos.setPrecio(rs.getInt(4));
                listaDeRepuestos.add(repuestos);
            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeRepuestos;
    }

    public static String InsertarRepuestos (Repuestos repuestos){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Repuestos VALUES(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,repuestos.getTipo());
            ps.setString(2,repuestos.getMarca());
            ps.setInt(3,repuestos.getCantidad());
            ps.setInt(4,repuestos.getPrecio());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }


    public static String ActualizarRepuestos (Repuestos repuestos){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Clientes SET  Tipo = ?, Marca = ?, Cantidad = ? WHERE DNI = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,repuestos.getTipo());
            ps.setString(2, repuestos.getMarca());
            ps.setInt(3, repuestos.getCantidad());
            ps.setInt(4, repuestos.getPrecio());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }

    public static String EliminarRepuestos (Repuestos repuestos){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Clientes WHERE DNI = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, repuestos.getTipo());
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

    public static List<Repuestos> BuscarRepuestos (Repuestos repuestos) throws SQLException {
        List<Repuestos> listaRepuestos= new  ArrayList<Repuestos>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT tipo, marca, cantidad, precio  FROM Clientes WHERE UPPER(Nombre) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+repuestos.getTipo().toUpperCase()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Repuestos repuestosObjeto = new Repuestos();
                    repuestosObjeto.setTipo(rs.getString(1));
                    repuestosObjeto.setMarca(rs.getString(2));
                    repuestosObjeto.setCantidad(rs.getInt(3));
                    repuestosObjeto.setPrecio(rs.getInt(4));

                    listaRepuestos.add(repuestosObjeto);
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

        return listaRepuestos;
    }

}