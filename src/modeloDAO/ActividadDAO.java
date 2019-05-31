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
import modelo.Actividad;
import util.MyException;

/**
 *
 * @author kuroy
 */
public class ActividadDAO {
    public static Boolean guardar(Actividad actividad, Connection connection) throws SQLException{
        String query = "INSERT INTO actividad_cliente (id, nombre, descripcion, hora) VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, actividad.getId());
        ps.setString(2, actividad.getNombre());
        ps.setString(3, actividad.getDescripcion());
        ps.setString(4, actividad.getHorario().toString());
        
        return ps.execute();
    }
    
    public static Boolean actualizar(Actividad actividad, Connection connection) throws SQLException{
        String query = "UPDATE actividad_cliente SET nombre=?, descripcion=?, hora=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, actividad.getNombre());
        ps.setString(2, actividad.getDescripcion());
        ps.setString(3, actividad.getHorario().toString());
        ps.setInt(4, actividad.getId());
        
        return ps.executeUpdate()!=0;
    }
    
    public static java.util.ArrayList<modelo.Actividad> buscar(Actividad actividad, Connection connection) throws SQLException{
        java.util.ArrayList<modelo.Actividad>  actividades = new java.util.ArrayList<>(); 
        String query = "SELECT * FROM actividad_cliente WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, actividad.getId());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Integer id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String horario[] = rs.getString("hora").split(":");
            Integer hora = Integer.parseInt(horario[0]);
            Integer minuto = Integer.parseInt(horario[1]);
            actividades.add(new Actividad(id, nombre, descripcion, new modelo.Hora(hora, minuto, 0)));
        }
        
        return actividades;
    }
    
    public static Boolean eliminar(Actividad actividad, Connection connection) throws SQLException{
        String query = "DELETE FROM actividad_cliente WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, actividad.getId());
        
        return ps.execute();
    }
}
