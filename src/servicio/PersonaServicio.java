/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.*;

/**
 *
 * @author usuario
 */
public class PersonaServicio {
    
    public void Guardar(Connection conexion, Responsable persona) throws SQLException{
        
        try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO persona (nombre, apellidos, cedula, telefono, e_mail)"
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
    
}