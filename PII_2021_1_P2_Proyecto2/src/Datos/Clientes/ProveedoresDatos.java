package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Proveedores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresDatos {

    public static List<Proveedores> LeerProveedores () {
        List<Proveedores> listaDeProveedores = new ArrayList<Proveedores>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT Nombre, Producto, Garantia, Precio  FROM Proveedores";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Proveedores proveedores = new Proveedores();
                proveedores.setNombre(rs.getString(1));
                proveedores.setProducto(rs.getString(2));
                proveedores.setGarantia(rs.getString(3));
                proveedores.setPrecio(rs.getInt(4));
                listaDeProveedores.add(proveedores);
            }
            cn.close();
        }catch (Exception e){
        }
        return listaDeProveedores;
    }
    public static String InsertarProveedores (Proveedores proveedores){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Proveedores VALUES(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,proveedores.getNombre());
            ps.setString(2,proveedores.getProducto());
            ps.setString(3,proveedores.getGarantia());
            ps.setLong(4,proveedores.getPrecio());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }
    public static String ActualizarProveedores (Proveedores proveedores){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Proveedores SET  Nombre = ?, producto = ?, garantia = ?, precio = ? WHERE Nombre = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,proveedores.getNombre());
            ps.setString(2, proveedores.getProducto());
            ps.setString(3, proveedores.getGarantia());
            ps.setLong(4, proveedores.getPrecio());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }
    public static String EliminarProveedores (Proveedores proveedores){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Proveedores WHERE Nombre = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, proveedores.getNombre());
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

    public static List<Proveedores> BuscarProveedores (Proveedores proveedores) throws SQLException {
        List<Proveedores> listaProveedores = new  ArrayList<Proveedores>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT  Nombre, producto, garantia  FROM Proveedores WHERE UPPER(Nombre) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+proveedores.getNombre().toUpperCase()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Proveedores proveedoresObjeto = new Proveedores();
                    proveedoresObjeto.setNombre(rs.getString(1));
                    proveedoresObjeto.setGarantia(rs.getString(2));
                    proveedoresObjeto.setProducto(rs.getString(3));
                    proveedoresObjeto.setPrecio(rs.getInt(4));

                    listaProveedores.add(proveedoresObjeto);
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

        return listaProveedores;
    }

}