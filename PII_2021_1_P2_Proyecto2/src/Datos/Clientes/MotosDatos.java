package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Motos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotosDatos {

    public static List<Motos> LeerMotos (){
        List<Motos> listaDeMotos = new ArrayList<Motos>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT Marca, Color, Placa, Reparacion  FROM Motos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Motos motos = new Motos();
                motos.setMarca(rs.getString(1));
                motos.setColor(rs.getString(2));
                motos.setPlaca(rs.getString(3));
                motos.setReparacion(rs.getString(4));
                listaDeMotos.add(motos);
            }
            cn.close();


        }catch (Exception e){
        }
        return listaDeMotos;
    }

    public static String InsertarMotos (Motos motos){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Motos VALUES(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,motos.getMarca());
            ps.setString(2,motos.getColor());
            ps.setString(3,motos.getPlaca());
            ps.setString(4,motos.getReparacion());
             ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
        return null;
    }


    public static String ActualizarMotos (Motos motos){
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Motos SET  Marca = ?, Color = ?, Placa = ?, Reparacion = ? WHERE Placa = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,motos.getMarca());
            ps.setString(2, motos.getColor());
            ps.setString(3, motos.getPlaca());
            ps.setString(4, motos.getReparacion());
            ps.execute();
            ps.close();
            cn.close();

        }catch (Exception e){
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
        return null;

    }

    public static String EliminarMotos (Motos motos){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Motos WHERE Placa = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, motos.getPlaca());
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

    public static List<Motos> BuscarMotos (Motos motos) throws SQLException {
        List<Motos> listaMotos = new  ArrayList<Motos>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
//
            String sql = "SELECT  Marca, Color, Placa, Reparacion  FROM Motos WHERE UPPER(Placa) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+motos.getMarca().toUpperCase()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Motos motosObjeto = new Motos();
                    motosObjeto.setMarca(rs.getString(1));
                    motosObjeto.setColor(rs.getString(2));
                    motosObjeto.setPlaca(rs.getString(3));
                    motosObjeto.setReparacion(rs.getString(4));

                    listaMotos.add(motosObjeto);
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

        return listaMotos;
    }


}

