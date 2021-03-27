package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Carro;
import Recursos.Clientes;
import Recursos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDatos {

    public static List<Producto> LeerProductos (){
        List<Producto> listaDeProductos = new ArrayList<Producto>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT Codigo, Nombre, Marca  FROM Productos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setMarca(rs.getString(3));
                listaDeProductos.add(producto);

            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeProductos;
    }

    public static String InsertarProducto (Producto producto) {
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Productos VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1,producto.getCodigo());
            ps.setString(2,producto.getNombre());
            ps.setString(3,producto.getMarca());

            ps.execute();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
        return null;
    }
    public static String ActualizarProducto (Producto producto) {
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Productos SET Codigo = ?, Nombre = ?, Marca = ? WHERE Codigo = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.execute();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return null;

    }
    public static String EliminarProducto (Producto producto){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Productos WHERE Codigo =? ";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;

    }
    public static List<Producto> BuscarProducto (Producto producto) throws SQLException {
        List<Producto> listaProductos = new  ArrayList<Producto>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT Codigo, Nombre, Marca FROM Productos WHERE UPPER(Codigo) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+producto.getCodigo()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Producto productoObjeto = new Producto();
                    productoObjeto.setCodigo(rs.getInt(1));
                    productoObjeto.setNombre(rs.getString(2));
                    productoObjeto.setMarca(rs.getString(3));

                   listaProductos.add(productoObjeto);
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

        return listaProductos;
    }





}
