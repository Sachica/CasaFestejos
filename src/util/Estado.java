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
public enum Estado {
    PAGO,
    NO_PAGO,
    CANCELADO,
    ACTIVO,
    FINALIZADO;
    
    public static Estado getEstado(String estadoString){
        switch(estadoString){
            case "PAGO" : return PAGO;
            case "NO_PAGO" : return NO_PAGO;
            case "CANCELADO" : return CANCELADO;
            case "ACTIVO" : return ACTIVO;
            case "FINALIZADO" : return FINALIZADO;
        }
        
        return null;
    }
}
