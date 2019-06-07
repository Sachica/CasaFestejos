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
import modelo.Articulo;
import modelo.TipoArticulo;

/**
 *
 * @author kuroy
 */
public class ArticuloClienteDAO {

    public static Boolean guardar(Articulo articulo, Connection connection) throws SQLException {
        String query = "INSERT INTO articulo_cliente (id, nombre, tipo, cantidad, costo) VALUES(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articulo.getId());
        ps.setString(2, articulo.getNombre());
        ps.setString(3, articulo.getTipo().toString());
        ps.setInt(4, articulo.getCantidad());
        ps.setInt(5, articulo.getCosto());

        return ps.execute();
    }

    public static Boolean actualizar(Articulo articulo, Connection connection) throws SQLException {
        String query = "UPDATE articulo_cliente SET cantidad=?, costo=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articulo.getCantidad());
        ps.setInt(2, articulo.getCosto());
        ps.setInt(3, articulo.getId());

        return ps.executeUpdate() != 0;
    }

    public static ArrayList<Articulo> buscar(Articulo articulo, Connection connection) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String query = "SELECT * FROM articulo_cliente WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articulo.getId());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String tipo = rs.getString("tipo");
            Integer cantidad = rs.getInt("cantidad");
            Integer costo = rs.getInt("costo");

            articulos.add(new Articulo(id, nombre, cantidad, costo, TipoArticulo.getTipoString(tipo)));
        }

        return articulos;
    }

    public static Boolean eliminarTodo(Articulo articulo, Connection connection) throws SQLException {
        String query = "DELETE FROM articulo_cliente WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articulo.getId());

        return ps.execute();
    }
    
    public static Boolean eliminarPorNombre(Articulo articuloCliente, Connection connection) throws SQLException {
        String query = "DELETE FROM articulo_cliente WHERE nombre=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, articuloCliente.getNombre());

        return ps.execute();
    }

    public static Boolean addAll(ArrayList<Articulo> articulos, Connection connection) throws SQLException {
        ArticuloClienteDAO.eliminarTodo(articulos.remove(articulos.size()-1), connection);
        for (Articulo articulo : articulos) {
            ArticuloClienteDAO.guardar(articulo, connection);
        }
        return true;
    }
    
    public static Boolean updateAll(ArrayList<Articulo> articulos, Connection connection) throws SQLException{
        String query = "UPDATE articulo_cliente SET cantidad=?, precio=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        for(Articulo articulo : articulos){
            ps.setInt(1, articulo.getCantidad());
            ps.setInt(2, articulo.getCosto());
            ps.setInt(3, articulo.getId());
            
            ps.executeUpdate();
        }
        return true;
    }
}
