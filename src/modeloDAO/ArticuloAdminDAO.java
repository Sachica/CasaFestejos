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
import modelo.Articulo;
import util.TipoArticulo;

/**
 *
 * @author kuroy
 */
public class ArticuloAdminDAO {

    public static Boolean guardar(Articulo articulo, Connection connection) throws SQLException{
        String query = "INSERT INTO articulo (id, tipo, nombre, precio) VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, articulo.getId());
        ps.setString(2, articulo.getTipo().toString());
        ps.setString(3, articulo.getNombre());
        ps.setInt(4, articulo.getPrecio());
        
        return ps.execute();
    }
    
    public static Boolean actualizar(Articulo articulo, Connection connection) throws SQLException{
        String query = "UPDATE articulo SET tipo=?, nombre=?, precio=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setString(1, articulo.getTipo().toString());
        ps.setString(2, articulo.getNombre());
        ps.setInt(3, articulo.getPrecio());
        ps.setInt(4, articulo.getId());
        
        return ps.executeUpdate()!=0;
    }
    
    public static java.util.ArrayList<Articulo> buscarPorID(Articulo articulo, Connection connection) throws SQLException{
        java.util.ArrayList<Articulo> articulos = new java.util.ArrayList<>(); 
        String query = "SELECT * FROM articulo WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, articulo.getId());
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Integer id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            String nombre = rs.getString("nombre");
            Integer precio = rs.getInt("precio");
            
            articulos.add(new Articulo(id, TipoArticulo.getTipoArticulo(tipo), nombre, precio));
        }
        
        return articulos;
    }
    
    public static java.util.ArrayList<Articulo> buscarPorTipo(Articulo articulo, Connection connection) throws SQLException{
        java.util.ArrayList<Articulo> articulos = new java.util.ArrayList<>(); 
        String query = "SELECT * FROM articulo WHERE tipo=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setString(1, articulo.getTipo().toString());
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Integer id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            String nombre = rs.getString("nombre");
            Integer precio = rs.getInt("precio");
            
            articulos.add(new Articulo(id, TipoArticulo.getTipoArticulo(tipo), nombre, precio));
        }
        
        return articulos;
    }
    
    public static Boolean eliminar(Articulo articulo, String table, Connection connection) throws SQLException{
        String query = "DELETE FROM articulo WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, articulo.getId());
        
        return ps.execute();
    }
}
