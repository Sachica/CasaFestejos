/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import modelo.Montaje;
/**
 *
 * @author kuroy
 */
public class MontajeFactory {
    private static final String TIPO[] = {"MONTAJE AUDITORIO", "MONTAJE TIPO ESCUELA", "MONTAJE EN FORMA DE U", 
                                          "MONTAJE TIPO BANQUETE", "MONTAJE TIPO COCTEL"};
    private static final Integer COSTO[] = {0, 0, 0, 0, 0};
    
    public static Montaje crearMontaje(String tipoMontaje){
        for(Integer i=0; i<TIPO.length; i++){
            if(TIPO[i].equals(tipoMontaje)){
                return new Montaje(TIPO[i], COSTO[i]);
            }
        }
        return null;
    }
}
