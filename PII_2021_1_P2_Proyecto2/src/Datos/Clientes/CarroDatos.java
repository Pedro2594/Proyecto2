package Datos.Clientes;

import Conexion.Conexion;
import Recursos.Carro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDatos {

    public static List<Carro> LeerCarro() {
        List<Carro> listaDeCarros = new ArrayList<>();

        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT Marca, Modelo, Placa, Reparacion, Color  FROM Carros";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Carro carros = new Carro();
                carros.setMarca(rs.getString(1));
                carros.setModelo(rs.getString(2));
                carros.setPlaca(rs.getInt(3));
                carros.setReparacion(rs.getString(4));
                carros.setColor(rs.getString(5));

                listaDeCarros.add(carros);
            }
            cn.close();


        } catch (Exception e) {
        }
        return listaDeCarros;
    }

    public static String InsertarCarros(Carro carros) {
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Carros VALUES(?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql );
            ps.setString(1, carros.getMarca());
            ps.setString(2, carros.getModelo());
            ps.setString(3, String.valueOf(carros.getPlaca()));
            ps.setString(4, carros.getReparacion());
            ps.setString(5, carros.getColor());
            ps.execute();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
        return null;
    }


    public static String ActualizarCarros(Carro carros) {
        try {
            Connection cn = Conexion.ObtenerConexion();
            String sql = "UPDATE Carros SET Marca = ?, Modelo = ?, Placa = ?, Color= ?, Reparacion = ? WHERE Placa = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, carros.getMarca());
            ps.setString(2, carros.getModelo());
            ps.setString(3, String.valueOf(carros.getPlaca()));
            ps.setString(4, carros.getColor());
            ps.setString(5, carros.getReparacion());
            ps.execute();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return null;

    }


    public static String EliminarCarros (Carro carros){
        try{
            Connection cn = Conexion.ObtenerConexion();
            String sql = "DELETE FROM Carros WHERE Placa = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, carros.getPlaca());
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
    public static List<Carro> BuscarCarro (Carro carro) throws SQLException {
        List<Carro> listaCarros = new ArrayList<>();
        try {
            Connection cn = Conexion.ObtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT Marca, Modelo, Placa, Color, Reparacion  FROM Carros WHERE UPPER(Placa) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+carro.getPlaca()+"%");
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                do {
                    Carro carroObjeto = new Carro();
                    carroObjeto.setMarca(rs.getString(1));
                    carroObjeto.setModelo(rs.getString(2));
                    carroObjeto.setPlaca(rs.getInt(3));
                    carroObjeto.setColor(rs.getString(4));
                    carroObjeto.setReparacion(rs.getString(5));

                    listaCarros.add(carroObjeto);
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

        return listaCarros;
    }





}
