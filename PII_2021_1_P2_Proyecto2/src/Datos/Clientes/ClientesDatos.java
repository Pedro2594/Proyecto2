package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Clientes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDatos {

    public static List<Clientes> LeerClientes (){
        List<Clientes> listaDeClientes = new ArrayList<Clientes>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT DNI, Nombre, Direccion, Telefono  FROM Clientes";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Clientes cliente = new Clientes();

                cliente.setDNI(rs.getLong(1));
                cliente.setNombre(rs.getString(2));
                cliente.setDireccion(rs.getString(3));
                cliente.setTelefono(rs.getLong(4));
                listaDeClientes.add(cliente);
            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeClientes;
    }

    public static String InsertarClientes (Clientes cliente){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Clientes VALUES(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1,cliente.getDNI());
            ps.setString(2,cliente.getNombre());
            ps.setString(3,cliente.getDireccion());
            ps.setLong(4,cliente.getTelefono());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }


    public static String ActualizarClientes (Clientes cliente){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Clientes SET DNI = ?, Nombre = ?, Direccion = ?, Telefono = ? WHERE DNI = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1,cliente.getDNI());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setLong(4, cliente.getTelefono());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }

    public static String EliminarClientes (Clientes cliente){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Clientes WHERE DNI = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, cliente.getDNI());
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

    public static List<Clientes> BuscarClientes (Clientes cliente) throws SQLException {
        List<Clientes> listaClientes= new  ArrayList<Clientes>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT DNI, Nombre, Direccion, Telefono  FROM Clientes WHERE UPPER(Nombre) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+cliente.getNombre().toUpperCase()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                  Clientes clienteObjeto = new Clientes();
                    clienteObjeto.setDNI(rs.getLong(1));
                    clienteObjeto.setNombre(rs.getString(2));
                    clienteObjeto.setDireccion(rs.getString(3));
                    clienteObjeto.setTelefono(rs.getLong(4));


                    listaClientes.add(clienteObjeto);
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

        return listaClientes;
    }



}
