/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
    
    public static TipoArticulo getTipoArticulo(String tipo){
        switch(tipo){
            case "SILLA" : return SILLA;
            case "MESA" : return MESA;
            case "MONTAJE" : return MONTAJE;
            case "BEBIDA" : return BEBIDA;
            case "PLATILLO" : return PLATILLO;
            default : return null;
        }       
    }
}
