package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Mecanico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDatos {
    public static List<Mecanico> LeerMecanicos (){
        List<Mecanico> listaDeMecanicos = new ArrayList<Mecanico>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT DNI, Nombre, Direccion, Telefono, Email FROM Mecanicos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Mecanico mecanico = new Mecanico();
                mecanico.setDNI(rs.getLong(1));
                mecanico.setNombre(rs.getString(2));
                mecanico.setDireccion(rs.getString(3));
                mecanico.setTelefono(rs.getLong(4));
                mecanico.setEmail(rs.getString(5));
                listaDeMecanicos.add(mecanico);
            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeMecanicos;
    }

    public static String InsertarMecanicos (Mecanico mecanico){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Mecanicos VALUES(?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, mecanico.getDNI());
            ps.setString(2, mecanico.getNombre());
            ps.setString(3, mecanico.getDireccion());
            ps.setLong(4,mecanico.getTelefono());
            ps.setString(5,mecanico.getEmail());
            ps.execute();
            ps.close();
            cn.close();


        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }



    public static String ActualizarMecanicos (Mecanico mecanico){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Mecanicos SET DNI = ?, Nombre = ?, Direccion = ?, Telefono = ?, Email=? WHERE DNI = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, mecanico.getDNI());
            ps.setString(2, mecanico.getNombre());
            ps.setString(3, mecanico.getDireccion());
            ps.setLong(4,mecanico.getTelefono());
            ps.setString(5,mecanico.getEmail());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }


    public static String EliminarMecanico (Mecanico mecanico){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Mecanicos WHERE DNI = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, mecanico.getDNI());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }

    public static List<Mecanico> BuscarMecanico (Mecanico mecanico) throws SQLException {
        List<Mecanico> listaMecanicos= new  ArrayList<Mecanico>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT DNI, Nombre, Direccion, Telefono, Email  FROM Mecanicos WHERE UPPER(DNI) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1, Long.parseLong("%"+ mecanico.getDNI()+"%"));
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {

                    Mecanico mecanicoObjeto = new Mecanico();

                    mecanicoObjeto.setDNI(rs.getLong(1));
                    mecanicoObjeto.setNombre(rs.getString(2));
                    mecanicoObjeto.setDireccion(rs.getString(3));
                    mecanicoObjeto.setTelefono(rs.getLong(4));
                    mecanicoObjeto.setEmail(rs.getString(5));
                    listaMecanicos.add(mecanicoObjeto);
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

        return listaMecanicos;
    }

}
