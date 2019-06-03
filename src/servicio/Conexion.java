/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class Conexion {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/casa_festejos";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Conexion() {
        try{
            this.conectarABase();
        }catch(ClassNotFoundException | SQLException e){
        }
    }
    
    public void conectarABase() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
