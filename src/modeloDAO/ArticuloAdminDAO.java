/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelo.Articulo;
import modelo.TipoArticulo;

/**
 *
 * @author kuroy
 */
public class ArticuloAdminDAO {

    public static Boolean guardar(Articulo articulo, Connection connection) throws SQLException, IOException{
        String query = "INSERT INTO articulo_admin (id, tipo, nombre, precio, cantidad, imagen) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, articulo.getId());
        ps.setString(2, articulo.getTipo().toString());
        ps.setString(3, articulo.getNombre());
        ps.setInt(4, articulo.getCosto());
        ps.setInt(5, articulo.getCantidad());
        FileInputStream imagenBinaria = new FileInputStream(new File(articulo.getImagen().getDescription()));
        ps.setBinaryStream(6, imagenBinaria);
        
        return ps.execute();
    }
    
    public static Boolean actualizar(Articulo articulo, Connection connection) throws SQLException, IOException{
        String query = "UPDATE articulo_admin SET tipo=?, nombre=?, cantidad=?, precio=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        
        ps.setString(1, articulo.getTipo().toString());
        ps.setString(2, articulo.getNombre());
        ps.setInt(3, articulo.getCantidad());
        ps.setInt(4, articulo.getCosto());
        ps.setInt(5, articulo.getId());
        
        return ps.executeUpdate()!=0;
    }
    
    public static Articulo buscarPorID(Articulo articulo, Connection connection) throws SQLException{
        String query = "SELECT * FROM articulo_admin WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, articulo.getId());
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Integer id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            String nombre = rs.getString("nombre");
            Integer cantidad = rs.getInt("cantidad");
            Integer precio = rs.getInt("precio");
            Blob blob = rs.getBlob("imagen");
            ImageIcon imagen = ArticuloAdminDAO.leerImagen(blob.getBinaryStream());
            
            return new Articulo(id, nombre, cantidad, precio, imagen, TipoArticulo.getTipoString(tipo));
        }
        
        return null;
    }
    
    public static Articulo buscarPorNombre(Articulo articulo, Connection connection) throws SQLException{
        String query = "SELECT * FROM articulo_admin WHERE nombre=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setString(1, articulo.getNombre());
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Integer id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            String nombre = rs.getString("nombre");
            Integer cantidad = rs.getInt("cantidad");
            Integer precio = rs.getInt("precio");
            Blob blob = rs.getBlob("imagen");
            ImageIcon imagen = ArticuloAdminDAO.leerImagen(blob.getBinaryStream());
            
            return new Articulo(id, nombre, cantidad, precio, imagen, TipoArticulo.getTipoString(tipo));
        }
        
        return null;
    }
    
    public static ArrayList<Articulo> getAll(Connection connection) throws SQLException{
        ArrayList<Articulo> articulos = new ArrayList<>(); 
        String query = "SELECT * FROM articulo_admin";

        ResultSet rs = connection.prepareStatement(query).executeQuery();
        
        while(rs.next()){
            Integer id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            String nombre = rs.getString("nombre");
            Integer cantidad = rs.getInt("cantidad");
            Integer precio = rs.getInt("precio");
            Blob blob = rs.getBlob("imagen");
            ImageIcon imagen = ArticuloAdminDAO.leerImagen(blob.getBinaryStream());
            articulos.add(new Articulo(id, nombre, cantidad, precio, imagen, TipoArticulo.getTipoString(tipo)));
        }
        
        return articulos;
    }
    
    private static ImageIcon leerImagen(InputStream imagenBinaria){
        BufferedImage imgBuff = null;
        try{
            imgBuff = ImageIO.read(imagenBinaria);
        }catch(IOException e){
        }
        return new ImageIcon(imgBuff);
    }
    
    public static Boolean eliminar(Articulo articulo, Connection connection) throws SQLException{
        String query = "DELETE FROM articulo_admin WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setInt(1, articulo.getId());
        
        return ps.execute();
    }
}
