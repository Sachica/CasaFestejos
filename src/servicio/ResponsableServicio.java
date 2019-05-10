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
import modelo.*;

/**
 *
 * @author usuario
 */
public class ResponsableServicio {
    
    public void guardar(Connection conexion, Responsable persona) throws SQLException{
        
        try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO responsable (nombre, apellidos, cedula, telefono, e_mail)"
                    +"VALUES(?, ?, ?, ?, ?)" );
            
            consulta.setString(1, persona.getNombre());
            consulta.setString(2, persona.getApellidos());
            consulta.setString(3, persona.getCedula());
            consulta.setString(4, persona.getTelefono());
            consulta.setString(5, persona.getE_mail());
            consulta.executeUpdate();
            
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
    
    public void actualizar(Connection conexion, Responsable persona) throws SQLException{
        
        try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("UPDATE responsable SET nombre=?, apellidos=?, cedula=?, telefono=?, e_mail=?"
                    +"WHERE cedula=?" );
            
            consulta.setString(1, persona.getNombre());
            consulta.setString(2, persona.getApellidos());
            consulta.setString(3, persona.getCedula());
            consulta.setString(4, persona.getTelefono());
            consulta.setString(5, persona.getE_mail());
            consulta.executeUpdate();
            
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
    
    public static void eliminar(Connection conexion, String cedula) throws SQLException{
      try{
          
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("DELETE FROM responsable "
                 + "WHERE cedula=?");        
         consulta.setString(3, cedula);
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
    
    public static Responsable buscar(Connection conexion, String cedula) throws SQLException{
        ResultSet rs = null;
        try{         
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("SELECT * FROM responsable "
                    + "WHERE cedula=?");        
            consulta.setString(3, cedula);
            rs = consulta.executeQuery();
        }catch(SQLException ex){
         throw new SQLException(ex);
        }
        return new Responsable(rs.getString("nombres"), rs.getString("apellidos"), rs.getString("cedula"), rs.getString("telefono"), rs.getString("e_mail"));
    }
}
