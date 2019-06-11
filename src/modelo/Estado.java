/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author kuroy
 */
public enum Estado {
    PAGO,
    NO_PAGO,
    ACTIVO,
    CANCELADO,
    FINALIZADO;
    
    public static Estado getEstado(String estadoString){
        switch(estadoString){
            case "PAGO" : return PAGO;
            case "NO_PAGO" : return NO_PAGO;
            case "ACTIVO" : return ACTIVO;
            case "CANCELADO" : return CANCELADO;
            case "FINALIZADO" : return FINALIZADO;
        }
        
        return null;
    }
}
