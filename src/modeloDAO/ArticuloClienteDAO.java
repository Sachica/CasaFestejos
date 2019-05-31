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
import modelo.ArticuloCliente;

/**
 *
 * @author kuroy
 */
public class ArticuloClienteDAO {

    public static Boolean guardar(ArticuloCliente articuloCliente, Connection connection) throws SQLException {
        String query = "INSERT INTO articulo_cliente (id, nombre, tipo, cantidad, costo) VALUES(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articuloCliente.getId());
        ps.setString(2, articuloCliente.getNombre());
        ps.setString(3, articuloCliente.getTipoArticulo().toString());
        ps.setInt(4, articuloCliente.getCantidad());
        ps.setInt(5, articuloCliente.getCosto());

        return ps.execute();
    }

    public static Boolean actualizar(ArticuloCliente articuloCliente, Connection connection) throws SQLException {
        String query = "UPDATE articulo_cliente SET cantidad=?, costo=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articuloCliente.getCantidad());
        ps.setInt(2, articuloCliente.getCosto());
        ps.setInt(3, articuloCliente.getId());

        return ps.executeUpdate() != 0;
    }

    public static java.util.ArrayList<ArticuloCliente> buscar(ArticuloCliente articuloCliente, Connection connection) throws SQLException {
        java.util.ArrayList<ArticuloCliente> articulos = new java.util.ArrayList<>();
        String query = "SELECT * FROM articulo_cliente WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articuloCliente.getId());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String tipo = rs.getString("tipo");
            Integer cantidad = rs.getInt("cantidad");
            Integer costo = rs.getInt("costo");

            articulos.add(new ArticuloCliente(id, util.TipoArticulo.getTipoArticulo(tipo), nombre, costo, cantidad));
        }

        return articulos;
    }

    public static Boolean eliminar(ArticuloCliente articuloCliente, Connection connection) throws SQLException {
        String query = "DELETE FROM articulo_cliente WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, articuloCliente.getId());

        return ps.execute();
    }

    public static Boolean addAll(java.util.ArrayList<ArticuloCliente> articulos, Connection connection) throws SQLException {
        for (ArticuloCliente articulo : articulos) {
            ArticuloClienteDAO.guardar(articulo, connection);
        }
        return true;
    }
}
