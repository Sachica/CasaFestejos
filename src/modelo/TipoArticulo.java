/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Controlador;
import java.util.ArrayList;
import modeloDAO.ArticuloAdminDAO;

/**
 *
 * @author kuroy
 */
public enum TipoArticulo {
    SILLA,
    MESA,
    MONTAJE,
    BEBIDA,
    PLATILLO;
    
    public static TipoArticulo getTipoString(String tipo){
        switch(tipo){
            case "SILLA" : return SILLA;
            case "MESA" : return MESA;
            case "MONTAJE" : return MONTAJE;
            case "BEBIDA" : return BEBIDA;
            case "PLATILLO" : return PLATILLO;
            default : return null;
        }       
    }
    
    public static TipoArticulo getTipoArticulo(String name){
        ArrayList<Articulo> articulos = new ArrayList<>();
        try{
            articulos = ArticuloAdminDAO.getAll(Controlador.getConnection());
        }catch(java.sql.SQLException e){
        }
        for(Articulo articulo : articulos){
            if(articulo.getNombre().equals(name)){
                return articulo.getTipo();
            }
        }
        
        return null;
    }
}
