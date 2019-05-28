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
import modelo.Comida;

/**
 *
 * @author kuroy
 */
public class ComidaDAO {
    public static final String PLATILLO = "platillo";
    public static final String BEBIDA = "bebida";
    
    public static Boolean guardar(Comida comida, String table, Connection connection) throws SQLException{
        String query = "INSERT INTO "+table+" (id, nombre, cantidad, costo) VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, comida.getId());
        ps.setString(2, comida.getNombre());
        ps.setInt(3, comida.getCantidad());
        ps.setInt(4, comida.getCosto());
        
        return ps.execute();
    }
    
    public static Boolean actualizar(Comida comida, String table, Connection connection) throws SQLException{
        String query = "UPDATE "+table+" SET cantidad=?, costo=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, comida.getCantidad());
        ps.setInt(2, comida.getCosto());
        ps.setInt(3, comida.getId());
        
        return ps.executeUpdate()!=0;
    }
    
    public static java.util.ArrayList<Comida> buscar(Comida comida, String table, Connection connection) throws SQLException{
        java.util.ArrayList<Comida> comidas = new java.util.ArrayList<>(); 
        String query = "SELECT * FROM "+table+" WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, comida.getId());
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Integer id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            Integer cantidad = rs.getInt("cantidad");
            Integer costo = rs.getInt("costo");
            
            comidas.add(new Comida(id, nombre, costo, cantidad));
        }
        
        return comidas;
    }
    
    public static Boolean eliminar(Comida comida, String table, Connection connection) throws SQLException{
        String query = "DELETE FROM "+table+" WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, comida.getId());
        
        return ps.execute();
    }
}
