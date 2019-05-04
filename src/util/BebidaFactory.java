/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import modelo.Bebida;

/**
 *
 * @author kuroy
 */
public class BebidaFactory {
    private static final String BEBIDAS[] = {"VINO", "WHISKY", "CHAMPAÑA", "LITRO DE GASESOSA", "GASEOSA PERSONAL", "LITRO DE JUGO HIT",
                                             "JUGO HIT PERSONAL"};
    private static final Integer COSTOS[] = {0, 0, 0, 0, 0, 0, 0};
    
    public static Bebida crearBebida(String comando, Integer cantidad){
        for(Integer i=0; i<BEBIDAS.length; i++){
            if(BEBIDAS[i].equals(comando)){
                return new Bebida(BEBIDAS[i], COSTOS[i], cantidad);
            }
        }
        return null;
    }
}
