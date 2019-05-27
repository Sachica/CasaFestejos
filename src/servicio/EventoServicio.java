/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Evento;

/**
 *
 * @author kuroy
 */
public class EventoServicio {
    public void guardar(Connection conexion, Evento evento) throws SQLException{
        try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO responsable (id, nombre, responsableid, direccion, monto_abonado, monto_total, estado)"
                    +"VALUES(?, ?, ?, ?, ?, ?, ?)" );
            
            consulta.setString(1, evento.getID());
            consulta.setString(2, evento.getNombre());
            consulta.setString(3, evento.getResponsable().getCedula());
            consulta.setString(4, evento.getDireccion_evento());
            consulta.setInt(5, evento.getMonto_abonado());
            consulta.setInt(6, evento.getMontoTotal());
            consulta.setString(7, evento.getEstado_pago().toString());
            consulta.executeUpdate();
            
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
    
    public void actualizar(Connection conexion, Evento evento) throws SQLException{        
        try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("UPDATE evento SET id=?, nombre=?, responsableid=?, direccion=?, monto_abonado=?, monto_total=?, estado=?"
                    +"WHERE id=?" );
            
            consulta.setString(1, evento.getID());
            consulta.setString(2, evento.getNombre());
            consulta.setString(3, evento.getResponsable().getCedula());
            consulta.setString(4, evento.getDireccion_evento());
            consulta.setInt(5, evento.getMonto_abonado());
            consulta.setInt(6, evento.getMontoTotal());
            consulta.setString(7, evento.getEstado_pago().toString());
            consulta.executeUpdate();
            
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
    
    public static void eliminar(Connection conexion, String id) throws SQLException{
      try{
          
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("DELETE FROM evento "
                 + "WHERE id=?");        
         consulta.setString(3, id);
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
    
    public static Evento buscar(Connection conexion, String id) throws SQLException{
        ResultSet rs = null;
        try{         
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("SELECT * FROM evento "
                    + "WHERE id=?");        
            consulta.setString(3, id);
            rs = consulta.executeQuery();
        }catch(SQLException ex){
         throw new SQLException(ex);
        }
        return null;
    }
}
