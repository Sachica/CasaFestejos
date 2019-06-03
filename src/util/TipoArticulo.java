/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controlador.Controlador;

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
        java.util.ArrayList<modelo.ArticuloAdmin> articulos = new java.util.ArrayList<>();
        try{
            articulos = modeloDAO.ArticuloAdminDAO.getAll(Controlador.getConnection());
        }catch(java.sql.SQLException e){
        }
        for(modelo.ArticuloAdmin articulo : articulos){
            if(articulo.getNombre().equals(name)){
                return articulo.getTipo();
            }
        }
        
        return null;
    }
}
