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
import java.util.ArrayList;
import modelo.Evento;

/**
 *
 * @author kuroy
 */
public class EventoDAO {

    public static Boolean guardar(Connection conexion, Evento evento) throws SQLException {
        String query = "INSERT INTO evento (id, fecha_celebracion, hora, direccion, monto_abonado, monto_total, estado, estado_pago) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement consulta = conexion.prepareStatement(query);

        consulta.setInt(1, evento.getID());
        consulta.setString(2, evento.getFecha_celebracion().toString());
        consulta.setString(3, evento.getFecha_celebracion().getHora().toString());
        consulta.setString(4, evento.getDireccion_evento());
        consulta.setInt(5, evento.getMonto_abonado());
        consulta.setInt(6, evento.getMonto_total());
        consulta.setString(7, evento.getEstado().toString());
        consulta.setString(8, evento.getEstado_pago().toString());

        return consulta.execute();
    }

    public static Boolean actualizar(Connection conexion, Evento evento) throws SQLException {
        String query = "UPDATE evento SET fecha_celebracion=?, hora=?, direccion=?, monto_abonado=?, monto_total=?, estado=?, estado_pago=? WHERE id=?";
        PreparedStatement consulta = conexion.prepareStatement(query);

        consulta.setString(1, evento.getFecha_celebracion().toString());
        consulta.setString(2, evento.getFecha_celebracion().getHora().toString());
        consulta.setString(3, evento.getDireccion_evento());
        consulta.setInt(4, evento.getMonto_abonado());
        consulta.setInt(5, evento.getMonto_total());
        consulta.setString(6, evento.getEstado().toString());
        consulta.setString(7, evento.getEstado_pago().toString());
        consulta.setInt(8, evento.getID());

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
                Integer id = rs.getInt("id");
                modelo.Responsable responsable = new modelo.Responsable();
                responsable.setCedula(id);
                responsable = ResponsableDAO.buscar(responsable, conexion);
                
                String fecha[] = rs.getString("fecha_celebracion").split("/");
                Integer dia = Integer.parseInt(fecha[0]);
                Integer mes = Integer.parseInt(fecha[1]);
                Integer año = Integer.parseInt(fecha[2]);
                String horario[] = rs.getString("hora").split(":");
                Integer hora = Integer.parseInt(horario[0]);
                Integer minuto = Integer.parseInt(horario[1]);
                modelo.Fecha fecha_celebracion = new modelo.Fecha(año, mes, dia, new modelo.Hora(hora, minuto, 0));
                
                String direccion = rs.getString("direccion");
                Integer monto_abonado = rs.getInt("monto_abonado");
                Integer monto_total = rs.getInt("monto_total");
                
                String estado = rs.getString("estado");
                
                return new Evento(fecha_celebracion, direccion, responsable, monto_abonado, monto_total, modelo.Estado.getEstado(estado));
            }
        return null;
    }
    
    public static ArrayList<Evento> getAll(Connection conexion) throws SQLException {
            ArrayList<Evento> eventos = new ArrayList<>();
            String query = "SELECT * FROM evento";
            
            ResultSet rs = conexion.prepareStatement(query).executeQuery();
            if(rs.next()){
                Integer id = rs.getInt("id");
                modelo.Responsable responsable = new modelo.Responsable();
                responsable.setCedula(id);
                responsable = ResponsableDAO.buscar(responsable, conexion);
                
                String fecha[] = rs.getString("fecha_celebracion").split("/");
                Integer dia = Integer.parseInt(fecha[0]);
                Integer mes = Integer.parseInt(fecha[1]);
                Integer año = Integer.parseInt(fecha[2]);
                String horario[] = rs.getString("hora").split(":");
                Integer hora = Integer.parseInt(horario[0]);
                Integer minuto = Integer.parseInt(horario[1]);
                modelo.Fecha fecha_celebracion = new modelo.Fecha(año, mes, dia, new modelo.Hora(hora, minuto, 0));
                
                String direccion = rs.getString("direccion");
                Integer monto_abonado = rs.getInt("monto_abonado");
                Integer monto_total = rs.getInt("monto_total");
                
                String estado = rs.getString("estado");
                
                eventos.add(new Evento(fecha_celebracion, direccion, responsable, monto_abonado, monto_total, modelo.Estado.getEstado(estado)));
            }
        return eventos;
    }
}
