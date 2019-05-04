/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import modelo.Silla;
/**
 *
 * @author kuroy
 */
public class SillaFactory {
    private static final String TIPO[] = {"ECONOMICA", "NORMAL", "NORMAL DECORADA", "AMUEBLADA", "EJECUTIVA"};
    private static final Integer COSTOS[] = {0, 0, 0, 0, 0};
    
    public static Silla crearSilla(String comando, Integer cantidad){
        for(Integer i=0; i<TIPO.length; i++){
            if(TIPO[i].equals(comando)){
                return new Silla(TIPO[i], COSTOS[i], cantidad);
            }
        }
        return null;
    }
}
