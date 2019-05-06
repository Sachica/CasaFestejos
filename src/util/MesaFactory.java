/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import modelo.Mesa;
/**
 *
 * @author kuroy
 */
public class MesaFactory {
    private static final String TIPO[] = {"MESA IMPERIAL", "MESA REDONDA", "MESA EJECUTIVA", "MESA RUSA"};
    private static final Integer COSTO[] = {0, 0, 0, 0};
    
    public static Mesa crearMesas(String comando, Integer cantidad){
        for(Integer i=0; i<TIPO.length; i++){
            if(TIPO[i].equals(comando)){
                return new Mesa(TIPO[i], COSTO[i], cantidad);
            }
        }
        return null;
    }
}
