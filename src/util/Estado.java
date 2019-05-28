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
    NO_PAGO;
    
    public static Estado getEstado(String estadoString){
        return estadoString.equals("PAGO") ? PAGO : NO_PAGO;
    }
}
