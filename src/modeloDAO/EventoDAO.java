/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Evento;

/**
 *
 * @author kuroy
 */
public class EventoDAO {

    public static Boolean GUARDguardarAR(Connection conexion, Evento evento) throws SQLException {
        String query = "INSERT INTO evento (id, nombre, fecha_celebracion, hora, direccion, monto_abonado, monto_total, estado) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement consulta = conexion.prepareStatement(query);

        consulta.setInt(1, evento.getID());
        consulta.setString(2, evento.getNombre());
        consulta.setString(3, evento.getFecha_celebracion().toString());
        consulta.setInt(3, evento.getID());
        consulta.setString(4, evento.getDireccion_evento());
        consulta.setInt(5, evento.getMonto_abonado());
        consulta.setInt(6, evento.getMonto_total());
        consulta.setString(7, evento.getEstado_pago().toString());

        return consulta.execute();
    }

    public static Boolean actualizar(Connection conexion, Evento evento) throws SQLException {
        String query = "UPDATE evento SET id=?, nombre=?, responsable_id=?, direccion=?, monto_abonado=?, monto_total=?, estado=? WHERE id=?";
        PreparedStatement consulta = conexion.prepareStatement(query);

        consulta.setInt(1, evento.getID());
        consulta.setString(2, evento.getNombre());
        consulta.setInt(3, evento.getResponsable().getCedula());
        consulta.setString(4, evento.getDireccion_evento());
        consulta.setInt(5, evento.getMonto_abonado());
        consulta.setInt(6, evento.getMonto_total());
        consulta.setString(7, evento.getEstado_pago().toString());

        return consulta.executeUpdate() != 0;
    }

    public static Boolean eliminar(Connection conexion, Evento evento) throws SQLException {
        String query = "DELETE FROM evento WHERE id=?";       
        PreparedStatement consulta = conexion.prepareStatement(query);
        
        consulta.setInt(1, evento.getID());

        return consulta.execute();
    }

    public static Evento buscar(Connection conexion, Evento evento) throws SQLException {
            String query = "SELECT * FROM evento WHERE id=?";
            PreparedStatement consulta = conexion.prepareStatement(query);
            
            consulta.setInt(1, evento.getID());
            
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                //id=?, nombre=?, responsable_id=?, direccion=?, monto_abonado=?, monto_total=?, estado=?
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Integer responsable_id = rs.getInt("responsable_id");
                String direccion = rs.getString("direccion");
                Integer monto_abonado = rs.getInt("monto_abonado");
                Integer monto_total = rs.getInt("monto_total");
                String estado = rs.getString("estado");
            }
        return null;
    }
}
