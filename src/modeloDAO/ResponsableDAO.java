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
import modelo.Responsable;

/**
 *
 * @author usuario
 */
public class ResponsableDAO {

    public static Boolean guardar(Responsable persona, Connection conexion) throws SQLException {
        String query = "INSERT INTO responsable (nombre, apellido, cedula, telefono, email) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement consulta = conexion.prepareStatement(query);

        consulta.setString(1, persona.getNombre());
        consulta.setString(2, persona.getApellidos());
        consulta.setInt(3, persona.getCedula());
        consulta.setString(4, persona.getTelefono());
        consulta.setString(5, persona.getE_mail());

        return consulta.execute();
    }

    public static Boolean actualizar(Responsable persona, Integer cedula, Connection conexion) throws SQLException {
        String query = "UPDATE responsable SET nombre=?, apellido=?, cedula=?, telefono=?, email=? WHERE cedula=?";
        PreparedStatement consulta = conexion.prepareStatement(query);

        consulta.setString(1, persona.getNombre());
        consulta.setString(2, persona.getApellidos());
        consulta.setInt(3, persona.getCedula());
        consulta.setString(4, persona.getTelefono());
        consulta.setString(5, persona.getE_mail());
        consulta.setInt(6, cedula);

        return consulta.executeUpdate()!=0;
    }

    public static Boolean eliminar(Responsable persona, Connection conexion) throws SQLException {
        String query = "DELETE FROM responsable WHERE cedula=?";
        PreparedStatement consulta = conexion.prepareStatement(query);
        consulta.setInt(1, persona.getCedula());
        
        return consulta.execute();
    }

    public static Responsable buscar(Responsable responsable, Connection conexion) throws SQLException {
        String query = "SELECT * FROM responsable WHERE cedula=?";
        PreparedStatement consulta = conexion.prepareStatement(query);
        consulta.setInt(1, responsable.getCedula());
        ResultSet rs = consulta.executeQuery();
        if(rs.next()){
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            Integer cedula = rs.getInt("cedula");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            
            return new Responsable(nombre, apellido, cedula, telefono, email);
        }
        return null;
    }
}
